package ru.unlegit.commons.concurrent.future;

import lombok.Setter;

public class SimpleFutureState extends AbstractFuture implements FutureState {

    @Setter
    private Runnable observer;

    @Override
    public void complete() {
        internalComplete();

        if (observer != null) {
            observer.run();
        }
    }
}