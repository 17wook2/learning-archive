package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj19236 {
    static int n = 4,ans;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Fish[] fishes = new Fish[17];
        int[][] arr = new int[4][4];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                int fn = Integer.parseInt(st.nextToken());
                int fd = Integer.parseInt(st.nextToken());
                arr[i][j] = fn;
                Fish fish = new Fish(i, j, fd - 1, true);
                fishes[fn] = fish;
            }
        }
        int fn = arr[0][0];
        int fd = fishes[fn].d;
        fishes[fn].isAlive = false;
        ans = fn;
        arr[0][0] = -1; // 상어가 있는곳
        simulate(arr,fishes,0,0,fd,fn);
        System.out.println(ans);
    }

    private static void simulate(int[][] arr, Fish[] fishes, int x, int y, int d, int sum) {
        ans = Math.max(ans, sum);
        move(arr, fishes);
        for(int k = 1; k <= 4; k++){
            int nx = x + dx[d] * k;
            int ny = y + dy[d] * k;
            if(0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] != 0){ // 움직일 수 있는 곳
                int[][] newArr = copyArr(arr);
                Fish[] newFishes = copyFish(fishes);
                int cur = newArr[nx][ny];
                newArr[x][y] = 0;
                newArr[nx][ny] = -1;
                newFishes[cur].isAlive = false;
                int cur_d = newFishes[cur].d;
                simulate(newArr, newFishes, nx, ny, cur_d, sum + cur);
            }
        }
    }
    private static int[][] copyArr(int[][] arr){
        int[][] temp = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
    private static Fish[] copyFish(Fish[] fishes){
        Fish[] temp = new Fish[17];
        for(int i = 1; i < 17; i++){
            temp[i] = new Fish(fishes[i].x, fishes[i].y,fishes[i].d,fishes[i].isAlive);
        }
        return temp;
    }

    private static void move(int[][] arr, Fish[] fishes) {
        for(int k = 1; k <= 16; k++){ // 1~16번까지 물고기 움직임
            Fish fish = fishes[k];
            int x = fish.x; int y = fish.y; int d = fish.d;
            if (!fish.isAlive) continue; // 해당 물고기가 죽은 경우
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] != -1){
                    int next = arr[nx][ny];
                    int temp = arr[x][y];
                    arr[x][y] = next;
                    arr[nx][ny] = temp;
                    fishes[k].x = nx; fishes[k].y = ny;
                    if(next != 0) {
                        fishes[next].x = x; fishes[next].y = y;
                    }
                    break;
                }else{
                    d = (d + 1) % 8;
                    fishes[k].d = d;
                }
            }
        }
    }
    static class Fish{
        int x;
        int y;
        int d;
        boolean isAlive;
        public Fish(int x, int y, int d, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.isAlive = isAlive;
        }
    }
}
