package org.eclipse.imp.java.editor;

import polyglot.ast.*;

language Java {
    icon packageIcon   = "icons/package.gif";

    icon staticModIcon = "icons/static.gif";
    icon finalModIcon  = "icons/final.gif";

    icon publicClassIcon     = "icons/publicClass.gif";
    icon privateClassIcon    = "icons/privateClass.gif";
    icon protectedClassIcon  = "icons/protectedClass.gif";
    icon packageClassIcon    = "icons/packageClass.gif";

    icon publicInterfaceIcon    = "icons/publicInterface.gif";
    icon privateInterfaceIcon   = "icons/privateInterface.gif";
    icon protectedInterfaceIcon = "icons/protectedInterface.gif";
    icon packageInterfaceIcon   = "icons/packageInterface.gif";

    icon publicFieldIcon     = "icons/publicField.gif";
    icon privateFieldIcon    = "icons/privateField.gif";
    icon protectedFieldIcon  = "icons/protectedField.gif";
    icon packageFieldIcon    = "icons/packageField.gif";

    icon publicMethodIcon    = "icons/publicMethod.gif";
    icon privateMethodIcon   = "icons/privateMethod.gif";
    icon protectedMethodIcon = "icons/protectedMethod.gif";
    icon packageMethodIcon   = "icons/packageMethod.gif";

    icon classIcon(Flags f) {
        case { {: f.isPublic()    :} -> publicClassIcon,
               {: f.isPackage()   :} -> packageClassIcon,
               {: f.isProtected() :} -> protectedClassIcon,
               {: f.isPrivate()   :} -> privateClassIcon }
    }

    icon interfaceIcon(Flags f) {
        case { {: f.isPublic()    :} -> publicInterfaceIcon,
               {: f.isPackage()   :} -> packageInterfaceIcon,
               {: f.isProtected() :} -> protectedInterfaceIcon,
               {: f.isPrivate()   :} -> privateInterfaceIcon }
    }

    icon fieldIcon(Flags f) {
        case { {: f.isPublic()    :} -> publicFieldIcon,
               {: f.isPackage()   :} -> packageFieldIcon,
               {: f.isProtected() :} -> protectedFieldIcon,
               {: f.isPrivate()   :} -> privateFieldIcon }
    }

    icon methodIcon(Flags f) {
        case { {: f.isPublic()    :} -> publicMethodIcon,
               {: f.isPackage()   :} -> packageMethodIcon,
               {: f.isProtected() :} -> protectedMethodIcon,
               {: f.isPrivate()   :} -> privateMethodIcon }
    }

    icon decoratedIcon(Flags f, icon i) {
        i +
        case { {: f.isStatic() :} -> staticIcon } +
        case { {: f.isFinal()  :} -> finalIcon  }
    }

    presentation { // generates ILabelProvider implementation
        Type t = {
            label = t.name();
            icon  = t.isInterface() ? decoratedIcon(t.flags(), interfaceIcon(t)) : decoratedIcon(t.flags(), classIcon(t));
        }
        MethodDecl m = {
            label = m.name() + m.signature();
            icon  = decoratedIcon(m.flags(), methodIcon(m.flags()));
        }
        FieldDecl f = {
            label = f.name();
            icon  = decoratedIcon(f.flags(), fieldIcon(f.flags()));
        }
    }

    text { // generates ITokenColorer
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
        string = {
            color = "red";
            font  = normal;
            style = { bold, italic };
        }
    }

    outline { // generates TreeModelBuilder implementation
        node PackageDecl;
        node Type;
        node MethodDecl;
        node FieldDecl;
    }
}
