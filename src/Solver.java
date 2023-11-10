package src;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;


public class Solver {

    private int count = 0;


    private Stack<Board> fin = new Stack<>();
    private boolean solvable;

    private SearchNode goa;



    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if(initial!=null) {
            Board twin = initial.twin();
            final MinPQ<SearchNode> que = new MinPQ<>();
            final MinPQ<SearchNode> que1 = new MinPQ<>();
            SearchNode t = new SearchNode(initial, count, null);
            SearchNode r = new SearchNode(twin, count, null);
            int tMoves = t.moves;
            int rMoves = r.moves;
            for (Board i : t.bo.neighbors()) {
                if (i != null) {
                    SearchNode ttr = new SearchNode(i, tMoves + 1, t);
                    que.insert(ttr);
                }
            }
            for (Board i : r.bo.neighbors()) {
                if (i != null) {
                        SearchNode ttr = new SearchNode(i, rMoves + 1, r);
                        que1.insert(ttr);

                }
            }
            int y = initial.manhattan();
            que.insert(t);
            que1.insert(r);
            while (true) {
                if (!t.getBoard().isGoal()) {
                    if(r.getBoard().isGoal()){
                        solvable = false;
                        break;
                    }
                    tMoves = t.moves;
                    rMoves = r.moves;
                    for (Board i : t.bo.neighbors()) {
                        if (i != null) {
                            if(t.prev!=null&&!t.getPrev().getBoard().equals(i)) {
                                SearchNode ttr = new SearchNode(i, tMoves + 1, t);
                                que.insert(ttr);
                            }
                        }
                    }
                    for (Board i : r.bo.neighbors()) {
                        if (i != null) {
                            if(r.prev!=null&&!r.getPrev().getBoard().equals(i)) {
                                SearchNode ttr = new SearchNode(i, rMoves + 1, r);
                                que1.insert(ttr);
                            }
                        }
                    }
                    t = que.delMin();
                    r = que1.delMin();
                } else {
                    int p = 0;
                    if (goa == null) {
                        goa = t;
                        solvable = true;
                        while (t.prev != null) {
                            fin.push(t.getBoard());
                            t = t.getPrev();
                            p++;
                        }
                        fin.push(initial);
                        count = p;
                        break;
                    }

                }
            }

        }
        else{
            throw new IllegalArgumentException("item is null");
        }
    }





    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if(solvable) return count;
        else return -1;

    }



    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if(solvable)return fin;
        return null;
    }






        // test client (see below)
        public static void main (String[]args){

            // create initial board from file
            In in = new In(args[0]);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    tiles[i][j] = in.readInt();
            Board initial = new Board(tiles);

            // solve the puzzle
            Solver solver = new Solver(initial);

            // print solution to standard output
            if (!solver.isSolvable())
                StdOut.println("No solution possible");
            else {
                StdOut.println("Minimum number of moves = " + solver.moves());
                for (Board board : solver.solution())
                    StdOut.println(board);
            }
        }

    }



