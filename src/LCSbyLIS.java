import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCSbyLIS {

    public static class Index {

        char c;
        int index_in_array;
        List<Integer> indexes;

        public Index(char key, int index_in_array){

            this.c = key;
            this.index_in_array = index_in_array;
            indexes = new ArrayList<>();
        }

        public int getIndex_in_array() {
            return this.index_in_array;
        }

        public List<Integer> get_list() {
            return this.indexes;
        }

        public char getC() {
            return this.c;
        }

        public void setC(char c) {
            this.c = c;
        }

        public void set_index_in_array(int index_in_array) {
            this.index_in_array = index_in_array;
        }

        public void add_to_list(int index) {
            this.indexes.add(0, index); //adds to the beginning of the list, so the bigger index will be at the beginning
        }
    }

    public static int index_binary_search(int[][] arr, int end, int element_to_insert){

        int startIndex = 0, middle =end/2, endIndex = end;

        if(element_to_insert<arr[startIndex][startIndex]){ //element is smaller then the element in the first cell - should replace it
            return 0;
        }
        else if(element_to_insert>arr[endIndex][endIndex]){ //element greater then the element in the end of the array - should insert this element after it
            return endIndex+1;
        }
        while(startIndex<=endIndex){

            if (element_to_insert>arr[middle][middle]){
                startIndex=middle+1;
            }
            else{//element < arr[middle]
                endIndex = middle-1;
            }
            middle = (startIndex+endIndex+1)/2;
        }
        return middle;
    }
    /*
    O(n^2 + nlogn)
     */
    public static int[] one_longest_LIS(List<Integer> list){
        int pointer =0;

        int[][] helper = new int[list.size()][list.size()];
        helper[0][0] = list.get(0);

        for (int i=1; i<list.size(); i++){ //O(n)
            int next_index = index_binary_search(helper, pointer, list.get(i)); //O(logn)
            helper[next_index][next_index] = list.get(i);
            copy_prev(helper, next_index, next_index-1); //O(n)
            if (next_index > pointer){
                pointer++;
            }
        }
        System.out.println("length of LIS: "+ (pointer+1));
        int[] ans = new int[pointer+1];
        for (int i=0; i<pointer+1; i++){  //copy the last row of the helper matrix - this is the longest increasing subarray
            ans[i] = helper[pointer][i];
        }
        return ans;
    }

    public static void copy_prev(int[][] arr, int row, int col){

        while(col>=0){
            arr[row][col] = arr[row-1][col];
            col--;
        }

    }
    /*
    LCS by LIS (4 steps)

    O(m + m*n + (m*n)^2 +mnlogmn)
    O(m*n*log(m*n))
     */
    public static String LcsByLis(String s1, String s2) {

        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        /*
        step 1 - building an array, each index in the array represent an object type Index.
        Index is like a node, its contains the char its represents, the index in the array, and list of indexes
        that represents the appearance of the char in the original string (could be number of appearance)
         */

        Index[] helper1 = new Index[26];

        for (int i = 0; i < s1.length(); i++) { //O(min(m+n))
            int indexInHelper = s1.charAt(i) - 'a';
            if (helper1[indexInHelper] == null) {
                helper1[indexInHelper] = new Index(s1.charAt(i), indexInHelper);
                helper1[indexInHelper].add_to_list(i);
            } else {
                helper1[indexInHelper].add_to_list(i); // already created node with this char
            }

        }

        /*
        step 2 - building a list helper2
        iterate through s2 letters, each letter - check the helper1 array we build earlier, and take its list of indexes
        that represents the appearance in s1.
        put it in helper2 list from the big index to low
         */


        List<Integer> helper2 = new ArrayList<>(); //maximum size it can be is s1*s2 in case s1 is identical to s2 like: aaa , aaa
        for (int i = 0; i < s2.length(); i++) {
            int index_in_helper1 = s2.charAt(i) - 'a';
            if (helper1[index_in_helper1] != null) {
                Index tempNode = helper1[index_in_helper1];
                for (int j = 0; j < tempNode.get_list().size(); j++) {
                    helper2.add(tempNode.get_list().get(j)); //removes and put in helper2 the indexes the char is appears in s1, but from the bigger index to the lowest
                }
            }
        }

        /*
        step 3 - send helper2 to LIS to find the longest increasing subArray
         */

        int[] ans = one_longest_LIS(helper2);

        /*
        step 4 - convert the array ans to string
         */

        String lis="";
        for (int i=0; i<ans.length; i++){
            lis = lis + s1.charAt(ans[i]);
        }

        return lis;
    }

    public static void main(String[] args) {
        String s1 = "bdcaba";
        String s2 = "abcbdab";

        String a = LcsByLis("abacav","bav");


        System.out.println(a);
    }

}
