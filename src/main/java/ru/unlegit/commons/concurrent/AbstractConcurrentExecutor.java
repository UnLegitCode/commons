package ru.unlegit.commons.concurrent;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.unlegit.commons.concurrent.future.FutureState;
import ru.unlegit.commons.concurrent.future.FutureValue;
import ru.unlegit.commons.concurrent.future.SimpleFutureState;
import ru.unlegit.commons.concurrent.future.SimpleFutureValue;
import ru.unlegit.commons.concurrent.task.ConcurrentTask;
import ru.unlegit.commons.concurrent.task.StateTask;
import ru.unlegit.commons.concurrent.task.ValueTask;
import ru.unlegit.commons.function.ExceptedRunnable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AbstractConcurrentExecutor implements IConcurrentExecutor {

    protected final BlockingQueue<ConcurrentTask> taskQueue = new LinkedBlockingQueue<>();

    @Override
    @SneakyThrows
    public FutureState putTask(@NonNull ExceptedRunnable task) {
        FutureState future = new SimpleFutureState();

        taskQueue.put(new StateTask(task, future));

        return future;
    }

    @Override
    @SneakyThrows
    public <V> FutureValue<V> putTask(@NonNull Callable<V> task) {
        FutureValue<V> future = new SimpleFutureValue<>();

        taskQueue.put(new ValueTask<>(task, future));

        return future;
    }

    protected void executeNext() {
        ConcurrentTask task = taskQueue.poll();

        if (task != null){
            task.execute();
        }
    }
}