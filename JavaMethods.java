import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class JavaMethods {

    // READ TXT FILE WITH BUFF.READER AND FILE READER.
    public void readFile() throws IOException {
        String path = "C:\\Users\\AbabaLaptop\\eclipse-workspace\\domesDedomenwn Ergasia 1\\src\\myfile.html";
        String str = null;
        String topItem = null;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((str = br.readLine()) != null) {
            String[] line = str.split("\\s");
            //for each word in the line
            for (String word : line) {
                //do sth
                System.out.println(word);
            }
            br.close();
        }


    }
    //LINKED LIST WITH GENERICS:
    public class LinkedList<T> {
        //Linked list includes Node<T> class
        //nneeds to be static
        private class Node<T>{
            //basic characteristics of each node
            private T data;
            private Node<T> next;
            //constructor
            public Node(T data){
                this.data = data;
            }

            public String toString() {
                return data.toString();
            }

        }
        //basic pivots of the queue, top is used both by the stack and the queue
        private Node<T> top = null;
        private Node<T> bot = null;

        //LIFO insertion
        public void addFirst(T data) {
            Node<T> first = new Node<T>(data);
            first.next = top;
            top = first;
        }
        //LIFO deletion
        public T removeFirst() {
            Node<T> first = top;
            top = top.next;
            return first.data;
        }
        //FIFO insertion
        public void addLast(T item) {
            Node<T> b = bot;
            bot = new Node<T>(item);
            if (isEmpty()) {
                top = bot;
            }else {
                b.next = bot;
            }
        }
        //FIFO deletion
        public T removeLast() {
            T v = top.data;
            Node<T> t = top.next;
            bot = t;
            return v;
        }
        public boolean isEmpty() {
            return top == null;
        }
        public T peaking() {
            return top.data;
        }
        public void printListStack(PrintStream stream) {
            Node<T> curr = top;
            while(curr!=null) {
                System.out.println(curr.toString());
                curr = curr.next;
            }
        }
        public void printListQueue(PrintStream stream) {
            Node<T> curr = top;
            while(curr!=null) {
                System.out.println(curr.toString());
                curr = curr.next;
            }
        }
        public int sumOfQueueItems() {
            int sum = 0;
            Node<T> curr = top;
            while(curr!=null) {
                sum =+ (Integer)curr.data;
            }

            return sum;


        }
    }





















}
