package boj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class boj1931 {
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new int[n][2];
        for(int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            arr[i] = new int[]{start, end};
        }
        Arrays.sort(arr, Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt(o -> o[0]));
        int cur = 0;
        int ans = 1;
        for(int i = 1; i < n; i++){
            if (arr[i][0] >= arr[cur][1]) {
                cur = i;
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
