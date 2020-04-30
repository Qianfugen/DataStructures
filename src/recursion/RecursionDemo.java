package recursion;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

/**
 * 走迷宫,0 没走过,1 墙, 2 通路,3 走过,死路
 */
public class RecursionDemo {
    //行
    private static final int ROWS = 8;
    //列
    private static final int COLS = 7;

    public static void main(String[] args) {
        int[][] map = new int[ROWS][COLS];
        //首尾两行设墙
        for (int i = 0; i < COLS; i++) {
            map[0][i] = 1;
            map[ROWS - 1][i] = 1;
        }
        //首尾两列设墙
        for (int i = 0; i < ROWS; i++) {
            map[i][0] = 1;
            map[i][COLS - 1] = 1;
        }

        //设墙
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][2] = 1;
//        map[2][2] = 1;

        //打印地图
        System.out.println("地图");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //开始游戏
        boolean flag = setWay(map, 1, 1);
        if (flag) {
            //记录步数
            int steps = 0;
            System.out.println("找到通路了!");
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (map[i][j] == 2) {
                        steps++;
                    }
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.printf("一共走了%d步!\n", steps);
        } else {
            System.out.printf("map[%d,%d]没有通路!\n", 1, 1);
        }

    }

    /**
     * 1.map表示地图
     * 2.(i,j) 表示起始位置
     * 3.如果小球能到 map[ROWS-2][COLS-2],则说明找到通路
     * 4.约定: map[i][j] = 0 没走过,1 墙, 2 通路,3 走过,死路
     * 5.在走迷宫时,需要确定一个策略,逆时针:下-右-上-左,如果该点不同,再回溯
     *
     * @param map 表示地图
     * @param i   i,j 代表起点位置
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[ROWS - 2][COLS - 2] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                //这个点没走过,先假设为通路
                map[i][j] = 2;
                //继续走下一步
                //设定走路策略:逆时针:下-右-上-左
                if (setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //都不行,该点为死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j]!=0,该点为1,2,3
                return false;
            }
        }
    }
}
