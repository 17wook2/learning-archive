package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class boj2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringBuilder sb = new StringBuilder();
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.add(new int[]{arr[0], 0});
        sb.append(0 + " ");
        for(int i = 1; i < n; i++){
            while(!stack.isEmpty() && arr[i] > stack.peekFirst()[0]){
                stack.pollFirst();
            }
            if(stack.isEmpty()) {
                stack.addFirst(new int[]{arr[i],i});
                sb.append(0+" ");
            }else{
                sb.append(stack.peekFirst()[1]+1 + " ");
                stack.addFirst(new int[]{arr[i],i});
            }
        }
        System.out.println(sb.toString());

    }
}
