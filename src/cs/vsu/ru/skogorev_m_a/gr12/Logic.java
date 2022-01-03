package cs.vsu.ru.skogorev_m_a.gr12;

public class Logic {

    public static int neighbors( int[][] arr, int i, int j){

        int count = 0;
        int minI = 0;
        int maxI = 0;
        int minJ = 0;
        int maxJ = 0;

        if (i - 1 >= 0) {
            minI = -1;
        }
        if (i + 1 < arr.length) {
            maxI = 1;
        }
        if (j - 1 >= 0) {
            minJ = -1;
        }
        if (j + 1 < arr[0].length) {
            maxJ = 1;
        }

        for (int r = minI; r <= maxI; r++) {
            for (int c = minJ; c <= maxJ; c++) {
                if (!(r == 0 && c == 0) && arr[i + r][j + c] == arr[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int[][] process(int[][] arr) {
        int[][] arrNew = new int[arr.length][arr[0].length];
        for (int i = 0; i<arr.length; i++){
            for (int j = 0; j<arr[0].length; j++) {
                arrNew[i][j]=neighbors(arr,i,j);
            }
        }
        return arrNew;
    }
}

