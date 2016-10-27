package hu.innobyte.checker;

import static org.simmetrics.builders.StringMetricBuilder.with;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.JaroWinkler;
import org.simmetrics.simplifiers.Simplifiers;

import lombok.Getter;

@Getter
public class StringComparator implements Checker {
    private String filePath;
    private float maxDistance;
    private List<String> words = new ArrayList<String>();

    @Override
    public String check(String word) {
	float minDistance = 0;
	String foundWord = word;

	for (String nextWord : words) {
	    StringMetric metric = with(new JaroWinkler())
							 .simplify(Simplifiers.toLowerCase())
							 .build();

	    float actDistance = metric.compare(word, nextWord);

	    if (actDistance == 1) {
		return word;
	    }

	    if (actDistance > maxDistance && minDistance < actDistance) {
		minDistance = actDistance;
		foundWord = nextWord;
	    }
	}
	return foundWord;
    }

    @Override
    public void init(CheckerInitData checkerInit) {
	StringComparatorInitData initData = (StringComparatorInitData) checkerInit;

	filePath = initData.getFilePath();
	maxDistance = initData.getDistance();

	wordsLoadFromFile();
    }

    private void wordsLoadFromFile() {
	File file = new File(filePath);
	try {
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		String line = "";

		while ((line = br.readLine()) != null) {
		    words.add(line);
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
