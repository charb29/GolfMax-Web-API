package com.Rest.GolfMax.API.ServiceTests;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.Implementations.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        User user1 = new User(1, "Olivier", "password", "email@email.com");
        User user2 = new User(2, "Anna", "password", "email@email.com");
        User user3 = new User(3, "John", "password", "email@email.com");

        List<User> userRequest = new ArrayList<>(Arrays.asList(user1, user2, user3));

        when(userService.getAllUsers()).thenReturn(userRequest);

        List<User> userResponse = userService.getAllUsers();

        assertThat(userRequest.size()).isSameAs(userResponse.size());
    }

    @Test
    public void createUser_should_return_user() {
        User userRequest = new User();
        userRequest.setUsername("Olivier");
        userRequest.setPassword("password");
        userRequest.setEmail("email@email.com");

        when(userRepository.save(userRequest)).thenReturn(new User());

        User createdUser = userService.createUser(userRequest);

        assertThat(createdUser.getUsername()).isSameAs(userRequest.getUsername());
    }

    @Test
    public void getUserById_should_return_user() {
        User userRequest = new User(1, "Olivier", "password", "email@gmail.com");

        when(userRepository.findById(userRequest.getId())).thenReturn(Optional.of(userRequest));

        User savedUser = userService.getUserById(userRequest.getId()).get();

        assertThat(savedUser.getId()).isSameAs(userRequest.getId());

    }

    @Test
    public void deleteUser_whenUserDeleted_thenNothing() {
        long userId = 1;

        willDoNothing().given(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
