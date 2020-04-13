package gui;

import com.intellij.util.ui.ColumnInfo;
import core.MethodSummary;
import java.util.Comparator;
import org.jetbrains.annotations.Nullable;

/**
 * All the column properties of the gui table are defined here.
 */
class ColumnInfoFactory {

    /**
     * Generates the columnvector for the table model.
     * @return ColumnInfo's
     */
    public ColumnInfo[] getColumnInfos() {
        return new ColumnInfo[] {
            new NameInfo(),
            new CCInfo(),
            new LOCInfo(),
            new ParamInfo(),
            new ReturnInfo()
        };
    }

    static class NameInfo extends ColumnInfo<MethodSummary, String> {

        public NameInfo() {
            super("Name");
        }

        @Nullable
        @Override
        public String valueOf(MethodSummary methodItem) {
            return methodItem.name;
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparing(o -> o.name);
        }
    }

    static class CCInfo extends ColumnInfo<MethodSummary, String> {

        public CCInfo() {
            super("CC");
        }

        @Nullable
        @Override
        public String valueOf(MethodSummary methodItem) {
            return Integer.toString(methodItem.CC);
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparingInt(o -> o.CC);
        }
    }

    static class LOCInfo extends ColumnInfo<MethodSummary, String> {

        public LOCInfo() {
            super("LOC");
        }

        @Nullable
        @Override
        public String valueOf(MethodSummary methodItem) {
            return Integer.toString(methodItem.LOC);
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparingInt(o -> o.LOC);
        }
    }

    static class ParamInfo extends ColumnInfo<MethodSummary, String> {

        public ParamInfo() {
            super("Parameter Size");
        }

        @Nullable
        @Override
        public String valueOf(MethodSummary methodSummary) {
            return Integer.toString(methodSummary.params);
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparingInt(o -> o.params);
        }
    }

    static class ReturnInfo extends ColumnInfo<MethodSummary, String> {

        public ReturnInfo() {
            super("Return Type");
        }

        @Nullable
        @Override
        public String valueOf(MethodSummary methodItem) {
            return methodItem.returnType;
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparing(o -> o.returnType);
        }
    }
}
