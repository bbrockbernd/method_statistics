package gui.markdownDisplay;

import com.intellij.util.ui.ColumnInfo;
import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinksColumnInfoFactoryTest extends TestCase {

    ColumnInfo[] infos;

    @BeforeEach
    void start() {
        infos = new LinksColumnInfoFactory().getColumnInfos();
    }

    @Test
    void linkTest() {
        assertEquals("Link", infos[0].getName());
    }

    @Test
    void typeTest() {
        assertEquals("Type", infos[1].getName());
    }

    @Test
    void coverTest() {
        assertEquals("Cover", infos[2].getName());
    }

    @Test
    void insideTheRepoTest() {
        assertEquals("Inside this Repo", infos[3].getName());
    }

}
