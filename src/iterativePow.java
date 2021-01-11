public class iterativePow {

    public double iterativePow(double base, int exponent){ //O(logn) because of the exponent /= 2

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
    public void recursivePow(double base, int exponent, double ans){ // ans == 1 in the beggining !!!!!!

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
}
