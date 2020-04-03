package day1;

import java.io.*;

public class SparseArray {
    //棋盘行数
    private static final int ROWS = 11;
    //棋盘列数
    private static final int COLUMNS = 11;

    public static void main(String[] args) {
        //0 没有棋子，1 黑子，2 白子
        int[][] chessArr1 = new int[ROWS][COLUMNS];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原有二维数组
        System.out.println("二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //二维数组转稀疏数组
        //求黑子和白子的总数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = ROWS;
        sparseArr[0][1] = COLUMNS;
        sparseArr[0][2] = sum;
        int count = 0;
        //遍历二维数组，将不为0的值填入稀疏数组
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println("稀疏数组：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //恢复稀疏数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //保存到文件map.data
        File file = new File("map.data");
        FileWriter out = null;
        try {
            out = new FileWriter(file);
            //将稀疏数组中的数据存入文件
            for (int i = 0; i < sparseArr.length; i++) {
                out.write(sparseArr[i][0] + "\t" + sparseArr[i][1] + "\t" + sparseArr[i][2] + "\r\n");
            }
            System.out.println("文件保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //从文件中读取稀疏数组
        BufferedReader in = null;
        int[][] sparseArr2 = new int[sum + 1][3];
        try {
            in = new BufferedReader(new FileReader(file));
            String line;
            int row = 0;
            while ((line = in.readLine()) != null) {
                String[] temp = line.split("\t");
                sparseArr2[row][0] = Integer.valueOf(temp[0]);
                sparseArr2[row][1] = Integer.valueOf(temp[1]);
                sparseArr2[row][2] = Integer.valueOf(temp[2]);
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("从文件中读取的稀疏数组：");
        for (int i = 0; i < sparseArr2.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr2[i][0], sparseArr2[i][1], sparseArr2[i][2]);
        }


        //恢复稀疏数组
        int[][] chessArr4 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr4[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr4) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
