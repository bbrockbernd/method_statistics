package gui;

import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

class ColumnInfoFactory {
    public ColumnInfo[] getColumnInfos() {
        return new ColumnInfo[] {
            new NameInfo(),
            new DescriptionInfo(),
            new CCInfo(),
            new LOCInfo(),
            new SignaturesInfo()
        };
    }

    static class NameInfo extends ColumnInfo<MethodItem, String> {

        public NameInfo() {
            super("Name");
        }

        @Nullable
        @Override
        public String valueOf(MethodItem methodItem) {
            return methodItem.name;
        }
    }

    static class DescriptionInfo extends ColumnInfo<MethodItem, String> {

        public DescriptionInfo() {
            super("Description");
        }

        @Nullable
        @Override
        public String valueOf(MethodItem methodItem) {
            return methodItem.description;
        }
    }

    static class CCInfo extends ColumnInfo<MethodItem, String> {

        public CCInfo() {
            super("CC");
        }

        @Nullable
        @Override
        public String valueOf(MethodItem methodItem) {
            return methodItem.CC;
        }
    }

    static class LOCInfo extends ColumnInfo<MethodItem, String> {

        public LOCInfo() {
            super("LOC");
        }

        @Nullable
        @Override
        public String valueOf(MethodItem methodItem) {
            return methodItem.LOC;
        }
    }

    static class SignaturesInfo extends ColumnInfo<MethodItem, String> {

        public SignaturesInfo() {
            super("Signatures");
        }

        @Nullable
        @Override
        public String valueOf(MethodItem methodItem) {
            return methodItem.signatures;
        }
    }
}
