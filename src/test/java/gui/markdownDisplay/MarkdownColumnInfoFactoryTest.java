package gui.markdownDisplay;

import com.intellij.util.ui.ColumnInfo;
import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkdownColumnInfoFactoryTest extends TestCase {

    ColumnInfo[] infos;

    @BeforeEach
    void start() {
        infos = new MarkdownColumnInfoFactory().getColumnInfos();
    }

    @Test
    void nameTest() {
        assertEquals("Name", infos[0].getName());
    }

    @Test
    void linkTest() {
        assertEquals("Links", infos[1].getName());
    }

    @Test
    void imagesTest() {
        assertEquals("Images", infos[2].getName());
    }

    @Test
    void paragraphsTest() {
        assertEquals("Paragraphs", infos[3].getName());
    }

    @Test
    void headersTest() {
        assertEquals("Headers", infos[4].getName());
    }

    @Test
    void tableHeadersTest() {
        assertEquals("Table Headers", infos[5].getName());
    }

    @Test
    void blockquotesTest() {
        assertEquals("Block Quotes", infos[6].getName());
    }



}
