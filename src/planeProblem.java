import java.util.ArrayList;
import java.util.List;

public class planeProblem {

    static node[][] greed;
    static int right; //cols
    static int up; // rows
    static List<String> allPaths;
    static int count = 0;

    public static class node {
        private int weight_for_x;
        private int weight_for_y;
        private int weight_of_this_node;
        private int num_of_low_path;
        private List<String> paths_to_this_node;

        public node(int weight_for_x, int weight_for_y) {
            this.weight_for_x = weight_for_x;
            this.weight_for_y = weight_for_y;
            this.weight_of_this_node = 0;
            this.num_of_low_path = 0;
            this.paths_to_this_node = new ArrayList<>();
        }

        public int getNum_of_low_path() {
            return num_of_low_path;
        }

        public int getWeight_for_x() {
            return weight_for_x;
        }

        public int getWeight_for_y() {
            return weight_for_y;
        }

        public int getWeight_of_this_node() {
            return weight_of_this_node;
        }

        public void setNum_of_low_path(int num_of_low_path) {
            this.num_of_low_path = num_of_low_path;
        }

        public void setWeight_of_this_node(int weight_of_this_node) {
            this.weight_of_this_node = weight_of_this_node;
        }

        public List<String> get_list_of_paths() {
            return this.paths_to_this_node;
        }

    }

    public planeProblem(node[][] greed) {
        this.greed = greed;
        this.allPaths = new ArrayList<>();
        this.right = greed[0].length; //the cols
        this.up = greed.length; //the rows
    }

    /*
    O(rows*cols)
    this should be called first - its updates the weights of all nodes
     */
    public static void update_the_path_weights() {
        for (int rows = 1; rows < up; rows++) {
            greed[rows][0].setWeight_of_this_node(greed[rows - 1][0].getWeight_for_y() + greed[rows - 1][0].getWeight_of_this_node());
        }

        for (int cols = 1; cols < right; cols++) {
            greed[0][cols].setWeight_of_this_node(greed[0][cols - 1].getWeight_for_x() + greed[0][cols - 1].getWeight_of_this_node());
        }

        for (int rows = 1; rows < up; rows++) {
            for (int cols = 1; cols < right; cols++) {
                int from_right = greed[rows][cols - 1].getWeight_for_x() + greed[rows][cols - 1].getWeight_of_this_node();
                int from_up = greed[rows - 1][cols].getWeight_for_y() + greed[rows - 1][cols].getWeight_of_this_node();
                greed[rows][cols].setWeight_of_this_node(Math.min(from_right, from_up));
                //takes the path that has the minimum weight on its edges between the nodes
            }
        }
    }

    /*
    O(rows*cols)
    function number 2 - updates every node the number of paths(best-low paths) exist to it
     */
    public static void numbers_of_low_paths() {
        for (int rows = 1; rows < up; rows++) {
            greed[rows][0].setNum_of_low_path(1);
        }
        for (int cols = 1; cols < right; cols++) {
            greed[0][cols].setNum_of_low_path(1);
        }

        for (int rows = 1; rows < up; rows++) {
            for (int cols = 1; cols < right; cols++) {
                int from_right = greed[rows][cols - 1].getWeight_for_x() + greed[rows][cols - 1].getWeight_of_this_node();
                int from_up = greed[rows - 1][cols].getWeight_for_y() + greed[rows - 1][cols].getWeight_of_this_node();
                if (from_right < from_up) { //takes the number of path from the right node
                    greed[rows][cols].setNum_of_low_path(greed[rows][cols - 1].getNum_of_low_path());
                } else if (from_right > from_up) { //takes the number of path from the downward node
                    greed[rows][cols].setNum_of_low_path(greed[rows - 1][cols].getNum_of_low_path());
                } else { //both path to this node are equals (if looking the weights) so we take the number of paths from both
                    greed[rows][cols].setNum_of_low_path(greed[rows - 1][cols].getNum_of_low_path() + greed[rows][cols - 1].getWeight_of_this_node());   //directions and adds them - because we can get to this node from the right node and from the node underneath this node

                }
            }
        }
    }

    /*
    O(m+n) - going on one path so rows + cols
    third function - start from the end of the greed, and do the reverse calculate of the path.
    if the low weight is from the left node to the main node(means we needed to ge right in the original path) - so add the list(path) 'R'
    if the low weight is from the node that underneath the main node - add the list 'U'
     */
    public static List<String> print_one_path() { //the best one

        List<String> path = new ArrayList<>();
        int upIndex = up - 1; //rows
        int rightIndex = right - 1; //cols

        while (upIndex > 0 && rightIndex > 0) { //didnt get to the left border of the greed or to the down border
            int from_right = greed[upIndex][rightIndex - 1].getWeight_for_x() + greed[upIndex][rightIndex - 1].getWeight_of_this_node();
            int from_up = greed[upIndex - 1][rightIndex].getWeight_for_y() + greed[upIndex - 1][rightIndex].getWeight_of_this_node();
            if (from_right < from_up) {//means the low path go from the node that is on the left from the main node(we need to go right)
                path.add(0, "R");
                rightIndex--;
            } else { //they could be equals as well, so i choose to go down in this case
                path.add(0, "U");
                upIndex--;
            }
        }
        while (upIndex > 0) { //rightIndex ==0 means that we get to the left border of the greed
            path.add(0, "U");
            upIndex--;
        }
        while (rightIndex > 0) { // upIndex ==0 means that we get to the button of the greed
            path.add(0, "R");
            rightIndex--;
        }
        return path;
    }

    public static void print_list(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + "-> ");
        }
        System.out.println(list.get(list.size() - 1));
    }

    /*
    O(rows+cols) * number of best paths,  returns List<String> - list of strings, each string is a path
    in case of allot of paths - the recursion can not work !! (stack overflow)
    so we can put an supremum on the number of recursion split ! so it doest explode
    in the end of this function, allPaths list will hold all the paths
     */
    public static void print_all_paths_rec(int upIndex, int rightIndex, String path){
        if (upIndex>0 && rightIndex>0){
            int from_right = greed[upIndex][rightIndex - 1].getWeight_for_x() + greed[upIndex][rightIndex - 1].getWeight_of_this_node();
            int from_up = greed[upIndex - 1][rightIndex].getWeight_for_y() + greed[upIndex - 1][rightIndex].getWeight_of_this_node();

            if (from_right<from_up){
                print_all_paths_rec(upIndex, rightIndex-1, "R"+path);
            }
            else if(from_right>from_up){
                print_all_paths_rec(upIndex-1, rightIndex, "U"+path);
            }
            else { //from_right==from_up, there is a split
                print_all_paths_rec(upIndex, rightIndex-1, "R"+path);
                print_all_paths_rec(upIndex-1, rightIndex, "U"+path);
            }
        }
        if (rightIndex>0 && upIndex==0){ // upIndex ==0 we get to the downward boarder, need to go all the way left
            print_all_paths_rec(upIndex, rightIndex-1, "R"+path);
        }
        if (upIndex>0 && rightIndex==0){ // we get to the left boarder, need to go all the way down
            print_all_paths_rec(upIndex-1, rightIndex, "U"+path);
        }
        if (upIndex==0 && rightIndex==0){
            allPaths.add(path);
        }
    }


    /*
    this function will return the index of the main list that holds the list with the least turns.
    we want to find the minimum turns in the path we have by looking in the sequence RU or UR
    and for each appearance of this sequence - counter will increase by 1
    the list with the lowest counter will win
     */
    public static int min_turns_in_path() {
        int num_of_paths = allPaths.size();
        int index_of_winner_String = 0;
        int min_number_of_turns = Integer.MAX_VALUE;

        if (num_of_paths == 1) {
            return 0; // if the list has just one optimal path - the index is 0
        }

        for (int i = 0; i < num_of_paths; i++) {
            int counter_of_turns = 0;
            String temp = allPaths.get(i);
            for (int len = 0; len < temp.length() - 1; len++) {
                if (temp.charAt(len) != temp.charAt(len + 1)) { //can add here && counter_of_turns lower then the min_number_turns because if its bigger there is no need to continue, no way it will be the winner
                    counter_of_turns++;
                }
            }
            if (counter_of_turns < min_number_of_turns) {
                min_number_of_turns = counter_of_turns;
                index_of_winner_String = i;
            }
        }
        return index_of_winner_String;
    }

    /*
    O(m*n), most of time less then this
    this function will calc the weight from node src to node dest
    if we want to check if specific node is on a best path, we can run this function :
    once - (start node(0,0), the node we want to check(i,j))
    second run - (the node we want to check(i,j), the end node(rows,cols))

    if once_second == the low path weight , the node is on a best path !
     */
    public static int weight_from_to(int srcI, int srcJ, int destI, int destJ) {
        //update_the_path_weights();
        //int weight = 0;

        greed[srcI][srcJ].setWeight_of_this_node(0);

        for (int rows = srcI + 1; rows <= destI; rows++) {
            greed[rows][srcJ].setWeight_of_this_node(greed[rows - 1][srcJ].getWeight_for_y() + greed[rows - 1][srcJ].getWeight_of_this_node());
        }

        for (int cols = srcJ + 1; cols <= destJ; cols++) {
            greed[srcI][cols].setWeight_of_this_node(greed[srcI][cols - 1].getWeight_for_x() + greed[srcI][cols - 1].getWeight_of_this_node());
        }

        for (int rows = srcI + 1; rows <= destI; rows++) {
            for (int cols = srcJ + 1; cols <=destJ; cols++) {
                int from_right = greed[rows][cols - 1].getWeight_for_x() + greed[rows][cols - 1].getWeight_of_this_node();
                int from_up = greed[rows - 1][cols].getWeight_for_y() + greed[rows - 1][cols].getWeight_of_this_node();
                greed[rows][cols].setWeight_of_this_node(Math.min(from_right, from_up));
            }
        }
        return greed[destI][destJ].getWeight_of_this_node();
    }

    public static boolean is_node_in_path(int iSRC, int jSRC){
        int theWeight = greed[up-1][right-1].getWeight_of_this_node();

        int start_src = weight_from_to(0,0, iSRC, jSRC);
        int src_end = weight_from_to(iSRC, jSRC, up-1, right-1);

        return (start_src+src_end) == theWeight;
    }

    /*
    this function should return a list of all nodes
     */
    public static List<node> all_nodes_in_paths() {
        List<node> allNodes = new ArrayList<>();
        allNodes.add(greed[0][0]); //first node
        for (int i = 0; i < allPaths.size(); i++) {
            String temp = allPaths.get(i);
            int upIndex = 0;
            int rightIndex = 0;
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == 'R') {
                    rightIndex++;
                }
                if (temp.charAt(j) == 'U') {
                    upIndex++;
                }
                allNodes.add(greed[upIndex][rightIndex]);
            }
        }
        return allNodes;
    }

    /*
    O(m*n) not recursive - we are filling each node list of paths while running on the greed once
    for each node - we check what direction it is best to go to it.
    if the best path is from the left node(going right) - so take all paths of this left node and "copy" (shallow copy)
    the lists to the main node. to each list we add "R".

    if the best is from the downward node - so copy the lists and add "U" each one

    if the directions are equal - take all the lists from the left node and from the downward node
    and adds them "U" or "R" respectively.
     */
    public static void print_all_paths_iterative() {
        //greed is already filled

        for (int i=0; i<up; i++){
            greed[i][0].get_list_of_paths().add("");
        }
        for (int i=0; i<right; i++){
            greed[0][i].get_list_of_paths().add("");
        }
        //initiate?
        for (int rows = 1; rows < up; rows++) {
            for (int cols = 1; cols < right; cols++) {
                int from_right = greed[rows][cols - 1].getWeight_for_x() + greed[rows][cols - 1].getWeight_of_this_node();
                int from_up = greed[rows - 1][cols].getWeight_for_y() + greed[rows - 1][cols].getWeight_of_this_node();

                if (from_right<from_up){
                    //for each list (path) in the node that located on the left side of the main node we add "R" and adds the lists ti the main node
                    for (int i=0; i<greed[rows][cols - 1].get_list_of_paths().size(); i++){
                        greed[rows][cols].get_list_of_paths().add(greed[rows][cols - 1].get_list_of_paths().get(i)+"R");
                    }
                }
                else if(from_right>from_up){
                    //for each list (path) in the node that located downward the main node we add "R" and adds the lists ti the main node
                    for (int i=0; i<greed[rows-1][cols].get_list_of_paths().size(); i++){
                        greed[rows][cols].get_list_of_paths().add(greed[rows-1][cols].get_list_of_paths().get(i)+"U");
                    }
                }
                else{ // we can get from the left node and from the node beneath
                    for (int i=0; i<greed[rows-1][cols].get_list_of_paths().size(); i++){
                        greed[rows][cols].get_list_of_paths().add(greed[rows-1][cols].get_list_of_paths().get(i)+"U");
                    }
                    for (int i=0; i<greed[rows][cols - 1].get_list_of_paths().size(); i++){
                        greed[rows][cols].get_list_of_paths().add(greed[rows][cols - 1].get_list_of_paths().get(i)+"R");
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        int m = 4;	//  number of rows
        int n = 5;		//  number of columns

        greed = new node[m][n];
        // the first row
        greed[0][0] = new node(3,1);
        greed[0][1] = new node(5,2);
        greed[0][2] = new node(10,4);
        greed[0][3] = new node(4,2);
        greed[0][4] = new node(0,4);
        // the second row
        greed[1][0] = new node(3,8);
        greed[1][1] = new node(11,5);
        greed[1][2] = new node(1,3);
        greed[1][3] = new node(5,3);
        greed[1][4] = new node(0,2);
        // the third row
        greed[2][0] = new node(8,3);
        greed[2][1] = new node(6,3);
        greed[2][2] = new node(4,1);
        greed[2][3] = new node(6,5);
        greed[2][4] = new node(0,4);
        // the forth row
        greed[3][0] = new node(4,0);
        greed[3][1] = new node(4,0);
        greed[3][2] = new node(5,0);
        greed[3][3] = new node(3,0);
        greed[3][4] = new node(0,0);

        planeProblem aa = new planeProblem(greed);
        update_the_path_weights(); //build the greed with weights
        numbers_of_low_paths(); // build each node with its paths to him
//        System.out.println("number of best paths is: " + greed[m-1][n-1].getNum_of_low_path());
//        System.out.println("the weight(price) of the best path: "+ greed[m-1][n-1].getWeight_of_this_node());
//        List<String> as = print_one_path();
//        System.out.println("This is one best path: ");
//        print_list(as); //print one best path
//
//        print_all_paths_rec(m-1, n-1, "");  //build allPaths with all best paths there is
//        System.out.println("number of all best paths: " + allPaths.size() + ", and the paths are: ");
//        for (int i=0; i<allPaths.size(); i++){
//            System.out.println(allPaths.get(i));
//        }
//        System.out.println();
//        int min_turns_index = min_turns_in_path(); //returns the index of the list with the lowest turns
//        System.out.println("Path with lowest turns is: " + allPaths.get(min_turns_index));

        print_all_paths_iterative();
        System.out.println(greed[m-1][n-1].get_list_of_paths().size());
        for (int i=0; i<greed[m-1][n-1].get_list_of_paths().size(); i++){
            System.out.println(greed[m-1][n-1].get_list_of_paths().get(i));
        }
    }

}

