package ru.unlegit.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validator {

    public void checkRange(int value, int min, int max) {
        if (value < min) throw new IllegalArgumentException("the value must be greater or equal to" + min);
        if (value > max) throw new IllegalArgumentException("the value must be less or equal to" + max);
    }

    public void checkRange(long value, long min, long max) {
        if (value < min) throw new IllegalArgumentException("the value must be greater or equal to" + min);
        if (value > max) throw new IllegalArgumentException("the value must be less or equal to" + max);
    }

    public void checkPositive(int value, boolean includeNull) {
        if (value < (includeNull ? 0 : 1)) throw new IllegalArgumentException("value must be positive or equal to 0");
    }

    public void checkPositive(long value, boolean includeNull) {
        if (value < (includeNull ? 0 : 1)) throw new IllegalArgumentException("value must be positive or equal to 0");
    }

    public void checkPositive(int value) {
        if (value < 1) throw new IllegalArgumentException("value must be positive");
    }

    public void checkPositive(long value) {
        if (value < 1) throw new IllegalArgumentException("value must be positive");
    }
}