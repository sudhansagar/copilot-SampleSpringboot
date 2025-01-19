package com.example.MySpringBootApp.service;

import org.springframework.stereotype.Service;
import org.apache.tika.Tika;
import edu.stanford.nlp.pipeline.*;

import java.io.IOException;
import java.util.Properties;

@Service
public class CVAnalysisService {

    public String analyze(String cvContent) {
        // Use Apache Tika to extract text from the CV content
        Tika tika = new Tika();
        String extractedText;
        try {
            extractedText = tika.parseToString(cvContent);
        } catch (IOException e) {
            return "Failed to extract text from CV";
        }

        // Use Stanford NLP to analyze the extracted text
        String analysisResult = analyzeText(extractedText);

        return analysisResult;
    }

    private String analyzeText(String text) {
        // Set up Stanford NLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create a document object
        CoreDocument document = new CoreDocument(text);

        // Annotate the document
        pipeline.annotate(document);

        // Extract named entities
        StringBuilder analysisResult = new StringBuilder();
        analysisResult.append("Named Entities:\n");
        for (CoreEntityMention em : document.entityMentions()) {
            analysisResult.append(em.text()).append(" (").append(em.entityType()).append(")\n");
        }

        return analysisResult.toString();
    }
}
