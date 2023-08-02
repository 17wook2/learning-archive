package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2798 {
    private static int n,m,ans,diff;
    private static int[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];
        diff = 3000001;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        go(0,0,0);
        System.out.println(m-diff);

    }

    private static void go(int cnt, int idx, int cur) {
        if(cnt == 3){
            if(cur <= m && m - cur < diff){
                diff = m - cur;
            }
            return;
        }
        for (int i = idx; i < n; i++) {
            go(cnt+1,i+1,cur + a[i]);
        }
    }
}
