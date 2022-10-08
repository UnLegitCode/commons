package ru.unlegit.commons.function;

import ru.unlegit.commons.Throwables;

@FunctionalInterface
public interface ExceptedRunnable extends Runnable {

    void exceptedRun() throws Exception;

    @Override
    default void run() {
        try {
            exceptedRun();
        } catch (Exception exception) {
            Throwables.rethrow(exception);
        }
    }
}