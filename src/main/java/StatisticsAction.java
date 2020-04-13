import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAction extends AnAction {

        @Override
        public void actionPerformed(AnActionEvent event) {
            // Using the event, create and show a dialog
            Project currentProject = event.getProject();
            StringBuffer dlgMsg = new StringBuffer(event.getPresentation().getText());
            String dlgTitle = event.getPresentation().getDescription();

            //get opened file in the editor
            VirtualFile[] files = FileEditorManager.getInstance(currentProject).getSelectedFiles();

            PsiFile currentFile = PsiManager.getInstance(currentProject).findFile(files[0]);

            //display methods name if the opened file is a Java file
            if(currentFile instanceof PsiJavaFile) {
                ClassSummary classSummary = new ClassSummary((PsiJavaFile) currentFile);
                dlgMsg.append(classSummary.toString());
            }
            Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon());
        }

        @Override
        public void update(AnActionEvent e) {
            // Set the availability based on whether a project is open
            Project project = e.getProject();
            e.getPresentation().setEnabledAndVisible(project != null);
            e.getPresentation().setIcon(AllIcons.Ide.Rating);
        }
    }

