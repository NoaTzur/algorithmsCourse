public class iterativeFibb {



    /*
    this function is rely on the algorithm for calculation a pow iteratively
    calc the matrix power with the help of iterative pow algo - explanation is there
     */
    public static long iterativeFibb(int indexNum){

        int temp = indexNum;
        if(temp == 1 || temp == 2){
            return 1; // fibbonachi: 1, 1, 2, 3, 5, 8,13 , 21.... index number 1 or two is 1
        }
        long mat[][] = {{1,1},
                        {1,0}};

        long fibbMat[][] = {{1,1},
                            {1,0}};

        while(temp != 0){
            if(temp%2 != 0){
                fibbMat = multMat(fibbMat, mat);
            }
            mat = multMat(mat,mat);
            temp = temp/2;
        }

        return fibbMat[0][0];

    }

    public static long[][] multMat(long[][] mat1, long[][] mat2){

        long x =  mat1[0][0]*mat2[0][0] + mat1[0][1]*mat2[1][0];
        long y =  mat1[0][0]*mat2[0][1] + mat1[0][1]*mat2[1][1];
        long z =  mat1[1][0]*mat2[0][0] + mat1[1][1]*mat2[1][0];
        long w =  mat1[1][0]*mat2[0][1] + mat1[1][1]*mat2[1][1];
        mat1[0][0] = x;
        mat1[0][1] = y;
        mat1[1][0] = z;
        mat1[1][1] = w;
        return mat1;

    }
    public static void main (String args[])
    {
        int n = 7;
        System.out.println(iterativeFibb(n));
    }

    /*
    matrix looks like:

        | Fn+2    Fn+1 |
        |              |
        | Fn+1    Fn   |

        ==

        | 1     0 | ^n
        | 1     0 |

        thats mean = insert n and you get the Fn+2 !!!!!!!1
        n = 7 , gets the Fn+2 == 34 (F9)
     */
}
