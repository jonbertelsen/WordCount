import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        Users users = new Users();
        boolean isLoggedIn = false;

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Indtast brugernavn: ");
            String userName = input.nextLine();
            System.out.println("Indtast kodeord: ");
            String userPassword = input.nextLine();

            isLoggedIn = users.isValidUser(userName, userPassword);
            if (isLoggedIn) {
                System.out.println("Du er nu logget ind");
            } else {
                System.out.println("Forkert brugernavn eller kodeord");
            }
        } while (!isLoggedIn);

        // *** Alice in Wonderland ****

        long start = System.nanoTime();

        Words aliceWords = new Words("alice.txt");

        long finish = System.nanoTime();
        long timeElapsed = (finish - start) / 1000000;
        System.out.println("Time: " + timeElapsed + " ms");

        System.out.println("Antal ord i Alice: " + aliceWords.getWordListSize());

        // *** Moby Dick ****

        start = System.nanoTime();

        Words mobyDickWords = new Words("mobydick.txt");

        finish = System.nanoTime();
        timeElapsed = (finish - start) / 1000000;
        System.out.println("Time: " + timeElapsed + " ms");

        System.out.println("Antal ord i Moby Dick: " + mobyDickWords.getWordListSize());

        // *** War and Peace ****

        start = System.nanoTime();

        Words warPeaceWords = new Words("warpeace.txt");

        finish = System.nanoTime();
        timeElapsed = (finish - start) / 1000000;
        System.out.println("Time: " + timeElapsed + " ms");

        System.out.println("Antal ord i Krig og Fred: " + warPeaceWords.getWordListSize());


        // ****** Hashmaps generation and stats

        aliceWords.createWordOccurrenceHashmap();
        System.out.println("Antal unikke ord i Alice: " + aliceWords.getHashmapSize());
        System.out.println("Unikkeord i Alice: " + aliceWords.getHashmapSize() * 100 / aliceWords.getWordListSize() + "%" );

        mobyDickWords.createWordOccurrenceHashmap();
        System.out.println("Antal unikke ord i Moby: " + mobyDickWords.getHashmapSize());
        System.out.println("Unikkeord i Moby: " + mobyDickWords.getHashmapSize() * 100 / mobyDickWords.getWordListSize() + "%" );

        warPeaceWords.createWordOccurrenceHashmap();
        System.out.println("Antal unikke ord i Krig og Fred: " + warPeaceWords.getHashmapSize());
        System.out.println("Unikkeord i Krig og Fred: " + warPeaceWords.getHashmapSize() * 100 / warPeaceWords.getWordListSize() + "%" );


        // ****** Find most used words

        aliceWords.createSortedListOfWords();
        //aliceWords.showWordOccurrenceList();

        mobyDickWords.createSortedListOfWords();
        //mobyDickWords.showWordOccurrenceList();

        warPeaceWords.createSortedListOfWords();
        warPeaceWords.showWordOccurrenceList();

        // Create a list of word that the two book have in common

        Set aliceSet = new HashSet<String>();
        Set mobySet = new HashSet<String>();

        aliceSet.addAll(aliceWords.getWordList());
        mobySet.addAll(mobyDickWords.getWordList());


        System.out.println("Alice set size: " + aliceSet.size());
        System.out.println("Moby set size: " + mobySet.size());

        Set<String> intersection = new TreeSet(aliceSet);

        intersection.retainAll(mobySet);

        System.out.println("Intersection set size: " + intersection.size());

        // printStringList(intersection);

        // Create a list of words in Alice that is unique to Alice

        Set<String> onlyAlice = new TreeSet<>(aliceSet);
        onlyAlice.removeAll(mobySet);


        //printStringList(onlyAlice);

        System.out.println("Only Alice size: " + onlyAlice.size());

        // Create a list of words in Moby that is not in Alice

        Set<String> onlyMoby = new TreeSet<>(mobySet);
        onlyMoby.removeAll(aliceSet);

        //printStringList(onlyMoby);

        System.out.println("Only Moby size: " + onlyMoby.size());
    }

    public static void printStringList(Set<String> dataSet){
        for (String wordItem: dataSet) {
            System.out.println(wordItem);
        }
    }


}
