package ru.nemchinovsergey.spring.loggers;

import org.springframework.stereotype.Component;
import ru.nemchinovsergey.spring.beans.Event;

@Component
public class ConsoleEventLogger implements EventLogger
{
    public void logEvent(Event event)
    {
        System.out.println(event.toString());
    }
}
