package org.eclipse.imp.presentation.wizards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.presentation.PSPActivator;
import org.eclipse.imp.presentation.builders.PSPNature;
import org.eclipse.imp.wizards.GeneratedComponentWizard;
import org.eclipse.imp.wizards.GeneratedComponentWizardPage;
import org.eclipse.imp.wizards.IMPWizard;
import org.eclipse.imp.wizards.WizardPageField;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class NewSpecWizard extends GeneratedComponentWizard {
    private static final String WIZARD_NAME = "New Presentation Specification";
    private static final String WIZARD_DESCRIPTION = "Creates a new IMP presentation specification file";

    public NewSpecWizard() {
        // TODO Auto-generated constructor stub
    }

    protected NewPresentationSpecPage thePage = null;

    @Override
    public void addPages() {
        thePage= new NewPresentationSpecPage(this);
        addPages(new GeneratedComponentWizardPage[] { thePage } );
    }

    @Override
    protected List<String> getPluginDependencies() {
        return Arrays.asList(new String[] {
                "org.eclipse.core.runtime", "org.eclipse.core.resources",
                "org.eclipse.imp.runtime", "org.eclipse.ui", "org.eclipse.jface.text", 
                "org.eclipse.ui.editors", "org.eclipse.ui.workbench.texteditor", "lpg.runtime" });
    }

    @Override
    protected void collectCodeParms() {
        super.collectCodeParms();
    }

    @Override
    protected void generateCodeStubs(IProgressMonitor mon) throws CoreException {
        Map<String, String> subs= getStandardSubstitutions(fProject);
        String presentationSpecName= subs.get("$LANG_NAME$") + ".psp";

        new PSPNature().addToProject(fProject);

        IFile specFile= createFileFromTemplate(presentationSpecName, PSPActivator.kPluginID, "presentation.psp", fPackageFolder, subs, fProject, mon);

        this.editFile(mon, specFile);
    }

    class NewPresentationSpecPage extends GeneratedComponentWizardPage {
        protected boolean fGenerateTreeModelBuilder = true;
        protected boolean fGenerateLabelProvider = true;
        protected boolean fActivatorAppend = true;
        protected boolean fResourcesAppend = true;

        public NewPresentationSpecPage(IMPWizard owner) {
            super(owner, "", false, fWizardAttributes, WIZARD_NAME, WIZARD_DESCRIPTION);
        }

        @Override
        protected void createAdditionalControls(Composite parent) {
            final WizardPageField f= createLabeledTextFieldWithFileBrowse(parent, "", "Spec File", "Name of the new presentation specification file", "", true);
            // arrange for default name of spec file to start w/ language name
            this.fLanguageText.addModifyListener(new ModifyListener() {
                public void modifyText(ModifyEvent e) {
                    f.fText.setText(((Text) e.widget).getText() + ".psp");
                } });
        }
    }
}
