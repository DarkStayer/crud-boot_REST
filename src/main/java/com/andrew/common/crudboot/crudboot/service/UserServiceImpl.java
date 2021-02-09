package com.andrew.common.crudboot.crudboot.service;

import com.andrew.common.crudboot.crudboot.repository.UserRepository;
import com.andrew.common.crudboot.crudboot.model.Role;
import com.andrew.common.crudboot.crudboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addUser(User user) {
        if (user.getId()==0){
            Set <Role> roleSet = new HashSet<>();
            roleSet.add(Role.USER);
            user.setRoles(roleSet);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        userRepository.save(user);
    }

    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional <User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(new User());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
