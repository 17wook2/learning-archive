package boj;
import java.io.*;
import java.util.*;

public class boj1806 {
    private static final int INF = 1000000000;
    private static int n,target;
    private static int[] arr;
    private static int ans = 1000001;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0; int e = 0; int prefix_sum = 0;
        while (true) {
            if (prefix_sum >= target) {
                prefix_sum -= arr[s];
                ans = Math.min(ans,e-s);
                s += 1;
            } else if (e == n) {
                break;
            }else{
                prefix_sum += arr[e];
                e += 1;
            }
        }
        if (ans == 1000001) {
            System.out.println(0);
        } else{
            System.out.println(ans);
        }

    }

}

