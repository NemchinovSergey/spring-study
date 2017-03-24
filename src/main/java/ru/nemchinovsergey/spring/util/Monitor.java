package ru.nemchinovsergey.spring.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Monitor implements ApplicationListener<ApplicationEvent>
{
    public void onApplicationEvent(ApplicationEvent applicationEvent)
    {
        System.out.println(applicationEvent.getClass().getSimpleName() + " > " + applicationEvent.getSource().toString());
    }
}
