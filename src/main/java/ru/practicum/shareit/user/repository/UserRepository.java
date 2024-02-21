package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private long userId = 0;
    private Map<Long, User> users = new HashMap<>();

    public List<UserDto> getAll() {
        return users.values().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        checkDuplicatedEmail(user.getEmail(), user.getId());
        user.setId(++userId);
        users.put(user.getId(), user);
        return UserMapper.toUserDto(user);
    }

    public UserDto getUserById(long userId) {
        return users.values().stream()
                .filter(user -> userId == user.getId())
                .map(UserMapper::toUserDto)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public UserDto updateUser(UserDto userDto, long userId) {
        User stored = users.get(userId);

        if (userDto.getName() != null) {
            stored.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            checkDuplicatedEmail(userDto.getEmail(), stored.getId());
            stored.setEmail(userDto.getEmail());
        }
        return UserMapper.toUserDto(stored);
    }

    public void deleteUserById(long userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
        }
    }

    public void checkDuplicatedEmail(String email, long userId) {
        Optional<User> user = users.values().stream()
                .filter(p -> email.equals(p.getEmail()))
                .findFirst();
        if (user.isPresent()) {
            if (user.get().getId() != userId) {
                throw new RuntimeException();
            }
        }
    }
}

