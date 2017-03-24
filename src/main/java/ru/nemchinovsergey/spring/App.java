package ru.nemchinovsergey.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.nemchinovsergey.spring.beans.Client;
import ru.nemchinovsergey.spring.beans.Event;
import ru.nemchinovsergey.spring.beans.EventType;
import ru.nemchinovsergey.spring.loggers.EventLogger;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null)
        {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource") // We will remove this suppress in further lessons
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        // to automatic close context
        ctx.registerShutdownHook();

        App app = (App) ctx.getBean("app");


        for (int i = 0; i < 5; i++) {
            Event event = ctx.getBean(Event.class);
            EventType eventType = (Math.random() * 2) > 1 ? EventType.INFO : EventType.ERROR;
            app.logEvent(eventType, event, String.format("Some event for user %d", i));
        }

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "The last event before application closing");

    }


}
