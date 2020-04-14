package gui.methodDisplay;

import com.intellij.util.ui.ColumnInfo;
import gui.methodDisplay.MethodColumnInfoFactory;
import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodColumnInfoFactoryTest extends TestCase {

    ColumnInfo[] infos;

    @BeforeEach
    void start() {
        infos = new MethodColumnInfoFactory().getColumnInfos();
    }

    @Test
    void nameTest() {
        assertEquals("Name", infos[0].getName());
    }

    @Test
    void ccTest() {
        assertEquals("CC", infos[1].getName());
    }

    @Test
    void locTest() {
        assertEquals("LOC", infos[2].getName());
    }

    @Test
    void paramsTest() {
        assertEquals("Parameters", infos[3].getName());
    }

    @Test
    void returnTypeTest() {
        assertEquals("Return Type", infos[4].getName());
    }

}