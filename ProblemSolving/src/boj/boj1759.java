package boj;

import java.util.*;

public class boj1759 {
    static int l,c;
    static char[] arr;
    static char[] ans_arr;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt(); c = sc.nextInt();
        arr = new char[c];
        ans_arr = new char[l];
        sb = new StringBuilder();
        for(int i = 0; i < c; i++) {
            char cur = sc.next().charAt(0);
            arr[i] = cur;
        }
        Arrays.sort(arr);
        comb(0,0,0,0);
        System.out.println(sb.toString());

    }

    private static void comb(int cnt, int start, int a, int b){
        if(cnt == l){
            if(a < 1 || b < 2) return;
            sb.append(String.valueOf(ans_arr)).append("\n");
            return;
        }
        for(int i = start; i < c; i++){
            ans_arr[cnt] = arr[i];
            if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u'){
                comb(cnt+1, i+1, a+1, b);
            }else{
                comb(cnt+1, i+1, a, b+1);
            }
        }
    }

}
