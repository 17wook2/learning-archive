package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2239 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9][9];
        for(int i = 0 ; i < 9; i++){
            char[] row = br.readLine().toCharArray();
            for(int j = 0; j < 9; j++) arr[i][j] = Integer.parseInt(String.valueOf(row[j]));
        }
        go(0,0);
    }

    private static void go(int x, int y) {
        if(y == 9) {
            go(x+1,0);
            return;
        }
        if(x == 9) {
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            System.exit(0);
        }
        if (arr[x][y] != 0) {
            go(x,y+1);
            return;
        }
        for(int k = 1; k <= 9; k++){
            if (check(x,y,k)) {
                arr[x][y] = k;
                go(x,y+1);
                arr[x][y] = 0;
            }

        }

    }

    private static boolean check(int x, int y, int k) {
        int box_x = (x / 3) * 3;
        int box_y = (y / 3) * 3;
        for (int i = box_x; i < box_x + 3; i++) {
            for(int j = box_y; j < box_y + 3; j++){
                if (arr[i][j] == k) return false;
            }
        }
        for(int j = 0; j < 9; j++){
            if(y != j && arr[x][j] == k) return false;
        }
        for(int i = 0; i < 9; i++){
            if(x != i && arr[i][y] == k) return false;
        }
        return true;
    }
}
