package com.example.backend.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dto.UserCreateDTO;
import com.example.backend.dto.UserUpdateDTO;
import com.example.backend.dto.UserViewDTO;
import com.example.backend.exception.NotFoundException;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserViewDTO getUserById(Long id) {
		final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));
		return UserViewDTO.of(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserViewDTO> getUsers() { // çok sayıda kullanıcıyı getirmek için
		return userRepository.findAll().stream().map(UserViewDTO::of).collect(Collectors.toList());
		// JPArepository içerisinde findAll metodu tanımlı
	}

	@Override
	@Transactional
	public UserViewDTO createUSer(UserCreateDTO userCreateDTO) {
		// save methodu jparepository içerisinde tanımlı
		final User user = userRepository
				.save(new User(userCreateDTO.getFirstName(), userCreateDTO.getLastName(), userCreateDTO.getUserName()));
		return UserViewDTO.of(user);
	}

	@Override
	@Transactional
	public UserViewDTO updateUsers(Long id, UserUpdateDTO userUpdateDTO) {
		// güncellenmeye çalışılan id var mı kontrol edeceğiz öncelikle
		final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));
		user.setFirstName(userUpdateDTO.getFirstName());
		user.setLastName(userUpdateDTO.getLastName());

		final User updatedUser = userRepository.save(user); // builder kullanımı

		return UserViewDTO.of(updatedUser);
	}

	@Override
	@Transactional
	public void deleteUSer(Long id) {
		
		System.out.println("---------------");
		System.out.println(userRepository.findById(id));
		
		if (userRepository.findById(id).orElseThrow() == null) {
			
			System.out.println("User Can't Found");
		} 
		else {System.out.println("User  Found ");
			final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(null));
			userRepository.deleteById(user.getId());
		}

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserViewDTO> slice(Pageable pageable) {
		return userRepository.findAll(pageable).stream().map(UserViewDTO::of).collect(Collectors.toList());
	}

}
