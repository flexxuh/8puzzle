package src;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class Board {
    //private final int[][] board;
    private final char[] board;
    private int boardLen;
    private int n;
    private int x0;
    private int y0;

    private Board tBoard = null;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        int p = 0;
        n = tiles.length;
        board = new char[tiles.length * tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                board[XYto1D(j,i)] =  ((char) tiles[i][j]);
            }
        }

        boardLen = board.length;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[XYto1D(j, i)] == 0) {
                    x0 = j;
                    y0 = i;
                    break outer;
                }
            }
        }
    }
    private Board(char[] tiles, int n, int x0, int y0){
        board = tiles;
        this.n = n;
        this.x0 = x0;
        this.y0 = y0;
        boardLen = board.length;
    }
    private Board(int[] tiles, int n,int x0,int y0) {
        board = new char[tiles.length];
        for(int i=0;i<tiles.length;i++){
            board[i] = ((char) tiles[i]);
        }
        this.n = n;
        this.x0 = x0;
        this.y0 = y0;
        boardLen = board.length;
    }



    private int XYto1D(int x, int y){
        return (y*n)+x;
    }
    // string representation of this board
    public String toString(){
            StringBuilder s = new StringBuilder();
            s.append(n + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int x = board[XYto1D(j, i)];
                    s.append(x+" ");
                }
                s.append("\n");
            }
            return s.toString();
        }


    // board dimension n
    public int dimension(){
        return n;
    }

    // number of tiles out of place
    public int hamming(){
        int counter = 0;
        int count = 1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                char b = board[XYto1D(j,i)];
                if(count++!=Character.valueOf(b)&&b != 0) {
                    counter++;
                }
            }
        }
        return counter;
    }
    private void setXY0(int x,int y){
      x0=x;
      y0=y;
    }



    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int counter=0;

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                char b = board[XYto1D(j,i)];
                if(b!=0) {
                        int x = Character.valueOf(b) % n;
                        int y = Character.valueOf(b) / n;
                        if(x==0){
                            x = n;
                            y-=1;
                        }
                        x-=1;
                        double xDiff = Math.abs(x - j);
                        double yDiff = Math.abs(y-i);
                     counter += xDiff+yDiff;
                    }


            }
        }
        return counter;
    }

    // is this board the goal board?
    public boolean isGoal(){

        boolean equal = true;
        int count =1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                char b = board[XYto1D(j,i)];
                if (count++ != b && b != 0) {
                    equal = false;
                }
            }
        }
        return equal;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if(y!=null) {
            if (y instanceof Board) {
                if (((Board) y).dimension() == dimension()) {
                    boolean equals = true;
                    for (int i = 0; i <n; i++) {
                        for (int j = 0; j < n; j++) {
                            Board b = (Board) y;
                            char bt = board[XYto1D(j,i)];
                            if (bt != b.board[XYto1D(j, i)]) {
                                equals = false;
                                break;
                            }

                        }
                    }
                    return equals;
                }
            }
        }

        return false;
    }

    private Board swap(int x, int y,boolean dx, int mag) {
        char[] temp = Arrays.copyOf(board,boardLen);
        int count = 0;
        Board bt = null;
        char t1 = board[XYto1D(x,y)];
        if (dx) {
            if (mag > 0) {
                if (x + mag < n) {
                    char t2 = board[XYto1D(x+mag,y)];
                    temp[XYto1D(x,y)] = t2;
                    temp[XYto1D(x+mag,y)] = t1;
                    bt = new Board(temp,this.n,this.x0,this.y0);
                    bt.setXY0(x+mag,y);

                }
            }
            if (mag < 0) {
                if (x + mag >= 0) {
                    char t2 = board[XYto1D(x+mag,y)];
                    temp[XYto1D(x,y)] = t2;
                    temp[XYto1D(x+mag,y)] = t1;
                    bt = new Board(temp,this.n,this.x0,this.y0);
                    bt.setXY0(x+mag,y);
                }
            }
        }else {
            if (mag > 0) {
                if (y + mag < n) {
                    char t2 = board[XYto1D(x,y+mag)];
                    temp[XYto1D(x,y)] = t2;
                    temp[XYto1D(x,y+mag)] = t1;
                    bt = new Board(temp,this.n,this.x0,this.y0);
                    bt.setXY0(x,y+mag);
                }
            }
            if (mag < 0) {
                if (y + mag >= 0) {
                    char t2 = board[XYto1D(x,y+mag)];
                    temp[XYto1D(x,y)] = t2;
                    temp[XYto1D(x,y+mag)] = t1;
                    bt = new Board(temp,this.n,this.x0,this.y0);
                    bt.setXY0(x,y+mag);
                }
            }
        }

        return bt;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int x = 0;
        int y = 0;
        ArrayList<Board> bo = new ArrayList<>();
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[XYto1D(j,i)] == 0) {
                    y = i;
                    x = j;
                    Board tem1 = swap(x, y, true, 1);
                    Board tem2 = swap(x, y, true, -1);
                    Board tem3 = swap(x, y, false, 1);
                    Board tem4 = swap( x, y, false, -1);
                    if(tem1!=null){
                        bo.add(tem1);
                    }
                    if(tem2!=null){
                        bo.add(tem2);
                    }
                    if(tem3!=null){
                        bo.add(tem3);
                    }
                    if(tem4!=null){
                        bo.add(tem4);
                    }
                    break outer;
                }
            }
        }


        return bo;
    }


    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if(tBoard==null){
        char[] temp = new char[boardLen];
        int count = 0;
        temp = Arrays.copyOf(board, boardLen);
        while (true) {
            int rand = StdRandom.uniformInt(n);
            int rand1 = StdRandom.uniformInt(n);
            int rand3 = StdRandom.uniformInt(n);
            if (rand != rand3 && temp[XYto1D(rand, rand1)] != 0 && temp[XYto1D(rand3, rand1)] != 0) {
                char var = board[XYto1D(rand, rand1)];
                char var1 = board[XYto1D(rand3, rand1)];
                temp[XYto1D(rand, rand1)] = var1;
                temp[XYto1D(rand3, rand1)] = var;
                tBoard = new Board(temp, this.n, this.x0, this.y0);
                return tBoard;
            }
        }

        }
        else{
            return tBoard;
        }
    }

    // unit testing (not graded)
    public static void main(String[] args){
        RandomizedQueue<Integer> que = new RandomizedQueue<>();
        int n = 5*5;
        for(int i=0;i<=n;i++){
            que.enqueue(i);
        }
        int[][] temp = new int[5][5];

      for(int i=0;i<5;i++){
          for(int j=0;j<5;j++){
              temp[i][j] = que.dequeue();
          }
      }
      Board bo = new Board(temp);

      System.out.println(bo.neighbors());

    }

}
