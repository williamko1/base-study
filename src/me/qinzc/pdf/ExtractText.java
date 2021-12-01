package me.qinzc.pdf;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import java.io.FileWriter;
import java.io.IOException;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 11:30 2021/11/25
 * modifier:
 * modifyTime:
 */
public class ExtractText {
    public static void main(String[]args) throws Exception {
        //加载测试文档
        PdfDocument pdf = new PdfDocument("/Users/redknife/Desktop/20191031.pdf");

        //实例化StringBuilder类
        StringBuilder sb = new StringBuilder();
        //定义一个int型变量
        int index = 0;

        //遍历PDF文档中每页
        PdfPageBase page;
        for (int i= 0; i<pdf.getPages().getCount();i++) {
            page = pdf.getPages().get(i);
            //调用extractText()方法提取文本
            sb.append(page.extractText(true));
            FileWriter writer;
            try {
                //将StringBuilder对象中的文本写入到txt
                writer = new FileWriter("ExtractText.txt");
                writer.write(sb.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pdf.close();
    }
}
