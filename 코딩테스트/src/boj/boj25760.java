package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj25760 {
    static int[] arr;
    static boolean[][] graph;
//    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        graph = new boolean[n+1][n+1];
        int cnt = 0; int ans = 0;
        StringTokenizer st;
        for(int i = 0; i < n-1; i++){
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]); int y = Integer.parseInt(s[1]);
            System.out.println(x + " " + y);
            graph[x][y] = true;
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int x = Integer.parseInt(st.nextToken());
            if (x == 1) cnt += 1;
            arr[i] = x;
        }
        for(boolean[] x: graph) System.out.println(Arrays.toString(x));
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        while(!q.isEmpty()){
            int cur = q.pollFirst();
            if(cur == 1){
                cnt -= 1;
                arr[cur] = 0;
            }
            for(int j = 1; j <= n; j++){
                if(graph[cur][j]){
                    if(arr[cur] != 1 && arr[j] == 1){
                        arr[cur] = 1; arr[j] = 0;
                    }
                    q.add(j);
                }
            }
            System.out.println(ans);
            System.out.println(Arrays.toString(arr));
            System.out.println();
        }
        System.out.println(ans);
    }
}
