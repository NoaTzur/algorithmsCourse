public class TurtleHare {

    public static class node{

        private int key=0;
        private node next;

        public node(int key){
            this.key = key;
            this.next = null;
        }

        public void setNext(node next) {
            this.next = next;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getKey() {
            return this.key;
        }

        public node getNext() {
            return this.next;
        }
    }

    /*
    returns true if there is cycle in this linked list of nodes
     */
    public static boolean listHasLoop(node root){
        node turtle = root;
        node hare = root;

        while(turtle.getNext() != null && hare.getNext() != null){
            turtle = turtle.getNext();
            hare = hare.getNext().getNext();
            if (turtle == null || hare == null){
                return false;
            }
            if (turtle.getKey() == hare.getKey()){
                return true;
            }
        }
        return false;
    }
    /*
    returns the node meeting - calling this function if and only if the linked list has cycle !!!!
     */
    public static node theMeetingNode(node root) {
        if (listHasLoop(root)) {
            node turtle = root;
            node hare = root;
            turtle = turtle.getNext();
            hare = hare.getNext().getNext();

            while (turtle.getKey() != hare.getKey()) {
                turtle = turtle.getNext();
                hare = hare.getNext().getNext();
            }
            return hare;
        }
        return null; //there isnt cycle here !!! no meeting node
    }

    /*
    returns the beginning node of the cycle
     */
    public static node theBeginningOfCycle(node root, node meeting){
        node turtle = root;
        node hare = meeting;

        while(turtle.getKey() != hare.getKey()){
            hare = hare.getNext();
            if(hare.getKey() == turtle.getKey()){
                break;
            }
            turtle = turtle.getNext();
        }

        return hare;
    }

    public static int lengthOfCycle(node beginning){
        node turtle = beginning;
        node hare = beginning;
        int counter = 1;
        hare = hare.getNext();

        while(turtle.getKey() != hare.getKey()){
            hare = hare.getNext();
            counter ++;
        }
        return counter;
    }

    public static void main(String[] args) {
        node root = new node(0);
        node one = new node(1);
        node two = new node(2);
        node three = new node(3);
        node four = new node(4);
        node five = new node(5);
        node six = new node(6);
        node seven = new node(7);
        node eight = new node(8);
        node nine = new node(9);

        root.setNext(one);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(seven);
        seven.setNext(eight);
        eight.setNext(nine);
        nine.setNext(four);

        node meet = theMeetingNode(root);
        assert meet != null;
        node begin = theBeginningOfCycle(root, meet);
        System.out.println(lengthOfCycle(meet));




    }

    /*

    PROOF that if there is a loop, they will always meet:

    length of arm of the list = S
    length of the cycle of the list = L
    length from beginning od cycle to the meeting node = X

                    __  __
              L   __       __
                       __ meeting X
    __________________
            S

    so turtle do S+X
    and the hare do 2*(S+X)
    till the meeting node

    we can look at this at another angle - the hare do S+X and more L (another cycle)
    and then reached the meeting node.

    so:
    2*(S+X) = S+X+L
    S = L-X

    that means the the arm length is the length of the cycle, minus the X that represents
    the length from beginning node to meeting node.
    so if we put the hare in the meeting node
    and the turtle at the root node
    and we will move them by one (jump once at a time)
    they will meet at the beginning of the cycle.

     */
}
