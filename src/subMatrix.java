import java.util.Arrays;

public class subMatrix {
    /*
    O(m*n)
     */
    public static int subMat(int[][] greed) {

        int maxLength = 0;
        int index_row = 0;
        int index_col = 0;
        int[][] helper = new int[greed.length][greed[0].length];

        for (int rows = 0; rows < greed.length; rows++) {
            helper[rows][0] = greed[rows][0];
        }

        for (int cols = 0; cols < greed[0].length; cols++) {
            helper[0][cols] = greed[0][cols];
        }

        for (int rows = 1; rows < greed.length; rows++) {
            for (int cols = 1; cols < greed[0].length; cols++) {
                if (greed[rows][cols] != 0) {

                    int temp_min = Math.min(helper[rows - 1][cols], helper[rows][cols - 1]);
                    helper[rows][cols] = Math.min(temp_min, helper[rows - 1][cols - 1]) + 1;
                    if (helper[rows][cols] > maxLength) {
                        maxLength = helper[rows][cols];
                        index_col = cols;
                        index_row = rows;
                    }
                }
            }
        }
        printingGreeds(helper);

        return maxLength;
    }


    public static void printingGreeds(int[][] greed){

        for (int rows=0; rows<greed.length; rows ++){
            for (int cols=0; cols<greed[0].length; cols++){
                System.out.print(greed[rows][cols] + "  ");
            }
            System.out.println();

        }
    }
    public static void main(String[] args) {
        int[][] mat = {{0, 0, 0, 1, 0},
                        {0,1,1,1,0},
                       {0,1,1,1,0},
                       {0,1,1,1,0},
                       {0,1,1,1,0},
                       {0,1,0,1,1}};

        int[][] a = {{0,1,1,1,0},
                {1,1,1,1,0},
                {0,1,1,0,0},
                {1,1,0,1,0}};

        int arr[][] = {{1,0,1,0},{1,1,1,1},{0,1,1,0},{1,1,0,0}};

        int ans = subMat(a);
        System.out.println(ans);
        printingGreeds(a);
        //subRectangle(mat);
    }


    //Questions from exams

    /*
    find the subRectangle with just ones in it from a given rectangle
    the formula = if the value of L*U that came from the cell up to the main cell so
    i take the U value of this cell and add 1 to it
    this will be the U value of the main cell . in order to fill L value of the main cell we will do
    min(L )from the 3 cells that adjacent to the main cell (the one up to it, the from left and from the diagonal up&left)
    and plus 1

    if the L*U is bigger from the cell left to the main cell
    we take L value +1
    and min(U) from the 3 cells adjacent
     */

    public static class cell{
        int L;
        int U;
        //(U,L) , U is for UP - represent the number of rows that contains one's there is up to the cell
        //L - represents the number of columns contains one's there is left to the cell
        // L*U gives us the size of the rectangle contains only one's!!
        public cell(int U, int L){
            this.L=L;
            this.U=U;
        }

    }

    public static void subRectangle(int[][] rectangle){

        cell[][] helperRec = new cell[rectangle.length][rectangle[0].length];
        int max_rows = 0;
        int max_cols = 0;

        for (int rows=0; rows<rectangle.length; rows++){  //first initialize - rows
            int first_init = rectangle[rows][0];
            helperRec[rows][0] = new cell(first_init, first_init);
        }
        for (int cols=0; cols<rectangle[0].length; cols++){  //first initialize - cols
            int first_init = rectangle[0][cols];
            helperRec[0][cols] = new cell(first_init, first_init);
        }

        for(int rows=1; rows<rectangle.length; rows++){
            for (int cols=1; cols<rectangle[0].length; cols++){
                int from_up = helperRec[rows-1][cols].L * helperRec[rows-1][cols].U;
                int from_left = helperRec[rows][cols-1].L* helperRec[rows][cols-1].U;

                if (from_up>from_left){
                    int U = helperRec[rows-1][cols].U +1;
                    int min_L = Math.min(helperRec[rows-1][cols].L, helperRec[rows][cols-1].L);
                    int L = Math.min(min_L, helperRec[rows-1][cols-1].L) +1;
                    helperRec[rows][cols] = new cell(U,L);


                    if (max_rows<helperRec[rows][cols].U){
                        max_rows = helperRec[rows][cols].U;
                    }
                    if(max_cols<helperRec[rows][cols].L){
                        max_cols = helperRec[rows][cols].L;
                    }

                }
                else{ //from_up <= from_left
                    int L = helperRec[rows-1][cols].L +1;
                    int min_U = Math.min(helperRec[rows-1][cols].U, helperRec[rows][cols-1].U);
                    int U =  Math.min(min_U, helperRec[rows-1][cols-1].U) +1;
                    helperRec[rows][cols]= new cell(U,L);


                    if (max_rows<helperRec[rows][cols].U){
                        max_rows = helperRec[rows][cols].U;
                    }
                    if(max_cols<helperRec[rows][cols].L){
                        max_cols = helperRec[rows][cols].L;
                    }
                }
            }
        }

        System.out.println("num of cols of subRectangle: " + (max_cols-1));
        System.out.println("num of rows of subRectangle: " + (max_rows-1));

    }

    /*
    question 2 - what is the number of subMatrix in size n*n
    we run the original function of subMat. after the helper matrix is all filled - iterate through all cells.
    if we see a cell that contains integer equals ro bigger n - counter ++;

    return the counter.
     */
}
