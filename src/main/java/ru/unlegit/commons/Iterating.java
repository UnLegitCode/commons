package ru.unlegit.commons;

import lombok.experimental.UtilityClass;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Predicate;

@UtilityClass
public class Iterating {

    public void repeat(int repeats, Runnable action) {
        for (int i = 0; i < repeats; i++) {
            action.run();
        }
    }

    public void repeatWhile(BooleanSupplier repeatCondition, Runnable action) {
        while (repeatCondition.getAsBoolean()) {
            action.run();
        }
    }

    public <T> void repeatWhile(T value, Predicate<T> repeatCondition, Consumer<T> action) {
        while (repeatCondition.test(value)) {
            action.accept(value);
        }
    }
}