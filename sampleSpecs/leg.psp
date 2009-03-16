package org.eclipse.imp.java.editor;

import org.eclipse.imp.leg.Ast.*;

language LEG {
    presentation {
        icon funcIcon  = "icons/function.gif";
        icon blockIcon = "icons/block.gif";
        icon declIcon  = "icons/decl.gif";

        functionDeclaration f = {
            label = {: f.getfunctionHeader().getidentifier().toString() :};
            icon  = funcIcon;
        }
        block b = {
            label = "Block";
            icon  = blockIcon;
        }
        declarationStmt d = {
            label = {: d.getprimitiveType() + " " + d.getidentifier().toString() :};
            icon  = declIcon;
        }
    }

    text {
        font normal = "courier";
        keyword = {
            color = "blue";
            font  = normal;
            style = { bold };
        }
        comment = {
            color = "green";
            font  = normal;
            style = { italic };
        }
    }

    outline {
        node functionDeclaration;
        node block;
        node declarationStmt;
    }

    foldable {
        node functionDeclaration;
    }
}
