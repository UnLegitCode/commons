package ru.unlegit.commons.concurrent;

import lombok.NonNull;
import ru.unlegit.commons.Destroyable;
import ru.unlegit.commons.concurrent.future.FutureState;
import ru.unlegit.commons.concurrent.future.FutureValue;
import ru.unlegit.commons.function.ExceptedRunnable;

import java.util.concurrent.Callable;

public interface IConcurrentExecutor extends Destroyable {

    FutureState putTask(@NonNull ExceptedRunnable task);

    <V> FutureValue<V> putTask(@NonNull Callable<V> task);
}