package x10.uide.views;

language X10 extends Java {
    icon nullableIcon = "icons/nullable.gif";
    icon valueIcon    = "icons/value.gif";

    set modifierIcons(Declaration decl) {
        return super.modifierIcons(decl) +
            {: decl.modifiers().isNullable() => nullableIcon :} +
            {: decl.modifiers().isValue()    => valueIcon :}
    }

    Outline extends Java.Outline {
        node async; // N.B.: presentation is inherited declPresentation, using override of modifierIcons()
    }
}
