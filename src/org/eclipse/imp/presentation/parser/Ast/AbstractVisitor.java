package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

public abstract class AbstractVisitor implements Visitor
{
    public abstract void unimplementedVisitor(String s);

    public boolean preVisit(IAst element) { return true; }

    public void postVisit(IAst element) {}

    public boolean visit(ASTNodeToken n) { unimplementedVisitor("visit(ASTNodeToken)"); return true; }
    public void endVisit(ASTNodeToken n) { unimplementedVisitor("endVisit(ASTNodeToken)"); }

    public boolean visit(compilationUnit n) { unimplementedVisitor("visit(compilationUnit)"); return true; }
    public void endVisit(compilationUnit n) { unimplementedVisitor("endVisit(compilationUnit)"); }

    public boolean visit(packageSpec n) { unimplementedVisitor("visit(packageSpec)"); return true; }
    public void endVisit(packageSpec n) { unimplementedVisitor("endVisit(packageSpec)"); }

    public boolean visit(importSpecList n) { unimplementedVisitor("visit(importSpecList)"); return true; }
    public void endVisit(importSpecList n) { unimplementedVisitor("endVisit(importSpecList)"); }

    public boolean visit(importSpec n) { unimplementedVisitor("visit(importSpec)"); return true; }
    public void endVisit(importSpec n) { unimplementedVisitor("endVisit(importSpec)"); }

    public boolean visit(simpleNameList n) { unimplementedVisitor("visit(simpleNameList)"); return true; }
    public void endVisit(simpleNameList n) { unimplementedVisitor("endVisit(simpleNameList)"); }

    public boolean visit(languageSpecList n) { unimplementedVisitor("visit(languageSpecList)"); return true; }
    public void endVisit(languageSpecList n) { unimplementedVisitor("endVisit(languageSpecList)"); }

    public boolean visit(languageSpec n) { unimplementedVisitor("visit(languageSpec)"); return true; }
    public void endVisit(languageSpec n) { unimplementedVisitor("endVisit(languageSpec)"); }

    public boolean visit(super_opt n) { unimplementedVisitor("visit(super_opt)"); return true; }
    public void endVisit(super_opt n) { unimplementedVisitor("endVisit(super_opt)"); }

    public boolean visit(languageBody n) { unimplementedVisitor("visit(languageBody)"); return true; }
    public void endVisit(languageBody n) { unimplementedVisitor("endVisit(languageBody)"); }

    public boolean visit(languageMemberList n) { unimplementedVisitor("visit(languageMemberList)"); return true; }
    public void endVisit(languageMemberList n) { unimplementedVisitor("endVisit(languageMemberList)"); }

    public boolean visit(foldingSpec n) { unimplementedVisitor("visit(foldingSpec)"); return true; }
    public void endVisit(foldingSpec n) { unimplementedVisitor("endVisit(foldingSpec)"); }

    public boolean visit(foldableNodeList n) { unimplementedVisitor("visit(foldableNodeList)"); return true; }
    public void endVisit(foldableNodeList n) { unimplementedVisitor("endVisit(foldableNodeList)"); }

    public boolean visit(foldableNode n) { unimplementedVisitor("visit(foldableNode)"); return true; }
    public void endVisit(foldableNode n) { unimplementedVisitor("endVisit(foldableNode)"); }

    public boolean visit(functionDecl n) { unimplementedVisitor("visit(functionDecl)"); return true; }
    public void endVisit(functionDecl n) { unimplementedVisitor("endVisit(functionDecl)"); }

    public boolean visit(formalArgList n) { unimplementedVisitor("visit(formalArgList)"); return true; }
    public void endVisit(formalArgList n) { unimplementedVisitor("endVisit(formalArgList)"); }

    public boolean visit(formalArg n) { unimplementedVisitor("visit(formalArg)"); return true; }
    public void endVisit(formalArg n) { unimplementedVisitor("endVisit(formalArg)"); }

    public boolean visit(typeName n) { unimplementedVisitor("visit(typeName)"); return true; }
    public void endVisit(typeName n) { unimplementedVisitor("endVisit(typeName)"); }

    public boolean visit(functionBody n) { unimplementedVisitor("visit(functionBody)"); return true; }
    public void endVisit(functionBody n) { unimplementedVisitor("endVisit(functionBody)"); }

    public boolean visit(resourceDecl n) { unimplementedVisitor("visit(resourceDecl)"); return true; }
    public void endVisit(resourceDecl n) { unimplementedVisitor("endVisit(resourceDecl)"); }

    public boolean visit(textColoringSpec n) { unimplementedVisitor("visit(textColoringSpec)"); return true; }
    public void endVisit(textColoringSpec n) { unimplementedVisitor("endVisit(textColoringSpec)"); }

    public boolean visit(textColoringMemberList n) { unimplementedVisitor("visit(textColoringMemberList)"); return true; }
    public void endVisit(textColoringMemberList n) { unimplementedVisitor("endVisit(textColoringMemberList)"); }

    public boolean visit(styleSet n) { unimplementedVisitor("visit(styleSet)"); return true; }
    public void endVisit(styleSet n) { unimplementedVisitor("endVisit(styleSet)"); }

    public boolean visit(styleKeyList n) { unimplementedVisitor("visit(styleKeyList)"); return true; }
    public void endVisit(styleKeyList n) { unimplementedVisitor("endVisit(styleKeyList)"); }

    public boolean visit(tokenDecl n) { unimplementedVisitor("visit(tokenDecl)"); return true; }
    public void endVisit(tokenDecl n) { unimplementedVisitor("endVisit(tokenDecl)"); }

    public boolean visit(textAttributeDeclList n) { unimplementedVisitor("visit(textAttributeDeclList)"); return true; }
    public void endVisit(textAttributeDeclList n) { unimplementedVisitor("endVisit(textAttributeDeclList)"); }

    public boolean visit(fontAttributeDecl n) { unimplementedVisitor("visit(fontAttributeDecl)"); return true; }
    public void endVisit(fontAttributeDecl n) { unimplementedVisitor("endVisit(fontAttributeDecl)"); }

    public boolean visit(colorAttributeDecl n) { unimplementedVisitor("visit(colorAttributeDecl)"); return true; }
    public void endVisit(colorAttributeDecl n) { unimplementedVisitor("endVisit(colorAttributeDecl)"); }

    public boolean visit(styleAttributesDecl n) { unimplementedVisitor("visit(styleAttributesDecl)"); return true; }
    public void endVisit(styleAttributesDecl n) { unimplementedVisitor("endVisit(styleAttributesDecl)"); }

    public boolean visit(presentationSpec n) { unimplementedVisitor("visit(presentationSpec)"); return true; }
    public void endVisit(presentationSpec n) { unimplementedVisitor("endVisit(presentationSpec)"); }

    public boolean visit(presentationMemberList n) { unimplementedVisitor("visit(presentationMemberList)"); return true; }
    public void endVisit(presentationMemberList n) { unimplementedVisitor("endVisit(presentationMemberList)"); }

    public boolean visit(nodePresentation n) { unimplementedVisitor("visit(nodePresentation)"); return true; }
    public void endVisit(nodePresentation n) { unimplementedVisitor("endVisit(nodePresentation)"); }

    public boolean visit(nodePresentationAttributeList n) { unimplementedVisitor("visit(nodePresentationAttributeList)"); return true; }
    public void endVisit(nodePresentationAttributeList n) { unimplementedVisitor("endVisit(nodePresentationAttributeList)"); }

    public boolean visit(labelPresentation n) { unimplementedVisitor("visit(labelPresentation)"); return true; }
    public void endVisit(labelPresentation n) { unimplementedVisitor("endVisit(labelPresentation)"); }

    public boolean visit(iconPresentation n) { unimplementedVisitor("visit(iconPresentation)"); return true; }
    public void endVisit(iconPresentation n) { unimplementedVisitor("endVisit(iconPresentation)"); }

    public boolean visit(outlineSpec n) { unimplementedVisitor("visit(outlineSpec)"); return true; }
    public void endVisit(outlineSpec n) { unimplementedVisitor("endVisit(outlineSpec)"); }

    public boolean visit(outlineSpecMemberList n) { unimplementedVisitor("visit(outlineSpecMemberList)"); return true; }
    public void endVisit(outlineSpecMemberList n) { unimplementedVisitor("endVisit(outlineSpecMemberList)"); }

    public boolean visit(nodeSpec n) { unimplementedVisitor("visit(nodeSpec)"); return true; }
    public void endVisit(nodeSpec n) { unimplementedVisitor("endVisit(nodeSpec)"); }

    public boolean visit(caseExpression n) { unimplementedVisitor("visit(caseExpression)"); return true; }
    public void endVisit(caseExpression n) { unimplementedVisitor("endVisit(caseExpression)"); }

    public boolean visit(alternativeList n) { unimplementedVisitor("visit(alternativeList)"); return true; }
    public void endVisit(alternativeList n) { unimplementedVisitor("endVisit(alternativeList)"); }

    public boolean visit(alternative n) { unimplementedVisitor("visit(alternative)"); return true; }
    public void endVisit(alternative n) { unimplementedVisitor("endVisit(alternative)"); }

    public boolean visit(simpleName0 n) { unimplementedVisitor("visit(simpleName0)"); return true; }
    public void endVisit(simpleName0 n) { unimplementedVisitor("endVisit(simpleName0)"); }

    public boolean visit(simpleName1 n) { unimplementedVisitor("visit(simpleName1)"); return true; }
    public void endVisit(simpleName1 n) { unimplementedVisitor("endVisit(simpleName1)"); }

    public boolean visit(styleKey0 n) { unimplementedVisitor("visit(styleKey0)"); return true; }
    public void endVisit(styleKey0 n) { unimplementedVisitor("endVisit(styleKey0)"); }

    public boolean visit(styleKey1 n) { unimplementedVisitor("visit(styleKey1)"); return true; }
    public void endVisit(styleKey1 n) { unimplementedVisitor("endVisit(styleKey1)"); }

    public boolean visit(styleKey2 n) { unimplementedVisitor("visit(styleKey2)"); return true; }
    public void endVisit(styleKey2 n) { unimplementedVisitor("endVisit(styleKey2)"); }

    public boolean visit(styleKey3 n) { unimplementedVisitor("visit(styleKey3)"); return true; }
    public void endVisit(styleKey3 n) { unimplementedVisitor("endVisit(styleKey3)"); }

    public boolean visit(resourceValue0 n) { unimplementedVisitor("visit(resourceValue0)"); return true; }
    public void endVisit(resourceValue0 n) { unimplementedVisitor("endVisit(resourceValue0)"); }

    public boolean visit(resourceValue1 n) { unimplementedVisitor("visit(resourceValue1)"); return true; }
    public void endVisit(resourceValue1 n) { unimplementedVisitor("endVisit(resourceValue1)"); }

    public boolean visit(primitiveType0 n) { unimplementedVisitor("visit(primitiveType0)"); return true; }
    public void endVisit(primitiveType0 n) { unimplementedVisitor("endVisit(primitiveType0)"); }

    public boolean visit(primitiveType1 n) { unimplementedVisitor("visit(primitiveType1)"); return true; }
    public void endVisit(primitiveType1 n) { unimplementedVisitor("endVisit(primitiveType1)"); }

    public boolean visit(primitiveType2 n) { unimplementedVisitor("visit(primitiveType2)"); return true; }
    public void endVisit(primitiveType2 n) { unimplementedVisitor("endVisit(primitiveType2)"); }

    public boolean visit(primitiveType3 n) { unimplementedVisitor("visit(primitiveType3)"); return true; }
    public void endVisit(primitiveType3 n) { unimplementedVisitor("endVisit(primitiveType3)"); }

    public boolean visit(primitiveType4 n) { unimplementedVisitor("visit(primitiveType4)"); return true; }
    public void endVisit(primitiveType4 n) { unimplementedVisitor("endVisit(primitiveType4)"); }

    public boolean visit(primitiveType5 n) { unimplementedVisitor("visit(primitiveType5)"); return true; }
    public void endVisit(primitiveType5 n) { unimplementedVisitor("endVisit(primitiveType5)"); }

    public boolean visit(expression0 n) { unimplementedVisitor("visit(expression0)"); return true; }
    public void endVisit(expression0 n) { unimplementedVisitor("endVisit(expression0)"); }

    public boolean visit(expression1 n) { unimplementedVisitor("visit(expression1)"); return true; }
    public void endVisit(expression1 n) { unimplementedVisitor("endVisit(expression1)"); }

    public boolean visit(literal0 n) { unimplementedVisitor("visit(literal0)"); return true; }
    public void endVisit(literal0 n) { unimplementedVisitor("endVisit(literal0)"); }

    public boolean visit(literal1 n) { unimplementedVisitor("visit(literal1)"); return true; }
    public void endVisit(literal1 n) { unimplementedVisitor("endVisit(literal1)"); }


    public boolean visit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) return visit((ASTNodeToken) n);
        else if (n instanceof compilationUnit) return visit((compilationUnit) n);
        else if (n instanceof packageSpec) return visit((packageSpec) n);
        else if (n instanceof importSpecList) return visit((importSpecList) n);
        else if (n instanceof importSpec) return visit((importSpec) n);
        else if (n instanceof simpleNameList) return visit((simpleNameList) n);
        else if (n instanceof languageSpecList) return visit((languageSpecList) n);
        else if (n instanceof languageSpec) return visit((languageSpec) n);
        else if (n instanceof super_opt) return visit((super_opt) n);
        else if (n instanceof languageBody) return visit((languageBody) n);
        else if (n instanceof languageMemberList) return visit((languageMemberList) n);
        else if (n instanceof foldingSpec) return visit((foldingSpec) n);
        else if (n instanceof foldableNodeList) return visit((foldableNodeList) n);
        else if (n instanceof foldableNode) return visit((foldableNode) n);
        else if (n instanceof functionDecl) return visit((functionDecl) n);
        else if (n instanceof formalArgList) return visit((formalArgList) n);
        else if (n instanceof formalArg) return visit((formalArg) n);
        else if (n instanceof typeName) return visit((typeName) n);
        else if (n instanceof functionBody) return visit((functionBody) n);
        else if (n instanceof resourceDecl) return visit((resourceDecl) n);
        else if (n instanceof textColoringSpec) return visit((textColoringSpec) n);
        else if (n instanceof textColoringMemberList) return visit((textColoringMemberList) n);
        else if (n instanceof styleSet) return visit((styleSet) n);
        else if (n instanceof styleKeyList) return visit((styleKeyList) n);
        else if (n instanceof tokenDecl) return visit((tokenDecl) n);
        else if (n instanceof textAttributeDeclList) return visit((textAttributeDeclList) n);
        else if (n instanceof fontAttributeDecl) return visit((fontAttributeDecl) n);
        else if (n instanceof colorAttributeDecl) return visit((colorAttributeDecl) n);
        else if (n instanceof styleAttributesDecl) return visit((styleAttributesDecl) n);
        else if (n instanceof presentationSpec) return visit((presentationSpec) n);
        else if (n instanceof presentationMemberList) return visit((presentationMemberList) n);
        else if (n instanceof nodePresentation) return visit((nodePresentation) n);
        else if (n instanceof nodePresentationAttributeList) return visit((nodePresentationAttributeList) n);
        else if (n instanceof labelPresentation) return visit((labelPresentation) n);
        else if (n instanceof iconPresentation) return visit((iconPresentation) n);
        else if (n instanceof outlineSpec) return visit((outlineSpec) n);
        else if (n instanceof outlineSpecMemberList) return visit((outlineSpecMemberList) n);
        else if (n instanceof nodeSpec) return visit((nodeSpec) n);
        else if (n instanceof caseExpression) return visit((caseExpression) n);
        else if (n instanceof alternativeList) return visit((alternativeList) n);
        else if (n instanceof alternative) return visit((alternative) n);
        else if (n instanceof simpleName0) return visit((simpleName0) n);
        else if (n instanceof simpleName1) return visit((simpleName1) n);
        else if (n instanceof styleKey0) return visit((styleKey0) n);
        else if (n instanceof styleKey1) return visit((styleKey1) n);
        else if (n instanceof styleKey2) return visit((styleKey2) n);
        else if (n instanceof styleKey3) return visit((styleKey3) n);
        else if (n instanceof resourceValue0) return visit((resourceValue0) n);
        else if (n instanceof resourceValue1) return visit((resourceValue1) n);
        else if (n instanceof primitiveType0) return visit((primitiveType0) n);
        else if (n instanceof primitiveType1) return visit((primitiveType1) n);
        else if (n instanceof primitiveType2) return visit((primitiveType2) n);
        else if (n instanceof primitiveType3) return visit((primitiveType3) n);
        else if (n instanceof primitiveType4) return visit((primitiveType4) n);
        else if (n instanceof primitiveType5) return visit((primitiveType5) n);
        else if (n instanceof expression0) return visit((expression0) n);
        else if (n instanceof expression1) return visit((expression1) n);
        else if (n instanceof literal0) return visit((literal0) n);
        else if (n instanceof literal1) return visit((literal1) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
    public void endVisit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) endVisit((ASTNodeToken) n);
        else if (n instanceof compilationUnit) endVisit((compilationUnit) n);
        else if (n instanceof packageSpec) endVisit((packageSpec) n);
        else if (n instanceof importSpecList) endVisit((importSpecList) n);
        else if (n instanceof importSpec) endVisit((importSpec) n);
        else if (n instanceof simpleNameList) endVisit((simpleNameList) n);
        else if (n instanceof languageSpecList) endVisit((languageSpecList) n);
        else if (n instanceof languageSpec) endVisit((languageSpec) n);
        else if (n instanceof super_opt) endVisit((super_opt) n);
        else if (n instanceof languageBody) endVisit((languageBody) n);
        else if (n instanceof languageMemberList) endVisit((languageMemberList) n);
        else if (n instanceof foldingSpec) endVisit((foldingSpec) n);
        else if (n instanceof foldableNodeList) endVisit((foldableNodeList) n);
        else if (n instanceof foldableNode) endVisit((foldableNode) n);
        else if (n instanceof functionDecl) endVisit((functionDecl) n);
        else if (n instanceof formalArgList) endVisit((formalArgList) n);
        else if (n instanceof formalArg) endVisit((formalArg) n);
        else if (n instanceof typeName) endVisit((typeName) n);
        else if (n instanceof functionBody) endVisit((functionBody) n);
        else if (n instanceof resourceDecl) endVisit((resourceDecl) n);
        else if (n instanceof textColoringSpec) endVisit((textColoringSpec) n);
        else if (n instanceof textColoringMemberList) endVisit((textColoringMemberList) n);
        else if (n instanceof styleSet) endVisit((styleSet) n);
        else if (n instanceof styleKeyList) endVisit((styleKeyList) n);
        else if (n instanceof tokenDecl) endVisit((tokenDecl) n);
        else if (n instanceof textAttributeDeclList) endVisit((textAttributeDeclList) n);
        else if (n instanceof fontAttributeDecl) endVisit((fontAttributeDecl) n);
        else if (n instanceof colorAttributeDecl) endVisit((colorAttributeDecl) n);
        else if (n instanceof styleAttributesDecl) endVisit((styleAttributesDecl) n);
        else if (n instanceof presentationSpec) endVisit((presentationSpec) n);
        else if (n instanceof presentationMemberList) endVisit((presentationMemberList) n);
        else if (n instanceof nodePresentation) endVisit((nodePresentation) n);
        else if (n instanceof nodePresentationAttributeList) endVisit((nodePresentationAttributeList) n);
        else if (n instanceof labelPresentation) endVisit((labelPresentation) n);
        else if (n instanceof iconPresentation) endVisit((iconPresentation) n);
        else if (n instanceof outlineSpec) endVisit((outlineSpec) n);
        else if (n instanceof outlineSpecMemberList) endVisit((outlineSpecMemberList) n);
        else if (n instanceof nodeSpec) endVisit((nodeSpec) n);
        else if (n instanceof caseExpression) endVisit((caseExpression) n);
        else if (n instanceof alternativeList) endVisit((alternativeList) n);
        else if (n instanceof alternative) endVisit((alternative) n);
        else if (n instanceof simpleName0) endVisit((simpleName0) n);
        else if (n instanceof simpleName1) endVisit((simpleName1) n);
        else if (n instanceof styleKey0) endVisit((styleKey0) n);
        else if (n instanceof styleKey1) endVisit((styleKey1) n);
        else if (n instanceof styleKey2) endVisit((styleKey2) n);
        else if (n instanceof styleKey3) endVisit((styleKey3) n);
        else if (n instanceof resourceValue0) endVisit((resourceValue0) n);
        else if (n instanceof resourceValue1) endVisit((resourceValue1) n);
        else if (n instanceof primitiveType0) endVisit((primitiveType0) n);
        else if (n instanceof primitiveType1) endVisit((primitiveType1) n);
        else if (n instanceof primitiveType2) endVisit((primitiveType2) n);
        else if (n instanceof primitiveType3) endVisit((primitiveType3) n);
        else if (n instanceof primitiveType4) endVisit((primitiveType4) n);
        else if (n instanceof primitiveType5) endVisit((primitiveType5) n);
        else if (n instanceof expression0) endVisit((expression0) n);
        else if (n instanceof expression1) endVisit((expression1) n);
        else if (n instanceof literal0) endVisit((literal0) n);
        else if (n instanceof literal1) endVisit((literal1) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
}

