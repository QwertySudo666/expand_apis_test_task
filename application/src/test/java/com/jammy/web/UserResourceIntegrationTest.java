package com.jammy.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jammy.business.facade.UserFacade;
import com.jammy.domain.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(UserResource.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(UserResource.class)
@AutoConfigureWebMvc
public class UserResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;// = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserFacade userFacade;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtEncoder encoder;

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
//        Mockito.when(userFacade.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAuthenticate() throws Exception {
//        // Mock an Authentication object without authentication details
//        Authentication authentication = Mockito.mock(Authentication.class);
//
//        mockMvc.perform(post("/api/user/authenticate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(authentication)))
//                .andExpect(status().isOk());
    }
}