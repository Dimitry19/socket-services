package com.example.user.service;

import com.example.user.dao.UserRepository;
import com.example.user.ent.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserService  implements UserDetailsService {

	@Resource(name = "userDao")
	UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
			this.userRepository = userRepository;
		}

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

			Objects.requireNonNull(username);
			User user = userRepository.findUserWithName(username)
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));

			return user;
		}
}
