package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16926 {

    static int n,m,r;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < r; i++) rotate();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void rotate(){
        int cycle = Math.min(n,m) / 2;
        for (int cy = 0; cy < cycle; cy ++) {
            int temp = arr[cy][cy];
            for(int j = cy; j < m-1-cy; j++){
                arr[cy][j] = arr[cy][j+1];
            }
            for(int i = cy; i < n-1-cy; i++){
                arr[i][m-1-cy] = arr[i+1][m-1-cy];
            }
            for(int j = m-1-cy; j > cy; j--){
                arr[n-1-cy][j] = arr[n-1-cy][j-1];
            }
            for(int i = n-1-cy; i > cy; i--){
                arr[i][cy] = arr[i-1][cy];
            }
            arr[cy+1][cy] = temp;
        }
    }
}
