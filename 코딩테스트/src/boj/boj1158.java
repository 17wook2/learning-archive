package boj;

import java.util.*;

public class boj1158 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) q.add(i);
        sb.append("<");
        while(!q.isEmpty()){
            for(int i = 0; i < k-1; i++) q.addLast(q.pollFirst());
            sb.append(q.pollFirst() + ", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(">");
        System.out.println(sb.toString());
    }
}
