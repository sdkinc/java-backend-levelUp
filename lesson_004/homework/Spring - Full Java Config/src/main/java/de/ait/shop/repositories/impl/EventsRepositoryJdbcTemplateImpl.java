package de.ait.shop.repositories.impl;

import de.ait.shop.models.Event;
import de.ait.shop.repositories.EventsRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcTemplateImpl implements EventsRepository {

  private static final String SQL_SELECT_ALL = "select * from event order by id";
  private static final String SQL_REMOVE_ONE_BY_ID = "delete * from event where id = ?";
  private static final String SQL_SELECT_ONE_BY_TITLE = "select * from event where title = ? limit 1";
  private static final String SQL_UPDATE_ONE_BY_ID = "update event set title=?,city=?, date=? where id = ?";
  private static final RowMapper<Event> EVENT_ROW_MAPPER = (row, rowNum) -> Event.builder()
      .id(row.getLong("id"))
      .title(row.getString("title"))
      .city(row.getString("city"))
      .date(row.getDate("date").toLocalDate())
      .build();
  private final JdbcTemplate jdbcTemplate;

  @Override
  public Event findById(Long id) {
    List<Event> eventList = jdbcTemplate.query(SQL_SELECT_ALL, EVENT_ROW_MAPPER);
    Event eventById = null;

    for (Event event : eventList) {

      if (event.getId().equals(id)) {
        eventById = event;
        break;
      }
    }
    return eventById;
  }

  @Override
  public List<Event> findAll() {
    return jdbcTemplate.query(SQL_SELECT_ALL, EVENT_ROW_MAPPER);
  }

  @Override
  public void save(Event model) {
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("event")
        .usingGeneratedKeyColumns("id");

    Map<String, Object> row = new HashMap<>();
    row.put("title", model.getTitle());
    row.put("city", model.getCity());
    row.put("date", model.getDate());

    Long generatedId = insert.executeAndReturnKey(row).longValue();

    model.setId(generatedId);
  }

  @Override
  public void deleteById(Long id) {
    try {
      jdbcTemplate.update(SQL_REMOVE_ONE_BY_ID, id);
    } catch (EmptyResultDataAccessException e) {
      System.out.println(e.toString());
    }
  }

  @Override
  public void update(Event model) {
    jdbcTemplate.update(SQL_UPDATE_ONE_BY_ID, model.getTitle(), model.getCity(), model.getDate(),
        model.getId());
  }

  @Override
  public Event findOneByTitle(String title) {
    try {
      return jdbcTemplate.queryForObject(SQL_SELECT_ONE_BY_TITLE, EVENT_ROW_MAPPER, title);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}
