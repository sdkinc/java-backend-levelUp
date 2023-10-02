package de.ait.shop.services;

import de.ait.shop.models.Event;
import java.time.LocalDate;
import java.util.List;

public interface EventService {

  Event addEvent(String title, String city, LocalDate date);

  List<Event> getAllEvents();
  List<Event> getAllEventsByDate(LocalDate date);

  void updateEvent(Long id, String title, String city, LocalDate date);

  void removeEvent(Long id);

}
