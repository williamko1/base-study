package me.qinzc.practice;

import java.io.*;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 13:37 2020/7/8
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
        // 打印原始数组
        printArray(origin);
        // 原始数组转成稀疏数组
        int[][] sparseArray = convertSparse(origin);

        printArray(sparseArray);
        String filename = "map.data";
        // 稀疏数组落盘
        saveSparse(sparseArray, filename);
        // 落盘数据转成稀疏数组
        int[][] sparseArray2 = fileToSparse(filename);
        // 打印数组
        printArray(sparseArray2);
        // 稀疏数组恢复成原始数组
        int[][] recoveryArray = sparseToArray(sparseArray2);
        // 打印数组
        printArray(recoveryArray);
    }

    private static int[][] sparseToArray(int[][] sparseArray) {
        int[][] origin = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            origin[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return origin;
    }

    private static int[][] fileToSparse(String filename) {
        int[][] sparseArray = null;
        FileReader reader = null;
        try {
            File file = new File(filename);
            reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.mark((int) file.length() + 1);
            int row = Integer.valueOf(String.valueOf(bufferedReader.lines().count()));
            sparseArray = new int[row][3];
            bufferedReader.reset();
            String line;
            String[] split;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                split = line.split("\t");
                for (int i = 0; i < split.length; i++) {
                    sparseArray[count][i] = Integer.valueOf(split[i]);
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sparseArray;

    }

    private static void saveSparse(int[][] sparseArray, String filename) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            for (int[] row : sparseArray) {
                writer.write(String.format("%d\t%d\t%d\n", row[0], row[1], row[2]));
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }   finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int[][] convertSparse(int[][] origin) {

        int sum = 0;
        for (int[] row : origin) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = origin.length;
        sparseArray[0][1] = origin[0].length;
        sparseArray[0][2] = sum;
        int rowIndex = 1;
        for (int i = 0; i < origin.length; i++) {
            int[] row = origin[i];
            for (int j = 0; j < row.length; j++) {
                if (origin[i][j] != 0) {
                    sparseArray[rowIndex][0] = i;
                    sparseArray[rowIndex][1] = j;
                    sparseArray[rowIndex++][2] = origin[i][j];
                }
            }
        }
        return sparseArray;
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.printf("\n");
        }
    }
}
