package com.jammy.web;

import com.jammy.business.facade.UserFacade;
import com.jammy.domain.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class UserResource {
    private final UserDetailsService userDetailsService;
    private final UserFacade userFacade;

    PasswordEncoder passwordEncoder;

    JwtEncoder encoder;

    public UserResource(UserDetailsService userDetailsService, UserFacade userFacade, PasswordEncoder passwordEncoder, JwtEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.userFacade = userFacade;
        this.passwordEncoder = passwordEncoder;
        this.encoder = encoder;
    }

    @PostMapping("add")
    public User addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userFacade.save(user);
    }

    @PostMapping("authenticate")
    public String authenticate(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}

