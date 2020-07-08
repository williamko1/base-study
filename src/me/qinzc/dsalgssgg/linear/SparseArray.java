package me.qinzc.dsalgssgg.linear;

import java.io.*;
import java.util.stream.Stream;

/**
 * desc : 稀疏数组的创建于还原
 * 二维数组转稀疏数组的思路
 * 1. 遍历 原始的二维数组，得到有效数据的个数sum
 * 2. 根据sum创建稀疏数组sparse =  int[sum+1][3]
 * 3. 将二维数组的有效数据存入稀疏数组
 * <p>
 * 稀疏数组转二维数组
 * 1. 读取稀疏数组的第一行，创建原始二维数组 int[sparse[0][0]][sparse[0][1]]
 * 2. 读取稀疏数组后几行的数据，并赋值给原始的二维数组
 * <p>
 * 把稀疏数组保存到磁盘中
 * 从磁盘读取稀疏数组
 *
 * @author : Zane Qin
 * creatTime : 17:09 2020/7/2
 * modifier:
 * modifyTime:
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0表示没有棋子，1表示黑子，2表示蓝子
        int[][] origin = new int[11][11];
        origin[1][2] = 1;
        origin[2][3] = 2;
        System.out.println("原始的二维数组");
        for (int[] row : origin) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        int[][] sparseArray = getSparseArray(origin);

        // 打印稀疏数组
        System.out.println("得到的稀疏数组为");
        for (int[] row : sparseArray) {
            System.out.printf("%d\t%d\t%d\t\n", row[0], row[1], row[2]);
        }
        String fileName = "map.data";
        toFile(fileName, sparseArray);
        int[][] sparseArray2 = fileToSparseArray(fileName);
        // 稀疏数组转二维数组
        int[][] convertArray = sparseRecovery(sparseArray2);

        // 打印稀疏数组
        System.out.println("得到转化后的二维数组为");
        for (int[] row : convertArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("");
        }
    }

    /**
     * 通过原始二维数组得到稀疏数组
     *
     * @param origin
     * @return
     */
    public static int[][] getSparseArray(int[][] origin) {
        // 将二维数组转成稀疏数组
        // 得到非0数据的个数
        int sum = 0;
        for (int[] row : origin) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        // 初始化稀疏数组第一行元信息
        int[][] sparseArray = new int[sum + 1][3];
        // 一行一列存原二维数组行数
        sparseArray[0][0] = origin.length;
        // 一行二列存原二维数组列数
        sparseArray[0][1] = origin[0].length;
        // 一行三列存原二维数组数据总数
        sparseArray[0][2] = sum;
        // 将二维数组的数据存入稀疏数组
        for (int i = 0, num = 1; i < origin.length; i++) {
            for (int j = 0; j < origin[i].length; j++) {
                if (origin[i][j] != 0) {
                    sparseArray[num][0] = i;
                    sparseArray[num][1] = j;
                    sparseArray[num++][2] = origin[i][j];
                }
            }
        }
        return sparseArray;
    }

    /**
     * 稀疏数组恢复成二维数组
     *
     * @param sparseArray
     * @return
     */
    public static int[][] sparseRecovery(int[][] sparseArray) {
        // 稀疏数组转二维数组
        int[][] convertArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            convertArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return convertArray;
    }

    /**
     * 稀疏数组落盘
     *
     * @param sparseArray
     */
    public static void toFile(String fileName, int[][] sparseArray) {
        FileWriter file;
        try {
            file = new FileWriter(fileName);
            for (int i = 0; i < sparseArray.length; i++) {
                file.write(String.format("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]));
            }
            file.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 从文件读取稀疏数组
    public static int[][] fileToSparseArray(String fileName) {

        FileReader reader = null;
        int[][] sparseArray = null;
        try {
            File file = new File(fileName);
            reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.mark((int) file.length() + 1);
            String line;
            String[] split;

            int rowCount = 0;
            Stream<String> lines = bufferedReader.lines();
            int count = Integer.parseInt(String.valueOf(lines.count()));

            sparseArray = new int[count][3];

            bufferedReader.reset();

            while ((line = bufferedReader.readLine()) != null) {
                split = line.split("\t");
                for (int i = 0; i < split.length; i++) {
                    sparseArray[rowCount][i] = Integer.valueOf(split[i]);
                }
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sparseArray;

    }
}
