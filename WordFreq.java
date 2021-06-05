public class WordFreq {
    //Word's name
    private String word;
    //Times seen
    private int times;

    //Constructor with word
    public WordFreq(String word){
        this.times = 1;
        this.word = word;
    }
    //Constructor with word + times seen
    public WordFreq(String word, int times){
        this.times = times;
        this.word = word;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void incrTimes(){
        this.times++;
    }
    public String key(){
        return this.word;
    }

    public int getTimes() {
        return times;
    }

    @Override
    public String toString() {
        return  word + " : number of times used: " + times;
    }

    /**
     * Compares two words based on their name
     * @param item
     * @return 0 if they are equal
     * @return 1 if first word is greater
     * @return -1 if not
     */
    public int compare(WordFreq item){

        if (this.word.equals(item.word)){
            return 0;
        }
        //Converts both words to charArrays so we can compare them letter to letter
        char[] first = this.word.toCharArray();
        char[] second = item.word.toCharArray();
        //Getting min length
        int minLength = Math.min(first.length, second.length);

        for (int i = 0; i < minLength; i++){
            if (first[i] > second[i]){
                return 1;
            }
            if (first[i] < second[i]){
                return -1;
            }
        }
        return 1;
    }

}