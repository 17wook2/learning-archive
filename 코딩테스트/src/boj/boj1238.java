package boj;
import java.io.*;
import java.util.*;

public class boj1238 {
    private static final int INF = 1000000000;
    private static int n,m,x;
    private static List<List<Node>> list, revList;
    private static int[] dist, revDist;

    static class Node implements Comparable<Node>{
        int idx, distance;

        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        revList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            revList.add(new ArrayList<>());
        }

        dist = new int[n+1];
        revDist = new int[n+1];
        Arrays.fill(dist,INF);
        Arrays.fill(revDist,INF);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(u).add(new Node(v, weight));
            revList.get(v).add(new Node(u, weight));
        }

        dijkstra(list,dist,x);
        dijkstra(revList,revDist,x);

        print();
        br.close();
    }

    private static void dijkstra(List<List<Node>> list, int[] distance, int start){
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()){
            int idx = pq.poll().idx;
            if(visited[idx]) continue;
            visited[idx] = true;
            for (Node node: list.get(idx)){
                if(distance[node.idx] > distance[idx] + node.distance){
                    distance[node.idx] = distance[idx] + node.distance;
                    pq.add(new Node(node.idx, distance[node.idx]));
                }
            }
        }
    }

    private static void print(){
        int ans = -1;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i] + revDist[i]);
        }
        System.out.println(ans);
    }
}
