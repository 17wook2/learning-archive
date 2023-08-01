package boj;
import java.io.*;
import java.util.*;

public class boj1253 {
    private static final int INF = 1000000000;
    private static int n;
    private static int[] list;
    private static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        list = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(list);
        for (int i = 0; i <n; i++) {
            solve(i);
        }
        System.out.println(ans);
    }

    static void solve(int idx) {
        int s = 0;
        int e = list.length -1;
        int value = list[idx];
        while (s < e) {
            int p_sum = list[s] + list[e];
            if (s == idx) {
                s += 1;
                continue;
            }
            if (e == idx) {
                e -= 1;
                continue;
            }
            if (p_sum == value) {
                ans += 1;
                return;
            }
            if (p_sum<value) s += 1;
            else if(p_sum>value) e -= 1;
        }
    }

}

