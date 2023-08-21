package boj;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;

public class boj2252 {
    static int n,m;
    static int[] indegree;
    static Node[] g;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        g = new Node[n+1];
        indegree = new int[n+1];
        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            g[a] = new Node(b, g[a]);
            indegree[b]++;
        }
        bfs();
    }
    private static void bfs(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i < n+1; i++) if(indegree[i] == 0) q.offer(i);
        while (!q.isEmpty()){
            int i = q.poll();
            System.out.print(i + " ");
            for(Node x = g[i]; x != null; x = x.link){
                if(--indegree[x.vertex] == 0) q.add(x.vertex);
            }
        }

    }
    static class Node{
        int vertex;
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }
    }
}
