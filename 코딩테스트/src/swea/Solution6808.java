package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution6808 {

    static int win, lose;
    static int[] card;
    static int[] left;
    static int[] right;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= t; tc++) {
            win = 0; lose = 0;
            card = new int[19];
            visited = new boolean[19];
            left = new int[9];
            right = new int[9];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i <9; i++) {
                int c = Integer.parseInt(st.nextToken());
                card[c] = 1; left[i] = c;
            }
            int j = 0;
            for(int i = 1; i < 19; i++){
                if(card[i] != 1) right[j++] = i;
            }
//            go(0);
            do{
                int score = 0;
                for (int i = 0; i < 9; i++) {
                    int s = left[i] + right[i];
                    if(left[i] < right[i]) score -= s;
                    else score += s;
                }
                if (score < 0) lose += 1;
                else if (score > 0) win += 1;
            }while (next_permutation(right));

            sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean next_permutation(int[] p) {
        int i = 8;
        while (i > 0 && p[i-1] >= p[i]) i--;
        if(i == 0) return false;

        int j = 8;
        while (p[i-1] >= p[j]) j --;
        swap(p,i-1,j);

        int k = 8;
        while (i < k) swap(p, i++, k--);

        return true;
    }
    private static void swap(int[] p, int a, int b){
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }

    private static void go(int cnt) {
        if (cnt == 9) {
            int score = 0;
            for (int i = 0; i < 9; i++) {
                int s = left[i] + right[i];
                if(left[i] < right[i]) score -= s;
                else score += s;
            }
            if (score < 0) lose += 1;
            else if (score > 0) win += 1;
            return;
        }
        for (int i = 1; i < 19; i++) {
            if(card[i] == 1) continue;
            if(visited[i]) continue;
            visited[i] = true;
            right[cnt] = i;
            go(cnt+1);
            visited[i] = false;
        }

    }
}
