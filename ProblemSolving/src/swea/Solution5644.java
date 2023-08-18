package swea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Solution5644 {
    static int m,a,ax,ay,bx,by,ans;
    static int[] userA, userB;
    static AP[] aps;
    static int[] dx = {0,-1,0,1,0};
    static int[] dy = {0,0,1,0,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            m = sc.nextInt(); a = sc.nextInt();
            userA = new int[m+1]; userB = new int[m+1];
            aps = new AP[8];
            ax = 0; ay = 0; bx = 9; by = 9;
            for(int i = 0; i < m; i++) userA[i] = sc.nextInt();
            for(int j = 0; j < m; j++) userB[j] = sc.nextInt();
            for(int i = 0; i < a; i++) {
                int x = sc.nextInt(); int y = sc.nextInt();
                int c = sc.nextInt(); int p = sc.nextInt();
                aps[i] = new AP(y-1,x-1,c,p);
            }
            userA[m] = 0; userB[m] = 0;
//            System.out.println(Arrays.toString(aps));
            ans = 0;
            for(int time = 0; time <= m; time ++){
                go(time);
            }
            System.out.println("#" + tc + " "+ ans);
        }
    }

    private static void go(int time) {
        ArrayList<Integer> aa = new ArrayList<>();
        ArrayList<Integer> ba = new ArrayList<>();
        for(int i = 0; i < a; i++){ // 충전소 개수만큼 반복문
            int cx = aps[i].x; int cy = aps[i].y;
            if(getDist(ax,ay,cx,cy) <= aps[i].c) aa.add(i);
            if(getDist(bx,by,cx,cy) <= aps[i].c) ba.add(i);
        }
//        System.out.println(time);
//        System.out.println(ax + " " + ay + " " + bx + " " + by);
//        System.out.println(Arrays.toString(aa));
//        System.out.println(Arrays.toString(ba));
        int max = 0;
        if(aa.size() == 0 && ba.size() == 0) max = 0;
        if (aa.size() == 0 && ba.size() != 0) for(int b: ba) max = Math.max(max, aps[b].p);
        if (aa.size() != 0 && ba.size() == 0) for(int b: aa) max = Math.max(max, aps[b].p);
        if (aa.size() != 0 && ba.size() != 0){
            for(int i=0;i<aa.size();i++) {
                for(int j=0;j<ba.size();j++) {
                    int a=aa.get(i);
                    int b=ba.get(j);
                    if(a==b) {
                        max=Math.max(max, aps[a].p);
                    }else {
                        max=Math.max(max, aps[a].p+aps[b].p);
                    }
                }
            }
        }


//        System.out.println(time + " " + max);
        ans += max;
        ax += dx[userA[time]];
        ay += dy[userA[time]];
        bx += dx[userB[time]];
        by += dy[userB[time]];
//        System.out.println();
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

        @Override
        public String toString() {
            return "AP{" +
                    "x=" + x +
                    ", y=" + y +
                    ", c=" + c +
                    ", p=" + p +
                    '}';
        }
    }

}
