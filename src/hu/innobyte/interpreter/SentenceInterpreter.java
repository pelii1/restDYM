package hu.innobyte.interpreter;

import java.util.ArrayList;
import java.util.List;

import hu.innobyte.checker.Checker;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SentenceInterpreter {
    private Checker checker;

    private final String GLYPHS = ",.?!";

    public String normalizeSentence(String sentence) {
	List<String> words = splitSentence(sentence);
	String normalizedSentence = "";

	for (String word : words) {
	    if (!normalizedSentence.isEmpty()) {
		normalizedSentence += " ";
	    }

	    normalizedSentence += checker.check(word);
	}

	return sentenceAfterProcessing(normalizedSentence);
    }

    public List<String> splitSentence(String sentence) {
	List<String> result = new ArrayList<String>();

	String[] words = gylphSetting(sentence).split(" ");
	for (String word : words) {
	    result.add(word);
	}
	return result;
    }

    private String gylphSetting(String sentence) {
	String resultSentence = "";
	for (int position = 0; position < sentence.length(); position++) {
	    if (GLYPHS.indexOf(sentence.charAt(position)) > -1) {
		resultSentence += " ";
	    }

	    resultSentence += sentence.charAt(position);
	}

	return resultSentence;
    }

    private String sentenceAfterProcessing(String sentence) {
	String resultSentence = "";
	sentence += " ";
	for (int position = 0; position < sentence.length() - 1; position++) {
	    if (GLYPHS.indexOf(sentence.charAt(position + 1)) > -1 && (int) sentence.charAt(position) == 32) {
		continue;
	    }

	    resultSentence += sentence.charAt(position);
	}
	return resultSentence;
    }

}
