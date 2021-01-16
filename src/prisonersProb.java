import java.util.Random;

public class prisonersProb {



    /*
    in this problem = the gard is choosing the number of iterations.
    the array is fill in zero.
    i chose the first cell to be the counter prisoner.
    each iteration we are choosing randomly one index of the array. [0-99]
    if the index chosen is 0 - counter prison get in the room.
    if he see that the lamp is on - we will do counter++ to this cell in index 0 and will turn off the light
    if the light is off - do nothing

    if another prisoners is chosen [1-99] and not the counter :
    if the cell of the chosen prisoner is 0 AND the light is off - the prisoner will turn on the light and his cell change to 1

    if the cell of the chosen prisoner is 0 AND the light is on - do nothing

    if the cell of the chosen prisoner is 1 - do nothing
     */
    public static boolean all_prisoners_was_in(int[] prisoners, int iteration){

        boolean light = false;
        while(iteration>0){
            Random rand = new Random();
            int randomIndex = rand.nextInt(100); //generate numbers between [0-99]

            if(randomIndex == 0){ //counter prisoner is chosen
                if (light){
                    prisoners[0]++;
                    light=false;
                }
            }
            else{ //other prisoner from the 99 that not the counter is chosen
                if(prisoners[randomIndex] == 0 && light==false) {
                   prisoners[randomIndex]=1;
                   light=true;
                }
            }
            iteration--;
        }
        return prisoners[0] == prisoners.length;
    }

}
