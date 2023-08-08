package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution9229 {
    static int n, m,ans;
    static int[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ans = -1;
            go(0,0,0);
            System.out.println("#"+tc+" "+ans);
        }

    }

    private static void go(int cnt, int start, int sum) {
        if (cnt == 2) {
            if (sum <= m) ans = Math.max(ans, sum);
            return;
        }
        for (int i = start; i < n; i++) go(cnt+1, i+1, sum + a[i]);
    }
}
