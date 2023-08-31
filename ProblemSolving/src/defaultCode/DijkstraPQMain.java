package defaultCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraPQMain {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] dist = new int[N]; // 다익스트라 w[]
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				g[i][j] = sc.nextInt();
			}
			dist[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
		dist[0] = 0;
		pq.offer(new int[]{0,0}); // 정점, 가중치
		while(!pq.isEmpty()) { // 방문 안한 정점 찾기
			int[] cur = pq.poll();
			int minVertex = cur[0];
			int min = cur[1];
			if(v[minVertex]) continue;
			v[minVertex] = true;
			if(minVertex==N-1) break;
			for(int j = 0; j < N; j++) {
				if(!v[j] && g[minVertex][j] != 0 && dist[j] > min + g[minVertex][j]) {
					dist[j] = min + g[minVertex][j];
					pq.offer(new int[] {j, dist[j]});
				}
			}
			System.out.println(Arrays.toString(dist));
			System.out.println("====");
		}
		System.out.println(dist[N-1]);
		sc.close();
	}

}
