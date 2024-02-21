package ru.practicum.shareit.user.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */

@Data
@Builder
public class User {

    private long id;

    private String name;

    @Email
    @NotNull
    private String email;
}
