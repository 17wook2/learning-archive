package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution4012 {
    static int n,ans;
    static int[][] arr;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            check = new boolean[n];
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                String[] split = br.readLine().split(" ");
                for(int j = 0; j < n; j++) arr[i][j] = Integer.parseInt(split[j]);
            }
            go(0,0);
            System.out.println("#" + tc + " " + ans/2);

        }
    }

    private static void go(int cnt, int start) {
        if (cnt == n/2) {
            int left = 0, right = 0;
            for (int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++){
                    if(i != j && check[i] && check[j]) {
                        left += arr[i][j];
                        left += arr[j][i];
                    }
                    if (i != j && !check[i] && !check[j]) {
                        right += arr[i][j];
                        right += arr[j][i];
                    }
                }
            }
            int diff = Math.abs(left - right);
            ans = Math.min(ans, diff);
            return;
        }
        for (int i = start; i < n; i++) {
            check[i] = true;
            go(cnt+1, i + 1);
            check[i] = false;
        }


    }
}
