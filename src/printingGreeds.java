public class printingGreeds {


    public static void printingGreeds(int[][] greed){

        for (int rows=0; rows<greed.length; rows ++){
            for (int cols=0; cols<greed[0].length; cols++){
                System.out.print(greed[rows][cols] + "  ");
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        int[][] aa = {{5,3,4,6,7},
                {1324,4,6,6,3},
                {2,4,6,3,2,3}};
        printingGreeds(aa);
        System.out.println(aa[0][0]);
    }

}
