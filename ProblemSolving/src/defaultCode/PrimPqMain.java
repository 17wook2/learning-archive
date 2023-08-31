package defaultCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimPqMain {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] minEdge = new int[N]; // 프림 w[]
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				g[i][j] = sc.nextInt();
			}
			minEdge[i] = Integer.MAX_VALUE;
		}
		int result = 0, cnt = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
		minEdge[0] = 0;
		pq.offer(new int[]{0,0}); // 정점, 가중치
		while(!pq.isEmpty()) { // 방문 안한 정점 찾기
			int[] cur = pq.poll();
			int minVertex = cur[0];
			int min = cur[1];
			if(v[minVertex]) continue;
			v[minVertex] = true;
			result += min;
			if(cnt++ == N-1) break;
			for(int j = 0; j < N; j++) {
				if(!v[j] && g[minVertex][j] != 0 && minEdge[j] > g[minVertex][j]) {
					minEdge[j] = g[minVertex][j];
					pq.offer(new int[] {j, minEdge[j]});
				}
			}
			System.out.println(Arrays.toString(minEdge));
			System.out.println("====");
		}
		System.out.println(result);
		sc.close();
	}

}
