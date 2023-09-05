package boj;

import java.util.Scanner;

public class boj1182 {
    static int n,s,ans;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.nextInt();
        arr = new int[n];
        visited = new boolean[n];
        ans = 0;
        for(int i = 0; i <n ;i++) arr[i] = sc.nextInt();
        go(0,0,0);
        System.out.println(ans);
    }

    private static void go(int idx, int sum, int cnt) {
        if(idx == n){
            if(cnt >= 1 && sum == s) ans += 1;
            return;
        }
        visited[idx] = true;
        go(idx + 1, sum + arr[idx], cnt+1);
        visited[idx]  = false;
        go(idx + 1,sum , cnt);
    }
}
