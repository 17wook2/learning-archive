package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16935 {
    static int n,m,r;
    static int[][] arr;
    static int[] commands;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        commands = new int[r];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) commands[i] = Integer.parseInt(st.nextToken());

        for(Integer command: commands) go(command);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
//        for(int[] row: arr) System.out.println(Arrays.toString(row));
        System.out.println(sb.toString());
    }

    private static void go(int command) {
        int row = arr.length;
        int col = arr[0].length;
        if (command == 1) {
            int[][] new_array = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) new_array[i][j] = arr[row-1-i][j];
            }
            arr = new_array;
        }
        if (command == 2) {
            int[][] new_array = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) new_array[i][j] = arr[i][col-1-j];
            }
            arr = new_array;
        }
        if (command == 3) {
            int[][] new_array = new int[col][row];
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) new_array[i][j] = arr[row-1-j][i];
            }
            arr = new_array;
        }
        if (command == 4) {
            int[][] new_array = new int[col][row];
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) new_array[i][j] = arr[j][col-1-i];
            }
            arr = new_array;
        }
        if (command == 5) {
            int[][] new_array = new int[row][col];
            for (int i = 0; i < row / 2; i++) {
                for (int j = 0; j < col / 2; j++) {
                    new_array[i][j] = arr[row/2 + i][j];
                }
                for(int j = col / 2; j < col; j++){
                    new_array[i][j] = arr[i][j - col/2];
                }
            }
            for (int i = row / 2; i < row; i++) {
                for (int j = 0; j < col / 2; j++) {
                    new_array[i][j] = arr[i][col/2 + j];
                }
                for(int j = col / 2; j < col; j++){
                    new_array[i][j] = arr[i - row/2][j];
                }
            }
            arr = new_array;
        }
        if (command == 6) {
            int[][] new_array = new int[row][col];
            for (int i = 0; i < row / 2; i++) {
                for (int j = 0; j < col / 2; j++) {
                    new_array[i][j] = arr[i][j + col/2];
                }
                for(int j = col / 2; j < col; j++){
                    new_array[i][j] = arr[i + row/2][j];
                }
            }
            for (int i = row / 2; i < row; i++) {
                for (int j = 0; j < col / 2; j++) {
                    new_array[i][j] = arr[i - row/2][j];
                }
                for(int j = col / 2; j < col; j++){
                    new_array[i][j] = arr[i][j - col/2];
                }
            }
            arr = new_array;
        }
    }
}