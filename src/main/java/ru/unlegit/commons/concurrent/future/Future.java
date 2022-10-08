package ru.unlegit.commons.concurrent.future;

import lombok.SneakyThrows;
import ru.unlegit.commons.Iterating;
import ru.unlegit.commons.Validator;

import java.util.concurrent.TimeUnit;

public interface Future {

    State getState();

    Exception getCause();

    void fail(Exception cause);

    @SneakyThrows
    default void await() {
        while(getState() == State.NON_COMPLETED) {}
    }

    @SneakyThrows
    default boolean await(long durationMillis) {
        Validator.checkPositive(durationMillis);

        long startStamp = System.currentTimeMillis();

        while (getState() == State.NON_COMPLETED) {
            if (startStamp < System.currentTimeMillis()) {
                return false;
            }
        }

        return true;
    }

    default boolean await(int duration, TimeUnit timeUnit) {
        return await(timeUnit.toMillis(duration));
    }

    enum State {

        NON_COMPLETED,
        SUCCESS,
        FAILED
    }
}