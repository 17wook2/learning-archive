package swea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution5215 {
    static int t,n,limit,taste,cal;
    static int[][] arr;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());
            arr = new int[n+1][limit+1];

            for(int i = 1; i < n+1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                taste = Integer.parseInt(st.nextToken());
                cal = Integer.parseInt(st.nextToken());
                for(int j = 0; j < limit + 1; j++) {
                    if (j-cal >= 0) {
                        if(arr[i-1][j] < arr[i-1][j-cal] + taste){
                            arr[i][j] = arr[i-1][j-cal] + taste;
                        }else{
                            arr[i][j] = arr[i-1][j];
                        }
                    }
                    else {
                        arr[i][j] = arr[i-1][j];
                    }
                }
            }

            System.out.println("#"+tc+" "+arr[n][limit]);
        }
    }
}
