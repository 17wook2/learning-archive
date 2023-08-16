package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution11446 {
    static long[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = sc.nextInt();
            long m = sc.nextLong();
            arr = new long[n];
            for(int i = 0; i < n; i++) arr[i] = sc.nextLong();
            Arrays.sort(arr);
            long start = 0;
            long end = arr[arr.length-1]+1;
            long mid;
            while(start + 1 < end){
                mid = (start + end) / 2; // 가방의 갯수
                long cnt = 0;
                for(int i = 0; i < n; i++) cnt += (arr[i] / mid);
                if(cnt >= m) start = mid;
                else end = mid;
            }
            System.out.println("#" + tc+ " "+ start);
        }
    }
}
