package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution10580 {
    static int[][] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            a = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                a[i][0] = Integer.parseInt(st.nextToken());
                a[i][1] = Integer.parseInt(st.nextToken());
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                int start = a[i][0];
                int end = a[i][1];
                for (int j = i+1; j < n; j++) {
                    int cur_start = a[j][0];
                    int cur_end = a[j][1];
                    if(start < cur_start && end > cur_end) ans += 1;
                    if(start > cur_start && end < cur_end) ans += 1;
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}
