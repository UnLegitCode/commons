package ru.unlegit.commons.function;

import ru.unlegit.commons.Throwables;

import java.util.function.Consumer;

@FunctionalInterface
public interface ExceptedConsumer<V> extends Consumer<V> {

    void exceptedAccept(V v) throws Exception;

    @Override
    default void accept(V v) {
        try {
            exceptedAccept(v);
        } catch (Exception exception) {
            Throwables.rethrow(exception);
        }
    }
}