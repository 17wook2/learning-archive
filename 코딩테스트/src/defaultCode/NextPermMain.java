package defaultCode;

import java.util.Arrays;

public class NextPermMain {

    static int N = 4, C = 0;
    static int[] a = {3,1,4,2};

    static void swap(int i, int j) {
        int T = a[i]; a[i] = a[j]; a[j] = T;
    }

    static boolean npn() {
        //1 꼭대기 찾기
        int i = N-1;
        while(i > 0 && a[i-1] >= a[i]) i--;
        if(i == 0) return false;


        //2 교환할 값 찾기
        int j = N-1;
        while(a[i-1] >= a[j]) j--;
        swap(i-1,j);

        //3 뒤에 오름차순 정렬
        int k = N-1;
        while(i < k) swap(i++,k--);


        return true;

    }
    public static void main(String[] args) throws Exception {
        Arrays.sort(a);
        do{
            System.out.println(Arrays.toString(a)); C++;
        }while(npn());
        System.out.println(C);
    }

}
