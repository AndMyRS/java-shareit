package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@Valid @RequestHeader("X-Sharer-User-Id") long ownerId,
            @Valid @RequestBody ItemDto itemDto) {
        log.info("Got a request to add item {}", itemDto);
        return itemService.addItem(itemDto, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@Valid @RequestHeader("X-Sharer-User-Id") long ownerId,
                              @PathVariable Long itemId,
                              @RequestBody ItemDto itemDto) {
        log.info("Got a request to update item {}", itemDto);
        return itemService.updateItem(ownerId, itemId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@Valid @RequestHeader("X-Sharer-User-Id") long ownerId,
                           @PathVariable Long itemId) {
        log.info("Got a request for item with id {}", itemId);
        return itemService.getItem(ownerId, itemId);
    }

    @GetMapping
    public List<ItemDto> getItemsByOwner(@RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Got a request for all items of user with id {}", ownerId);
        return itemService.getItemsByOwner(ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchForItem(@RequestParam String text) {
        log.info("Got a request to search items by name or description containing {}", text);
        return itemService.searchForItem(text);
    }

}
