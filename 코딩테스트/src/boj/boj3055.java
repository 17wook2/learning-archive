package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj3055 {
    private static int r,c,sx,sy;
    private static char[][] map;
    private static List<int[]> wlist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        wlist = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                char cur = split[j].charAt(0);
                if(cur == 'S'){
                    sx = i; sy = j;
                }else if(cur == '*'){
                    wlist.add(new int[]{i, j});
                }

            }
        }

    }
}
