package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution5658 {
    private static int n,k,d;
    private static Map<String, Boolean> map;
    private static String number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            d = n / 4;
            String row = br.readLine();
            number = row.concat(row);
            map = new HashMap<>();
            PriorityQueue<String> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 0; i < number.length() - d; i++) {
                String sub = number.substring(i, i + d);
                if(!map.containsKey(sub)){
                    pq.offer(sub);
                    map.put(sub,true);
                }
            }
            String cur = pq.peek();
            for (int i = 0; i < k; i++) {
                cur = pq.poll();
            }
            int res = Integer.valueOf(cur,16);
            System.out.println("#" + tc + " " + res);
        }

    }
}
