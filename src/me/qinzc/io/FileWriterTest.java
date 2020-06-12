package me.qinzc.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 16:43 2019-02-15
 * modifier:
 * modifyTime:
 */
public class FileWriterTest {
    public static void main(String[] args) {
        try(
            FileWriter fw = new FileWriter("test.txt");
            FileReader fr = new FileReader("./src/me/qinzc/io/FileWriterTest.java");
            FileReader fr2 = new FileReader("test.txt")
        ){
            int hasRead;
            char[] tmp = new char[32];
            char[] tmp2 = new char[32];
            while((hasRead = fr.read(tmp)) > 0){
                fw.write(new String(tmp, 0, hasRead));
                System.out.print(new String (tmp, 0, hasRead));
            }
            fw.flush();
            System.out.println("\n");
            System.out.println("test.txt文件");

            while((hasRead = fr2.read(tmp2)) > 0){
                System.out.print(new String (tmp2, 0, hasRead));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
