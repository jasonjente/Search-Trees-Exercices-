import java.io.IOException;


public class testMain {
    public static void main(String[] argc) throws IOException {

        //Input file
        //FULL PATH NEEDED TO LOAD TXT FILES
        String filename1= "C:\\Users\\jente\\IdeaProjects\\DomesDedomenwnErg3\\src\\txt_1";
        String filename2= "C:\\Users\\jente\\IdeaProjects\\DomesDedomenwnErg3\\src\\txt_2";
        String filename3= "C:\\Users\\jente\\IdeaProjects\\DomesDedomenwnErg3\\src\\txt_3";
        String filename4= "C:\\Users\\jente\\IdeaProjects\\DomesDedomenwnErg3\\src\\txt_4";
        String filename5= "C:\\Users\\jente\\IdeaProjects\\DomesDedomenwnErg3\\src\\txt_5";

        ST bst = new ST();
        bst.initiateStopWordList();

        bst.addStopWord("do");
        bst.addStopWord("did");
        bst.addStopWord("that");
        bst.addStopWord("this");
        bst.addStopWord("is");
        bst.addStopWord("was");
        bst.addStopWord("not");
        bst.addStopWord("for");
        bst.printStopWordList();
        bst.load(filename3);
        bst.printTreeAlphabetically();
        System.out.println("--------------------------------");

        bst.update("a");
        bst.update("a");
        bst.printTreeAlphabetically();
        System.out.println(bst.getFrequency("a"));
        System.out.println(bst.getFrequency("v"));

        System.out.println(bst.getMaximumFrequency());

    }

}