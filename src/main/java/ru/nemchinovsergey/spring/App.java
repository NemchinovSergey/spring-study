package ru.nemchinovsergey.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.nemchinovsergey.spring.beans.Client;
import ru.nemchinovsergey.spring.beans.Event;
import ru.nemchinovsergey.spring.loggers.EventLogger;

/**
 * Created by Sergey on 23.03.2017.
 */
public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource") // We will remove this suppress in further lessons
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        // to automatic close context
        ctx.registerShutdownHook();

        App app = (App) ctx.getBean("app");

        for (int i = 0; i < 15; i++) {
            Event event = ctx.getBean(Event.class);
            app.logEvent(event, String.format("Some event for user %d", i));
        }

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "The last event before application closing");

    }


}
