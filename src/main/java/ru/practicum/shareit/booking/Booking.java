package ru.practicum.shareit.booking;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
public class Booking {

    private long id;

    private LocalDateTime start;

    private LocalDateTime end;

    private Item item; // или id предмета

    private User user; // или id пользователя

    private BookingStatus status;

    private enum BookingStatus {
        WAITING, APPROVED, REJECTED, CANCELED
    }
}
