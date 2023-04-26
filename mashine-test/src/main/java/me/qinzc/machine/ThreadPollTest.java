package me.qinzc.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 16:31 2023/4/21
 * modifier:
 * modifyTime:
 */
public class ThreadPollTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

//        fixedThreadPool();
//        cachedThreadPool();
//        scheduledThreadPool();
//        rejectPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                // 自定义 一条不少
                (r, executor) -> System.out.println("我失败啦")
                // 失败之后就不执行了
//                new ThreadPoolExecutor.DiscardPolicy()
//                new ThreadPoolExecutor.AbortPolicy()
                // 会丢弃老的
//                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
//        Executors.newSingleThreadExecutor()
        try {
            for (int i = 0; i < 100; i++) {
                threadPoolExecutor.submit(fun(0, i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private static boolean test() {
        System.out.println("jinlaile");
        return true;
    }

    private static void rejectPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                r -> new Thread(r, "schema_task_pool_" + r.hashCode()),
                // 除非是非running状态，否则就是调用者线程直接运行,等于说是maximumPoolSize + 当前线程 分摊整个任务池
//                new ThreadPoolExecutor.CallerRunsPolicy()
                // 丢的动作很快，大部分任务都会被丢弃，如果任务很多执行很慢的话
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                // 报错之后后续的任务就不进行的,总执行数量为队列数量+ maximumPoolSize
                new ThreadPoolExecutor.AbortPolicy()
        );

        // invokeAll报错会调用中断方法
//        List<Callable<Integer>> callables = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            callables.add(fun(100000, i));
//        }
//        List<Future<Integer>> submits = threadPoolExecutor.invokeAll(callables);
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(fun(100000, i));
        }
    }

    private static void scheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(fun(1000, 1), 5, TimeUnit.SECONDS);

        System.out.println(schedule);
        while (!schedule.isDone()) {
            System.out.println("do something else");
        }
        System.out.println(123);
    }


    private static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 2000; i++) {
            cachedThreadPool.submit(fun(10000, i));
        }
    }

    private static void fixedThreadPool() throws InterruptedException, ExecutionException {
        ExecutorService threadPollExecutors = Executors.newFixedThreadPool(3);
        Future<Integer> result3 = threadPollExecutors.submit(fun(3000, 3));

        Future<Integer> result2 = null;
        try {
            result2 = threadPollExecutors.submit(fun(2000, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Future<Integer> result1 = threadPollExecutors.submit(fun(1000, 1));
        System.out.println(result3.get());
        System.out.println(result2.get());
        System.out.println(result1.get());
    }

    private static Callable<Integer> fun(int millis, int x) {
        return () -> {
//            if (x == 2) {
//                throw new RuntimeException("error");
//            }
            try {
                Thread.sleep(millis);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        };
    }
}
