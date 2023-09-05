package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1992 {
    static int n;
    static int[][] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for(int j = 0; j <n ;j++) arr[i][j] = Integer.parseInt(split[j]);
        }
        go(0,0,n);
        System.out.println(sb);
    }

    private static void go(int x, int y, int size) {
        int sum = 0;
        for (int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++){
                sum += arr[i][j];
            }
        }
        if (sum == 0) {
            sb.append(0);
        }else if(sum == size*size){
            sb.append(1);
        }else{
            sb.append("(");
            go(x, y, size / 2);
            go(x, y+size / 2, size / 2);
            go(x+size / 2, y, size / 2);
            go(x+size/2, y+size / 2, size / 2);
            sb.append(")");
        }
    }
}
