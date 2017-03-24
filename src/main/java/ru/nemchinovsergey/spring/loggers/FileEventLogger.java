package ru.nemchinovsergey.spring.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nemchinovsergey.spring.beans.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class FileEventLogger implements EventLogger {

    private File file;

    @Value("${events.file:target/events_log.txt}")
    private String fileName;


    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    private void init() throws IOException
    {
        file = new File(fileName);
        if (file.exists() && !file.canWrite())
        {
            throw new IllegalArgumentException("Can't write to file " + fileName);
        }
        else if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (Exception e)
            {
                throw new IllegalArgumentException("Can't create file", e);
            }
        }
    }

    public void logEvent(Event event)
    {
        try
        {
            FileUtils.writeStringToFile(file, event.toString() + "\n", Charset.forName("UTF-8"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
