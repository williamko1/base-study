package me.qinzc.io;

import java.io.FileInputStream;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 16:31 2019-02-15
 * modifier:
 * modifyTime:
 */
public class FileInputStreamTest {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("/Users/redknife/Documents/workspaces/base-study/src/me/qinzc/io/FileInputStreamTest.java");
        byte[] temp = new byte[10];
        int hasRead = 0;
        while((hasRead = fis.read(temp)) > 0){
            System.out.print(new String(temp, 0, hasRead));
        }

        fis.close();
    }
}
