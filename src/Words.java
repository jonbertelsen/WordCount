import java.io.*;
import java.util.*;

public class Words {

    private List<String> wordList = new LinkedList<String>();
    private Map<String,Integer> wordOccurrenceHashmap = new HashMap<String,Integer>();
    private List<Map.Entry<String, Integer>> sortedWordList = null;

    private String fileName = "";

    public Words(String fileName) {
        this.fileName = fileName;
        this.wordList = readWordFile(fileName);
    }

    public List<String> getWordList() {
        return wordList;
    }

    public List<String> readWordFile(String fileName) {

        BufferedReader inputStream = null;
        String[] wordArray;
        int currentValue = 0;

        try {
            inputStream = new BufferedReader(new FileReader(fileName));
            String l;

            while ((l = inputStream.readLine()) != null) {
                l = l.toLowerCase();
                l = l.replaceAll("’", "");
                l = l.replaceAll("‘", "");
                l = l.replaceAll("!", "");
                l = l.replaceAll(":", "");
                l = l.replaceAll("~", "");
                l = l.replaceAll("\\(", "");

                l = l.replaceAll("\\)", "");
                l = l.replaceAll("\\?", "");
                l = l.replaceAll("'", "");
                l = l.replaceAll("--", " ");
                l = l.replaceAll(",", "");
                l = l.replaceAll(";", "");
                l = l.replaceAll("\\.", "");
                l = l.replaceAll("\"", "");
                wordArray = l.split(" ");
                for (String word : wordArray) {
                    // insert into list
                    if (word.length()>0)
                        wordList.add(word.replaceAll(" ", ""));
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("Hey makker - der mangler en fil");
        } catch (Exception e) {
            System.out.println("Der er opstået en generel fejl");
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return wordList;
    }

    public void showWordList(){
        for (String wordItem: wordList) {
            System.out.println(wordItem);
        }
    }

    public int getWordListSize(){
        return wordList.size();
    }

    public void createWordOccurrenceHashmap(){
        int count = 0;
        for (String wordItem: wordList) {
            if (wordOccurrenceHashmap.containsKey(wordItem)){
                count = wordOccurrenceHashmap.get(wordItem);
                wordOccurrenceHashmap.put(wordItem,count + 1);
            } else
            {
                wordOccurrenceHashmap.put(wordItem,1);
            }
        }
    }

    public int getHashmapSize(){
        return wordOccurrenceHashmap.size();
    }

    public void createSortedListOfWords(){
        sortedWordList = new LinkedList<Map.Entry<String, Integer>>(wordOccurrenceHashmap.entrySet());
        WordComparator wc = new WordComparator();
        Collections.sort(sortedWordList,wc);
    }

    public Map<String, Integer> getWordOccurrenceHashmap() {
        return wordOccurrenceHashmap;
    }

    public List<Map.Entry<String, Integer>> getSortedWordList() {
        return sortedWordList;
    }

    public void showWordOccurrenceList(){
        int sum = 0;
        for (Map.Entry<String, Integer> wordItem: sortedWordList) {
            System.out.println(wordItem);
            sum += wordItem.getValue();
        }
        System.out.println("I alt: " + sum);
    }
}
