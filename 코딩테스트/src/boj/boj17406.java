package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj17406 {
    static int n,m,k,r,c,s,ans;
    static int[][] arr, new_array;
    static int[] b;
    static boolean[] visited;
    static int[][] commands;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        commands = new int[k][3];
        b = new int[k];
        visited = new boolean[k];
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            r -= 1; c -= 1;
            commands[i][0] = r; commands[i][1] = c; commands[i][2] = s;
        }
//        for(int[] row : arr) System.out.println(Arrays.toString(row));
        perm(0);
        System.out.println(ans);
    }

    private static void go(int[][] new_array,int r, int c, int s) {
        int start = r - s;
        int end = c - s;
        int h = 2 * s + 1;
        for (int cy = 0; cy < s; cy ++) {
            int temp = new_array[start+cy][end+cy];
            for(int i = cy; i < h-1-cy; i++){
                new_array[start + i][end+cy] = new_array[start+i+1][end+cy];
            }
            for (int j = cy; j < h - 1 - cy; j++) {
                new_array[start+h-1-cy][end+j] = new_array[start+h-1-cy][end+j+1];
            }
            for (int i = cy; i < h - 1 - cy; i++) {
                new_array[start+h-1-i][end+h-1-cy] = new_array[start+h-2-i][end+h-1-cy];
            }
            for (int j = cy; j < h - 1 - cy; j++) {
                new_array[start+cy][end+h-1-j] = new_array[start+cy][end+h-2-j];
            }
            new_array[start + cy][end + cy + 1] = temp;
//            System.out.println();
//            for(int[] row : arr) System.out.println(Arrays.toString(row));
        }
    }

    private static void perm(int cnt) {
        if (cnt == k) {
//            System.out.println(Arrays.toString(b));
            new_array = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    new_array[i][j] = arr[i][j];
                }
            }
//            for(int[] row : new_array) System.out.println(Arrays.toString(row));
            for(int i = 0; i < k; i++){
                go(new_array,commands[b[i]][0], commands[b[i]][1], commands[b[i]][2]);
//                System.out.println();
            }
//            for(int[] row : new_array) System.out.println(Arrays.toString(row));
//            System.out.println();
            for(int[] row : new_array){
                ans = Math.min(ans, Arrays.stream(row).sum());
            }
            return;
        }
        for (int i = 0; i < k; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            b[cnt] = i;
            perm(cnt+1);
            visited[i] = false;
        }
    }

}


