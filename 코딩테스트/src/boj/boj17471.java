package boj;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class boj17471 {
    static int n,ans;
    static boolean[] area;
    static int[] population;
    static Node[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        population = new int[n];
        graph = new Node[n];
        for(int i = 0; i < n; i++) population[i] = sc.nextInt();
        for(int i = 0; i < n; i++){
            int c = sc.nextInt();
            for(int j = 0; j < c; j++){
                int x = sc.nextInt()-1;
                graph[i] = new Node(x,graph[i]);
            }
        }
//        for(Node x : graph) System.out.println(x);
        ans = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++){
            area = new boolean[n];
            comb(0,0,i);
        }
        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    private static void comb(int cnt, int start, int size) {
        if(cnt == size){
//            System.out.println(size);
//            System.out.println(Arrays.toString(area));
            int left = go(false);
            int right = go(true);
//            System.out.println(left + " " + right);
//            System.out.println();
            if(left == -1 || right == -1) return;
            ans = Math.min(ans,Math.abs(left - right));
            return;
        }
        for(int i = start; i < n; i++){
            area[i] = true;
            comb(cnt+1,i+1,size);
            area[i] = false;
        }

    }
    private static int go(boolean check){
        boolean[] v = new boolean[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int cnt = 0;
        int sum = 0;
        for(int i = 0; i < n; i++){
            if(area[i] == check && !v[i]){
                q.offer(i);
                v[i] = true;
                cnt += 1;
                while (!q.isEmpty()) {
                    int x = q.poll();
                    sum += population[x];
//                    System.out.println(population[x]);
                    for(Node j = graph[x]; j != null; j = j.link){
                        if(!v[j.vertex] && area[j.vertex] == check){
                            v[j.vertex] = true;
                            q.offer(j.vertex);
                        }
                    }
                }
            }
        }
        if(cnt == 1) return sum;
        else return -1;
    }

    static class Node{
        int vertex;
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", link=" + link +
                    '}';
        }
    }

}
