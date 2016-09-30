package test;

import org.apache.lucene.analysis.TokenStream;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.FileReader;

/**
 * Created by gang.qin on 2016/9/29.
 */
public class IKAnalyzerTest {
    public static void main(String[] args) {
        IKAnalyzer analyzer = new IKAnalyzer();
        try {
            TokenStream tokenStream = analyzer.tokenStream("content", new FileReader());
            tokenStream.addAttribute(CharTermAttribute.class);
            while (tokenStream.incrementToken()) {
                CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
                System.out.println(charTermAttribute.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
