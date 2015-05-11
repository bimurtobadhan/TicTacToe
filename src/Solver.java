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
        buildGameTree(inital);
        //showGameTree(inital);
        start();
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

//        System.out.println("Parent:");
//        System.out.println(initial);
//        System.out.println("Children number "+children.size()+":");
//        for(int i=0;i<children.size();i++){
//            System.out.println(children.elementAt(i).player);
//            System.out.println(children.elementAt(i));
//
//        }
//        in.next();

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
