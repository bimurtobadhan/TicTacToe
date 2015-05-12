import java.util.Vector;

/**
 * Created by Bimurto on 11-May-15.
 */
public class State {
    char board[][] = new char[3][3];
    Vector<State> childeren = new Vector<>();
    //Map

    int bestValue, alpha = -1000 , beta = 1000;

    String player;

    public State(char board[][], String player){
        this.player = new String(player);
        bestValue = (player.equals(Constants.MAX))?-1000:1000;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.board[i][j] = board[i][j];
            }
        }
    }

    public State(char board[][], String player, int row, int column, char value){
        this.player = new String(player);
        bestValue = (player.equals(Constants.MAX))?-1000:1000;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.board[i][j] = board[i][j];
            }
        }
        this.board[row][column] = value;
    }


    public State(){
        bestValue = -1000;
        this.player = Constants.MAX;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.board[i][j] = Constants.BLANK;
            }
        }
    }

    public State(State s, int row, int column) {
        this.player = s.player.equals(Constants.MAX)?Constants.MIN:Constants.MAX;
        bestValue = (player.equals(Constants.MAX))?-1000:1000;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.board[i][j] = s.board[i][j];
            }
        }
        this.board[row][column] = Constants.O;
    }

    public Vector<State> getChildren(){
        for(int i=0;i<3;i++){
            if(isGoal())
                break;
            for(int j=0;j<3;j++){
                if(this.board[i][j] == Constants.BLANK && player.equals(Constants.MAX)){
                    childeren.add(new State(board,Constants.MIN,i,j,Constants.X));
                }else if(this.board[i][j] == Constants.BLANK && player.equals(Constants.MIN)) {
                    childeren.add(new State(board, Constants.MAX, i, j, Constants.O));
                }
            }
        }
        return childeren;
    }

    public int getHeuristics(){
        if(isGoal() && player.equals(Constants.MAX))
            return -1;
        else if(isGoal() && player.equals(Constants.MIN))
            return 1;
        else if(childeren.size() == 0)
            return 0;
        return 0;
    }

    public boolean isGoal(){
        char check = player.equals(Constants.MAX)? Constants.O:Constants.X;
        for(int i=0;i<3;i++){
            //check each row
            if(board[i][0] == check && board[i][1] == check && board[i][2] == check)
                return true;
        }
        for(int j=0;j<3;j++){
            //check each row
            if(board[0][j] == check && board[1][j] == check && board[2][j] == check)
                return true;
        }
        if(board[0][0] == check && board[1][1] == check && board[2][2] == check)
            return true;
        if(board[2][0] == check && board[1][1] == check && board[2][0] == check)
            return true;
        return false;
    }

    public boolean isEmpty(int row, int column){
        if(board[row][column] == Constants.BLANK)return true;
        return false;
    }

    public boolean isX(int row, int column){
        if(board[row][column] == Constants.X)return true;
        return false;
    }

    public boolean isO(int row, int column){
        if(board[row][column] == Constants.O)return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        State state = (State) obj;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.board[i][j] != state.board[i][j])
                    return false;
            }
        }
        return true;
    }

//    @Override
//    public int hashCode() {
//        String s = "";
//        s +=    "\n*********\n"
//                //+ "Is Goal: " + isGoal() +"\n"
//                + board[0][0]+" | "+board[0][1]+" | "+board[0][2]
//                + "\n---------\n"
//                + board[1][0]+" | "+board[1][1]+" | "+board[1][2]
//                + "\n---------\n"
//                + board[2][0]+" | "+board[2][1]+" | "+board[2][2]
//                +"\n*********\n";
//        return s.hashCode();
//    }

    @Override
    public String toString() {
        String s = "";
        s +=    "\n*********\n"
               // + "Is Goal: " + isGoal() +"\n"
                + board[0][0]+" | "+board[0][1]+" | "+board[0][2]
                + "\n---------\n"
                + board[1][0]+" | "+board[1][1]+" | "+board[1][2]
                + "\n---------\n"
                + board[2][0]+" | "+board[2][1]+" | "+board[2][2]
                +"\n*********\n";
        return s;
    }

}
