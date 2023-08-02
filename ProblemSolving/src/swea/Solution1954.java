package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution1954 {
    private static int n;
    private static int[][] arr;
    private static int[] dx = {0,1,0,-1};
    private static int[] dy= {1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            arr = new int[n][n];
            int start = 1;
            arr[0][0] = start;
            int x = 0, y = 0, d = 0;
            while (start < n * n) {
                while (true) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (check(nx, ny) && arr[nx][ny] == 0) {
                        start += 1;
                        arr[nx][ny] = start;
                        x = nx; y = ny;
                    }else{
                        d = (d+1) % 4;
                        break;
                    }
                }
            }
            System.out.println("#"+tc);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    private static boolean check(int a, int b){
        if (0 <= a && a < n && 0 <= b && b < n) {
            return true;
        }
        return false;
    }
}
