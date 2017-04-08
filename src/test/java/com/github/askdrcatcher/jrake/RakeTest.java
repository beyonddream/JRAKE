package com.github.askdrcatcher.jrake;

import com.github.askdrcatcher.jrake.util.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by askdrcatcher on 3/12/17.
 */
public class RakeTest {

    private static String input = String.format("%s%s%s%s%s%s",
                    "Compatibility of systems of linear constraints over the set of natural numbers.",
                    " Criteria of compatibility of a system of linear Diophantine equations, strict inequations,",
                    " and nonstrict inequations are considered. Upper bounds for components of a minimal set of solutions",
                    " and algorithms of construction of minimal generating sets of solutions for all types of systems are given.",
                    " These criteria and the corresponding algorithms for constructing a minimal supporting set of solutions ",
                    "can be used in solving all the considered types of systems and systems of mixed types.");

    private static String expectedKeyWordCandidates = String.format("%s%s%s%s%s%s",
            "{natural numbers=4.0, algorithms=1.0, components=1.0, types=1.6666666666666667, set=2.0," ,
                    " constructing=1.0, solving=1.0, nonstrict inequations=4.0, criteria=1.0, considered=1.5, solutions=1.0," ,
                    " minimal generating sets=8.666666666666666, considered types=3.166666666666667, upper bounds=4.0," ,
                    " strict inequations=4.0, minimal set=4.666666666666666, linear constraints=4.5, system=1.0, systems=1.0," ,
                    " construction=1.0, minimal supporting set=7.666666666666666, mixed types=3.666666666666667," ,
                    " compatibility=1.0, linear diophantine equations=8.5}");

    private static String expectedSortedKeyWordCandidates = String.format("%s%s%s",
            "{minimal generating sets=8.666666666666666," ,
            " linear diophantine equations=8.5, minimal supporting set=7.666666666666666, minimal set=4.666666666666666," ,
            " linear constraints=4.5, natural numbers=4.0, nonstrict inequations=4.0, upper bounds=4.0}");

    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }

    @Test
    public void basicSetup() throws IOException {

        final Rake rakeInstance = new Rake();

        final Sentences sentences = new SentenceTokenizer().split(input);
        final StopList stopList = new StopList().generateStopWords(new FileUtil("SmartStoplist.txt"));
        final CandidateList candidateList = new CandidateList().generateKeywords(sentences, stopList.getStopWords());


        final Map<String, Double> wordScore = rakeInstance.calculateWordScores(candidateList.getPhraseList());
        final Map<String, Double> keywordCandidates = rakeInstance.generateCandidateKeywordScores(candidateList.getPhraseList(),
                                                            wordScore);

        assertEquals("Actual keyWordCandidates didn't match the expected", expectedKeyWordCandidates,
                keywordCandidates.toString());

        assertEquals("Actual sortedKeyWordCandidates didn't match the expected", expectedSortedKeyWordCandidates,
                rakeInstance.sortKeyWordCandidates(keywordCandidates).toString());

    }

}
