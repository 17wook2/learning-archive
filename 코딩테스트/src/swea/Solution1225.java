package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.stream.Stream;

public class Solution1225 {
    static ArrayDeque<Integer> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            br.readLine();
            String[] row = br.readLine().split(" ");
            q = new ArrayDeque<>();
            Stream.of(row).forEach(v -> q.add(Integer.valueOf(v)));
            go();
            System.out.print("#"+t+" ");
            for(Integer x: q) System.out.print(x+" ");
            System.out.println();

        }

    }

    private static void go() {
        while (true) {
            for(int i = 1; i <= 5; i++){
                Integer x = q.poll();
                if(x - i > 0) q.add(x-i);
                else {
                    q.add(0);
                    return;
                }
            }
        }
    }
}
