package me.qinzc.machine;

import java.util.concurrent.*;

/**
 * desc :
 *
 * @author : Zane Qin
 * @createTime : 16:41 2023/4/20
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(5);

        Future<Integer> result = threadPoolExecutor.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 3;
        });
        Future<Integer> result2 = threadPoolExecutor.submit(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        });
        Future<Integer> result3 = threadPoolExecutor.submit(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });
        System.out.println(result3.get());
        System.out.println(result2.get());
        System.out.println(result.get());

//        String a = "abcdefg";
//        int i  =3;
//        System.out.println(shiftStr(a, i));
    }
    public static String shiftStr(String source, int n) {
        String substr = source.substring(0, n);
        String substr2 = source.substring(n);

        String reverse1 = new StringBuilder(substr).reverse().toString();
        String reverse2 = new StringBuilder(substr2).reverse().toString();
        return new StringBuilder(reverse1).append(reverse2).toString();
    }
}
