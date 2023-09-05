package defaultCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TopologicalSortBfs {
    static int N,M; //정점수 , 간선수
    static List<Integer>[] g;
    static int[] indegree; // 진입차수를 관리하는 배열 (선행 과목 수)

    static void topologicalSort() { // topology sort는 진입차수를 메모 했다가 진입 차수가 0인 것을 넣는다
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i < N+1; i++) {
            if(indegree[i] == 0) q.offer(i);
        }
        while(!q.isEmpty()) {
            int i = q.poll();
            System.out.print(i + " ");
            for(int j : g[i]) {
                if(--indegree[j] == 0) q.offer(j);
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        g = new List[N+1]; for(int i = 0; i < N+1; i++) g[i] = new ArrayList<>();
        indegree = new int[N+1];
        for(int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g[a].add(b);
            indegree[b] ++;
        }
        topologicalSort();

        sc.close();

    }


}
