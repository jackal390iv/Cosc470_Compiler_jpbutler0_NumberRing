/*
 * Class: COSC 489
 * Project: Capstone
 */
package cosc489.capstone.number_ring;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class Holder {

    private Node current = null;
    private Integer size = 0;
    private ArrayList<String> file = new ArrayList<String>();

    public Holder() {

    }

    public void run() {
        read();
        fileOperations();
    }

    private void read() {
        File dummy;
        String holder, kept;
        Scanner scan;
        try {
            //create dummy file
            dummy = new File("");
            //set holder to file's path
            holder = dummy.getAbsolutePath();
            //System.out.println("Holder : " + holder);
            //move holder's path to the directory above source code folder
            holder = holder.substring(0, holder.lastIndexOf(File.separator));
            //System.out.println("Holder : " + holder);
            //move holder's path to the directory above project folder
            holder = holder.substring(0, holder.lastIndexOf(File.separator));
            //System.out.println("Holder : " + holder);

            scan = new Scanner(new File(holder + "/COSC489_PT_2158_Input.txt"));
            while (scan.hasNextLine()) {
                kept = scan.nextLine();
                if (!kept.isEmpty()) {
                    file.add(kept);
                }
            }

        } catch (Exception ex) {
            System.out.println("I'm sorry, but an error has occured durring file read; please try again.");
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage());
            //ex.printStackTrace();*/
            System.exit(0);
        }
    }

    private void fileOperations() {
        String holder;
        int temp;
        for (int i = 0; i < file.size(); i++) {
            holder = file.get(i);
            if (holder.contains("Initialize the ring with")) {
                initializeRing(file.get(i + 1));
                i++;
                //System.out.println("\nInitialize The Ring With:\n" + file.get(i));System.out.println("\n");
            }
            if (holder.contains("insert")) {
                temp = Integer.parseInt(holder.substring(holder.lastIndexOf(" ") + 1));
                //System.out.println("Insert: " + temp);
                insert(temp);
                //print();System.out.println("\n");
            }
            if (holder.contains("append")) {
                temp = Integer.parseInt(holder.substring(holder.lastIndexOf(" ") + 1));
                //System.out.println("Append: " + temp);
                append(temp);
                //print();System.out.println("\n");
            }
            if (holder.contains("jump")) {
                temp = Integer.parseInt(holder.substring(holder.lastIndexOf(" ") + 1));
                //System.out.println("Jump: " + temp);
                jump(temp);
                //print();System.out.println("\n");
            }
            if (holder.contains("move")) {
                temp = Integer.parseInt(holder.substring(holder.lastIndexOf(" ") + 1));
                //System.out.println("Move: " + temp);
                move(temp);
                //print();System.out.println("\n");
            }
            if (holder.contains("sort")) {
                //System.out.println("Sort: ");
                sort();
                //print();System.out.println("\n");
            }
            if (holder.contains("delete")) {
                //System.out.println("Delete: ");
                delete();
                //print();System.out.println("\n");
            }
            if (holder.contains("print")) {
                print();
            }
        }
    }

    private void initializeRing(String list) {
        //System.out.println(list);
        int temp;
        while (list.contains(" ")) {
            temp = Integer.parseInt(list.substring(list.lastIndexOf(" ") + 1));
            //System.out.println("Temp: "+temp);
            insert(temp);
            list = list.substring(0, list.lastIndexOf(" "));
            //System.out.println("List: "+list);
        }
        insert(Integer.parseInt(list));
        //print();
    }

    public void printFile() {
        System.out.println("Printing File:\n");
        for (String temp : file) {
            System.out.println(temp);
        }
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
