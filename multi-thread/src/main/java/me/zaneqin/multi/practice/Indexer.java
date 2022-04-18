package me.zaneqin.multi.practice;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * desc : 消费者，从队列中取出文件并对他们建立索引
 *
 * @author Zane Qin
 * creatTime : 13:29 2022/3/21
 * modifier:
 * modifyTime:
 */
public class Indexer implements Runnable{

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file) {

    }

}
