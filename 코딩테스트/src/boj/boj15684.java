package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15684 {
    private static int[][] arr;
    private static int n,m,h;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        arr = new int[h][n];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a-1][b-1] = 1;
        }
        check();
        for(int size = 0; size <= 3; size++){
            if(go(0,0,size)){
                System.out.println(size);
                return;
            }
        }
        System.out.println(-1);

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

    private static boolean go(int idx, int cnt, int size) {
        if (cnt == size){
            if(check()) return true;
            return false;
        }
        for(int i = idx; i < h; i++){
            for(int j = 0; j < n-1; j++){
                if (arr[i][j] == 1) continue;
                if(j-1 >= 0 && arr[i][j-1] == 1) continue;
                arr[i][j] = 1;
                if(go(i,cnt+1,size)) return true;
                arr[i][j] = 0;
            }
        }
        return false;
    }
}
