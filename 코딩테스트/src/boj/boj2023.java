package boj;

import java.util.Scanner;

public class boj2023 {
    static int n;
    static int[] a;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n];
        go(0);
    }

    private static void go(int cnt) {
        if (cnt == n) {
            for(int i = 0; i <n; i++) System.out.print(a[i]);
            System.out.println();
            return;
        }
        for(int i = 0; i < 10; i++){
            a[cnt] = i;
            if(check(cnt)) go(cnt+1);
        }
    }

    private static boolean check(int idx) {
        int x = 0;
        for(int i = idx; i >= 0; i--){
            x += a[i] * Math.pow(10, idx-i);
        }
        if(x < 2) return false;
        for (int i = 2; i * i <= x; i++) {
            if(x % i == 0) return false;
        }
        return true;
    }
}
