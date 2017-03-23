package ru.nemchinovsergey.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.nemchinovsergey.spring.beans.Client;
import ru.nemchinovsergey.spring.beans.Event;
import ru.nemchinovsergey.spring.loggers.EventLogger;

import java.text.DateFormat;
import java.util.Date;

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
        //@SuppressWarnings("resource") // We will remove this suppress in further lessons
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for user 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for user 2");
    }


}
