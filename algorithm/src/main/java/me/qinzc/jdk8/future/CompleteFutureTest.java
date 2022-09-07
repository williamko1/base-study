package me.qinzc.jdk8.future;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * desc : 异步通知
 *
 * @author Zane Qin
 * creatTime : 13:21 2022/8/16
 * modifier:
 * modifyTime:
 */
public class CompleteFutureTest {


    @Test
    public void basic() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
// 执行更多任务，比如查询其他商店
        shop.doSomethingElse();
// 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }


    public static class Shop{

        private String product;

        public double getPrice(String product) {
            return calculatePrice(product);
        }

        public Future<Double> getPriceAsync(String product) {
            CompletableFuture<Double> futurePrice = new CompletableFuture<>();
            new Thread( () -> {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            }).start();
            return futurePrice;
        }

        private double calculatePrice(String product) {
            delay();
            return new Random(100).nextDouble() * product.charAt(0) + product.charAt(1);
        }

        public static void delay() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public Shop(String product) {
            this.product = product;
        }

        public void doSomethingElse() {
            System.out.println("who are you ");
            System.out.println("Im fine");
        }
    }


}
