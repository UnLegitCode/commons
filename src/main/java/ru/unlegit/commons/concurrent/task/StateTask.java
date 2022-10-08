package ru.unlegit.commons.concurrent.task;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.unlegit.commons.concurrent.future.FutureState;
import ru.unlegit.commons.function.ExceptedRunnable;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StateTask implements ConcurrentTask {

    ExceptedRunnable task;
    FutureState future;

    @Override
    public void execute() {
        try {
            task.exceptedRun();

            future.complete();
        } catch (Exception exception) {
            future.fail(exception);
        }
    }
}