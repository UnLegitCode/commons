package ru.unlegit.commons.concurrent.pool;

import ru.unlegit.commons.concurrent.AbstractConcurrentExecutor;
import ru.unlegit.commons.concurrent.ThreadFactory;

public class FixedThreadPool extends AbstractConcurrentExecutor implements ThreadPool {

    private final Thread[] workers;

    public FixedThreadPool(int size) {
        workers = new Thread[size];

        for (int i = 0; i < size; i++) {
            Thread thread = ThreadFactory.repeating(this::executeNext);

            workers[i] = thread;

            thread.start();
        }
    }

    @Override
    public void destroy() {
        for (Thread thread : workers) {
            thread.interrupt();
        }
        taskQueue.clear();
    }
}