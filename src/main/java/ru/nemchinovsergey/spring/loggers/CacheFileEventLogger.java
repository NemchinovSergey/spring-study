package ru.nemchinovsergey.spring.loggers;

import ru.nemchinovsergey.spring.beans.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemchinov on 23.03.2017.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize)
    {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>();
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() >= cacheSize)
        {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache()
    {
        for (Event event : cache)
        {
            super.logEvent(event);
        }
    }

    private void destroy()
    {
        if (!cache.isEmpty())
            writeEventsFromCache();
    }
}
