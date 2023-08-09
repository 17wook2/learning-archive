package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj2563 {
    static boolean[][] a;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new boolean[101][101];
        int ans = 0;
        for (int k = 0; k < n; k++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    a[i][j] = true;
                }
            }
        }
        for(int i = 0; i < 101; i++){
            for(int j = 0; j < 101; j++){
                if(a[i][j]) ans += 1;
            }
        }
        System.out.println(ans);

    }
}
