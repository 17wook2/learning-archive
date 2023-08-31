package defaultCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PrimMain {
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
		}
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		int result = 0, cnt = 0;
		minEdge[0] = 0;
		for(int i = 0; i < N; i++) { // 방문 안한 정점 찾기
			
			// 비트리 중에 간선 비용이 최소인 vertex 찾기
			// minEdge는 해당 vertex를 트리에 넣을 시 비용으로 생각하면 된다. 
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < N; j++) {
				if(!v[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			v[minVertex] = true;
			result += min;
			System.out.println(Arrays.toString(v));
			System.out.println("minVertex = " + minVertex);
			System.out.println("min= " + min);
			System.out.println("result= " + result);
			if(cnt++ == N-1) break; // MST 완성했다면 break
			
			for(int j = 0; j < N; j++) { // 현재 선택한 정점의 간선들을 살펴보며 1) 방문하지 않았고 2) 연결되어 있고 3) 비용이 더 작다면 간선 비용 업데이트 하기
				if(!v[j] && g[minVertex][j] != 0 && minEdge[j] > g[minVertex][j]) {
					minEdge[j] = g[minVertex][j];
				}
			}
			System.out.println(Arrays.toString(minEdge));
			System.out.println("====");
		}
		System.out.println(result);
		sc.close();
	}

}
