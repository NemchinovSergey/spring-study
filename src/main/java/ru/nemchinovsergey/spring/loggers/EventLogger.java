package ru.nemchinovsergey.spring.loggers;

import ru.nemchinovsergey.spring.beans.Event;

public interface EventLogger {
    void logEvent(Event event);
}
