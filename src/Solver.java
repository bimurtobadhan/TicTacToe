import sun.nio.cs.ext.MacThai;

import java.util.*;

/**
 * Created by Bimurto on 11-May-15.
 */
public class Solver {
    State inital ;
    HashMap<String, Vector<State>> gameTree = new HashMap<>();
//    Map<State, Vector<State>> gameTree = new TreeMap<>();
    Scanner in = new Scanner(System.in);
    public Solver(){
        inital = new State();
//        buildGameTree(inital);
        //showGameTree(inital);
//        start();
//        System.out.println(alpjabetasearch(inital));
        start2();
    }



    private void start2() {
//        System.out.println(selectState(inital));
        char board [][] = new char[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = ' ';
            }
        }
        board[1][1] = Constants.X;
        State s = new State(board, "MAX");
        System.out.println(s);
        while(true){
            int value = alpjabetasearch(s).bestValue;
            s = selectState2(s);
//            System.out.println("Alpha Beta:");
//            System.out.println(s);

            int row = Integer.parseInt(in.next());
            int column = Integer.parseInt(in.next());
            State s1 = new State(s, row,column);
            System.out.println(s1);
            s = s1;
//            System.out.println(gameTree.containsKey(inital.toString()));
        }
    }

    public State selectState2(State state){
        Vector<State> children =state.getChildren();
        int max = -1000;
        State result = null;
        for(int i=0;i<children.size();i++){
            if(children.elementAt(i).bestValue > max){
                max = children.elementAt(i).bestValue;
                result = children.elementAt(i);
            }
        }
        return result;
    }

    public State alpjabetasearch(State state){
        System.out.println(state);
        return maxsearch(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public State maxsearch(State state, int a, int b){
        State child, result = null;
        Vector <State> children = state.getChildren();
        if(children.size() == 0){
            state.bestValue = state.getHeuristics();
            return state;
        }

        int v = -1000;

        for(int i=0;i<children.size();i++){
            child = children.elementAt(i);
            int min = minsearch(child, a, b).bestValue;
            if(min > v ){
                result = minsearch(child,a,b);
                result.bestValue = v;
                v  = min;
            }
            if(v >= b)
                return result;
            a = Math.max(a,v);
        }
        return result;
    }

    public State minsearch(State state, int a, int b){
        State child, result = null;
        Vector <State> children = state.getChildren();
        if(children.size() == 0){
            state.bestValue = state.getHeuristics();
            return state;
        }

        int v = 1000;

        for(int i=0;i<children.size();i++){
            child = children.elementAt(i);
            int max = maxsearch(child, a, b).bestValue;
            if (max < v){
                result = maxsearch(child,a,b);
                result.bestValue = v;
                v  = max;
            }
            if(v <= a)
                return result;
            b = Math.min(v,b);
        }
        return result;
    }

    private void start() {
        System.out.println(selectState(inital));
        State s = inital;
        while(true){

            s = selectState(inital);
            System.out.println(selectState(inital));

            int row = Integer.parseInt(in.next());
            int column = Integer.parseInt(in.next());
            State s1 = new State(s, row,column);
            System.out.println(s1);
            inital = s1;
            System.out.println(gameTree.containsKey(inital.toString()));
        }
    }

    private State selectState(State state) {
        Vector<State> children = gameTree.get(state.toString());
        int max = -1000;
        State result = null;
        for(int i=0;i<children.size();i++){
            if(children.elementAt(i).bestValue > max){
                max = children.elementAt(i).bestValue;
                result = children.elementAt(i);
            }
        }
        return result;
    }

    public void buildGameTree(State initial){
        Vector<State> children = initial.getChildren();

        if(children.size() == 0) {
            initial.bestValue = initial.getHeuristics();
            return;
        }

        for(int i=0;i<children.size();i++){
            buildGameTree(children.elementAt(i));
            if(initial.player.equals(Constants.MAX) && initial.bestValue < children.elementAt(i).bestValue){
                initial.bestValue = children.elementAt(i).bestValue;
            }else if(initial.player.equals(Constants.MIN) && initial.bestValue > children.elementAt(i).bestValue){
                initial.bestValue = children.elementAt(i).bestValue;
            }
        }
        gameTree.put(initial.toString(), children);
    }

    public void showGameTree(State initial){
        Vector<State> children = gameTree.get(initial.toString());

        if(children == null || children.size() == 0) {
            return;
        }

        System.out.println("Parent:");
        System.out.println(initial);
        System.out.println("Children number " + children.size() + ":");
        for(int i=0;i<children.size();i++){
            System.out.println(children.elementAt(i).player);
            System.out.println(children.elementAt(i).bestValue);
            System.out.println(children.elementAt(i));

        }
        in.next();

        //gameTree.put(inital, children);
        for(int i=0;i<children.size();i++){
            showGameTree(children.elementAt(i));
        }
    }


}
