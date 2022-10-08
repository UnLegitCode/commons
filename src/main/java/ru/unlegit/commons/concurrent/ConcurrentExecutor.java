package ru.unlegit.commons.concurrent;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcurrentExecutor extends AbstractConcurrentExecutor {

    Thread thread = ThreadFactory.repeating(this::executeNext);

    {
        thread.start();
    }

    @Override
    public void destroy() {
        thread.interrupt();
        taskQueue.clear();
    }
}