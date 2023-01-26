package com.anttijuustila.tira;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BookImplementation implements Book {

    static final int MAX_TABLE_SIZE = 10000000;
    static final int MAX_WORD_SIZE = 100;
    private Word[] bookWords = new Word[MAX_TABLE_SIZE];
    private String[] ignore = new String[500];
    private Word[] uniqueBookWords;
    int collisionsCount = 0;
    int totalWordCount = 0;
    int uniqueWordCount = 0;
    int ignoredWordCount = 0;
    int ignoredWordsInIgnoreFile = 0;
    File bookFile;
    File ignoreWords;


   
    @Override
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {

        bookFile = new File(fileName);
        ignoreWords = new File(ignoreWordsFile);

        if (!bookFile.isFile()) {
            bookFile = null;
            throw new FileNotFoundException("File do not exist");
        }

        if (!ignoreWords.isFile()) {
            ignoreWords = null;
            throw new FileNotFoundException("File do not exist");
        }
    }

    @Override
    public void countUniqueWords() throws IOException, OutOfMemoryError {

        String line;
        BufferedReader bReader = null;
        FileReader fReader = null;
        String[] ignoreArray = new String[MAX_TABLE_SIZE];
        int index = 0;
        String word;
        int c;
        int[] array = new int[MAX_WORD_SIZE];
        int currentIndex = 0;

        fReader = new FileReader(ignoreWords, StandardCharsets.UTF_8);
        bReader = new BufferedReader(fReader);

        while ((line = bReader.readLine()) != null) {
            line = line.toLowerCase();
            ignoreArray = line.split(",");

            for (int ii = 0; ii < ignoreArray.length; ii++) {
                ignore[index] = ignoreArray[ii];
                index++;
                ignoredWordsInIgnoreFile++;
            }

        }

        fReader.close();
        fReader = new FileReader(bookFile, StandardCharsets.UTF_8);
        bReader = new BufferedReader(fReader);

        while ((line = bReader.readLine()) != null) {

            if (currentIndex != 0) {
                word = new String(array, 0, currentIndex);
                word = word.toLowerCase();
                currentIndex = 0;
                isTheWordValid(word);
            }

            for (int i = 0; i < line.length(); i++) {

                c = line.codePointAt(i);

                if (Character.isLetter(c)) {
                    array[currentIndex] = c;
                    currentIndex++;
                } else {
                    word = new String(array, 0, currentIndex);
                    word = word.toLowerCase();
                    currentIndex = 0;

                    isTheWordValid(word);

                }
            }

        }


        uniqueBookWords = new Word[uniqueWordCount+1];

        fillOnlyWithUniqueWords();

        quickSort(uniqueBookWords, 0, uniqueWordCount - 1);


        fReader.close();
        bReader.close();
    }

    @Override
    public void report() {


        int i = 0;

        while (uniqueBookWords[i] != null && i < 100) {

            System.out.println(uniqueBookWords[i].toString());
            i++;
            
        }

        System.out.println("The count of total number of words in the book: " + totalWordCount);
        System.out.println("The count of unique words in the book: " + uniqueWordCount);
        System.out.println("the count of words to ignore: " + ignoredWordsInIgnoreFile);
        System.out.println("The count of words ignored in the book file: " + ignoredWordCount);
        System.out.println("Collision counter: " + collisionsCount);
    }

    @Override
    public void close() {

        bookFile = null;
        ignoreWords = null;
        uniqueBookWords = null;
        bookWords = null;
        ignore = null;

    }

    @Override
    public int getUniqueWordCount() {

        return uniqueWordCount;
    }

    @Override
    public int getTotalWordCount() {

        return totalWordCount;
    }

    @Override
    public String getWordInListAt(int position) {

        if (uniqueBookWords == null) {
            return null;
        } else if (position >= uniqueBookWords.length) {
            return null;
        } else if (position < 0) {
            return null;
        }

        return uniqueBookWords[position].getWord();
    }

    @Override
    public int getWordCountInListAt(int position) {

        if (uniqueBookWords == null) {
            return -1;
        } else if (position >= uniqueBookWords.length) {
            return -1;
        } else if (position < 0) {
            return -1;
        }

        return uniqueBookWords[position].getOccurrence();
    }

    private void isTheWordValid(String word) {

        if (word.length() > 1) {

            boolean ignoreThisWord = false;
            int j = 0;

            while (ignore[j] != null) {

                if (ignore[j].equals(word)) {

                    ignoreThisWord = true;
                    break;

                }
                j++;
            }
            if (!ignoreThisWord) {

                totalWordCount++;
                add(word);

            } else {
                ignoredWordCount++;
            }
        }
    }

    private void add(String word) throws IllegalArgumentException {

        int i = 0;

        while (i < bookWords.length - 1) {

            int index = wordArrayIndex(word, i);

            if (bookWords[index] == null) {
                bookWords[index] = new Word(word);
                uniqueWordCount++;
                return;
            } else {

                

                if (bookWords[index].getWord().equals(word)) {
                    bookWords[index].addOccurrenceByOne();
                    return;
                } else {
                    collisionsCount++;
                }

                i++;
            }

        }
    }

    private void fillOnlyWithUniqueWords() {

        int j = 0;

        for (int i = 0; i <= bookWords.length - 1; i++) {

            if (bookWords[i] != null) {
                uniqueBookWords[j] = bookWords[i];
                j++;
            }

        }

    }

    private int wordArrayIndex(String word, int value) {

        int index = 0;
        int hashValue = hashCode(word);

        index = ((hashValue + value) & 0x7fffffff) % MAX_TABLE_SIZE;

        return index;
    }

    public int hashCode(String word) {
        int hash = 31;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            hash = (hash * 31 + Character.getNumericValue(ch));
        }

        return hash;
    }

    private void quickSort(Word[] array, int fromIndex, int toIndex) {

        if (fromIndex < toIndex) {
            int pivot = partition(array, fromIndex, toIndex);
            quickSort(array, fromIndex, pivot - 1);
            quickSort(array, pivot + 1, toIndex);

        }

    }

    private int partition(Word[] array, int fromIndex, int toIndex) {

        int x = array[toIndex].getOccurrence();
        int i = fromIndex - 1;

        for (int j = fromIndex; j <= toIndex - 1; j++) {
            if (array[j].getOccurrence() > x) {
                i++;
                Word temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }
        Word temp = array[i + 1];
        array[i + 1] = array[toIndex];
        array[toIndex] = temp;

        return i + 1;

    }

}
