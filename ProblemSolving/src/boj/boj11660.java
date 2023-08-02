package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj11660 {
    static int n,m;
    static int[][] arr;
    static int[][] psum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1][n+1];
        psum = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                psum[i][j] = psum[i][j-1] + arr[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            int ans = 0;
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int j = x1; j <= x2; j ++){
                ans += (psum[j][y2] - psum[j][y1 - 1]);
            }
            System.out.println(ans);
        }

//        for(int[] row: psum) System.out.println(Arrays.toString(row));

    }
}
