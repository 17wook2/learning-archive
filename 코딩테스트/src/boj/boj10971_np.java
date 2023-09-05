package boj;

import java.util.Scanner;

public class boj10971_np {
    static int n,ans, w[][], arr[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) w[i][j] = sc.nextInt();
        }
        ans = Integer.MAX_VALUE;




        System.out.println(ans);
        // 2) next per


    }
}
