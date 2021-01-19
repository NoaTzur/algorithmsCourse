public class aliceANDbob {

    /*
    100% winning
    alice choose opposite from what she's getting
    bob choose what he is getting
     */

    public int flipTheCoin(){
        return (int)Math.round(Math.random()); // math.random (0,1) so round to 0 or 1
    }
    public boolean strategy1(){
        int alice = flipTheCoin();
        int bob = flipTheCoin();

        int oppositeAlice = Math.abs(alice-1); // !alice .... Math.abs == absolute value
        return (bob == alice) || (oppositeAlice == bob);
    }

    /*
    50 % winning
    alice choose randomly
     */
    public boolean strategy2(){
        int alice = flipTheCoin();
        if(alice < 1){
            return true;
        }
        return false;

    }


    /*
    75 % winning
    alice choose 1 and bob choose 1
    */
    public boolean strategy3(){
        int alice = flipTheCoin();
        int bob = flipTheCoin();
        return (bob==1)||(alice==1);
    }

    /*
    50% winning
    alice and bob choose what they gets
    */
    public boolean strategy4(){
        int alice = flipTheCoin();
        int bob = flipTheCoin();
        return alice==bob;
    }

}
