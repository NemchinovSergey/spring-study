package ru.nemchinovsergey.spring.loggers;

import org.apache.commons.io.FileUtils;
import ru.nemchinovsergey.spring.beans.Event;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by nemchinov on 23.03.2017.
 */
public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    private void init() throws IOException
    {
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
