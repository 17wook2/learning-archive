package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1233 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            n = Integer.parseInt(br.readLine());
            int ans = 1;
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                char node = st.nextToken().charAt(0);
                if (st.hasMoreTokens()) {
                    if (node >= '0' && node <= '9') ans = 0;
                } else {
                    if (node < '0' || node > '9') ans = 0;
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}