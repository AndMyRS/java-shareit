package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */

@Data
@Builder
public class Item {

    private long id;

    @NotEmpty
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Boolean available;

    @NotNull
    private long owner;

    private ItemRequest request;
}
