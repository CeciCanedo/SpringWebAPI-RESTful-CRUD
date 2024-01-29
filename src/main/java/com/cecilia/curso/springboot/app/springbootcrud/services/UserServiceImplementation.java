package com.cecilia.curso.springboot.app.springbootcrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cecilia.curso.springboot.app.springbootcrud.entities.Role;
import com.cecilia.curso.springboot.app.springbootcrud.entities.User;
import com.cecilia.curso.springboot.app.springbootcrud.repositories.RoleRepository;
import com.cecilia.curso.springboot.app.springbootcrud.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return(List<User>)repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        Optional<Role> optionalRoleUser= roleRepository.findByName("role_user");
        final List<Role> roles= new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin= roleRepository.findByName("role_admin");
            optionalRoleAdmin.ifPresent(roles::add);        
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
    
    
}
