package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class boj13023 {
    static int N,M,ans;
    static List<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new List[N+1];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b); graph[b].add(a);
        }
        for(int i = 0; i < N; i++){
            visited = new boolean[N];
            visited[i] = true;
            dfs(i,0);
//            System.out.println(Arrays.toString(visited));
            if (ans == 1) break;
        }
        System.out.println(ans);

    }

    private static void dfs(int i, int depth){
        if(depth == 4){
            ans = 1;
            return;
        }
        for(int x: graph[i]){
            if (visited[x]) continue;
            visited[x] = true;
            dfs(x, depth + 1);
            if (ans == 1) return;
            visited[x] = false;
        }
    }
}

//depth가 5이상인거 체크
