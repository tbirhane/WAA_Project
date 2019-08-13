package edu.mum.cs.onlinehabeshaclothing.service;

import edu.mum.cs.onlinehabeshaclothing.controller.dto.UserDto;
import edu.mum.cs.onlinehabeshaclothing.model.User;
import edu.mum.cs.onlinehabeshaclothing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User save(UserDto userDto) {
        User user = new User();
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setAddress(userDto.getAddress());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole((("ROLE_"+userDto.getRole().toUpperCase())));
        return userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), authorities);
        //mapRolesToAuthorities(user.getRoles()));
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUser(Long uid) {
        return userRepository.findById(uid).get();
    }

    public void deleteUser(Long uid){

        userRepository.deleteById(uid);
    }

}
