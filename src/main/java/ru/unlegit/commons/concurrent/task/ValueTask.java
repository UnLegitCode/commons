package ru.unlegit.commons.concurrent.task;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.unlegit.commons.Throwables;
import ru.unlegit.commons.concurrent.future.FutureValue;

import java.util.concurrent.Callable;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ValueTask<V> implements ConcurrentTask {

    Callable<V> task;
    FutureValue<V> future;

    @Override
    public void execute() {
        try {
            future.complete(task.call());
        } catch (Exception exception) {
            Throwables.rethrow(exception);
        }
    }
}