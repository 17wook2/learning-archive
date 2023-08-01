package swea;

import java.io.*;
import java.util.*;


public class Solution1247 {

    private static int t,n,sx,sy,ex,ey;
    private static boolean[] visited;
    private static ArrayList<Node> v;
    private static ArrayList<Node> customers;
    private static int answer;
    static class Node {
        int x,y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            answer = 100000000;
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());
            visited = new boolean[n + 1];
            v = new ArrayList<>();
            customers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                customers.add(new Node(cx, cy));
            }
            go(0,sx,sy,0);
            System.out.println("#"+ (tc+1) +" "+answer);
        }

    }
    private static int getDistance(int x, int y, int a, int b) {
        return Math.abs(x - a) + Math.abs(y - b);
    }

    private static void check(int distance) {
        distance += getDistance(ex,ey,v.get(n-1).x,v.get(n-1).y);
        if (distance < answer) {
            answer = distance;
        }
    }

    private static void go(int cnt, int px, int py, int dist) {
        if (dist > answer) return;
        if (cnt == n) {
            check(dist);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            v.add(customers.get(i));
            int cur_dist = getDistance(px,py,customers.get(i).x, customers.get(i).y);
            go(cnt+1,customers.get(i).x, customers.get(i).y, dist + cur_dist);
            v.remove(v.size()-1);
            visited[i] = false;
        }
    }
}
