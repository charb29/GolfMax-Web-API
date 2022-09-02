package com.Rest.GolfMax.API.DtoConversionTests;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserToUserDtoTests {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertUserEntityToUserDto_thenCorrect() {
        User user = new User(1L, "Olivier", "password", "email");
        UserDto userDto = modelMapper.map(user, UserDto.class);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    public void whenConvertUserEntityListToUserDtoList_thenCorrect() {
        User user1 = new User(1L, "Olivier", "password", "email");
        User user2 = new User(2L, "John", "password", "email");
        User user3 = new User(3L, "Bill", "password", "email");
        User user4 = new User(4L, "Bob", "password", "email");
        List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));

        List<UserDto> userDtoList = Arrays.asList(modelMapper.map(userList, UserDto[].class));

        assertEquals(userList.size(), userDtoList.size());
    }
}
