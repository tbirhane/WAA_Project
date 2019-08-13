package edu.mum.cs.onlinehabeshaclothing.service;

import edu.mum.cs.onlinehabeshaclothing.controller.dto.UserDto;
import edu.mum.cs.onlinehabeshaclothing.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserDto user);
    User findByEmail(String email);
    User getUser(Long uid);
    void deleteUser(Long uid);
}
