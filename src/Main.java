import java.util.Arrays;

public  class Main {
    int[][] solution(int[][] matrix) {
        int lep = 10;
        int[][] solutions = new int[lep][2];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    int[] maxCor = new int[2];
                    maxCor[0] = i;
                    maxCor[1] = j;
                    int maxR = matrix[i][j];
                    int num = maxR;
                    int len = (2 * matrix[i][j]) + 1;
                    for (int y = 0; y <= num; y++) {
                        for (int x = 0; x <= num; x++) {
                            if (!((x == num && y == num) || (x == num && y == 0) || (x == 0 && y == num) || (x == 0 && y == 0))) {
                                if (i + y < matrix.length && j + x < matrix[0].length) {
                                    if (matrix[i + y][j + x] != 0) {
                                        if (matrix[i + y][j + x] > maxR) {
                                            maxCor[0] = i + y;
                                            maxCor[1] = j + x;
                                        }
                                        if(matrix[i + y][j + x]==maxR){
                                            maxCor = new int[2];
                                        }
                                    }
                                }
                                if (i - y >= 0 && j + x < matrix[0].length) {
                                    if (matrix[i - y][j + x] != 0) {
                                        if (matrix[i - y][j + x] > maxR) {
                                            maxCor[0] = i - y;
                                            maxCor[1] = j + x;
                                        }
                                        if(matrix[i - y][j + x]==maxR){
                                            maxCor = new int[2];
                                        }
                                    }
                                }
                                if (i - y >= 0 && j - x >= 0) {
                                    if (matrix[i - y][j - x] != 0) {
                                        if (matrix[i - y][j - x] > maxR) {
                                            maxCor[0] = i - y;
                                            maxCor[1] = j - x;
                                        }
                                        if(matrix[i - y][j - x]==maxR){
                                            maxCor = new int[2];
                                        }
                                    }
                                }
                                if (i + y < matrix.length && j - x >= 0) {
                                    if (matrix[i + y][j - x] != 0) {
                                        if (matrix[i + y][j - x] > maxR) {
                                            maxCor[0] = i + y;
                                            maxCor[1] = j - x;
                                        }
                                        if(matrix[i + y][j - x]==maxR){
                                            maxCor = new int[2];
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (count < solutions.length) {
                        solutions[count++] = maxCor;
                    } else {
                        int[][] temp = new int[lep * 2][2];
                        lep *= 2;
                        for (int e = 0; e < solutions.length; e++) {
                            temp[e] = solutions[e];
                        }
                        solutions = temp;
                    }
                }
            }
        }
        for(int i=0;i<solutions.length;i++){
            for(int j=0;j<solutions.length;j++){
                if(i!=j){
                    if(Arrays.equals(solutions[i], solutions[j])){
                        solutions[j] = null;
                    }
                }
            }
        }
        return solutions;
    }
    int solution1(String pattern, String source) {
        int count=0;
        for(int i=0;i<=source.length()-pattern.length();i++){
            boolean ew = true;
            for(int t=0;t<pattern.length();t++){
                if(pattern.charAt(t) == '1'){
                    if((source.charAt(i+t)=='a'||source.charAt(i+t)=='e'||source.charAt(i+t)=='i'||source.charAt(i+t)=='o'||source.charAt(i+t)=='u'||source.charAt(i+t)=='y')){
                        ew = false;
                    }
                }
                else if(pattern.charAt(t) =='0'){
                    if(!(source.charAt(i+t)=='a'||source.charAt(i+t)=='e'||source.charAt(i+t)=='i'||source.charAt(i+t)=='o'||source.charAt(i+t)=='u'||source.charAt(i+t)=='y')){
                        ew = false;
                    }
                }

            }
            if(ew){
                count++;
            }

        }
        return count;
    }
    public static void main(String[] args){
        int [][] mat = {{3,0,0,0,0},{0,0,1,0,0},{0,0,2,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,3,0,0,3}};
        Main m = new Main();
        System.out.println((m.solution1("010","amazing")));


    }
}