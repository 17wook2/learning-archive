package boj;
import java.io.*;
import java.util.*;

public class boj1522 {
    private static String x;
    private static int ans = 1001;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        x = br.readLine();
        int a_cnt = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == 'a') {
                a_cnt += 1;
            }
        }

        for (int i = 0; i < x.length(); i++) {
            int b_cnt = 0;
            for (int j = i; j < i+a_cnt; j++) {
                int idx = j % x.length();
                if (x.charAt(idx) == 'b') {
                    b_cnt += 1;
                }
            }
            ans = Math.min(ans, b_cnt);
        }
        System.out.println(ans);
    }

}

