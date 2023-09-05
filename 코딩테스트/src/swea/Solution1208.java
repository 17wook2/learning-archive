package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1208 {

    private static int t;
    private static int[] arr;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_d3_1208.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new int[100];
        for (int tc = 1; tc <= 10; tc++) {
            t = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for(int i  = 0; i < t; i++){
                Arrays.sort(arr);
                arr[0] += 1;
                arr[99] -= 1;
            }
            Arrays.sort(arr);
            System.out.println("#"+tc+" "+(arr[99]-arr[0]));

        }

    }
}