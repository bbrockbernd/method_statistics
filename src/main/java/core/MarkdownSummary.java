package core;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Method summary object for storing relevant method data for the plugin.
 */
public class MarkdownSummary {

    public PsiFile file;

    private String name;
    private ArrayList<PsiElement> links;

    /**
     * Constructor for the MarkdownSummary class.
     * It calculates the statistics for a markdown file.
     * @param file markdown file to analyze.
     */
    public MarkdownSummary(PsiFile file) {
        this.file = file;
        name = file.getName();
        MarkdownVisitor visitor = new MarkdownVisitor();
        file.accept(visitor);
        links = visitor.links;

    }

    public ArrayList<PsiElement> getLinks() {
        return links;
    }
}

