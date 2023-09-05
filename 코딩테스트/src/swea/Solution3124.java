package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution3124 {

    static int v,e;
    static StringTokenizer st;
    static int[] p;
    static Edge[] edgeList;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            // Kruskal (간선 중심)
            edgeList = new Edge[e];
            for(int i = 0; i < e; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from,to,weight);
            }
            long ans = kruskal();
            System.out.println("#" + tc + " " + ans);


        }
    }
    private static void makeSet(){
        p = new int[v+1];
        for(int i = 0; i < v; i++) p[i] = i;
    }
    private static int find(int a){
        if(a == p[a]) return a;
        else return p[a] = find(p[a]);
    }
    private static boolean union(int a, int  b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        p[bRoot] = aRoot;
        return true;
    }

    private static long kruskal(){
        Arrays.sort(edgeList, Comparator.comparingInt(v -> v.weight)); // 간선 정렬
        makeSet(); // p 배열 초기화
        long result = 0; // 최소 스패닝 결과
        int cnt = 0;
        for(Edge edge: edgeList){
            if(union(edge.from, edge.to)){
                result += edge.weight;
                if(++cnt == v-1) break;
            }
        }
        return result;
    }
    static class Edge{
        int from,to,weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
