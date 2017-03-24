package ru.nemchinovsergey.spring.loggers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nemchinovsergey.spring.beans.Event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    // Use system property cache.size or 5 if property is not set
    @Value("${cache.size:5}")
    private int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(String fileName, int cacheSize)
    {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>();
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<>(cacheSize);
    }

    @PreDestroy
    private void destroy()
    {
        if (!cache.isEmpty())
            writeEventsFromCache();
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


}
