package boj;

import java.util.*;

public class boj15685 {
    static int n, ans;
    static boolean[][] visited;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[101][101];
        ans = 0;
        for(int i = 0; i < n; i++){
            int x = sc.nextInt(); int y = sc.nextInt(); int d = sc.nextInt(); int g = sc.nextInt();
            int temp = x; x = y; y = temp;
            go(x,y,d,g);
        }
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]) ans += 1;
            }
        }
        System.out.println(ans);
    }

    private static void go(int x, int y, int d, int g) {
        ArrayList<Integer> list = new ArrayList<>();
        visited[x][y] = true;
        x += dx[d]; y += dy[d];
        visited[x][y] = true;
        list.add((d+1) % 4);
        for (int level = 0; level < g; level++) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int cur_direction = list.get(size-1-i);
                x += dx[cur_direction];
                y += dy[cur_direction];
                visited[x][y] = true;
                list.add((cur_direction + 1) % 4);
            }
        }
    }

}

