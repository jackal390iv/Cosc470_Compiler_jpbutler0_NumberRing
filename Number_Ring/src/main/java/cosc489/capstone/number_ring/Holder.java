/*
 * Class: COSC 489
 * Project: Capstone
 */
package cosc489.capstone.number_ring;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class Holder {

    private Node current = null;
    private Integer size = 0;

    public Holder() {

    }

    public void insert(int data) {
        Node temp = new Node(data);
        if (size == 0) {
            current = temp;
            size++;
        } else if (size == 1) {
            current.setNext(temp);
            current.setPrevious(temp);
            temp.setNext(current);
            temp.setPrevious(current);
            size++;
        } else if (size == 2) {
            current.setPrevious(temp);
            current.getNext().setNext(temp);
            temp.setNext(current);
            temp.setPrevious(current.getNext());
            size++;
        } else {
            current.getPrevious().setNext(temp);
            temp.setPrevious(current.getPrevious());
            current.setPrevious(temp);
            temp.setNext(current);
            size++;
        }
    }

    public void append(int data) {
        Node temp = new Node(data);
        if (size == 0) {
            current = temp;
            size++;
        } else if (size == 1) {
            current.setNext(temp);
            current.setPrevious(temp);
            temp.setNext(current);
            temp.setPrevious(current);
            size++;
        } else if (size == 2) {
            current.setNext(temp);
            temp.setPrevious(current);
            current.getPrevious().setPrevious(temp);
            temp.setNext(current.getPrevious());
            size++;
        } else {
            current.getNext().setPrevious(temp);
            temp.setNext(current.getNext());
            temp.setPrevious(current);
            current.setNext(temp);
            size++;
        }
    }

    public void delete() {
        if (size == 1) {
            current = null;
            size--;
        } else if (size > 1) {
            current.getNext().setPrevious(current.getPrevious());
            current.getPrevious().setNext(current.getNext());
            Node temp = current.getNext();
            current = null;
            current = temp;
            size--;
        }
    }

    public void deleteAll() {
        System.out.printf("\nDelete All Values:\n");
        while (size != 0) {
            delete();
        }
    }

    public void sortRing() {
        if (size > 2) {

        }
    }

    public void jump(int count) {
        boolean negative = false;
        if (count < 0) {
            negative = true;
            count = count * (-1);
        }
        if (size > 1) {
            for (int i = 0; i < count; i++) {
                if (negative == false) {
                    current = current.getNext();
                } else {
                    current = current.getPrevious();
                }
            }
        }
    }

    public void move(int count) {
        boolean negative = false;
        if (count < 0) {
            negative = true;
            //count = count * (-1);
        }
        if (size > 2) {
            current.getNext().setPrevious(current.getPrevious());
            current.getPrevious().setNext(current.getNext());

            Node temp = current;
            //delete
            if (negative == true) {
                count = count + 1;
                jump(count);
            } else {
                count = count - 1;
                jump(count);
                append(temp.getData());
            }

            /*
             if (negative == true) {
             for (int i = 0; i < count; i++) {
             temp = temp.getPrevious();
             }
             } else {
             for (int i = 0; i < count; i++) {
             temp = temp.getNext();
             }
             }//*/
        }
    }

    public Integer getCurrentData() {
        return current.getData();
    }

    public void printCurrent() {
        System.out.printf("\nCurrent Node: %d\n", getCurrentData());
    }

    public void printRingClockwise() {
        System.out.printf("\nPrinting Ring Clockwise:\n");
        for (int i = 0; i < size; i++) {
            System.out.printf("%d ", getCurrentData());
            jump(1);
        }
        System.out.printf("\n");
    }

    public void printRingCounterClockwise() {
        System.out.printf("\nPrinting Ring Counter-Clockwise:\n");
        for (int i = 0; i < size; i++) {
            System.out.printf("%d ", getCurrentData());
            jump(-1);
        }
        System.out.printf("\n");
    }

}
