package me.qinzc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 16:41 2019-02-15
 * modifier:
 * modifyTime:
 */
public class FileOutputStreamTest {
    public static void main(String[] args) {

        try (
            FileOutputStream fos = new FileOutputStream("test.txt");
            FileInputStream fis = new FileInputStream("/Users/redknife/Documents/workspaces/base-study/src/me/qinzc/io/FileInputStreamTest.java");
            FileInputStream fis2 = new FileInputStream("test.txt");
        ){
            byte[] temp = new byte[32];
            while(fis.read(temp) > 0){
                fos.write(temp);
            }
            byte[] temp2 = new byte[32];
            while(fis2.read(temp2) > 0){
                System.out.print(new String(temp2));
            }
            File f = new File("test.txt");
            System.out.println(f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
