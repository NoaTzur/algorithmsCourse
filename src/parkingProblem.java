public class parkingProblem {

    public static class linkedCars{
        char ch;
        linkedCars next;
        linkedCars prev;

        public char getCh(){
            return ch;
        }
        public void setCh(char ch){
            this.ch = ch;
        }

        public linkedCars getNext() {
            return this.next;
        }

        public linkedCars getPrev() {
            return this.prev;
        }

        public void setNext(linkedCars next) {
            this.next = next;
        }

        public void setPrev(linkedCars prev) {
            this.prev = prev;
        }
    }

    /*

    this function will return the number of car in the loop
    it will start at the head of the linked list of cars, the head will be marked with char V
    then we will iterate through the list, if we see car with mark V we mark it with W
    and reverse the list number of counter times.
    if in the end of the counter we see car with W it means we closed a loop and counter is the number of cars
    in the garage (loop)
     */
    public static int loopOfCars(linkedCars root){
        boolean flagOfTheEnd = true;
        root.setCh('V');
        linkedCars nextCar = root.getNext();
        int counter =1; //we begin in the second car after the root
        char firstSign = 'V', secondSign = 'W';
        int stepToReverse = 0;

        while(flagOfTheEnd){
            if(nextCar.getCh() != 'V'){
                nextCar = nextCar.getNext();
                counter ++;
            }
            else{
                nextCar.setCh('W');
                stepToReverse = counter;
                while (stepToReverse>0){// go reverse in the loop
                    nextCar = nextCar.getPrev();
                    stepToReverse--;
                }
                if (nextCar.getCh() == secondSign){ //arrived to the end of the loop
                    flagOfTheEnd = false;
                }
                else{
                    counter = 1;
                    nextCar = root.getNext(); //beginning again from the start point of the loop
                }
            }
        }
        return counter;

    }

    public static void main(String[] args) {
        int[] arr = {'V', 'B', 'M', 'V', 'S', 'A','X', 'P', 'V', 'T'};
        linkedCars root = new linkedCars();
        linkedCars root1 = new linkedCars();
        linkedCars root2= new linkedCars();
        linkedCars root3 = new linkedCars();
        linkedCars root4 = new linkedCars();
        linkedCars root5 = new linkedCars();
        linkedCars root6 = new linkedCars();
        linkedCars root7 = new linkedCars();
        linkedCars root8 = new linkedCars();
        linkedCars root9 = new linkedCars();
        root.setCh('V');
        root1.setCh('B');
        root2.setCh('M');
        root3.setCh('V');
        root4.setCh('S');
        root5.setCh('A');
        root6.setCh('X');
        root7.setCh('P');
        root8.setCh('V');
        root9.setCh('T');

        root.setNext(root1);
        root1.setNext(root2);
        root2.setNext(root3);
        root3.setNext(root4);
        root4.setNext(root5);
        root5.setNext(root6);
        root6.setNext(root7);
        root7.setNext(root8);
        root8.setNext(root9);
        root9.setNext(root);

        root1.setPrev(root);
        root2.setPrev(root1);
        root3.setPrev(root2);
        root4.setPrev(root3);
        root5.setPrev(root4);
        root6.setPrev(root5);
        root7.setPrev(root6);
        root8.setPrev(root7);
        root9.setPrev(root8);
        root.setPrev(root9);

        int ans = loopOfCars(root);
        System.out.println(ans);


    }
}
