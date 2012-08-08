package org.wiztools.iconsetcreator;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author subwiz
 */
public class IconSetCreatorMain {
    public static void main(String[] arg) throws IOException {
        if(arg.length != 2) {
            System.err.println("Program expects TWO parameters:");
            System.err.println("\t1. Image file");
            System.err.println("\t2. Out directory");
            System.exit(1);
        }
        
        File imgFile = new File(arg[0]);
        File outParentDir = new File(arg[1]);
        IconSetCreatorUtil.create(imgFile, outParentDir);
    }
}
