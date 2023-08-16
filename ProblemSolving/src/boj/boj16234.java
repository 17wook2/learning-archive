package boj;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class boj16234 {
    static int n,l,r, arr[][];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); l = sc.nextInt(); r = sc.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) arr[i][j] = sc.nextInt();
        }
        int ans = 0;
        while (simulate()){
            ans += 1;
        }
        System.out.println(ans);
    }
    private static boolean simulate(){
        int[][] temp = new int[n][n];
        int cnt = 1;
        int[] area = new int[3000];
        int[] count = new int[3000];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++){
                if(temp[i][j] == 0){
                    temp[i][j] = cnt;
                    area[cnt] += arr[i][j];
                    count[cnt] += 1;
                    q.add(new int[]{i,j});
                    while (!q.isEmpty()) {
                        int[] node = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = node[0] + dx[k];
                            int ny = node[1] + dy[k];
                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                            if (temp[nx][ny] != 0) continue;
                            int dist = Math.abs(arr[node[0]][node[1]] - arr[nx][ny]);
                            if (l <= dist && dist <= r) {
                                temp[nx][ny] = cnt;
                                count[cnt] += 1;
                                area[cnt] += arr[nx][ny];
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }
                    cnt += 1;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int cur = temp[i][j];
                arr[i][j] = area[cur] / count[cur];
            }
        }
        if(cnt == (n*n)+1) return false;
        return true;
//        for(int[] row: arr) System.out.println(Arrays.toString(row));
    }
}
