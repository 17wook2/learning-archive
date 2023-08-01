package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1210 {
    private static int[][] arr;
    private static int ans;
    private static int[] dx = {0,1,0};
    private static int[] dy = {1,0,-1};
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/input_d4_1210.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            arr = new int[101][101];
            for(int i = 0; i < 100; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = -1;
            for (int j = 0; j < 100; j++) {
                if (arr[0][j] == 1) go(0,j,1);
                if (ans != -1) break;
            }
            System.out.println("#"+(tc+1)+" "+ans);

        }

    }

    private static void go(int x, int y, int d) {
        if(x == 100){
//            System.out.println(x + " " + y);
            if (arr[x-1][y] == 2) {
                ans = y;
            }
            return;
        }


        if (0 <= y + 1 && y + 1 < 100 && arr[x][y + 1] == 1) {
            go(x, y+1,0);
            return;
        }
        if (d == 2){
            if (0 <= y - 1 && y - 1 < 100 && arr[x][y-1] == 1) {
                go(x,y-1,2);
                return;
            }
        }
        go(x+1,y,1);
    }
}
