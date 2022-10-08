package ru.unlegit.commons.concurrent;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import ru.unlegit.commons.Throwables;
import ru.unlegit.commons.Validator;
import ru.unlegit.commons.function.ExceptedRunnable;

@UtilityClass
public class ThreadFactory {

    public Thread repeating(int interval, @NonNull ExceptedRunnable action) {
        Validator.checkPositive(interval);

        return new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(interval);

                    action.exceptedRun();
                } catch (Exception exception) {
                    Throwables.rethrow(exception);
                }
            }
        });
    }

    public Thread repeating(@NonNull ExceptedRunnable action) {
        return new Thread(() -> {
            while(true) {
                try {
                    action.exceptedRun();
                } catch (Exception exception) {
                    Throwables.rethrow(exception);
                }
            }
        });
    }
}