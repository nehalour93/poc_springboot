package com.example.POC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.POC.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(int id);

	List<User> findAll();

	User save(User persisted);

	User getById(int id);

}
