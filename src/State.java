/**
 * Created by Bimurto on 11-May-15.
 */
public class State {
    char board[][] = new char[3][3];
    String player;

    public State(char board[][], String player){
        this.player = new String(player);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.board[i][j] = board[i][j];
            }
        }
    }

    public State(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.board[i][j] = Constants.BLANK;
            }
        }
    }

    public void buildGameTree(){

    }

    //public void

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
    public String toString() {
        String s = "";
        board[0][0] ='X';
        board[1][1]='O';
        if(board[1][0] == ' ')
         System.out.println("Yes");
        s += board[0][0]+" | "+board[0][1]+" | "+board[0][2]
                + "---------"
                + board[1][0]+" | "+board[1][1]+" | "+board[1][2]
                + "---------"
                + board[2][0]+" | "+board[2][1]+" | "+board[2][2];
        System.out.println(board[0][0]+" | "+board[0][1]+" | "+board[0][2]);
        System.out.println("---------");
        System.out.println(board[1][0]+" | "+board[1][1]+" | "+board[1][2]);
        System.out.println("---------");
        System.out.println(board[2][0]+" | "+board[2][1]+" | "+board[2][2]);
        return s;
    }
}
