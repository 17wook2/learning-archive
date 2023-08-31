package defaultCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DijkstraMain {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] dist = new int[N]; // 다익스트라 d[]
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				g[i][j] = sc.nextInt();
			}
		}
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		for(int i = 0; i < N; i++) { // 방문 안한 정점 찾기
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < N; j++) {
				if(!v[j] && min > dist[j]) {
					min = dist[j];
					minVertex = j;
				}
			}
			v[minVertex] = true;
			System.out.println(Arrays.toString(v));
			System.out.println("minVertex = " + minVertex);
			System.out.println("min= " + min);
			if(minVertex == N-1) break;
			
			for(int j = 0; j < N; j++) { 
				if(!v[j] && g[minVertex][j] != 0 && dist[j] > min + g[minVertex][j]) {
					dist[j] = min + g[minVertex][j];
				}
			}
			System.out.println(Arrays.toString(dist));
			System.out.println("====");
		}
		System.out.println(dist[N-1]);
		sc.close();
	}

}
