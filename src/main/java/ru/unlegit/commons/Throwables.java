package ru.unlegit.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Throwables {

    public void rethrow(Exception exception) {
        throw new RuntimeException(exception);
    }
}