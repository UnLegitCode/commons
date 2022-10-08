package ru.unlegit.commons.concurrent.future;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.function.Consumer;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleFutureValue<V> extends AbstractFuture implements FutureValue<V> {

    @Getter
    V value;
    @Setter
    Consumer<V> observer;

    @Override
    public void complete(@NonNull V value) {
        this.value = value;
        internalComplete();

        if (observer != null) {
            observer.accept(value);
        }
    }
}