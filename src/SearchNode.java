
package src;

public class SearchNode implements Comparable{
    Board bo;


    int hamming;
    int manhattan;
    int moves;
    int prior;
    SearchNode prev;


    public SearchNode(Board board, int mov,SearchNode prev) {

    if(board!=null) {
        bo = board;
        moves = mov;
        hamming = board.hamming();
        manhattan = board.manhattan();
        prior = manhattan+moves;
        this.prev = prev;
    }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SearchNode){
            if(((SearchNode) obj).getBoard()!= getBoard()){
                return false;
            }
            return true;
        }
        throw new IllegalArgumentException("Item null or not SearchNode");
    }

    public Board getBoard(){
        return bo;
    }
    public SearchNode getPrev(){
        return prev;
    }

    @Override
    public int compareTo(Object o) {
        SearchNode temp = (SearchNode) o;
        if(this.prior>temp.prior){
            return 1;
        }else if(this.prior<temp.prior){
            return -1;
        }
        else{
            if(this.manhattan>temp.manhattan) return 1;
            else if( this.manhattan<temp.manhattan)
                return -1;
            else return 0;
        }
    }
}
