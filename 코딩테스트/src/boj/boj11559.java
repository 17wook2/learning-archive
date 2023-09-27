package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class boj11559 {
    private static char[][] map;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for(int j = 0;  j < 6; j++){
                map[i][j] = line.charAt(j);
            }
        }
        int cnt = 0;
        while (true) {
            boolean check = go();
            if(!check) break;
            fall();
            cnt ++;
        }
//        for(char[] row : map) System.out.println(row);
//        System.out.println();
//        go();
//        for(char[] row : map) System.out.println(row);
//        System.out.println();
//        fall();
//        for(char[] row : map) System.out.println(row);
        System.out.println(cnt);

    }
    private static boolean go(){
        ArrayDeque<int[]> q = new ArrayDeque();
        int[][] arr = new int[12][6];
        int[] count = new int[100];
        int idx = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] == 0 && map[i][j] != '.') {
                    idx++;
                    q.add(new int[]{i, j});
                    arr[i][j] = idx;
                    int cnt = 1;
                    while (!q.isEmpty()) {
                        int[] poll = q.pop();
                        for (int k = 0; k < 4; k++) {
                            int nx = poll[0] + dx[k];
                            int ny = poll[1] + dy[k];
                            if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
                            if (arr[nx][ny] != 0) continue;
                            if(map[nx][ny] == map[poll[0]][poll[1]]){
                                arr[nx][ny] = idx;
                                cnt++;
                                q.add(new int[]{nx,ny});
                            }
                        }
                    }
                    count[idx] = cnt;
                }
            }
        }
        boolean check = false;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if(arr[i][j] != 0){
                    if(count[arr[i][j]] >= 4) {
                        map[i][j] = '.';
                        check = true;
                    }
                }
            }
        }
        return check;
    }
    private static void fall(){
        for (int j = 0; j < 6; j++) {
            for(int i = 10; i >= 0; i--){
                if(map[i][j] != '.'){
                    int x = i;
                    int y = j;
                    while (x < 11 && map[x+1][y] == '.'){
                        x++;
                    }
                    if(i != x){
                        map[x][y] = map[i][j];
                        map[i][j] = '.';
                    }
                }
            }
        }
    }
}
