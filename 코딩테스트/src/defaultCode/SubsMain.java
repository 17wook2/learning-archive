package defaultCode;

import java.util.ArrayDeque;
import java.util.Arrays;

public class SubsMain {
    public static int N = 4, /*R = 3,*/ C = 0;
    static int[] a = {1,2,3,4}/*, b= new int[R]*/;
    static boolean[] v = new boolean[N];

    public static void subs(int cnt, String str) {
        if (cnt == N) {
            System.out.println(str); C++;
            return;
        }
        v[cnt] = true;
        subs(cnt+1, str + a[cnt]);
        v[cnt] = false;
        subs(cnt+1, str);
    }

    public static void main(String[] args) {
        C = 0;
        subs(0,"");
        System.out.println(C);
    }
}