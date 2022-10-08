package ru.unlegit.commons.concurrent.future;

public interface FutureState extends Future {

    void complete();

    void setObserver(Runnable observer);
}