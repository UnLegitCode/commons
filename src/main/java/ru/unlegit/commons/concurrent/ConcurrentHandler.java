package ru.unlegit.commons.concurrent;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import ru.unlegit.commons.Destroyable;
import ru.unlegit.commons.concurrent.future.FutureValue;
import ru.unlegit.commons.concurrent.future.SimpleFutureValue;
import ru.unlegit.commons.function.ExceptedConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcurrentHandler<V> implements Destroyable {

    BlockingQueue<Task<V>> taskQueue = new LinkedBlockingQueue<>();
    Thread thread;

    public ConcurrentHandler(@NonNull ExceptedConsumer<V> handler) {
        thread = ThreadFactory.repeating(() -> {
            Task<V> task = taskQueue.poll();

            if (task != null) {
                try {
                    handler.exceptedAccept(task.value);
                } catch (Exception exception) {
                    task.future.fail(exception);
                }

                task.future.complete(task.value);
            }
        });

        thread.start();
    }

    @SneakyThrows
    public FutureValue<V> putValue(V value) {
        FutureValue<V> future = new SimpleFutureValue<>();

        taskQueue.put(new Task<>(value, future));

        return future;
    }

    @Override
    public void destroy() {
        thread.interrupt();
        taskQueue.clear();
    }

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    private static class Task<V> {

        V value;
        FutureValue<V> future;
    }
}