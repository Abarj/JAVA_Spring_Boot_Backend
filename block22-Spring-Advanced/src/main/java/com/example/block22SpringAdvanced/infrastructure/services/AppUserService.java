package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.domain.entities.documents.AppUserDocument;
import com.example.block22SpringAdvanced.domain.repositories.mongo.AppUserRepository;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.ModifyUserService;
import com.example.block22SpringAdvanced.util.exceptions.UsernameNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AppUserService implements ModifyUserService, UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public Map<String, Boolean> enabled(String username) {
        var user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));
        user.setEnabled(!user.isEnabled());
        var userSaved = appUserRepository.save(user);
        log.info("{} is {}", userSaved.getUsername(), userSaved.isEnabled());
        return Collections.singletonMap(userSaved.getUsername(), userSaved.isEnabled());
    }

    @Override
    public Map<String, Set<String>> addRole(String username, String role) {
        var user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));
        user.getRole().getGranted_authorities().add(role);
        var userSaved = appUserRepository.save(user);
        var authorities = userSaved.getRole().getGranted_authorities();
        log.info("{} role has been added to the user {}", userSaved.getRole().getGranted_authorities().toString(), userSaved.getUsername());
        return Collections.singletonMap(userSaved.getUsername(), authorities);
    }

    @Override
    public Map<String, Set<String>> removeRole(String username, String role) {
        var user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));
        user.getRole().getGranted_authorities().remove(role);
        var userSaved = appUserRepository.save(user);
        var authorities = userSaved.getRole().getGranted_authorities();
        log.info("{} role has been removed to the user {}", userSaved.getRole().getGranted_authorities().toString(), userSaved.getUsername());
        return Collections.singletonMap(userSaved.getUsername(), authorities);
    }

    private static final String COLLECTION_NAME = "app_users";


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));

        return mapUserToUserDetails(user);

    }

    private static UserDetails mapUserToUserDetails(AppUserDocument user) {
        Set<GrantedAuthority> authorities = user.getRole()
                .getGranted_authorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}
