package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj9328 {

    static int r,c;
    static char[][] map;
    static boolean[] key;
    static ArrayList<Point>[] gates;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            String[] inputMap = br.readLine().split(" ");
            r = Integer.parseInt(inputMap[0]);
            c = Integer.parseInt(inputMap[1]);
            map = new char[r + 2][c + 2];
            key = new boolean[26];
            visited = new boolean[r + 2][c + 2];
            gates = new ArrayList[26];
            ans = 0;
            for (int i = 0; i < 26; i++) {
                gates[i] = new ArrayList<>();
            }
            for (int i = 0; i < r+2; i++) {
                for (int j = 0; j < c+2; j++) {
                    map[i][j] = '.';
                }
            }
            for (int i = 1; i <= r; i++) {
                String input = br.readLine();
                for (int j = 1; j <= c; j++) {
                    map[i][j] = input.charAt(j - 1);
                }
            }
            String keyInput = br.readLine();
            if (!keyInput.equals("0")) {
                for (int i = 0; i < keyInput.length(); i++) {
                    int temp = keyInput.charAt(i) - 'a';
                    key[temp] = true;
                }
            }
            bfs();
            System.out.println(ans);

        }

    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= r+ 2 || ny >= c+2){
                    continue;
                }
                if (map[nx][ny] == '*' || visited[nx][ny]){
                    continue;
                }
                int elem = map[nx][ny];
                if (elem - 'A' >= 0 && elem - 'A' <= 25){
                    if(key[elem - 'A']){
                        map[nx][ny] = '.';
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                    }else{
                        gates[elem - 'A'].add(new Point(nx,ny));
                    }
                }else if(elem - 'a' >= 0 && elem - 'a' <= 25){
                    key[elem - 'a'] = true;
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                    for (int j = 0; j < 25; j++) {
                        if (gates[j].size() != 0 && key[j]) {
                            for (int z = 0; z < gates[j].size(); z++) {
                                Point temp = gates[j].get(z);
                                map[temp.x][temp.y] = '.';
                                visited[temp.x][temp.y] = true;
                                q.add(new Point(temp.x,temp.y));
                            }
                        }
                    }
                }else if(elem == '$'){
                    ans ++;
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }else{
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }
            }

        }

    }

    static class Point{
        int x; int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
