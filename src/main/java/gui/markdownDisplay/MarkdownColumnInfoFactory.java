package gui.markdownDisplay;

import com.intellij.util.ui.ColumnInfo;
import core.markdownStats.MarkdownSummary;
import java.util.Comparator;
import org.jetbrains.annotations.Nullable;


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
            new NameInfo(),
            new LinksInfo(),
            new ImageInfo(),
            new ParagraphInfo(),
            new HeaderInfo(),
            new TableHeaderInfo(),
            new BlockQuoteInfo()
        };
    }

    static class NameInfo extends ColumnInfo<MarkdownSummary, String> {

        public NameInfo() {
            super("Name");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return methodItem.getName();
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparing(o -> o.getName());
        }
    }

    static class LinksInfo extends ColumnInfo<MarkdownSummary, String> {

        public LinksInfo() {
            super("Links");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return String.valueOf(methodItem.getLinks().size());
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparingInt(o -> o.getLinks().size());
        }
    }

    static class ImageInfo extends ColumnInfo<MarkdownSummary, String> {

        public ImageInfo() {
            super("Images");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return String.valueOf(methodItem.getNumberOfImages());
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparingInt(o -> o.getNumberOfImages());
        }
    }

    static class ParagraphInfo extends ColumnInfo<MarkdownSummary, String> {

        public ParagraphInfo() {
            super("Paragraphs");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return String.valueOf(methodItem.getNumberOfParagraphs());
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparingInt(o -> o.getNumberOfParagraphs());
        }
    }

    static class HeaderInfo extends ColumnInfo<MarkdownSummary, String> {

        public HeaderInfo() {
            super("Headers");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return String.valueOf(methodItem.getHeaders());
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparingInt(o -> o.getHeaders());
        }
    }

    static class TableHeaderInfo extends ColumnInfo<MarkdownSummary, String> {

        public TableHeaderInfo() {
            super("Table Headers");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return String.valueOf(methodItem.getTableHeaders());
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparingInt(o -> o.getTableHeaders());
        }
    }

    static class BlockQuoteInfo extends ColumnInfo<MarkdownSummary, String> {

        public BlockQuoteInfo() {
            super("Block Quotes");
        }

        @Nullable
        @Override
        public String valueOf(MarkdownSummary methodItem) {
            return String.valueOf(methodItem.getBlockQuotes());
        }

        @Nullable
        @Override
        public Comparator<MarkdownSummary> getComparator() {
            return Comparator.comparingInt(o -> o.getBlockQuotes());
        }
    }

}
