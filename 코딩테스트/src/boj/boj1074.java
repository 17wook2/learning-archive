package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj1074 {
    static int cur,r,c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        cur = 0;
        int length = (int) Math.pow(2,n);
        go(0,0,0,length);
    }

    private static void go(int x, int y, int cnt, int size) {
        if(x > r || r >= x +size || y > c || c >= y + size) return;
        if (size == 1) {
            if(x == r && y == c) System.out.println(cnt);
            return;
        }
        go(x,y,cnt + (size / 2 * size / 2*0) , size /2);
        go(x,y + size/2,cnt + (size / 2 * size / 2*1) , size /2);
        go(x+ size/2,y,cnt + (size / 2 * size / 2*2) , size /2);
        go(x+ size/2,y+ size/2,cnt + (size / 2 * size / 2*3) , size /2);
    }
}
