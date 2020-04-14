package gui;

import com.intellij.util.ui.ColumnInfo;
import core.MarkdownSummary;
import core.MethodSummary;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * All the column properties of the gui table are defined here.
 */
class MarkdownColumnInfoFactory {

    /**
     * Generates the columnvector for the table model.
     * @return ColumnInfo's
     */
    public ColumnInfo[] getColumnInfos() {
        return new ColumnInfo[] {
            new LinksInfo()
        };
    }

    static class LinksInfo extends ColumnInfo<MarkdownSummary, String> {

        public LinksInfo() {
            super("Links");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return Integer.toString(methodItem.getLinks().size());
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparingInt(o -> o.getLinks().size());
        }
    }

}
