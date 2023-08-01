package boj;
import java.io.*;
import java.util.*;

public class boj5972 {
    private static int n,m,x;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    private static int[] distance;

    static class Node implements Comparable<Node>{
        int idx, distance;
        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[n+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[1] = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b,c));
            graph.get(b).add(new Node(a,c));
        }
        dijkstra(1);
        System.out.println(distance[n]);

    }
    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        while (!pq.isEmpty()){
            Node now = pq.poll();
            int dist = now.distance;
            int idx = now.idx;
            for (Node node: graph.get(idx)){
                int cur_dist = node.distance;
                int cur_des = node.idx;
                if (distance[cur_des] > dist + cur_dist){
                    distance[cur_des] = dist + cur_dist;
                    pq.add(new Node(cur_des, distance[cur_des]));
                }
            }
        }

    }


}

