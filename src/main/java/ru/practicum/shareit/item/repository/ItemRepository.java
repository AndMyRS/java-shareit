package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.OwnerNotFoundException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final UserRepository userRepository;

    private long itemId = 0;

    private Map<Long, Item> items = new HashMap<>();

    public ItemDto addItem(ItemDto itemDto, long ownerId) {
        validateOwnerId(ownerId);
        Item item = ItemMapper.toItem(itemDto);
        item.setOwner(ownerId);
        item.setId(++itemId);

        items.put(item.getId(), item);

        return ItemMapper.toItemDto(item);
    }

    public void validateOwnerId(long ownerId) {
        var user = userRepository.getAll().stream()
                .filter(userDto -> userDto.getId() == ownerId)
                .findFirst();
        if (user.isEmpty()) {
            throw new OwnerNotFoundException("User not found");
        }
    }

    public ItemDto updateItem(long ownerId, Long itemId, ItemDto itemDto) {
            Item item = null;
            if (items.containsKey(itemId)) {
                item = items.get(itemId);
                    if (ownerId != item.getOwner()) {
                        throw new OwnerNotFoundException("Item can be updated by owner only");

            } else {
                        if (itemDto.getName() != null) {
                            item.setName(itemDto.getName());
                        }
                        if (itemDto.getDescription() != null) {
                            item.setDescription(itemDto.getDescription());
                        }
                        if (itemDto.getAvailable() != null) {
                            item.setAvailable(itemDto.getAvailable());
                        }
                    }
        }
            return ItemMapper.toItemDto(item);
    }

    public ItemDto getItem(long ownerId, Long itemId) {
        if (items.containsKey(itemId)) {
            Item item = items.get(itemId);
            return ItemMapper.toItemDto(item);
        } else throw new IllegalArgumentException();
    }

    public List<ItemDto> getItemsByOwner(long ownerId) {
        return items.values().stream()
                .filter(item -> item.getOwner() == ownerId)
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> searchForItem(String text) {
        if (text.isEmpty()) {
            return Collections.emptyList();
        } else return items.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase())
                        || item.getDescription().toLowerCase().contains(text.toLowerCase()))
                .filter(Item::getAvailable)
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
