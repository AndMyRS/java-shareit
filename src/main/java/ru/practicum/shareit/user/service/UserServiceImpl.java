package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        var users = userRepository.getAll();
        log.info("Users {} were received from the repository", users);
        return users;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        var user = userRepository.addUser(userDto);
        log.info("User {} was added to the repository", user);
        return user;
    }

    @Override
    public UserDto getUserById(long userId) {
        var user = userRepository.getUserById(userId);
        log.info("User {} was received from the repository", user);
        return user;
    }

    @Override
    public UserDto updateUser(UserDto userDto, long userId) {
        var user = userRepository.updateUser(userDto, userId);
        log.info("User {} was updated by the repository", user);
        return user;
    }

    @Override
    public void deleteUserById(long userId) {
        log.info("User with id {} is going to be deleted from the repository", userId);
        userRepository.deleteUserById(userId);
    }
}
