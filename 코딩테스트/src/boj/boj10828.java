package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10828 {

    public static int[] stack;
    public static int size = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        stack = new int[n];
        while (n-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            String token = st.nextToken();
            if(token.equals("push")){
                int item = Integer.parseInt(st.nextToken());
                push(item);
            }
            else if(token.equals("pop")){
                sb.append(pop()).append('\n');
            }
            else if(token.equals("size")){
                sb.append(size).append('\n');
            } else if (token.equals("empty")) {
                if (size == 0){
                    sb.append(1).append('\n');
                }else{
                    sb.append(0).append('\n');
                }
            } else if(token.equals("top")) {
                sb.append(top()).append('\n');
            }

        }
        System.out.println(sb);
    }

    private static int pop() {
        if (size == 0) {
            return -1;
        }
        int item = stack[size-1];
        stack[size-1] = 0;
        size --;
        return item;
    }

    private static void push(int item) {
        stack[size] = item;
        size ++;
    }

    private static int top(){
        if (size == 0) {
            return -1;
        }
        return stack[size-1];
    }

}
