package defaultCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MSTPrimTest {

	static int V, adjMatrix[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		adjMatrix = new int[V][V];
		
		for(int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < V; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[] visited = new boolean[V];
		int[] minEdge = new int[V]; // 자신의 트리의 정점들 간 최소 간선 비용.
		
		Arrays.fill(minEdge, Integer.MAX_VALUE); // 최소값 갱신 위해 큰 값으로 세팅
		minEdge[0] = 0;
		
		int result = 0; // 최소신장트리 비용
		int min = 0;
		for(int c = 0; c < V; c++) {
			int minVertex = -1;
			min = Integer.MAX_VALUE;
			// step1 : 미방문(비트리) 정점 중 최소간선비용의 정점을 선택
			for(int i = 0; i < V; i++) {
				if(!visited[i] && min > minEdge[i]) {
					minVertex = i;
					min = minEdge[i];
				}
			}
			
			// step2 : 방문(트리) 정점에 추가
			visited[minVertex] = true;
			result += min;
			
			// step3 : 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선비용 고려
			for(int i = 0; i < V; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}	
			}
		}
		System.out.println(result);
		
	}

}
