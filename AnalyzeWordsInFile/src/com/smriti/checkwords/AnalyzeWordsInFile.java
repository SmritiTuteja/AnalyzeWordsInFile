package com.smriti.checkwords;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class AnalyzeWordsInFile {

	public Map<String, Integer> getWordCount(String fName) {

		FileInputStream fInput = null;
		DataInputStream dInput = null;
		BufferedReader br = null;
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		try {
			fInput = new FileInputStream(fName);
			dInput = new DataInputStream(fInput);
			br = new BufferedReader(new InputStreamReader(dInput));
			String line = null;

			// stop words list
			Set<String> stopWordsList = new HashSet<>(
					Arrays.asList(new String[] { "a", "about", "above",
							"after", "again", "against", "all", "am", "an",
							"and", "any", "are", "aren't", "as", "at", "be",
							"because", "been", "before", "being", "below",
							"between", "both", "but", "by", "can't", "cannot",
							"could", "couldn't", "did", "didn't", "do", "does",
							"doesn't", "doing", "don't", "down", "during",
							"each", "few", "for", "from", "further", "had",
							"hadn't", "has", "hasn't", "have", "haven't",
							"having", "he", "he'd", "he'll", "he's", "her",
							"here", "here's", "hers", "herself", "him",
							"himself", "his", "how", "how's", "i", "i'd",
							"i'll", "i'm", "i've", "if", "in", "into", "is",
							"isn't", "it", "it's", "its", "itself", "let's",
							"me", "more", "most", "mustn't", "my", "myself",
							"no", "nor", "not", "of", "off", "on", "once",
							"only", "or", "other", "ought", "our", "ours",
							"ourselves", "out", "over", "own", "same",
							"shan't", "she", "she'd", "she'll", "she's",
							"should", "shouldn't", "so", "some", "such",
							"than", "that", "that's", "the", "their", "theirs",
							"them", "themselves", "then", "there", "there's",
							"these", "they", "they'd", "they'll", "they're",
							"they've", "this", "those", "through", "to", "too",
							"under", "until", "up", "very", "was", "wasn't",
							"we", "we'd", "we'll", "we're", "we've", "were",
							"weren't", "what", "what's", "when", "when's",
							"where", "where's", "which", "while", "who",
							"whom", "who's", "why", "why's", "with", "won't",
							"would", "wouldn't", "you", "you'd", "you'll",
							"you're", "you've", "your", "yours", "yourself",
							"yourselves" }));

			// map key,value excluding stop words
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String temp = st.nextToken().toLowerCase();
					if (!stopWordsList.contains(temp)) {

						if (wordMap.containsKey(temp)) {
							wordMap.put(temp, wordMap.get(temp) + 1);
						} else {
							wordMap.put(temp, 1);
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception ex) {
			}
		}
		return wordMap;
	}

	public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {

		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
				set);

		// sort values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> x,
					Map.Entry<String, Integer> y) {
				return (y.getValue()).compareTo(x.getValue());
			}

		});
		return list;
	}

	public static void main(String a[]) {
		AnalyzeWordsInFile wordsFile = new AnalyzeWordsInFile();
		Map<String, Integer> wordMap = wordsFile
				.getWordCount("C:/Users/Karthi/Desktop/testfile.txt");

		// print word count
		List<Entry<String, Integer>> list = wordsFile.sortByValue(wordMap);
		System.out.println("Words sorted by count : ");
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		// get five Most Common Words
		List<Entry<String, Integer>> topFiveList = list.subList(0, 5);
		System.out.println("\n5 Most Common Words are : ");
		for (Map.Entry<String, Integer> entry : topFiveList) {
			System.out.println(entry.getKey());
		}

		// get list size
		int size = list.size();

		// get five Least Common Words
		List<Entry<String, Integer>> bottomFiveList = list.subList(size - 6,
				size);
		System.out.println("\n5 Least Common Words are : ");
		for (Map.Entry<String, Integer> entry : bottomFiveList) {
			System.out.println(entry.getKey());
		}
	}

}
