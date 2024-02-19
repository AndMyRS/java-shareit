package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto addUser(UserDto userDto);

    UserDto getUserById(long userId);

    UserDto updateUser(UserDto userDto, long userId);

    void deleteUserById(long userId);
}
