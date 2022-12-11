package com.example.backend.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.UserCreateDTO;
import com.example.backend.dto.UserUpdateDTO;
import com.example.backend.dto.UserViewDTO;
import com.example.backend.service.UserService;
import com.example.backend.shared.GenericResponse;

@RestController
@RequestMapping("api")
public class UserAPI {

	private final UserService userService;

	public UserAPI(UserService userService) { // autowired kullanabilirdik bunun yerine contructor injection yaptık
		this.userService = userService;
	}

	@GetMapping("v1/user/{id}")
	public ResponseEntity<UserViewDTO> getUserById(@PathVariable Long id) {
		final UserViewDTO user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("v1/user") // getirilen veriler UserViewDTO'ya dönüştürüldü yani USerViewDTO'da izin
							// verilen kısmı gelecek
	public ResponseEntity<List<UserViewDTO>> getUsers() { // çok sayıda kullanıcıyı getirmek için
		final List<UserViewDTO> users = userService.getUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping("v1/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
		userService.createUSer(userCreateDTO);
		return ResponseEntity.ok(new GenericResponse("User Created."));
	}

	@PutMapping("v1/user/{id}")
	public ResponseEntity<UserViewDTO> updateUser(@PathVariable("id") Long id,
			@RequestBody UserUpdateDTO userUpdateDTO) {
		final UserViewDTO user = userService.updateUsers(id, userUpdateDTO);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("v1/user/{id}") // yetkilendirme yapabiliriz yalnızca admin user silebilsin vb.
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		final UserViewDTO user = userService.getUserById(id);
		userService.deleteUSer(id);
		return ResponseEntity.ok(new GenericResponse("User Deleted.")); // mesajları farklı yerden almasını sağlamak
		// daha iyi bir seçenek olur
	}

	// Performanslı bir yöntem
	@GetMapping("v1/user/slice") // ilk veriyi böyle paging yaparız
									// http://localhost:8088/api/v1/user/slice?page=0&size=1
	public ResponseEntity<List<UserViewDTO>> slice(Pageable pageable) {
		final List<UserViewDTO> users = userService.slice(pageable);
		return ResponseEntity.ok(users);
	}

}
