package boj;
import java.io.*;
import java.util.*;

public class boj12919 {
    private static String S;
    private static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        S = br.readLine();
        String T = br.readLine();
        dfs(T);
        System.out.println(ans);
    }

    static void dfs(String string){
        if (S.length() == string.length()) {
            if (S.equals(string)){
                ans = 1;
                return;
            }
            return;
        }
        if (string.charAt(string.length() - 1) == 'A') {
            String substr = string.substring(0, string.length() - 1);
            dfs(substr);
        }
        if (string.charAt(0) == 'B') {
            String substr = string.substring(1, string.length());
            StringBuffer sb = new StringBuffer(substr);
            String reversed = sb.reverse().toString();
            dfs(reversed);
        }
        return;
    }
}

