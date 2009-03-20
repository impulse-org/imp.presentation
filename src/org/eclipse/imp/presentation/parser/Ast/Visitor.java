package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

public interface Visitor extends IAstVisitor
{
    boolean visit(ASTNode n);
    void endVisit(ASTNode n);

    boolean visit(ASTNodeToken n);
    void endVisit(ASTNodeToken n);

    boolean visit(compilationUnit n);
    void endVisit(compilationUnit n);

    boolean visit(packageSpec n);
    void endVisit(packageSpec n);

    boolean visit(importSpecList n);
    void endVisit(importSpecList n);

    boolean visit(importSpec n);
    void endVisit(importSpec n);

    boolean visit(simpleNameList n);
    void endVisit(simpleNameList n);

    boolean visit(languageSpecList n);
    void endVisit(languageSpecList n);

    boolean visit(languageSpec n);
    void endVisit(languageSpec n);

    boolean visit(super_opt n);
    void endVisit(super_opt n);

    boolean visit(languageBody n);
    void endVisit(languageBody n);

    boolean visit(languageMemberList n);
    void endVisit(languageMemberList n);

    boolean visit(foldingSpec n);
    void endVisit(foldingSpec n);

    boolean visit(foldableNodeList n);
    void endVisit(foldableNodeList n);

    boolean visit(foldableNode n);
    void endVisit(foldableNode n);

    boolean visit(functionDecl n);
    void endVisit(functionDecl n);

    boolean visit(formalArgList n);
    void endVisit(formalArgList n);

    boolean visit(formalArg n);
    void endVisit(formalArg n);

    boolean visit(typeName n);
    void endVisit(typeName n);

    boolean visit(functionBody n);
    void endVisit(functionBody n);

    boolean visit(resourceDecl n);
    void endVisit(resourceDecl n);

    boolean visit(textColoringSpec n);
    void endVisit(textColoringSpec n);

    boolean visit(textColoringMemberList n);
    void endVisit(textColoringMemberList n);

    boolean visit(styleSet n);
    void endVisit(styleSet n);

    boolean visit(styleKeyList n);
    void endVisit(styleKeyList n);

    boolean visit(tokenDecl n);
    void endVisit(tokenDecl n);

    boolean visit(textAttributeDeclList n);
    void endVisit(textAttributeDeclList n);

    boolean visit(fontAttributeDecl n);
    void endVisit(fontAttributeDecl n);

    boolean visit(colorAttributeDecl n);
    void endVisit(colorAttributeDecl n);

    boolean visit(styleAttributesDecl n);
    void endVisit(styleAttributesDecl n);

    boolean visit(presentationSpec n);
    void endVisit(presentationSpec n);

    boolean visit(presentationMemberList n);
    void endVisit(presentationMemberList n);

    boolean visit(nodePresentation n);
    void endVisit(nodePresentation n);

    boolean visit(nodePresentationAttributeList n);
    void endVisit(nodePresentationAttributeList n);

    boolean visit(labelPresentation n);
    void endVisit(labelPresentation n);

    boolean visit(iconPresentation n);
    void endVisit(iconPresentation n);

    boolean visit(outlineSpec n);
    void endVisit(outlineSpec n);

    boolean visit(outlineSpecMemberList n);
    void endVisit(outlineSpecMemberList n);

    boolean visit(nodeSpec n);
    void endVisit(nodeSpec n);

    boolean visit(caseExpression n);
    void endVisit(caseExpression n);

    boolean visit(alternativeList n);
    void endVisit(alternativeList n);

    boolean visit(alternative n);
    void endVisit(alternative n);

    boolean visit(simpleName0 n);
    void endVisit(simpleName0 n);

    boolean visit(simpleName1 n);
    void endVisit(simpleName1 n);

    boolean visit(styleKey0 n);
    void endVisit(styleKey0 n);

    boolean visit(styleKey1 n);
    void endVisit(styleKey1 n);

    boolean visit(styleKey2 n);
    void endVisit(styleKey2 n);

    boolean visit(styleKey3 n);
    void endVisit(styleKey3 n);

    boolean visit(resourceValue0 n);
    void endVisit(resourceValue0 n);

    boolean visit(resourceValue1 n);
    void endVisit(resourceValue1 n);

    boolean visit(primitiveType0 n);
    void endVisit(primitiveType0 n);

    boolean visit(primitiveType1 n);
    void endVisit(primitiveType1 n);

    boolean visit(primitiveType2 n);
    void endVisit(primitiveType2 n);

    boolean visit(primitiveType3 n);
    void endVisit(primitiveType3 n);

    boolean visit(primitiveType4 n);
    void endVisit(primitiveType4 n);

    boolean visit(primitiveType5 n);
    void endVisit(primitiveType5 n);

    boolean visit(expression0 n);
    void endVisit(expression0 n);

    boolean visit(expression1 n);
    void endVisit(expression1 n);

    boolean visit(literal0 n);
    void endVisit(literal0 n);

    boolean visit(literal1 n);
    void endVisit(literal1 n);

}


