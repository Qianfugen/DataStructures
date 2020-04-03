package sparsearray;

import java.io.*;

public class SparseArray2 {
    //棋盘行数
    private static final int ROWS = 11;
    //棋盘列数
    private static final int COLUMNS = 11;
    //二维数组
    static int[][] chessArr = new int[ROWS][COLUMNS];
    //黑子和白字之和
    private static int sum = 0;

    /**
     * 打印二维数组
     *
     * @param chessArr
     */
    public static void printArr(int[][] chessArr) {
        System.out.println("二维数组");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 二维数组转稀疏数组
     *
     * @param chessArr
     * @return
     */
    public static int[][] chessArr2SparesArr(int[][] chessArr) {
        //求黑子和白子的总数
        for (int[] row : chessArr) {
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
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 稀疏数组转二维数组
     *
     * @param sparseArr
     * @return
     */
    public static int[][] sparseArr2chessArr(int[][] sparseArr) {
        int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return chessArr;
    }

    /**
     * 保存数组
     *
     * @param sparseArr 需要保存的数组
     * @param fileName  文件名
     */
    public static void saveArr2File(int[][] sparseArr, String fileName) {
        File file = new File(fileName);
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
    }

    /**
     * 从文件读取数组
     *
     * @param fileName
     * @return
     */
    public static int[][] readArrFromFile(String fileName) {
        //从文件中读取稀疏数组
        BufferedReader in = null;
        int[][] sparseArr = new int[sum + 1][3];
        try {
            in = new BufferedReader(new FileReader(fileName));
            String line;
            int row = 0;
            while ((line = in.readLine()) != null) {
                String[] temp = line.split("\t");
                sparseArr[row][0] = Integer.valueOf(temp[0]);
                sparseArr[row][1] = Integer.valueOf(temp[1]);
                sparseArr[row][2] = Integer.valueOf(temp[2]);
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
        return sparseArr;
    }

    public static void main(String[] args) throws InterruptedException {
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        printArr(chessArr);
        int[][] parseArr = chessArr2SparesArr(chessArr);
        printArr(parseArr);
        saveArr2File(parseArr, "test.data");
        Thread.sleep(1);
        int[][] parseArr2 = readArrFromFile("test.data");
        printArr(parseArr2);
        chessArr = sparseArr2chessArr(parseArr2);
        printArr(chessArr);
    }

}
