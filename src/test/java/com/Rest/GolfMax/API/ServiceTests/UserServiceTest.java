package com.Rest.GolfMax.API.ServiceTests;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.Implementations.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getAllUsers_should_return_list_size_3() {
        User user1 = new User(1L, "firstname", "lastname", "username", "password", "email@email.com");
        User user2 = new User(2L, "firstname", "lastname", "username", "password", "email@email.com");
        User user3 = new User(3L, "firstname", "lastname", "username", "password", "email@email.com");

        List<User> userRequest = new ArrayList<>(Arrays.asList(user1, user2, user3));

        when(userService.getAllUsers()).thenReturn(userRequest);

        List<User> userResponse = userService.getAllUsers();

        assertThat(userRequest.size()).isSameAs(userResponse.size());
        assertThat(userResponse.size()).isEqualTo(3);
    }

    @Test
    public void createUser_should_return_user() throws NoSuchAlgorithmException, InvalidKeySpecException,
            MessagingException, UnsupportedEncodingException {
        User userRequest = new User();
        userRequest.setUsername("Olivier");
        userRequest.setPassword("password");
        userRequest.setEmail("email@email.com");
        HttpServletRequest request = null;

        when(userRepository.save(userRequest)).thenReturn(new User());

        User createdUser = userService.registerUser(userRequest, getSiteURL(request));

        assertThat(createdUser.getUsername()).isSameAs(userRequest.getUsername());
    }

    @Test
    public void getUserById_should_return_user() {
        User userRequest = new User(1L, "firstname", "lastname", "username", "password", "email@email.com");

        when(userRepository.findById(userRequest.getId())).thenReturn(Optional.of(userRequest));

        User userResponse = userService.getUserById(userRequest.getId());

        assertThat(userResponse.getId()).isSameAs(userRequest.getId());

    }

    @Test
    public void deleteUser_whenUserDeleted_thenNothing() {
        Long userId = 1L;

        willDoNothing().given(userRepository).deleteById(userId);

        userService.deleteUserById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
