package boj;
import java.io.*;
import java.util.*;

public class main {

    private static int[][] arr;
    private static int n,m,h,ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        ans = 4;
        arr = new int[h][n];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a-1][b-1] = 1;
        }
        check();
        go(0,0);
        if(ans == 4){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }

    }
    private static boolean check(){
        for(int i = 0; i < n; i++){
            int move = i;
            for(int x = 0; x < h; x++){
                if (move < n && arr[x][move] == 1) move += 1;
                else if (move - 1 >= 0 && arr[x][move-1] == 1) move -= 1;
            }
            if(move != i) return false;
        }
        return true;
    }

    private static void go(int idx, int cnt) {
        if (cnt >= ans) return;
        if(check()) {
            ans = cnt;
            return;
        }
        for(int i = idx; i < h; i++){
            for(int j = 0; j < n; j++){
                if (arr[i][j] == 1) continue;
                if(j-1 >= 0 && arr[i][j-1] == 1) continue;
                arr[i][j] = 1;
                go(i,cnt+1);
                arr[i][j] = 0;
            }
        }
    }

}

