package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj3109 {
    static int r, c, ans;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited = br.readLine().split(" ");
        r = Integer.parseInt(splited[0]);
        c = Integer.parseInt(splited[1]);
        arr = new char[r][c];
        ans = 0;
        for(int i = 0; i < r; i++) arr[i] = br.readLine().toCharArray();
        for(int i = 0; i < r; i++) {
            if (go(i,0)) ans += 1;
        }
        System.out.println(ans);
    }

    private static boolean go(int row, int col) {
        arr[row][col] = '-';
        if(col == c-1) return true;
        if(row > 0 && arr[row-1][col+1] == '.'){
            if(go(row-1, col+1)) return true;
        }
        if(arr[row][col+1] == '.'){
            if(go(row,col+1)) return true;
        }
        if(row + 1 < r && arr[row+1][col+1] == '.'){
            if(go(row+1,col+1)) return true;
        }
        return false;
    }


}
