public class iterativePow {

    /*
    in order to calculate power in logn steps, we will represent the power in binary
    example: X^11 =
     2^0 + 2^1 + 2^3 = 11

     so -  x^(2^0) * x^(2^1) * x^(2^3) = x^1 * x^2 *  x^8 = x^11
     how does it helps ?
     we can represent the power as binary like this : 1    0     1    1
                                                      2^3  2^2  2^1   2^0
     2^2 is represent with 0 because we didn't use it to calc x^11.

     so we convert the power to binary in the way we learned : diverge in 2 and take the remainder, if the reminder is 1 we calc the
     ans = ans*base and if 0 we do nothing ...

     */
    //O(logn)
    public static double iterativePow(double base, int exponent){ //O(logn) because of the exponent /= 2

        double ans = 1;

        while(exponent>0){
            if(exponent%2 == 1){
                ans = ans*base;
            }
            base = base*base;
            exponent/=2;
        }
        return ans;
    }

    //O(logn) too
    public static void recursivePow(double base, int exponent, double ans){ // ans == 1 in the beggining !!!!!!

        if(exponent == 0){
            System.out.println(ans); //arrive to the end
        }
        if(exponent%2 == 1){
            ans = ans*base;
        }
        exponent/=2;
        base = base*base;
        recursivePow(base, exponent, ans);

    }

    public static void main(String[] args) {
        System.out.println(iterativePow(2,3));
    }
}
