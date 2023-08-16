package swea;

import java.util.Scanner;

public class Solution2806 {
    static int n,ans,col[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            ans = 0;
            n = sc.nextInt();
            col = new int[n+1];
            go(1);
            System.out.println("#"+ tc + " " + ans);
        }
    }

    private static void go(int row) {
        if (row == n + 1) {
            ans += 1;
            return;
        }
        for (int i = 1; i <= n; i++) {
            col[row] = i;
            if(isAvailable(row)) go(row+1);
        }

    }

    private static boolean isAvailable(int row) {
        for (int i = 1; i < row; i++) {
            if(col[i] == col[row] || row - i == Math.abs(col[row] - col[i])) return false;
        }
        return true;
    }
}
