package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution4008 {
    static int t,n,left,right;
    static int[] op, arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++){
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            op = new int[4];
            arr = new int[n];
            for(int i = 0; i < 4; i++) op[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
            left = Integer.MAX_VALUE;
            right = Integer.MIN_VALUE;
            go(0,arr[0]);
            System.out.println("#"+tc+" "+(right - left));
        }


    }
    private static void go(int cnt, int sum){
        if (cnt == n-1){
            left = Math.min(left, sum);
            right = Math.max(right, sum);
            return;
        }
        for(int i = 0; i < 4; i++){
            if(op[i] > 0){
                op[i] -= 1;
                if(i == 0) go(cnt+1,sum + arr[cnt+1]);
                if(i == 1) go(cnt+1, sum - arr[cnt+1]);
                if(i == 2) go(cnt+1, sum * arr[cnt+1]);
                if(i == 3) go(cnt +1, sum / arr[cnt+1]);
                op[i] += 1;
            }
        }
    }
}
