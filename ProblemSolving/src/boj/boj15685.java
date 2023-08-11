package boj;

import java.util.*;

public class boj15685 {
    static int n;
    static boolean[][] visited;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[101][101];
        for(int i = 0; i < n; i++){
            int x = sc.nextInt(); int y = sc.nextInt(); int d = sc.nextInt(); int g = sc.nextInt();
            int temp = x; x = y; y = temp;
            go(x,y,d,g);
            for (int q = 0; q < 10; q++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(visited[q][j] + " ");
                }
                System.out.println();
            }
//            for(boolean[] row: visited) System.out.println(Arrays.toString(row));
//            System.out.println();

        }

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

