package gui.markdownDisplay;

import com.intellij.util.ui.ColumnInfo;
import core.markdownStats.LinkSummary;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * All the column properties of the gui table are defined here.
 */
public class LinksColumnInfoFactory {

    /**
     * Generates the columnvector for the table model.
     * @return ColumnInfo's
     */
    public ColumnInfo[] getColumnInfos() {
        return new ColumnInfo[] {
                new NameInfo(),
                new TypeInfo(),
                new CoverInfo(),
                new LocationInfo()
        };
    }

    static class NameInfo extends ColumnInfo<LinkSummary, String> {

        public NameInfo() {
            super("Link");
        }

        @Nullable
        @Override
        public String valueOf(LinkSummary linkItem) {
            return linkItem.getLinkName();
        }

        @Nullable
        @Override
        public Comparator<LinkSummary> getComparator() {
            return Comparator.comparing(o -> o.getLinkName());
        }
    }
    static class TypeInfo extends ColumnInfo<LinkSummary, String> {

        public TypeInfo() {
            super("Type");
        }

        @Nullable
        @Override
        public String valueOf(LinkSummary linkItem) {
            return linkItem.getType();
        }

        @Nullable
        @Override
        public Comparator<LinkSummary> getComparator() {
            return Comparator.comparing(o -> o.getType());
        }
    }
    static class CoverInfo extends ColumnInfo<LinkSummary, String> {

        public CoverInfo() {
            super("Cover");
        }

        @Nullable
        @Override
        public String valueOf(LinkSummary linkItem) {
            return linkItem.getText();
        }

        @Nullable
        @Override
        public Comparator<LinkSummary> getComparator() {
            return Comparator.comparing(o -> o.getText());
        }
    }
    static class LocationInfo extends ColumnInfo<LinkSummary, String> {

        public LocationInfo() {
            super("Inside this Repo");
        }

        @Nullable
        @Override
        public String valueOf(LinkSummary linkItem) {
            return String.valueOf(linkItem.isInThisRepo());
        }

        @Nullable
        @Override
        public Comparator<LinkSummary> getComparator() {
            return Comparator.comparing(o -> o.isInThisRepo());
        }
    }

}
