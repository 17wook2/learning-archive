package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj19236 {
    static int n = 4,ans;
    static int[][] arr;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    static int[] fish_x;
    static int[] fish_y;
    static int[] fish_d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        fish_x = new int[17]; fish_y = new int[17]; fish_d = new int[17];
        arr = new int[4][4];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                int fnumber = Integer.parseInt(st.nextToken());
                int fdirection = Integer.parseInt(st.nextToken());
                arr[i][j] = fnumber;
                fish_x[fnumber] = i; fish_y[fnumber] = j; fish_d[fnumber] = fdirection-1;
            }
        }
        int fnumber = arr[0][0];
        fish_x[fnumber] = -1; fish_y[fnumber] = -1;
        ans = 0;
        arr[0][0] = -1; // 상어가 있는곳
        simulate(0,0,fish_d[fnumber],fnumber);


    }

    private static void simulate(int x, int y, int d, int sum) {
        int[][] new_arr = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) new_arr[i][j] = arr[i][j];
        }
        move();
        if(!isPossible(x,y,d)){ // 상어가 더이상 움직일 수 없다면
            ans = Math.max(ans, sum);
            return;
        }
        arr = new_arr;
        while (true) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] != 0){
                int k = arr[nx][ny]; // 물고기 먹음
                int fd = fish_d[k];
                arr[nx][ny] = -1;
                arr[x][y] = 0;
                fish_x[k] = -1; fish_y[k] = -1;
                simulate(nx,ny,fd, sum + k);
                arr[nx][ny] = k;
                arr[x][y] = -1;
                fish_x[k] = nx; fish_y[k] = ny; // 물고기 살려냄
                x = nx; y = ny;
            } else break;
        }
    }
    private static boolean isPossible(int x, int y, int d){ // 상어가 더이상 움직일 수 있는가
        int cnt = 0;
        while (true){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] != 0){
                cnt += 1;
                x = nx; y = ny;
            }else{
                break;
            }
        }
        if (cnt == 0) return false;
        return true;
    }

    private static void move() {

        for(int k = 1; k <= 16; k++){ // 1~16번까지 물고기 움직임
            int x = fish_x[k]; int y = fish_y[k]; int d = fish_d[k];
            if (x == -1 && y == -1) continue; // 해당 물고기가 죽은 경우
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] != -1){
                    int next = arr[nx][ny];
                    int temp = arr[x][y];
                    arr[x][y] = next;
                    arr[nx][ny] = temp;
                    fish_x[k] = nx; fish_x[y] = ny;
                    fish_x[next] = x; fish_y[next] = y;
                    break;
                }else{
                    d = (d + 1) % 8;
                    fish_d[k] = d;
                }
            }
        }
    }
}
