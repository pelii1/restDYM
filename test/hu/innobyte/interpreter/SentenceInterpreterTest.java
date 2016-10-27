package hu.innobyte.interpreter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import hu.innobyte.checker.Checker;
import hu.innobyte.checker.StringComparatorInitData;

public class SentenceInterpreterTest {
    private final String SENTENCE_ONE = "Hello Vanda! Mennyi az egyenlegem?";
    private final String SENTENCE_TWO = "Hello Vanda! Mennyi az egyenlegem?";
    private final String SENTENCE_THREE = "Helo Vanad! Mennyi az egyenlegm?";

    @Test
    public void testRepeater() {
	Checker checker = CheckerType.Repeater.getChecker();
	SentenceInterpreter testObj = SentenceInterpreter.builder().checker(checker).build();

	assertEquals(testObj.normalizeSentence(SENTENCE_ONE), SENTENCE_ONE);
    }

    @Test
    public void testSplit() {
	SentenceInterpreter testObj = SentenceInterpreter.builder().build();

	List<String> items = testObj.splitSentence("Hello Vanda!");

	assertEquals(items.size(), 3);
	assertEquals(items.get(0), "Hello");
	assertEquals(items.get(2), "!");
    }

    @Test
    public void testStringCompare() {
	Checker checker = CheckerType.StringComparator.getChecker();
	checker.init(StringComparatorInitData.builder()
					     .distance(0.9f)
					     .filePath(System.getProperty("user.dir") + "\\files\\StringCompare.txt")
					     .build());

	SentenceInterpreter testObj = SentenceInterpreter.builder().checker(checker).build();

	assertEquals(testObj.normalizeSentence(SENTENCE_THREE), SENTENCE_TWO);

    }

}
