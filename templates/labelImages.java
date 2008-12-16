package $PACKAGE_NAME$;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.imp.services.IOutlineImage;
import $BUNDLE_ACTIVATOR_QUAL_CLASS$;

/**
 * !!!!!!!!!!!!!!!!!!!!!!
 * !!! GENERATED FILE !!!
 * !!! DO NOT EDIT    !!!
 * !!!!!!!!!!!!!!!!!!!!!!
 * 
 * This file provides an implementation of the language-dependent aspects of a
 * label provider for $LANG_NAME$. This implementation was generated from
 * specification file $SPEC_NAME$ at $DATE_TIME$.
 */
public class $LABEL_IMAGES_CLASS_NAME$ implements IOutlineImage {
    private $LABEL_IMAGES_CLASS_NAME$() { }

    private static IOutlineImage image= null;

    public static IOutlineImage getImppImages() {
        if (image == null) {
            image= new $LABEL_IMAGES_CLASS_NAME$();
        }
        return image;
    }

    public static final String IMAGE_ROOT= "icons";

    public static ImageDescriptor OUTLINE_ITEM_DESC= AbstractUIPlugin.imageDescriptorFromPlugin("$PLUGIN_ID$", IMAGE_ROOT
        + "/outline_item.gif");

    public static Image OUTLINE_ITEM_IMAGE= OUTLINE_ITEM_DESC.createImage();

    private static ImageRegistry sImageRegistry= $BUNDLE_ACTIVATOR_CLASS$.getInstance().getImageRegistry();
    
    public static Image DEFAULT_IMAGE = sImageRegistry.get($RESOURCES_CLASS_NAME$.DEFAULT_IMAGE);
    public static Image FILE_IMAGE = sImageRegistry.get($RESOURCES_CLASS_NAME$.FILE);
    public static Image FILE_WITH_WARNING_IMAGE = sImageRegistry.get($RESOURCES_CLASS_NAME$.FILE_WARNING);
    public static Image FILE_WITH_ERROR_IMAGE = sImageRegistry.get($RESOURCES_CLASS_NAME$.FILE_ERROR);

    $EXTRA_IMAGE_DECLS$

    public String getImageRoot() {
        return IMAGE_ROOT;
    }

    public ImageDescriptor getOutlineItemDesc() {
        return OUTLINE_ITEM_DESC;
    }

    public Image getOutlineItemImage() {
        return OUTLINE_ITEM_IMAGE;
    }
}
