package boj;

import java.util.ArrayDeque;
import java.util.Scanner;

public class boj15961 {
    static int n,d,k,c;
    static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); d = sc.nextInt(); k = sc.nextInt(); c = sc.nextInt();
        int[] belt = new int[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        ArrayDeque<Integer> choice = new ArrayDeque<>();
        for(int i = 0; i < n; i++) belt[i] = sc.nextInt();
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < n; j++) q.add(belt[j]);
        }
        visited = new int[d+1];
        visited[c] = 1;
        int cnt = 1;
        for(int i = 0; i < k; i++) {
            int x = q.poll();
            choice.add(x);
            if(visited[x] == 0){
                cnt += 1;
            }
            visited[x] ++;
        }
        int head = choice.peek();
        int ans = cnt;
        while (!q.isEmpty()) {
            visited[head] --;
            if(visited[head] == 0) cnt--;
            choice.poll();
            head = choice.peek();
            int x = q.poll();
            choice.add(x);
            if(visited[x] == 0){
                cnt ++;
            }
            visited[x] ++;
//            System.out.println(cnt + " " +choice);

            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }
}
