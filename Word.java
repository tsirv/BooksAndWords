package com.anttijuustila.tira;

public class Word implements Comparable<Word> {

    private String word;
    private int occurrence = 1;
    
    public Word(String word){
        this.word = word;
    }

    public Word(String wordName, int occurrence){
        this.word = wordName;
        this.occurrence = occurrence;
    }

    public String getWord(){
        return word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public int getOccurrence(){
        return occurrence;
    }

    public void setOccurrence(int occurrence){
        this.occurrence = occurrence;
    }

    public void addOccurrenceByOne(){
        this.occurrence = occurrence + 1;

    }



    @Override
    public boolean equals(Object other) {
        if (other instanceof Word) {
            return this.word.equals(((Word)other).word);
        }
        return false;
    }

    @Override
    public int compareTo(Word other) {
        
        return word.compareTo(other.word);
    }

    @Override
    public String toString(){
        return this.getWord() + ".........." + this.getOccurrence();
    }
}
