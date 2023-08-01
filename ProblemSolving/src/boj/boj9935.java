package boj;
import java.io.*;
import java.util.*;

public class boj9935 {
    private static String a,b;
    private static char[] ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        ans = new char[a.length()];
        int idx = 0;
        for (int i = 0; i < a.length(); i++) {
            ans[idx] = a.charAt(i);
            if (check(idx)) idx -= b.length();
            idx += 1;
        }
        String res = String.valueOf(ans,0,idx);
        if (res.length() == 0) System.out.println("FRULA");
        else System.out.println(res);
    }
    private static boolean check(int idx){
        if (idx < b.length() - 1) return false;
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) != ans[idx - b.length() + 1 + i]) return false;
        }
        return true;
    }
}

