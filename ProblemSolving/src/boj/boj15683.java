package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class boj15683 {
    static int n, m,ans;
    static List<cctv> list;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        int[][] map = new int[n][m];
        list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                int k = sc.nextInt();
                if (1 <= k && k <= 5) list.add(new cctv(i,j,k));
                map[i][j] = k;
            }
        }
        ans = n*m + 1;
        simulate(map,0);
        System.out.println(ans);
    }
    private static void simulate(int[][] arr, int cnt){
        if(cnt == list.size()){
            int res = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++) if(arr[i][j] == 0) res += 1;
            }
            ans = Math.min(ans, res);
            return;
        }
        cctv cctv = list.get(cnt);
        int number = cctv.n;
        int x = cctv.x; int y = cctv.y;
        if(number == 1){
            for(int i = 0; i < 4; i++){
                int[][] copyArr = copyArr(arr);
                light(copyArr, x,y,i);
                simulate(copyArr,cnt+1);
            }
        }
        if(number == 2){
            int[][] copyArr = copyArr(arr);
            light(copyArr, x,y,0);
            light(copyArr, x,y,2);
            simulate(copyArr,cnt+1);
            copyArr = copyArr(arr);
            light(copyArr, x,y,1);
            light(copyArr, x,y,3);
            simulate(copyArr,cnt+1);
        }
        if(number == 3){
            for(int i = 0; i < 4; i++){
                int[][] copyArr = copyArr(arr);
                light(copyArr, x,y,i);
                light(copyArr, x,y,(i+1)%4);
                simulate(copyArr,cnt+1);
            }
        }
        if(number == 4){
            for(int i = 0; i < 4; i++){
                int[][] copyArr = copyArr(arr);
                light(copyArr, x,y,i);
                light(copyArr, x,y,(i+1)%4);
                light(copyArr, x,y,(i+2)%4);
                simulate(copyArr,cnt+1);
            }
        }
        if(number == 5){
            int[][] copyArr = copyArr(arr);
            for(int i = 0; i < 4; i++){
                light(copyArr, x,y,i);
            }
            simulate(copyArr,cnt+1);
        }


    }
    private static void light(int[][] arr, int x, int y, int d){
        while(true){
            x += dx[d]; y += dy[d];
            if(x < 0 || x >= n || y < 0 || y >= m || arr[x][y] == 6) break;
            arr[x][y] = -1;
        }
    }
    private static int[][] copyArr(int[][] arr){
        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
    static class cctv{
        int x; int y; int n;

        public cctv(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }
}
