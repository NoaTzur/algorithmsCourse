import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class truel {

    static int counter_of_death=0;

    public static class player{


        double probability_to_win;
        double wins;
        boolean is_dead;

        public player(double prob){

            this.probability_to_win=prob;
            this.wins=0;
            this.is_dead=false;
        }

    }

    /*
    this function choosing the order we are going to play randomly with shuffle method
    there is 3! different orders:

    ABC
    ACB
    BAC
    BCA
    CAB
    CBA


    A is represent with 1
    B with 2
    C with 3
     */
    public static void game_order(List<Integer> order_to_play){

        order_to_play.add(1);
        order_to_play.add(2);
        order_to_play.add(3);
        Collections.shuffle(order_to_play);
    }

    public static void main_game(player A, player B, player C){
        counter_of_death = 0;
        List<Integer> order_to_play = new ArrayList<>();
        game_order(order_to_play);// choose the order

        while(counter_of_death < 2){
            for (int i=0; i<3; i++) {
                if (order_to_play.get(i) == 1 && !A.is_dead) { //A turn and she is not dead
                    A_turn(A, B, C);
                }
                if (order_to_play.get(i) == 2 && !B.is_dead) { //B turn and he is not dead
                    B_turn(A, B, C);
                }
                if (order_to_play.get(i) == 3 && !C.is_dead) {//C turn and he is not dead
                    C_turn(A, B, C);
                }
            }
        }

        //checks who is still alive - winner

        if (!A.is_dead){
            A.wins++;
        }
        else if(!B.is_dead){
            B.wins++;
        }
        else if(!C.is_dead){
            C.wins++;
        }
    }
    /*
    100% win
     */
    public static void A_turn(player A, player B, player C){
        if (!B.is_dead){
            B.is_dead = true; //A kill B
            counter_of_death++;
        }
        else if(!C.is_dead){
            C.is_dead = true;
            counter_of_death++;
        }
    }

    /*
    80%
     */
    public static void B_turn(player A, player B, player C) {
        double random = Math.random();
        if (!A.is_dead && random<B.probability_to_win){ //random < 0.8
            A.is_dead=true;
            counter_of_death++;
        }
        else if (!C.is_dead && random<B.probability_to_win){ //random < 0.8
            C.is_dead =true;
            counter_of_death++;
        }

    }

    /*
    50%
     */
    public static void C_turn(player A, player B, player C){
        double random = Math.random();
        if (A.is_dead && !B.is_dead){ //A is dead and B not - C does not need to shoot in the air - he can try to kill B
           if (random<C.probability_to_win){ // random<0.5
               B.is_dead=true;
               counter_of_death++;
           }

        }
        if (B.is_dead && !A.is_dead){ //B is dead and A not - C does not need to shoot in the air - he can try to kill A
            if (random<0.5){
                A.is_dead=true;
                counter_of_death++;
            }
        }
        //if their are both dead so C wins ... nothing to do
        //if their are both alive - shoot in the air - nothing to do ...
    }

    public static void main(String[] args) {
       player A = new player(1.0);
       player B= new player(0.8);
       player C= new player(0.5);

        for (int i=0; i<5000000; i++){
            main_game(A, B, C);
            A.is_dead=false;
            B.is_dead=false;
            C.is_dead=false;
        }

        System.out.println("A wins: " + A.wins/5000000.0);
        System.out.println("B wins: " + B.wins/5000000.0);
        System.out.println("C wins: " + C.wins/5000000.0);

    }
}
