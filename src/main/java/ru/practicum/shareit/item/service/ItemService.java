package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto addItem(ItemDto itemDto, long ownerId);

    ItemDto updateItem(long ownerId, Long itemId, ItemDto itemDto);

    ItemDto getItem(long ownerId, Long itemId);

    List<ItemDto> getItemsByOwner(long ownerId);

    List<ItemDto> searchForItem(String text);
}
