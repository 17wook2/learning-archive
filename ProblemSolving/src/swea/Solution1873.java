package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution1873 {
    static int h,w,sx,sy,d;
    static char[][] arr;
    static char[] commands;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Map<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        map.put('U',0); map.put('R', 1); map.put('D',2); map.put('L',3);
        for(int tc = 1; tc <= t; tc++){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            arr = new char[h][w];
            for(int i = 0; i < h; i++){
                char[] row = br.readLine().toCharArray();
                for(int j = 0; j < w; j++) {
                    arr[i][j] = row[j];
                    if (row[j] == '^' || row[j] == '>' || row[j] == 'v' || row[j] == '<') {
                        sx = i; sy = j;
                        if(row[j] == '^') d = 0; if(row[j] == '>') d = 1; if(row[j] == 'v') d= 2; if(row[j] == '<') d = 3;
                        arr[i][j] = '.';
                    }
                }
            }
            int n = Integer.parseInt(br.readLine());
            commands = br.readLine().toCharArray();
            for(char command : commands) go(command);
            if(d == 0) arr[sx][sy] = '^'; if (d == 1) arr[sx][sy] = '>'; if(d == 2) arr[sx][sy] = 'v'; if(d==3) arr[sx][sy] = '<';
            System.out.print("#" + tc + " ");
            for (int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
    }

    private static void go(char command) {
        if(command == 'S'){
            int nx = sx; int ny = sy;
            while (true){
                nx += dx[d];
                ny += dy[d];
                if(!check(nx,ny)) return;
                if(arr[nx][ny] == '#') return;
                if (arr[nx][ny] == '*'){
                    arr[nx][ny] = '.';
                    return;
                }
            }
        } else{
            d = map.get(command);
            int nx = sx + dx[d];
            int ny = sy + dy[d];
            if(!check(nx,ny)) return;
            if (arr[nx][ny] == '.'){
                sx = nx; sy = ny;
            }
        }
    }

    private static boolean check(int x, int y) {
        if(x < 0 || x >= h || y < 0 || y >= w) return false;
        return true;
    }
}
