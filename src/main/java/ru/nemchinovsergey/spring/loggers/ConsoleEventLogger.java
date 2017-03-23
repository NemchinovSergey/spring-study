package ru.nemchinovsergey.spring.loggers;

/**
 * Created by Sergey on 23.03.2017.
 */
public class ConsoleEventLogger implements EventLogger
{
    public void logEvent(String msg)
    {
        System.out.println(msg);
    }
}
