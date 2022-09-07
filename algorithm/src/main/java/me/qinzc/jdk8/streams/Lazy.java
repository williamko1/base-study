package me.qinzc.jdk8.streams;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * desc : 利用惰性写出高性能且抽象的代码
 *
 * Lazy<Long> param1Lazy = Lazy.of(() -> 2L);
 * Lazy<Long> param2Lazy = Lazy.of(() -> 2L);
 * Lazy<Long> param3Lazy = Lazy.of(() -> 2L);
 * Lazy<Long> result = param1Lazy.flatMap(param1 ->
 *         param2Lazy.flatMap(param2 ->
 *                 param3Lazy.map(param3 -> param1 + param2 + param3)
 *         )
 * );
 * @author Zane Qin
 * creatTime : 11:02 2022/7/13
 * modifier:
 * modifyTime:
 */
public class Lazy<T> implements Supplier<T> {

    private final Supplier<? extends T> supplier;

    private T value;

    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    public T get() {
        if (value == null) {
            T newValue = supplier.get();

            if (newValue == null) {
                throw new IllegalStateException("Lazy value can not be null!");
            }

            value = newValue;
        }

        return value;
    }

    public <S> Lazy<S> map(Function<? super T, ? extends S> function) {
        return Lazy.of(() -> function.apply(get()));
    }

    public <S> Lazy<S> flatMap(Function<? super T, Lazy<? extends S>> function) {
        return Lazy.of(() -> function.apply(get()).get());
    }
}