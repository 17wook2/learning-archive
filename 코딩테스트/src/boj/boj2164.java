package boj;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.stream.IntStream;

public class boj2164 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        IntStream.range(1,n+1).forEach(q::add);
        int ans = 0;
        while(!q.isEmpty()){
            ans = q.pop();
            if(q.isEmpty()) break;
            q.add(q.pollFirst());
        }
        System.out.println(ans);
    }
}
