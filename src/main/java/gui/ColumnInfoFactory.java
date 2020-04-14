package gui;

import com.intellij.util.ui.ColumnInfo;
import core.MethodSummary;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * All the column properties of the gui table are defined here.
 */
class ColumnInfoFactory {

    /**
     * Generates the columnvector for the table model.
     *
     * @return ColumnInfo's
     */
    public ColumnInfo[] getColumnInfos() {
        return new ColumnInfo[]{
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
            return Integer.toString(methodItem.cc);
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparingInt(o -> o.cc);
        }
    }

    static class LOCInfo extends ColumnInfo<MethodSummary, String> {

        public LOCInfo() {
            super("LOC");
        }

        @Nullable
        @Override
        public String valueOf(MethodSummary methodItem) {
            return Integer.toString(methodItem.loc);
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparingInt(o -> o.loc);
        }
    }

    static class ParamInfo extends ColumnInfo<MethodSummary, String> {

        public ParamInfo() {
            super("Parameters");
        }

        @Nullable
        @Override
        public String valueOf(MethodSummary methodSummary) {
            return methodSummary.parameterList;
        }

        @Nullable
        @Override
        public Comparator<MethodSummary> getComparator() {
            return Comparator.comparingInt(o -> o.parameterSize);
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
