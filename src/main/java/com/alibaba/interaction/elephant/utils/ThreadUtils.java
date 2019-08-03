package com.alibaba.interaction.elephant.utils;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Optional;
import java.util.concurrent.*;

/**
 *
 */
public class ThreadUtils {


    public static ExecutorService defaultExecutor = new ThreadPoolExecutor(3, 10, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<>(300), new ThreadFactoryBuilder().setNameFormat("default_thread_%d").build());


    /**
     * @param coreSize
     * @param threadPoolName
     * @return
     */
    public static ExecutorService createExecutorService(int coreSize, String threadPoolName) {

        ExecutorService executor = new ThreadPoolExecutor(coreSize, coreSize, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<>(300), new ThreadFactoryBuilder().setNameFormat(threadPoolName + "_%d").build());

        return executor;
    }

    /**
     * @param call
     * @param <T>
     * @return
     */
    public static <T> T runAsync(Callable<T> call) {

        return Optional.ofNullable(call).map(d -> {

            try {
                Future<T> submit = defaultExecutor.submit(d);
                return submit.get();
            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        }).orElse(null);

    }


    /**
     * @param call
     * @param <T>
     * @return
     */
    public static <T> Future<T> runAsyncNoWait(Callable<T> call) {

        return Optional.ofNullable(call).map(d -> {


            Future<T> submit = defaultExecutor.submit(d);
            return submit;

        }).orElse(null);

    }

    public static <T> FutureTask<T> createFuture(Callable<T> call) {


        return (FutureTask<T>) Optional.ofNullable(call).map(d -> defaultExecutor.submit(d)).orElse(null);

    }

    public static void sleep(long ms) {

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
