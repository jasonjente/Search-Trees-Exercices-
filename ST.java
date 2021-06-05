import java.io.*;
import java.util.*;
class ST{
    private TreeNode head;
    private int numOfDistinctWords = 0;
    private WordFreq max = new WordFreq("", 0);
    private ArrayList<String> stopWordList = new ArrayList<String>();
    private int totalWords;
    class TreeNode {
        int totalNodes;

        WordFreq item;
        TreeNode l;
        TreeNode r;
        int number;
        //Constructor
        TreeNode(WordFreq item){
            this.item = item;
            this.l = null;
            this.r = null;
            this.number = 0;
            totalNodes++;
        }

    }

    /**
     * Inserts an item to the tree
     */
    void insert(WordFreq item){
        head = insertR(head, item);
    }

    /**
     * Inserts an item to the tree
     * If it already exists, it's frequency is increased
     * @param h The node checked
     * @param item The item to be inserted
     * @return
     */
    TreeNode insertR(TreeNode h, WordFreq item) {
        //Found the Treenode to be inserted into
        if (h == null) {
            numOfDistinctWords++;
            head= new TreeNode(item);
            return head;
        }

        //The Treenode contains the item we want to insert
        if(h.item.compare(item)==0) {
            h.item.incrTimes();
            //Keeping element with max frequecy for getMaximumFrequency()
            if (h.item.getTimes() > max.getTimes()){
                max.setWord(h.item.key());
                max.setTimes(h.item.getTimes());
            }
            return h;
        }
        //If item is bigger we go to left subtree

        if(h.item.compare(item)==1){
            h.l = insertR(h.l, item);
        }else{
            h.r = insertR(h.r, item);
            return h;
        }
        return h;
    }

    /**
     * Searches for the String w the whole tree
     * If found its frequency is increased by 1
     * If not it gets inserted in the tree
     */
    void update(String w){
        WordFreq temp = search(w);
        if(temp==null){
            WordFreq word = new WordFreq(w);
            this.insert(word);
        }else{
            temp.incrTimes();
        }

    }

    /**
     * Searches for the String w the whole tree
     * If found and it's frequency is greater than the MeanFrequency
     * The word becomes the root of the tree
     */
    WordFreq search(String key) {
        char c = ' ';
        return searchR(head, key, c);
    }

    private WordFreq searchR(TreeNode h, String w, char c) {
        if (h==null) return null;
        WordFreq temp = new WordFreq(w);
        if(temp.compare(h.item)==0) {
            if (h.item.getTimes() > getMeanFrequency()){
                if (c == 'l'){
                    rotR(h);
                }
                if (c == 'r'){
                    rotL(h);
                }
            }

            return h.item;
        }
        if(temp.compare(h.item)==-1){
            c = 'l';
            return searchR(h.l,w, c);
        }else{
            c = 'r';
            return searchR(h.r,w, c);
        }
    }

    /**
     * Removes the word with given name from the tree
     * @param item
     */
    void remove(String item) {
        removeR(head, item);
        numOfDistinctWords--;
        System.out.println("REMOVING ITEM : " + item);
    }

    private TreeNode removeR(TreeNode h,  String item) {
        if (h == null) return null;
        WordFreq temp = new WordFreq(item);
        if(temp.compare(h.item)==-1) {
            h.l = removeR(h.l, item);
        }else if(temp.compare(h.item)==1){
            h.r = removeR(h.r, item);
        }else{
            h.r = removeR(h.r, item);
        }
        if (temp.compare(h.item)==0) {
            h = joinLR(h.l, h.r);
        }

        return h;
    }

    private TreeNode joinLR(TreeNode a, TreeNode b) {
        if (b == null)
            return a;
        b = partR(b, 0);
        b.l = a;
        return b;
    }

    private TreeNode partR(TreeNode h, int k) {
        int t = (h.l == null) ? 0 : h.l.number;
        if (t > k) {
            h.l = partR(h.l, k);
            h = rotR(h); }
        if (t < k) {
            h.r = partR(h.r, k-t-1);
            h = rotL(h); }
        return h;
    }

    private TreeNode rotR(TreeNode h) {
        TreeNode x = h.l; h.l = x.r; x.r = h; return x;
    }

    private TreeNode rotL(TreeNode h) {
        TreeNode x = h.r;
        h.r = x.l;
        x.l = h;
        return x;
    }

    /**
     * Loads the file and adds every word to the tree
     * @param filename the File given
     */
    void load(String filename){
        try {
            Scanner scan = new Scanner(new File(filename));
            totalWords = 0;
            while (scan.hasNext()){
                String word = scan.next();
                word = word.toLowerCase();
                String temp = delimeter(word);
                if (!temp.equals(".")){
                    this.insert(new WordFreq(temp));
                    totalWords++;
                }

            }
        } catch (IOException e){
            System.out.println("load method");
            e.printStackTrace();
        }
    }

    /**
     * Checks if the word given:
     * Is a number
     * Is a word from the stopword list
     * Starts or ends with characters that we want to ignore
     * @param word The word given
     * @return . if we don't want the word, otherwise we return the word
     */
    private String delimeter(String word){
        if (word.startsWith("0") || word.startsWith("1") || word.startsWith("2") || word.startsWith("3") || word.startsWith("4") || word.startsWith("5") || word.startsWith("6") || word.startsWith("7") || word.startsWith("8") || word.startsWith("9")){
            return ".";
        }
        if (stopWordList.contains(word)){
            return ".";
        }
        if (word.equals("")){
            return ".";
        }
        char t = word.charAt(0);
        String temp = Character.toString(t);
        //If word starts with something we don't want, we remove it and recursively call this function
        if (stopWordList.contains(temp) || t == ('"')){
            word = word.substring(1);
            word = delimeter(word);
        }
        t = word.charAt(word.length() - 1);
        temp = Character.toString(t);
        //If word ends with something we don't want, we remove it and recursively call this function
        if (stopWordList.contains(temp) || t == '"'){
            word = word.substring(0, word.length() - 1);
            word = delimeter(word);
        }
        return word;
    }

    /**
     * @return totalWords added to tree in the last file that was loaded
     */
    private int countR(TreeNode h) {
        if (h == null) return 0;
        return 1 + countR(h.l) + countR(h.r);
    }

    int getTotalWords(){
        int nonStopWords = countR(head);
        return nonStopWords;
    }

    /**
     * @return Number of distinct words in our tree
     */
    int getDistinctWords() {
        return numOfDistinctWords;
    }

    /**
     * Searches the whole tree for the word
     * @param w the word searched
     * @return the word's frequency
     */
    int getFrequency(String w) {
        WordFreq temp = search(w);
        if(temp==null)
            return 0;
        return temp.getTimes();
    }

    /**
     * @return the word with the highest frequency
     */
    WordFreq getMaximumFrequency(){
        return max;
    }

    /**
     * @return mean frequency
     */
    double getMeanFrequency(){
        return getMeanFrequency(head)/getTotalWords();
    }

    double getMeanFrequency(TreeNode h){
        if (h == null) return 0;
        return h.item.getTimes() + getMeanFrequency(h.l) + getMeanFrequency(h.r);
    }

    /**
     * Adds a word to the stopword list
     * Needs to be called before a file is loaded in order for the word to be cleansed from the file
     */
    void addStopWord(String w){
        stopWordList.add(w);
        System.out.println(w + " Has been added as an exception to our stopWordList");
    }

    /**
     * Removes a word from the stopword list
     * @param w the word to be removed
     */
    void removeStopWord(String w){
        for (int i = 0; i < stopWordList.size();i++){
            if (stopWordList.get(i).equals(w)){
                stopWordList.remove(i);
                return;
            }
        }
    }

    /**
     * Makes a new stopword list with some starting elements
     */
    void initiateStopWordList(){
        ArrayList list = new ArrayList();
        Collections.addAll(list, "&", ",", ".", "?", ";", "!", "-", ":", '"', "(", ")","<",">","@","+","'");
        stopWordList = list;
    }

    /**
     * prints elements in stopword list
     */
    void printStopWordList(){
        System.out.println("The stopWordList contains the following stop words: ");
        System.out.println(Arrays.toString(stopWordList.toArray()));
        System.out.println();
    }

    /**
     * Prints the whole tree alphabetically
     */
    void printTreeAlphabetically(){
        printSTR(head);
    }

    /**
     * Does DFS print on the tree
     */
    private void printSTR(TreeNode h){
        if(h == null)
            return;
        System.out.println(h.item.key()+ " " + h.item.getTimes());
        //Recursive call
        printSTR(h.l);
        printSTR(h.r);
    }

}