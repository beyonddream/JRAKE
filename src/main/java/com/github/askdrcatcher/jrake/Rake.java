package com.github.askdrcatcher.jrake;

import com.github.askdrcatcher.jrake.util.FileUtil;
import com.github.askdrcatcher.jrake.util.StringUtil;

import java.io.IOException;
import java.util.*;

/**
 * Main App
 *
 * Author: askdrcatcher
 * License: MIT
 */
public class Rake {

    private List<String> separateWords(final String text, final int minimumWordReturnSize) {

        final List<String> separateWords = new ArrayList<String>();
        final String[] words = text.split("[^a-zA-Z0-9_\\+\\-/]");

        if (words != null && words.length > 0) {

            for (final String word : words) {

                String wordLowerCase = word.trim().toLowerCase();

                if (wordLowerCase.length() > 0 && wordLowerCase.length() > minimumWordReturnSize &&
                        !StringUtil.isNumber(wordLowerCase)) {

                    separateWords.add(wordLowerCase);
                }
            }
        }

        return separateWords;
    }



    public Map<String,Double> calculateWordScores(List<String> phraseList) {

        final Map<String, Integer> wordFrequency = new HashMap<String, Integer>();
        final Map<String, Integer> wordDegree = new HashMap<String, Integer>();
        final Map<String, Double> wordScore = new HashMap<String, Double>();

        for (final String phrase : phraseList) {

            final List<String> wordList = separateWords(phrase, 0);
            final int wordListLength = wordList.size();
            final int wordListDegree = wordListLength - 1;

            for (final String word : wordList) {

                if (!wordFrequency.containsKey(word)) {
                    wordFrequency.put(word, 0);
                }

                if (!wordDegree.containsKey(word)) {
                    wordDegree.put(word, 0);
                }

                wordFrequency.put(word, wordFrequency.get(word) + 1);
                wordDegree.put(word, wordDegree.get(word) + wordListDegree);
            }
        }

        final Iterator<String> wordIterator = wordFrequency.keySet().iterator();

        while (wordIterator.hasNext()) {
            final String word = wordIterator.next();

            wordDegree.put(word, wordDegree.get(word) + wordFrequency.get(word));

            if (!wordScore.containsKey(word)) {
                wordScore.put(word, 0.0);
            }

            wordScore.put(word, wordDegree.get(word) / (wordFrequency.get(word) * 1.0));
        }

        return wordScore;
    }

    public Map<String, Double> generateCandidateKeywordScores(List<String> phraseList,
                                                              Map<String, Double> wordScore) {

        final Map<String, Double> keyWordCandidates = new HashMap<String, Double>();

        for (String phrase : phraseList) {

            final List<String> wordList = separateWords(phrase, 0);
            double candidateScore = 0;

            for (final String word : wordList) {
                candidateScore += wordScore.get(word);
            }

            keyWordCandidates.put(phrase, candidateScore);
        }

        return keyWordCandidates;
    }

    public static void main(String[] args) throws IOException {

        final String text =
                "Compatibility of systems of linear constraints over the set of natural numbers. Criteria of compatibility of a system of linear Diophantine equations, strict inequations, and nonstrict inequations are considered. Upper bounds for components of a minimal set of solutions and algorithms of construction of minimal generating sets of solutions for all types of systems are given. These criteria and the corresponding algorithms for constructing a minimal supporting set of solutions can be used in solving all the considered types of systems and systems of mixed types.";

        final Rake rakeInstance = new Rake();


        Sentences sentences = new SentenceTokenizer().split(text);

        StopList stopList = new StopList();
        stopList.generateStopWords(new FileUtil("SmartStoplist.txt"));

        final List<String> phraseList = new CandidateList().getPhraseList();
        final Map<String, Double> wordScore = rakeInstance.calculateWordScores(phraseList);
        final Map<String, Double> keywordCandidates = rakeInstance.generateCandidateKeywordScores(phraseList, wordScore);

        System.out.println("keyWordCandidates = "+ keywordCandidates);

        System.out.println("sortedKeyWordCandidates = " +
                rakeInstance.sortKeyWordCandidates(keywordCandidates));
    }

    public LinkedHashMap<String, Double> sortKeyWordCandidates
            (Map<String,Double> keywordCandidates) {

        final LinkedHashMap<String, Double> sortedKeyWordCandidates = new LinkedHashMap<String, Double>();
        int totaKeyWordCandidates = keywordCandidates.size();
        final List<Map.Entry<String, Double>> keyWordCandidatesAsList =
                new LinkedList<Map.Entry<String, Double>>(keywordCandidates.entrySet());

        Collections.sort(keyWordCandidatesAsList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Double>)o2).getValue()
                        .compareTo(((Map.Entry<String, Double>)o1).getValue());
            }
        });

        totaKeyWordCandidates = totaKeyWordCandidates / 3;
        for(final Map.Entry<String, Double> entry : keyWordCandidatesAsList) {
            sortedKeyWordCandidates.put(entry.getKey(), entry.getValue());
            if (--totaKeyWordCandidates == 0) {
                break;
            }
        }

        return sortedKeyWordCandidates;
    }

}