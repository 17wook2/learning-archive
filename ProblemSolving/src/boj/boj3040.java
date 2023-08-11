package boj;

import java.util.Scanner;

public class boj3040 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[9];
        int[] b = new int[9];
        for(int i = 0; i < 9; i++) a[i] = sc.nextInt();
        for(int i = 2; i < 9; i++) b[i] = 1;
        do{
            int s = 0;
            for(int i = 0; i < 9; i++) {
                if(b[i] == 1) s += a[i];
            }
            if(s == 100){
                for(int i = 0; i < 9; i++){
                    if(b[i] == 1) System.out.println(a[i]);
                }
            }
        }while (np(b));
    }

    private static boolean np(int[] p) {
        int i = 8;
        while ( i > 0 && p[i-1] >= p[i]) i--;
        if(i == 0) return false;

        int j = 8;
        while(p[i-1] >= p[j]) j --;
        swap(p, i-1, j);

        int k = 8;
        while(i < k) swap(p, i++, k--);
        return true;
    }

    private static void swap(int[] p, int a, int b){
        int t = p[a];
        p[a] = p[b];
        p[b] = t;
    }
}
