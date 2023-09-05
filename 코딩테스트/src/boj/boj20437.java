package boj;
import java.io.*;
import java.util.*;

public class boj20437 {

    private static int T,K;
    private static String W;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int min = 10001;
            int max = -1;
            W = br.readLine();
            K = Integer.parseInt(br.readLine());
            HashMap<Character, List<Integer>> hashMap = new HashMap<>();
            for (int j = 0; j < W.length(); j++) {
                if (!hashMap.containsKey(W.charAt(j))) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(j);
                    hashMap.put(W.charAt(j), list);
                }else{
                    hashMap.get(W.charAt(j)).add(j);
                }
            }
            for(Character key: hashMap.keySet()){
                int s = 0; int e = s+K-1;
                List<Integer> list = hashMap.get(key);
                if (list.size() < K) continue;
                while (e < list.size()) {
                    int length = list.get(e) - list.get(s) + 1;
                    min = Math.min(min,length);
                    max = Math.max(max,length);
                    s += 1;
                    e += 1;
                }
            }
            if ((min == 10001) || (max == -1)) {
                System.out.println(-1);
            }else System.out.println(min+ " " + max);
        }
    }

}

