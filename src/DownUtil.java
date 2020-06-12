import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 16:23 2019-02-12
 * modifier:
 * modifyTime:
 */
public class DownUtil {
    //定义下载资源路径
    private String path;
    //文件保存位置
    private String targetFile;
    private int threadNum;
    private DownThread[] threads;
    //下载文件总大小
    private int fileSize;

    public DownUtil(String targetFile, int threadNum) {
        this.threadNum = threadNum;
        this.threads = new DownThread[threadNum];
        this.targetFile = targetFile;
    }

    public class DownThread extends Thread{
        //当前线程下载位置
        private int startPos;
        //当前线程负责下载的文件大小
        private int currentPartSize;
        //当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        //已经下载的线程数
        public int length;

        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }
        @Override
        public void run(){
            try{
                HttpURLConnection conn = DownUtil.getConn(path);
                InputStream inputStream = conn.getInputStream();
                //跳过 startPos 个字节，表明该线程只负责下载自己负责的那部分文件
                inputStream.skip(this.startPos);
                byte[] buffer = new byte[2048];
                int hasRead;
                //读取网络中数据，并写入本地文件;
                while (length < currentPartSize && (hasRead = inputStream.read(buffer)) != -1){
                    currentPart.write(buffer, 0, hasRead);
                    //累计该线程下载大小
                    length += hasRead;
                }
                currentPart.close();
                inputStream.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void download(String path) throws Exception{
        this.path = path;
        HttpURLConnection conn = getConn(path);
        conn.setRequestProperty("Connection", "Keep-Alive");
        //得到文件大小
        fileSize = conn.getContentLength();
        conn.disconnect();
        int currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        //设置本地文件大小
        file.setLength(fileSize);
        file.close();
        for (int i = 0; i < threadNum; i++) {
            //计算每个线程下载的开始位置
            int startPos = i * currentPartSize;
            //每个线程使用一个RandomAccessFile进行下载;
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
            //定位该线程的下载位置
            currentPart.seek(startPos);
            //创建下载线程
            threads[i] = new DownThread(startPos, currentPartSize, currentPart);
            threads[i].start();
        }

    }

    /**
     * 获取下载的完成百分比
     */
    public double getCompleteRate(){
        //统计多线程下载的总大小
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += threads[i].length;
        }
        return sumSize * 1.0 / fileSize;
    }

    public static HttpURLConnection getConn(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/javascript," +
                "application/json," +
                "application/x-www-form-urlencoded," +
                "application/xml," +
                "application/zip," +
                "application/pdf," +
                "application/sql," +
                "application/graphql," +
                "application/ld+json," +
                "application/msword," +
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document," +
                "application/vnd.ms-excel," +
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet," +
                "application/vnd.ms-powerpoint," +
                "application/vnd.openxmlformats-officedocument.presentationml.presentation," +
                "application/vnd.oasis.opendocument.text," +
                "audio/mpeg," +
                "audio/ogg," +
                "multipart/form-data," +
                "text/css," +
                "text/html," +
                "text/xml," +
                "text/csv," +
                "text/plain," +
                "image/png," +
                "image/jpeg," +
                "image/gif," +
                "*/*");
        conn.setRequestProperty("AcceptLanguage", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        return conn;
    }

    public static void main(String[] args) {
        String path =
            "https://a11.gdl.netease.com/MuMuInstaller_1.0.16.0_gw-overseas.exe";
        DownUtil downUtil = new DownUtil("/Users/redknife/Downloads/test.pdf", 1);
        try {
            downUtil.download(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            int i = 0;
            while(downUtil.getCompleteRate() < 1){

                System.out.println(i + "秒已完成" + downUtil.getCompleteRate());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }).start();

    }
}
