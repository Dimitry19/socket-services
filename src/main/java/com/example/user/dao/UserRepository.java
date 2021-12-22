package com.example.user.dao;

import com.example.user.ent.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("userDao")
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(" select u from User u  where u.username = ?1")
	Optional<com.example.user.ent.User> findUserWithName(String username);
}
