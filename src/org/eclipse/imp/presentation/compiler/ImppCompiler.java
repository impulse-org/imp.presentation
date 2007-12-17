package org.eclipse.imp.presentation.compiler;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.builder.MarkerCreator;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.presentation.parser.ImppParseController;
import org.eclipse.imp.presentation.parser.Ast.*;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

public class ImppCompiler {
    private static final String sClassNameMacro= "$FILE$";

    private static final String sTemplateHeader= "";
//    	"public class " + sClassNameMacro + " {\n" +
//        "\tpublic static void main(String[] args) {\n" +
//        "\t\tnew " + sClassNameMacro + "().main();\n" +
//        "\t\tSystem.out.println(\"done.\");\n" +
//        "\t}\n";

    private static final String sTemplateFooter= ""; // "}\n";

    Stack<String> fTranslationStack= new Stack<String>();

    Map<ASTNode,String> fTranslationMap= new HashMap<ASTNode,String>();

    //public static final String PROBLEM_MARKER_ID= Activator.kPluginID + ".$PROBLEM_ID$";
    public String PROBLEM_MARKER_ID;
    
    public ImppCompiler(String problem_marker_id) {
    	PROBLEM_MARKER_ID = problem_marker_id;
    }

    private final class TranslatorVisitor extends AbstractVisitor {
//    	SymbolTable innerScope;

	public void unimplementedVisitor(String s) {
            // System.err.println("Don't know how to translate node type '" + s + "'.");
        }
	private void translate(ASTNode n, String xlat) {
	    fTranslationMap.put(n, xlat);
	}
	private String translationOf(Object n) {
	    if (n == null) return "";
	    if (!fTranslationMap.containsKey(n))
		return n.toString();
	    return fTranslationMap.get(n);
	}
        private String translateList(AbstractASTNodeList list, String sep) {
            StringBuilder sb= new StringBuilder();
            for(int i=0; i < list.size(); i++) {
        	if (i > 0)
        	    sb.append(sep);
        	sb.append(translationOf(list.getElementAt(i)));
            }
            return sb.toString();
        }
	private String outlineVisitMethod(ASTNode n, String nodeType, boolean isLeaf) {
	    return "public boolean visit(" + nodeType + " n) {\n" +
		    (isLeaf ? "addSubItem" : "pushSubItem") + "(n.getname().toString(), n);\n" +
        	    "return true;\n" +
        	    "}\n";
	}
	private String outlineEndVisitMethod(String nodeType) {
	    return "public void endVisit(" + nodeType + " n) {\n" +
	    	   "popSubItem();\n" +
        	   "}\n";
	}
        // START_HERE
        // Provide appropriate visitor methods (like the following examples)
        // for the node types in your AST
        ///*
        @Override
        public void endVisit(binary_op n) {
            translate(n, translationOf(n.getleft()) + n.getop() + translationOf(n.getright()));
        }
        @Override
        public void endVisit(callSuffix n) {
            translate(n, "(" + translationOf(n.getparameter_list()) + ")");
        }
	@Override
	public void endVisit(color_attribute_decl n) {
	    translate(n, "color = " + n.getresource_value());
	}
        @Override
        public void endVisit(compilationUnit n) {
            translate(n, translationOf(n.getpackage_spec()) +
        	    translationOf(n.getimport_specs()) +
        	    "import java.awt.font.TextAttribute;\n" +
        	    "import org.eclipse.jface.viewers.IContentProvider;\n" +
        	    "import org.eclipse.jface.resource.ImageDescriptor;\n" +
        	    translationOf(n.getpresentation_list()));
        }
        @Override
        public void endVisit(editor_body n) {
            translate(n, translationOf(n.getmembers()));
        }
        @Override
        public void endVisit(editor_body_memberList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(editor_decl n) {
            translate(n, "class " + n.getid() + translationOf(n.getsup()) + " implements IEditorPresentation {\n" +
        	    translationOf(n.getbody()) +
        	    "}\n");
        }
        @Override
        public void endVisit(expressionList n) {
            translate(n, translateList(n, ", "));
        }
        @Override
        public void endVisit(expression n) {
            translate(n, "n" /*n.getIDENTIFIER().toString()*/);
        }
	@Override
	public void endVisit(font_attribute_decl n) {
	    translate(n, "font = " + n.getresource_value());
	}
        @Override
        public void endVisit(icon_presentation n) {
            translate(n, "setIcon(" + translationOf(n.getexpression()) + ");\n");
        }
        @Override
        public void endVisit(import_specs n) {
            translate(n, translationOf(n.getimport_specs()) + translationOf(n.getimport_spec()));
        }
        @Override
        public void endVisit(import_spec n) {
            translate(n, "import " + n.getqualified_name() + ";\n");
        }
        @Override
        public void endVisit(label_presentation n) {
            translate(n, "setLabel(" + translationOf(n.getexpression()) + ");\n");
        }
        @Override
        public void endVisit(leaf_spec n) {
            translate(n, outlineVisitMethod(n, n.getid().toString(), true));
        }
        @Override
        public void endVisit(literal0 n) {
            translate(n, n.getSTRING_LITERAL().toString());
        }
        @Override
        public void endVisit(literal1 n) {
            translate(n, n.getINTEGER().toString());
        }
        @Override
        public void endVisit(member_ref n) {
            translate(n, translationOf(n.gettarget()) + "." + n.getIDENTIFIER() + translationOf(n.getcallSuffix()));
        }
        @Override
        public void endVisit(method_call n) {
            translate(n, n.getIDENTIFIER().toString() + translationOf(n.getcallSuffix()));
        }
        @Override
        public void endVisit(node_spec n) {
            translate(n, outlineVisitMethod(n, n.getid().toString(), false) +
        	    outlineEndVisitMethod(n.getid().toString()));
        }
        @Override
        public void endVisit(optInitializer n) {
            translate(n, translationOf(n.getexpression()));
        }
        @Override
        public void endVisit(outline_decl n) {
            translate(n, "class " + n.getid().toString() + translationOf(n.getsup()) + " implements IContentProvider {\n" +
        	    translationOf(n.getbody()) + "}\n");
        }
        @Override
        public void endVisit(outline_body n) {
            translate(n, translationOf(n.getmembers()));
        }
        @Override
        public void endVisit(outline_body_memberList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(package_spec n) {
            translate(n, "package " + n.getqualified_name() + ";\n");
        }
        @Override
        public void endVisit(presentation_body n) {
            translate(n, translationOf(n.getpresentation_members()));
        }
        @Override
        public void endVisit(presentation_declaration n) {
            translate(n, "class " + n.getname() + " {\n" + translationOf(n.getbody()) +
        	    "}\n");
        }
        @Override
        public void endVisit(presentation_members n) {
            translate(n, translationOf(n.getpresentation_members()) +
        	    translationOf(n.getpresentation_member()));
        }
        @Override
        public void endVisit(presentation_declarationList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(primitive_type0 n) {
            translate(n, "ImageDescriptor");
        }
        @Override
        public void endVisit(primitive_type1 n) {
            translate(n, "Font");
        }
        @Override
        public void endVisit(primitive_type2 n) {
            translate(n, "Color");
        }
        @Override
        public void endVisit(primitive_type3 n) {
            translate(n, "style");
        }
        @Override
        public void endVisit(primitive_type4 n) {
            translate(n, "set");
        }
        @Override
        public void endVisit(primitive_type5 n) {
            translate(n, "int");
        }
        @Override
        public void endVisit(primitive_type6 n) {
            translate(n, "boolean");
        }
        @Override
        public void endVisit(resource_decl n) {
            StringBuilder sb= new StringBuilder();
            sb.append(translationOf(n.getprimitive_type()));
            sb.append(' ');
            sb.append(n.getident().toString());
            if (n.getinitializer() != null) {
        	sb.append(" = ");
        	sb.append(translationOf(n.getinitializer()));
            }
            sb.append(";\n");
            translate(n, sb.toString());
        }
        @Override
        public void endVisit(resource_value0 n) {
            translate(n, n.getIDENTIFIER().toString());
        }
        @Override
        public void endVisit(resource_value1 n) {
            translate(n, n.getSTRING_LITERAL().toString());
        }
	@Override
	public void endVisit(style_attributes_decl n) {
	    translate(n, "style = " + n.getstyle_set());
	}
        @Override
        public void endVisit(style_key0 n) {
            translate(n, "regular");
        }
        @Override
        public void endVisit(style_key1 n) {
            translate(n, "bold");
        }
        @Override
        public void endVisit(style_key2 n) {
            translate(n, "italic");
        }
        @Override
        public void endVisit(style_key3 n) {
            translate(n, "under");
        }
        @Override
        public void endVisit(style_keyList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(style_set n) {
            translate(n, translationOf(n.getstyle_list()));
        }
        @Override
        public void endVisit(super_opt n) {
            translate(n, " extends " + n.getqualified_name());
        }
        @Override
        public void endVisit(text_attribute_declList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(text_presentation_spec n) {
            translate(n, "class " + n.getname() + translationOf(n.getsup()) + " implements ILabelProvider {\n" +
        	    "public TextAttribute getColoring(IParseController controller, IToken token) {\n" +
        	    "switch (token.getKind()) {\n" +
        	    translationOf(n.gettext_presentation_members()) +
        	    "}\n" +
        	    "}\n" +
        	    "}\n");
        }
        @Override
        public void endVisit(tree_presentation_spec n) {
            translate(n, "class " + n.getname() + " extends AbstractVisitor {\n" +
        	    translationOf(n.getmembers()) +
        	    "}\n");
        }
        @Override
        public void endVisit(tree_presentation_memberList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(tree_presentation_attributeList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(tree_presentation_member n) {
            translate(n, "public void endVisit(" + n.getname() + " n) {\n" +
        	    translationOf(n.getattrs()) +
        	    "}\n");
        }
        @Override
        public void endVisit(token_decl n) {
            translate(n, "case TK_" + n.getname() + ":\n" +
        	    "return " + translationOf(n.getattrs()) + ";\n");
        }
        @Override
        public void endVisit(text_presentation_memberList n) {
            translate(n, translateList(n, ""));
        }
        @Override
        public void endVisit(use_spec n) {
            translate(n, "/*use " + n.getid() + "*/\n" +
        	    "TreePresentation treePres = new " + n.getid() + "();\n");
        }
        //*/
    }

    public ImppCompiler() {
    }

    public String getFileContents(IFile file) {
        char[] buf= null;
        try {
            File javaFile= new File(file.getLocation().toOSString());
            FileReader fileReader= new FileReader(javaFile);
            int len= (int) javaFile.length();

            buf= new char[len];
            fileReader.read(buf, 0, len);
            return new String(buf);
        } catch(FileNotFoundException fnf) {
            System.err.println(fnf.getMessage());
            return "";
        } catch(IOException io) {
            System.err.println(io.getMessage());
            return "";
        }
    }

    private String formatJavaCode(String contents) {
	CodeFormatter formatter= org.eclipse.jdt.core.ToolFactory.createCodeFormatter(JavaCore.getOptions());
	TextEdit te= formatter.format(CodeFormatter.K_COMPILATION_UNIT, contents, 0, contents.length(), 0, "\n");

	if (te == null)
	    return contents;

	IDocument l_doc= new Document(contents);
	try {
	    te.apply(l_doc);
	} catch (MalformedTreeException e) {
	    e.printStackTrace();
	} catch (BadLocationException e) {
	    e.printStackTrace();
	} catch (NullPointerException e) {
		// Can happen that te is null
		e.printStackTrace();
	}
	contents= l_doc.get();
	return contents;
    }

    public void compile(IFile file, IProgressMonitor mon) {
    	
    	if (file == null) {
            System.err.println("ImppCompiler.compile(..):  File is null; returning without parsing");
    		return;
    	}
    	IProject project= file.getProject();
    	if (project == null) {
            System.err.println("ImppCompiler.compile(..):  Project is null; returning without parsing");
    		return;
    	}
		ISourceProject sourceProject = null;
    	try {
    		sourceProject = ModelFactory.open(project);
    	} catch (ModelException me){
            System.err.println("ImppCompiler.compile(..):  Model exception:\n" + me.getMessage() + "\nReturning without parsing");
            return;
    	}
        IParseController parseController= new ImppParseController();
        
        // Marker creator handles error messages from the parse controller
        MarkerCreator markerCreator = new MarkerCreator(file, parseController, PROBLEM_MARKER_ID);

        // If we have a kind of parser that might be receptive, tell it
        // what types of problem marker the builder will create
        parseController.getAnnotationTypeInfo().addProblemMarkerType(PROBLEM_MARKER_ID);
        
        parseController.initialize(file.getProjectRelativePath(), sourceProject, markerCreator);
    	
        parseController.parse(getFileContents(file), false, mon);

        ASTNode currentAst= (ASTNode) parseController.getCurrentAst();

        if (currentAst == null) {
            System.err.println("ImppCompiler.compile(..):  current AST is null (parse errors?); unable to compile.");
        	return;
        }

        String fileExten= file.getFileExtension();
        String fileBase= file.getName().substring(0, file.getName().length() - fileExten.length() - 1);

        currentAst.accept(new TranslatorVisitor());

        IFile javaFile= project.getFile(file.getProjectRelativePath().removeFileExtension().addFileExtension("java"));
        String javaSource= sTemplateHeader.replaceAll(sClassNameMacro.replaceAll("\\$", "\\\\\\$"), fileBase) + fTranslationMap.get(currentAst) + sTemplateFooter;

        javaSource= formatJavaCode(javaSource);

        ByteArrayInputStream bais = new ByteArrayInputStream(javaSource.getBytes());

        try {
            if (!javaFile.exists())
                javaFile.create(bais, true, mon);
            else
                javaFile.setContents(bais, true, false, mon);
        } catch (CoreException ce) {
            System.err.println(	ce.getMessage());
        }
    }
}
