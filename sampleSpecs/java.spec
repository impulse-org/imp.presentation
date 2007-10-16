package java.uide.views;

import polyglot.ast.*;

presentation Java {
    icon staticIcon    = "icons/static.gif";
    icon finalIcon     = "icons/final.gif";
    icon publicIcon    = "icons/public.gif";
    icon privateIcon   = "icons/private.gif";
    icon protectedIcon = "icons/protected.gif";
    icon packageIcon   = "icons/package.gif";

    set modifierIcons(Declaration decl) {
          {: decl.modifiers().isStatic()    -> staticIcon :} +
          {: decl.modifiers().isFinal()     -> finalIcon  :} +

          {: decl.modifiers().isPublic()    -> publicIcon    :} +
          {: decl.modifiers().isPrivate()   -> privateIcon   :} +
          {: decl.modifiers().isProtected() -> protectedIcon :} +
          {: decl.modifiers().isPackage()   -> packageIcon   :};
    }

    tree JavaDeclPresentation { // generates ILabelProvider and IImageProvider implementations
        node type = {
            label = type.name();
            icon  = modifierIcons(type);
        }
        node method = {
            label = method.name() + method.signature();
            icon  = modifierIcons(method);
        }
        node field = {
            label = field.name();
            icon  = modifierIcons(field);
        }
    }

    outline Outline extends Language.Outline { // no implicit derivation; default is override
        use JavaDeclPresentation;
        node type;
        node method;
        node field;
    }

    text JavaKeywordPresentation { // generates IOutliner implementation
        font normal = "courier";
        token keyword = {
            color = "blue";
            font  = normal;
            style = { bold };
        }
        token comment = {
            color = "green";
            font  = normal;
            style = { italic };
        }
        token string = {
            color = "red";
            font  = normal;
            style = { bold, italic };
        }
    }

    editor JavaEditor extends Editor {
        use JavaKeywordPresentation;
    }
}
