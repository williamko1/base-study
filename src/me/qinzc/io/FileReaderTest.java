package me.qinzc.io;

import java.io.FileReader;

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
public class FileReaderTest {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("/Users/redknife/Documents/workspaces/base-study/src/me/qinzc/io/FileInputStreamTest.java");
        int hasRead = 0;
        char[] temp = new char[32];
        while ((hasRead = fr.read(temp)) > 0) {
            System.out.println(new String(temp, 0, hasRead));
        }
    }
}
