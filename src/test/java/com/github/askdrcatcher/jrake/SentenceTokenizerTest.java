package com.github.askdrcatcher.jrake;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by askdrcatcher on 4/8/17.
 */
public class SentenceTokenizerTest {

    private static String input = String.format("%s%s%s%s%s%s",
            "Compatibility of systems of linear constraints over the set of natural numbers.",
            " Criteria of compatibility of a system of linear Diophantine equations, strict inequations,",
            " and nonstrict inequations are considered. Upper bounds for components of a minimal set of solutions",
            " and algorithms of construction of minimal generating sets of solutions for all types of systems are given.",
            " These criteria and the corresponding algorithms for constructing a minimal supporting set of solutions ",
            "can be used in solving all the considered types of systems and systems of mixed types.");

    @Test
    public void testSentenceSplit() {

        final Sentences sentences = new SentenceTokenizer().split(input);

        Iterator<Sentence> itr = sentences.iterator();

        int count = 0;

        while(itr.hasNext()) {
            count++;

            Sentence sentence = itr.next();

            assertThat(sentence.getValue(),
                    anyOf(equalTo("Compatibility of systems of linear constraints over the set of natural numbers"),
                            equalTo(" Criteria of compatibility of a system of linear Diophantine equations"),
                            equalTo(" strict inequations"),
                            equalTo(" and nonstrict inequations are considered"),
                            equalTo(" Upper bounds for components of a minimal set of solutions and algorithms of construction of minimal generating sets of solutions for all types of systems are given"),
                            equalTo(" These criteria and the corresponding algorithms for constructing a minimal supporting set of solutions can be used in solving all the considered types of systems and systems of mixed types")));
        }

        assertEquals(6, count);

    }
}
