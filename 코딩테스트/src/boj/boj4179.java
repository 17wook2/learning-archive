package boj;
import java.io.*;
import java.util.*;

public class boj4179 {

    private static int r,c;
    private static char[][] map;
    private static Queue<Node> q= new LinkedList<>();
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    static class Node{
        int x; int y; int move;
        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'J') {
                    q.add(new Node(i,j,0));
                } else if (map[i][j] == 'F') {
                    q.add(new Node(i,j,-1));
                }
            }
        }
        int res = solve();
        if (res == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(res);
    }
    static int solve(){
        int ans = -1;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x; int y = node.y; int move = node.move;
            if (check(x, y) && move > -1 && map[x][y] != 'F') {
                ans = move + 1;
                return ans;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] != '#') {
                    if (move == -1 && map[nx][ny] != 'F') {
                        map[nx][ny] = 'F';
                        q.add(new Node(nx,ny,-1));
                    } else if (move > -1 && map[nx][ny] == '.') {
                        map[nx][ny] = '_';
                        q.add(new Node(nx,ny,move + 1));
                    }
                }
            }
        }
    return ans;
    }
    static boolean check(int x,int y){
        if (x == 0 || x == r - 1 || y == 0 || y == c - 1) {
            return true;
        }else{
            return false;
        }
    }

}

