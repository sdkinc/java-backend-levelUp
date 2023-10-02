package de.ait.shop.repositories;

import de.ait.shop.models.Event;

public interface EventsRepository extends CrudRepository<Event>{
  Event findOneByTitle(String title); // добавили метод, который специфичен именно для Event
}
