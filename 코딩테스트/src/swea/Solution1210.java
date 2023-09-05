package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1210 {
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/input_d4_1210.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int tc = 0; tc < 10; tc++) {
            int t = Integer.parseInt(br.readLine());
            arr = new int[101][101];
            int sx = 0, sy = 0;
            for(int i = 0; i < 100; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] == 2){
                        sx = i; sy = j;
                    }
                }
            }
            int res = go(sx-1, sy);
            System.out.println("#"+(tc+1)+" "+res);

        }
    }

    private static int go(int x, int y) {
        while (x > 0) {
            int ly = y;
            int ry = y;
            while(0 <= ly - 1 && ly - 1 < 100 && arr[x][ly-1] == 1) ly -= 1;
            while(0 <= ry + 1 && ry + 1 < 100 && arr[x][ry+1] == 1) ry += 1;
            if (ly != y){
                x -= 1;
                y = ly;
                continue;
            }
            if(ry != y){
                x -= 1;
                y = ry;
                continue;
            }
            x -= 1;

        }
        return y;
    }
}