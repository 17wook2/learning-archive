package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution1218 {
    static int n;
    static ArrayDeque<Character> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> map = new HashMap<>();
        char[] open = {'(','[','{','<'};
        char[] close = {')', ']','}','>'};
        for(int i = 1; i <= 4; i++) {
            map.put(open[i-1],i);
            map.put(close[i-1],-i);
        }
        for (int tc = 1; tc <= 10; tc++) {
            n = Integer.parseInt(br.readLine());
            stack = new ArrayDeque<>();
            char[] charArray = br.readLine().toCharArray();
            for(int i = 0; i < n; i++) {
                if (!stack.isEmpty()) {
                    Character peek = stack.peek();
                    if (map.get(peek) > 0 && (map.get(charArray[i]) + map.get(peek)) == 0) stack.pop();
                    else stack.addFirst(charArray[i]);
                }else{
                    stack.addFirst(charArray[i]);
                }
            }
            if(stack.size() != 0) System.out.println("#" + tc + " "+ 0);
            else System.out.println("#" + tc + " " + 1);
        }
    }
}
