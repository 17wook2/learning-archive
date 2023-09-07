import java.util.*;

class Solution {
    static PriorityQueue<int[]> pq;
    static boolean[] visited;
    static int[] dist;
    static int[][] g;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
        visited = new boolean[N+1];
        dist = new int[N+1];
        g = new int[N+1][N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        for(int i = 0; i < road.length; i++){
            int start = road[i][0];
            int end = road[i][1];
            int c = road[i][2];
            if(g[start][end] > 0){
                c = Math.min(c,g[start][end]);
            }
            g[start][end] = c;
            g[end][start] = c;
        }
        dist[1] = 0;
        pq.offer(new int[]{1,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cur_v = cur[0];
            int cur_d = cur[1];
            if(visited[cur_v]) continue;
            visited[cur_v] = true;
            // if(cur_v == N) break;
            for(int j = 1; j <= N; j++){
                if(!visited[j] && g[cur_v][j] != 0 && dist[j] > cur_d + g[cur_v][j]){
                    dist[j] = cur_d + g[cur_v][j];
                    pq.offer(new int[]{j,dist[j]});
                }
            }
        }
        // System.out.println(Arrays.toString(dist));
        for(int i = 1; i <= N; i++){
            if(dist[i] <= K) answer++;
        }
        return answer;
    }
}