package ru.unlegit.commons.concurrent.future;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class AbstractFuture implements Future {

    @Getter
    volatile State state = State.NON_COMPLETED;
    @Getter
    Exception cause;

    @Override
    public void fail(Exception cause) {
        this.cause = cause;
        state = State.FAILED;
    }

    protected void internalComplete() {
        state = State.SUCCESS;
    }
}