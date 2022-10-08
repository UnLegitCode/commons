package ru.unlegit.commons.concurrent.future;

import lombok.NonNull;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public interface FutureValue<V> extends Future {

    V getValue();

    void complete(@NonNull V value);

    void setObserver(Consumer<V> observer);

    default V awaitValue() {
        await();

        return getValue();
    }

    default V awaitValue(long durationMillis) {
        return await(durationMillis) ? getValue() : null;
    }

    default V awaitValue(int duration, TimeUnit timeUnit) {
        return awaitValue(timeUnit.toMillis(duration));
    }
}