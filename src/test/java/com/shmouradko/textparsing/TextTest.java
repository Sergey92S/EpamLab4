package com.shmouradko.textparsing;

import com.shmouradko.textparsing.composite.Composite;
import com.shmouradko.textparsing.composite.TextComponent;
import com.shmouradko.textparsing.function.TextFunction;
import com.shmouradko.textparsing.handle.ParagraphHandler;
import com.shmouradko.textparsing.handle.SentenceHandler;
import com.shmouradko.textparsing.handle.WordHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by test on 16.11.2016.
 */
@RunWith(value = Parameterized.class)
public class TextTest {
    private TextComponent currentComponent;
    private List<String> parseCurrentText;
    private StringBuilder currentResultText;
    private String currentText;
    private String expectedDeleteText;
    private String expectedChangeText;
    private String expectedSortText;
    private char symbol;
    private int length;

    public TextTest(String currentText, String expectedDeleteText, String expectedChangeText, String expectedSortText, char symbol, int length){
        this.currentText = currentText;
        this.expectedDeleteText = expectedDeleteText;
        this.expectedChangeText = expectedChangeText;
        this.expectedSortText = expectedSortText;
        this.symbol = symbol;
        this.length = length;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"    It has survived not only five centuries, but also the leap into 13+(3++) electronic --3 typesetting, remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-4)-3)-2)-1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                        "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (0-(2*2*(3*(2-1/2*2)-2)-10/2))*(++5) Ipsum is that it has a more–or–less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
                        "    It is a (-5+1/2*(2+5*2))*1200 established fact that a reader will be of a page when looking at its layout.\n" +
                        "    Hello.",
                        "has survived not only five centuries, but also the leap into 17 electronic 2 typesetting, remaining 8 essentially -3 unchanged. was popularised in the 5 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using 6 Ipsum is that it has a more–or–less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. is a 1200 established fact that a reader will be of a page when looking at its layout. Hello.",
                        "unchanged has survived not only five centuries, but also the leap into 17 electronic 2 typesetting, remaining 8 essentially -3 It. Ipsum was popularised in the 5 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem It. layout is a long established fact that a reader will be distracted by the readable content of a page when looking at its It. English point of using 6 Ipsum is that it has a more–or–less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable The. layout is a 1200 established fact that a reader will be of a page when looking at its It. Hello.",
                        "Hello. It is a 1200 established fact that a reader will be of a page when looking at its layout. It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. It has survived not only five centuries, but also the leap into 17 electronic 2 typesetting, remaining 8 essentially -3 unchanged. The point of using 6 Ipsum is that it has a more–or–less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. It was popularised in the 5 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                        'I',
                        2
                }
        });
    }

    @Before
    public void initText() {
        currentComponent = new Composite();
        currentComponent.addElement(new Composite());
        parseCurrentText = new ArrayList<>();
        parseCurrentText.add(currentText);
        (new ParagraphHandler().setSuccessor(new SentenceHandler().setSuccessor(new WordHandler().setSuccessor(new WordHandler())))).chain(currentComponent, parseCurrentText);
    }

    @Test
    public void deleteSpecialLexemeTrue(){
        currentResultText = new StringBuilder();
        TextFunction.deleteSpecialLexeme(currentComponent.defineElement().get(0), symbol, length).print(currentResultText);
        assertTrue(currentResultText.toString().trim().equals(expectedDeleteText.trim()));
    }

    @Test
    public void deleteSpecialLexemeFalse(){
        currentResultText = new StringBuilder();
        TextFunction.deleteSpecialLexeme(currentComponent.defineElement().get(0), symbol, length).print(currentResultText);
        assertFalse(currentResultText.toString().trim().equals(expectedChangeText.trim()));
    }

    @Test
    public void changeLexemeAtSentenceTrue(){
        currentResultText = new StringBuilder();
        TextFunction.changeLexemeAtSentence(currentComponent.defineElement().get(0)).print(currentResultText);
        assertTrue(currentResultText.toString().trim().equals(expectedChangeText.trim()));
    }

    @Test
    public void changeLexemeAtSentenceFalse(){
        currentResultText = new StringBuilder();
        TextFunction.changeLexemeAtSentence(currentComponent.defineElement().get(0)).print(currentResultText);
        assertFalse(currentResultText.toString().trim().equals(expectedDeleteText.trim()));
    }

    @Test
    public void sortSentenceByLexemeTrue(){
        currentResultText = new StringBuilder();
        TextFunction.sortSentenceByLexeme(currentComponent.defineElement().get(0)).print(currentResultText);
        assertTrue(currentResultText.toString().trim().equals(expectedSortText.trim()));
    }

    @Test
    public void sortSentenceByLexemeFalse(){
        currentResultText = new StringBuilder();
        TextFunction.sortSentenceByLexeme(currentComponent.defineElement().get(0)).print(currentResultText);
        assertFalse(currentResultText.toString().trim().equals(expectedDeleteText.trim()));
    }

    @After
    public void clearText() {
        currentComponent = null;
        parseCurrentText = null;
        currentResultText = null;
    }

}
