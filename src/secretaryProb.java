import java.util.Arrays;

public class secretaryProb {

    /*
    each patient can be called as T1 ... Tn

    when:
    T1 = t1
    T2 = t1 + t2
    .
    .
    .
    Tn = t1+...+tn

    it means T2 wait in the office the time T1 wait, plus the time T2 was in the doctor
    the best way we can get for average for all patients is when
    we will arrange the queue as sorted from the lower time to be treated patient to the hight time .

     */

    public static int averageWaiting(int[] patients){
        int sum=0;
        int temp=0;
        Arrays.sort(patients);
        for (int i=0; i<patients.length; i++){
            temp = temp + patients[i];
            sum = sum + temp;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,19};
        System.out.println(averageWaiting(arr));
    }
}
