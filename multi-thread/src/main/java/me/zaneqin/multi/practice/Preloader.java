package me.zaneqin.multi.practice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * desc : FutureTask来执行一个高开销的计算，并且计算结果将在稍后使用。通过提前启动计算，可以减少在等待结果时需要的时间
 *
 * @author Zane Qin
 * creatTime : 13:57 2022/3/21
 * modifier:
 * modifyTime:
 */
public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });

    private ProductInfo loadProductInfo() {
        return new ProductInfo();
    }

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException{
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else{
                throw launderThrowable(cause);
            }
        }
    }

    private static RuntimeException launderThrowable(Throwable cause) {
         if (cause instanceof RuntimeException) {
             return (RuntimeException) cause;
         } else if (cause instanceof Error) {
             throw (Error) cause;
         } else {
             throw new IllegalStateException("Not unchecked", cause);
         }
    }

    public static class ProductInfo{

    }

    public static class DataLoadException extends Exception {

    }

}
