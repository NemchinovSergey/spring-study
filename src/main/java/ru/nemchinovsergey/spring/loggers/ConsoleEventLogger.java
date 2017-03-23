package ru.nemchinovsergey.spring.loggers;

import ru.nemchinovsergey.spring.beans.Event;

/**
 * Created by Sergey on 23.03.2017.
 */
public class ConsoleEventLogger implements EventLogger
{
    public void logEvent(Event event)
    {
        System.out.println(event.toString());
    }
}
