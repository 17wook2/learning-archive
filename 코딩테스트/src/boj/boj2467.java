package boj;
import java.io.*;
import java.util.*;

public class boj2467 {
    private static final int INF = 1000000000;
    private static int n, ans_s, ans_e;
    private static int[] arr;
    private static int target = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = 0; int e = n-1;
        while (s < e){
            int v = arr[s] + arr[e];
            int diff = Math.abs(v);
            if (diff <= target) {
                target = diff;
                ans_s = s; ans_e = e;
            }
            if (v < 0) s += 1;
            else if (v > 0) e -= 1;
            else break;
        }
        System.out.println(arr[ans_s]+ " " +arr[ans_e]);
    }

}

