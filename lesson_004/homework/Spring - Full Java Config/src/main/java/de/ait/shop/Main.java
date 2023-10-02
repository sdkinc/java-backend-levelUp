package de.ait.shop;

import de.ait.shop.config.ApplicationConfig;
import de.ait.shop.models.Event;
import de.ait.shop.repositories.EventsRepository;
import de.ait.shop.services.EventService;
import java.time.LocalDate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 9/25/2023
 * Spring
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        EventService eventService = context.getBean(EventService.class);
        EventsRepository eventsRepository = context.getBean("eventsRepositoryJdbcTemplateImpl", EventsRepository.class);

        Event event = Event.builder().title("Party on Prykarpattya").city("Bar").date(
            LocalDate.parse("2023-11-17")).build();

        System.out.println(event);

        eventsRepository.save(event);

        System.out.println(event);
    }
}
