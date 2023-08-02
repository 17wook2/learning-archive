package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2805 {
    private static int n, arr[][], diff, ans;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/input_d3_2805.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            ans = 0;
            diff = n / 2;
            for (int i = 0; i < n; i++) {
                char[] charArray = br.readLine().toCharArray();
                for(int j = 0; j < n; j++) arr[i][j] = Integer.parseInt(String.valueOf(charArray[j]));
            }
            go(0);
            System.out.println("#"+tc+" "+ans);

        }
    }

    private static void go(int cnt) {
        if(cnt == diff){
            for(int i = 0; i < n; i++) ans += arr[diff][i];
            return;
        }
        ans += arr[cnt][diff];
        ans += arr[n-1-cnt][diff];
        for (int i = 1; i < cnt+1; i++) {
            ans += arr[cnt][diff-i];
            ans += arr[cnt][diff+i];
            ans += arr[n-1-cnt][diff-i];
            ans += arr[n-1-cnt][diff+i];
        }
        go(cnt+1);
    }
}
