
package com.google.engedu.anagrams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;

public class AnagramDictionary {
    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<String>();
    private Set<String> wordSet = new HashSet<String>();
    private Map<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        boolean validdictword = wordSet.contains(word);
        boolean cont = word.toLowerCase().contains(base.toLowerCase());
        return (validdictword && !cont);
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> anagrams = new ArrayList<String>();
        for (String word2 : wordList) {
            if (isAnagram(targetWord, word2))
                anagrams.add(word2);
        }
        if (!lettersToWord.containsKey(targetWord))
            lettersToWord.put(targetWord, anagrams);
        return anagrams;
    }

    private static boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        word1 = sortLetters(word1);
        word2 = sortLetters(word2);
        return word1.equals(word2);
    }

    private static String sortLetters(String word) {
        String temp = "";
        int l = word.length();
        char[] arr = new char[l];
        for (int i = 0; i < l; i++) {
            arr[i] = word.charAt(i);
        }
        Arrays.sort(arr);
        for (int i = 0; i < l; i++) {
            temp = temp + arr[i];
        }
        return temp;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}

