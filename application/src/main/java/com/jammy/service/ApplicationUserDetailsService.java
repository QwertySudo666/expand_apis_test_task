package com.jammy.service;

import com.jammy.business.facade.UserFacade;
import com.jammy.domain.models.User;
import com.jammy.dto.ApplicationUser;
import com.jammy.dto.ApplicationRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserFacade userFacade;

    public ApplicationUserDetailsService(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFacade.findByUsername(username);
        return new ApplicationUser(user.getUsername(), user.getPassword(), new HashSet<ApplicationRole>());
    }
}
