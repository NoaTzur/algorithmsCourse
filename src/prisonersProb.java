import java.util.Arrays;
import java.util.Random;

public class prisonersProb {



    /*
    in this problem = the gard is choosing the number of iterations.
    the array is fill in zero.
    i chose the first cell to be the counter prisoner.
    each iteration we are choosing randomly one index of the array. [0-99]
    if the index chosen is 0 - counter prison get in the room.
    if he see that the lamp is off - we will do counter++ to this cell in index 0 and will turn on the light
    if the light is on - do nothing

    if another prisoners is chosen [1-99] and not the counter :
    if the cell of the chosen prisoner is 0 AND the light on - the prisoner will turn off the light and his cell change to 1

    if the cell of the chosen prisoner is 0 AND the light is off - do nothing

    if the cell of the chosen prisoner is 1 - do nothing
     */
    public static void all_prisoners_was_in(int[] prisoners){

        boolean light = true;
        int moves =0;

        while(prisoners[0]<prisoners.length-1){

            moves++;
            Random rand = new Random();
            int randomIndex = rand.nextInt(100); //generate numbers between [0-99]

            if(randomIndex == 0){ //counter prisoner is chosen
                if (!light){
                    prisoners[0]++;
                    light=true;
                }
            }
            else{ //other prisoner from the 99 that not the counter is chosen
                if(prisoners[randomIndex] == 0 && light) {
                   prisoners[randomIndex]=1;
                   light=false;
                }
            }

        }
        System.out.println("moves it takes: " + moves);
        System.out.println(Arrays.toString(prisoners));
        //can add an over check - iterate through all array and check if all the cells 1-99 contains 1
    }

    public static void lampUnknownCondition(int[] prisoners){

        int randomLight = (int)(Math.random()*2);
        boolean light;
        if(randomLight == 0){
            light = false;
        }
        else{
            light = true;
        }
        int moves =0;

        while(prisoners[0] < 2*prisoners.length-2){
            moves++;
            Random rand = new Random();
            int randomIndex = rand.nextInt(100); //generate numbers between [0-99]

            if (randomIndex == 0 && !light){ //if the prisoner is the counter and the lamp is off-get in the room, turn on the light and count++

                light = true;
                prisoners[0]++;
            }
            else{ //(other prisoner chose) or (the counter prisoner have been chose and the lamp is on)
                if(prisoners[randomIndex]<2 && light){ //prisoner didnt finish his job and the lamp is on - turn it off
                    prisoners[randomIndex]++;
                    light = false;
                }

            }

        }
        System.out.println("moves it takes: " + moves);
        System.out.println(Arrays.toString(prisoners));
        //can add an over check - iterate through all array and check if all the cells 1-99 contains 1
    }

    public static int lightIsUnknown(int num) {
        Random rand = new Random();
        boolean flag = rand.nextBoolean();
        int arr[] = new int[num];
        int iterations = 0;
        int counter =2;
        int index;
        while (counter < 2*num) {
            iterations++;

            index = rand.nextInt(100); //generate numbers between [0-99]
            if (index==0) {
                if (!flag) {
                    flag = true;
                    counter++;
                }
            } else {
                if (flag&&arr[index]<2) {
                    arr[index] = arr[index]+1;
                    flag = false;
                }
            }
        }
        boolean ok = true;
        for (int i = 1; i<num; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
        System.out.println("Correct: "+ok);
        System.out.println("Counter: "+counter);
        return iterations;
    }


    public static void main(String[] args) {
         int[] aa = new int[100];
       all_prisoners_was_in(aa);
        int[] aa1 = new int[100];
        lampUnknownCondition(aa1);
        //System.out.println(lightIsUnknown(100));
    }
}
