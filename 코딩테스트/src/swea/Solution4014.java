package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4014 {
    private static int n, x;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

    }

    private static void go() {
        for (int i = 0; i < n; i++) {
            int cur = arr[i][0];
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == cur) continue;
                else if(Math.abs(arr[i][j] - cur) >= 2) {
                    check = false;
                    break;
                }
//                 TODO
                else if(arr[i][j] - cur == 1){ // 상승

                }
                else if(cur - arr[i][j] == 1){ // 하강

                }
                cur = arr[i][j];
            }
        }
    }
    private static boolean check(int x, int y, int d){
        return false;
    }
}
