package boj;

import java.util.Scanner;
import java.util.stream.IntStream;

public class boj15650 {
    private static int n,m;
    private static int[] a,b;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        b = new int[m];
        a = IntStream.range(1,n+1).toArray();
        go(0,0);
    }

    private static void go(int cnt, int idx) {
        if (cnt == m) {
            for(int i = 0; i < b.length; i ++) System.out.print(b[i]+" ");
            System.out.println();
            return;
        }
        for (int i = idx; i < n; i++) {
            b[cnt] = a[i];
            go(cnt+1, i+1);
        }
    }

}
