package ru.nemchinovsergey.spring.loggers;

import ru.nemchinovsergey.spring.beans.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {

    }
}
