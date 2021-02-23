package com.leverx.app.service.impl;

import com.leverx.app.entity.pet.Pet;
import com.leverx.app.entity.user.User;
import com.leverx.app.repository.PetRepository;
import com.leverx.app.repository.UserRepository;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service("customUserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> find(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    @Override
    public void delete(long id) {
        List<Pet> pets = petRepository.findAllByUserId(id);
        pets.forEach(pet -> pet.setUser(null));
        petRepository.saveAll(pets);
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        if (!findByName(name).isPresent()) {
            throw new UsernameNotFoundException("Invalid username.");
        }
        User user = findByName(name).get();
        return new org.springframework.security.core.userdetails.User
                (user.getName(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
