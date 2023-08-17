package swea;

import java.util.Map;
import java.util.Scanner;

public class Solution5644 {
    static int m,a,ax,ay,bx,by;
    static int[][] arr;
    static int[] userA, userB;
    static AP[] aps;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            arr = new int[11][11];
            m = sc.nextInt(); a = sc.nextInt();
            userA = new int[m]; userB = new int[m];
            aps = new AP[8];
            ax = 0; ay = 0; bx = 9; by = 9;
            for(int i = 0; i < m; i++) userA[i] = sc.nextInt();
            for(int j = 0; j < m; j++) userB[j] = sc.nextInt();
            for(int i = 0; i < a; i++) {
                int x = sc.nextInt(); int y = sc.nextInt();
                int c = sc.nextInt(); int p = sc.nextInt();
                arr[y][x] = i;
                aps[i] = new AP(x,y,c,p);
            }

        }
    }

    private static void go() {
        boolean[] aa = new boolean[a];
        boolean[] ba = new boolean[a];
        for(int i = 0; i < a; i++){ // 충전소 개수만큼 반복문
            int cx = aps[i].x; int cy = aps[i].y;
            if(getDist(ax,ay,cx,cy) <= aps[i].c) aa[i] = true;
            if(getDist(bx,by,cx,cy) <= aps[i].c) ba[i] = true;
        }
        int max = 0;
        for(int i = 0; i < a; i++){
            for(int j = 0; j < a; j++){

            }
        }

    }

    private static int getDist(int x, int y, int a, int b) {
        return Math.abs(x - a) + Math.abs(y - b);
    }

    static class AP{
        int x;
        int y;
        int c;
        int p;

        AP(int x, int y,int c, int p){
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }
}
