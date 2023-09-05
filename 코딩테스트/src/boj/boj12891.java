package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj12891 {
    static int s,p;
    static int[] need;
    static int[] cur;
    static Map<Character, Integer> map;
    static String dna;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        dna = br.readLine();
        need = new int[4];
        cur = new int[4];
        map = new HashMap<>();
        map.put('A',0); map.put('C',1); map.put('G',2); map.put('T',3);
        int ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) need[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i < p; i++) cur[map.get(dna.charAt(i))] += 1;
        if (check()) ans += 1;
        for (int move = 0; move < s - p; move += 1) {
            cur[map.get(dna.charAt(move))] -= 1;
            cur[map.get(dna.charAt(move + p))] += 1;
            if(check()) ans += 1;
        }
        System.out.println(ans);
    }
    private static boolean check(){
        for (int i = 0; i < 4; i++) {
            if (need[i] <= cur[i]) continue;
            return false;
        }
        return true;
    }
}
