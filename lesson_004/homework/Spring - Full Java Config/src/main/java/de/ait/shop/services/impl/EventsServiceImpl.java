package de.ait.shop.services.impl;

import de.ait.shop.models.Event;
import de.ait.shop.repositories.EventsRepository;
import de.ait.shop.services.EventService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventService {

  private final EventsRepository eventsRepositoryJdbcTemplateImpl;
  @Override
  public Event addEvent(String title, String city, LocalDate date) {
    Event existedEvent = eventsRepositoryJdbcTemplateImpl.findOneByTitle(title); // search event by date

    if (existedEvent != null) { // if event exist
      throw new IllegalArgumentException("Event to this date already exist");
    }

    Event event = Event.builder()
        .title(title)
        .city(city)
        .date(date)
        .build();

    eventsRepositoryJdbcTemplateImpl.save(event); // save event to db

    return event; // return event as result
  }

  @Override
  public List<Event> getAllEvents() {
    return eventsRepositoryJdbcTemplateImpl.findAll();
  }

  @Override
  public List<Event> getAllEventsByDate(LocalDate date) {
    List<Event> events = eventsRepositoryJdbcTemplateImpl.findAll();
    List<Event> eventsByDate = new ArrayList<>();
    for (Event event: events) {
      if(event.getDate().equals(date))
        eventsByDate.add(event);
    }
    return eventsByDate;
  }

  @Override
  public void updateEvent(Long id, String title, String city, LocalDate date) {
    Event eventToUpdate = eventsRepositoryJdbcTemplateImpl.findById(id);
    if(eventToUpdate!=null){
      eventToUpdate.setCity(city);
      eventToUpdate.setTitle(title);
      eventToUpdate.setDate(date);
      eventsRepositoryJdbcTemplateImpl.update(eventToUpdate);
    }else{
      throw new IllegalArgumentException("Event with id <" + id + "> not found");
    }
  }

  @Override
  public void removeEvent(Long id) {
    eventsRepositoryJdbcTemplateImpl.deleteById(id);
  }
}
