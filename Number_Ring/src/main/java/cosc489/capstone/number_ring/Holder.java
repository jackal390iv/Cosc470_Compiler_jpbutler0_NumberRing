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
            current = current.getPrevious();
            size++;
        } else if (size == 2) {
            current.setPrevious(temp);
            current.getNext().setNext(temp);
            temp.setNext(current);
            temp.setPrevious(current.getNext());
            current = current.getPrevious();
            size++;
        } else {
            current.getPrevious().setNext(temp);
            temp.setPrevious(current.getPrevious());
            current.setPrevious(temp);
            temp.setNext(current);
            current = current.getPrevious();
            size++;
        }
        temp = null;
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
            current = current.getNext();
            size++;
        } else if (size == 2) {
            current.setNext(temp);
            temp.setPrevious(current);
            current.getPrevious().setPrevious(temp);
            temp.setNext(current.getPrevious());
            current = current.getNext();
            size++;
        } else {
            current.getNext().setPrevious(temp);
            temp.setNext(current.getNext());
            temp.setPrevious(current);
            current.setNext(temp);
            current = current.getNext();
            size++;
        }
        temp = null;
    }

    public void delete() {
        Node temp;
        if (size == 1) {
            current = null;
            size--;
        } else if (size > 1) {
            current.getNext().setPrevious(current.getPrevious());
            current.getPrevious().setNext(current.getNext());
            temp = current.getNext();
            current = null;
            current = temp;
            size--;
        }
        temp = null;
    }

    public void deleteAll() {
        System.out.printf("\nDelete All Values:\n");
        while (size != 0) {
            delete();
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
        if (size > 2) {
            //System.out.println("\ncount: " + count);
            int temp = current.getData();
            //System.out.printf("\nOriginal: ");
            //printRingClockwise();
            jump(count);
            //System.out.printf("\nBefore Add: ");
            //printRingClockwise();
            if (count < 0) {
                insert(temp);
            } else {
                append(temp);
            }
            //System.out.printf("\nAfter Add: ");
            //printRingClockwise();
            //System.out.println("\ncount: " + count);
            temp = 0;
            if ((Math.abs(count)) > size) {
                temp = (Math.abs(count)) / size;
            }
            //System.out.println("\ntemp: " + temp);
            if (count < 0) {
                jump(((count - temp) - 1) * -1);
            } else {
                jump(((count + temp) + 1) * -1);
            }
            //System.out.printf("\nBefore Delete: ");
            //printRingClockwise();
            delete();
            //System.out.printf("\nAfter Delete: ");
            //printRingClockwise();
            if (count < 0) {
                jump(count - 1);
            } else {
                jump(count);
            }
            //System.out.printf("\nFinished: ");
            //printRingClockwise();
        }
    }

    public void sort() {
        Node temp;
        int kept, pacer = size, holder = current.getData();
        if (size > 2) {
            for (int i = 0; i < size; i++) {
                current = current.getNext();
                temp = current;
                for (int j = 0; j < pacer; j++) {
                    temp = temp.getNext();
                    //System.out.printf("\n");
                    //print();
                    //System.out.printf("Current: %-5d\tTemp: %-5d\tHolder: %-5d\tPass-i: %-5d\tPass-t: %-5d", current.getData(), temp.getData(), pacer, i, j);
                    if (current.getData() > temp.getData()) {
                        kept = current.getData();
                        current.setData(temp.getData());
                        temp.setData(kept);
                        //System.out.println("\nswap");
                    }
                }
                pacer--;
            }
            //locate saved current (holder)
            temp = current.getPrevious();
            for (int i = 0; i < size; i++) {
                temp = temp.getNext();
                if (temp.getData() == holder) {
                    current = temp;
                }
            }
        }
        temp = null;
    }

    public Integer getCurrentData() {
        return current.getData();
    }

    public void printCurrent() {
        System.out.printf("\nCurrent Node: %d\n", getCurrentData());
    }

    public void print() {
        System.out.printf("\n");
        for (int i = 0; i < size; i++) {
            System.out.printf("%d ", getCurrentData());
            jump(1);
        }
        System.out.printf("\n");
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
