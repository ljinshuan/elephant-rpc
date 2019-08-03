package com.alibaba.interaction.elephant.rpc.concurrent;

import com.google.common.util.concurrent.AbstractFuture;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class DefaultListenableFuture<V> extends AbstractFuture<V> implements SettableFuture<V> {


    public static <T> DefaultListenableFuture<T> create() {

        DefaultListenableFuture<T> future = new DefaultListenableFuture();
        return future;
    }

    @Override
    public boolean set(V v) {

        boolean set = super.set(v);
        if (!set) {
            throw new IllegalStateException("fail to set result!");
        }
        return true;

    }

    @Override
    public boolean setException(Throwable t) {


        return super.setException(t);

    }


    /**
     * @param consumer
     * @param executor
     */

    public void addListener(Consumer<V> consumer, Executor executor) {

        this.addListener(() -> {

            try {
                V v = get();
                consumer.accept(v);
            } catch (Throwable t) {
            }
        }, executor);

    }
}
