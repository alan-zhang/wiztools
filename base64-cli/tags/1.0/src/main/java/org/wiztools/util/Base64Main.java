package org.wiztools.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;

/**
 * @author subhash
 *
 */
public class Base64Main {
    private static void printHelp(){
        System.out.println("Supported commandline arguments:");
        System.out.println("\t-n\tNo wrap.");
        System.out.println("\t-d\tDecode.");
        System.out.println("\t-h\tPrint this help.");
    }

    private static void printError(List<String> errors){
        for(String error: errors){
            System.err.println(error);
        }
    }

    public static void main( String[] args ) throws IOException {
        // Options
        boolean isOperationDecode = false;
        boolean isNoWrap = false;

        // Command line parsing:
        if(args.length != 0){ // has command-line args
            List<String> errors = new ArrayList<String>();
            for(int i=0; i<args.length; i++){
                if(args[i].equals("-d")){
                    isOperationDecode = true;
                }
                else if(args[i].equals("-n")){
                    isNoWrap = true;
                }
                else if(args[i].equals("-h")){
                    printHelp();
                    System.exit(0);
                }
                else{
                    errors.add("Unknown option: " + args[i]);
                }
            }
            if(errors.size() > 0){
                printError(errors);
                System.exit(1);
            }
        }

        // Encoding/Decoding:
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024*4];
        int len = -1;
        while((len=System.in.read(buf)) != -1){
            baos.write(buf, 0, len);
        }
        
        byte[] out = null;
        if(isOperationDecode){
            out = Base64.decodeBase64(baos.toByteArray());
        }
        else{
            out = Base64.encodeBase64(baos.toByteArray(), !isNoWrap);
        }
        System.out.write(out);
    }
}
