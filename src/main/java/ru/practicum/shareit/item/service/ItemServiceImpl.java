package ru.practicum.shareit.item.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.repository.ItemRepository;

import java.util.List;

@Service
@Data
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public ItemDto addItem(ItemDto itemDto, long ownerId) {
        var item = itemRepository.addItem(itemDto, ownerId);
        log.info("Item {} was added to the repository", item);
        return item;
    }

    @Override
    public ItemDto updateItem(long ownerId, Long itemId, ItemDto itemDto) {
        var item = itemRepository.updateItem(ownerId, itemId, itemDto);
        log.info("Item {} was updated by the repository", item);
        return item;
    }

    @Override
    public ItemDto getItem(long ownerId, Long itemId) {
        var item = itemRepository.getItem(ownerId, itemId);
        log.info("Item {} was received from the repository", item);
        return item;
    }

    @Override
    public List<ItemDto> getItemsByOwner(long ownerId) {
        var items = itemRepository.getItemsByOwner(ownerId);
        log.info("Items {} were received from the repository", items);
        return items;
    }

    @Override
    public List<ItemDto> searchForItem(String text) {
        var items = itemRepository.searchForItem(text);
        log.info("Items {} were found by the repository", items);
        return items;
    }
}
