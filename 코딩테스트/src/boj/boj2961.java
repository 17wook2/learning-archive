package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class boj2961 {

    static int n,ans;
    static int[] a,b;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n];
        b = new int[n];
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        go(0,0,1,0);
        System.out.println(ans);

    }

    private static void go(int idx, int cnt, int left, int right) {
        if(idx == n){
            if (cnt >= 1) {
                ans = Math.min(ans, Math.abs(left - right));
            }
            return;
        }
        go(idx+1,cnt+1,left*a[idx], right+b[idx]);
        go(idx+1,cnt,left,right);
    }
}
