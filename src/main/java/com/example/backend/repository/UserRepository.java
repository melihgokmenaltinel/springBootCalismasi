package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsUserByUsername(String username);
	//hangi entity'ye ait repository oluşturduysak class name ve id türünü yazarız
//JpaRepository yetmediği durumda sorgularımızı buraya yazarız
}
//model ile repository katmanı etkileşim halinde olmalı
//api ile servis katmanı etkileşim halinde olmalı