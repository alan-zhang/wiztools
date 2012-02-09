package org.wiztools.ldapauthvalidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import javax.naming.NamingException;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 *
 * @author subwiz
 */
public class LdapAuthValidatorMain {
    
    private static void printHelp(PrintStream out) {
        out.println("Usage: java -jar ldap-password-validator-NN-jar-with-dependencies.jar \\");
        out.println("\t-u <ldap://host:port/base.dn> [user-cn]");
        out.println();
    }
    
    public static void main(String[] arg) throws IOException {
        OptionParser parser = new OptionParser("hu:");
        try {
            OptionSet options = parser.parse(arg);
            
            if(options.has("h")) { // display help
                printHelp(System.out);
                System.exit(0);
            }
            
            if(!options.has("u")) {
                System.err.println("Mandatory parameter -u missing.");
                printHelp(System.err);
                System.exit(1);
            }
            
            if(options.nonOptionArguments().size() > 1) {
                System.err.println("Multiple user-CN arguments given.");
                printHelp(System.err);
                System.exit(1);
            }
            
            final String ldapUrl = options.valueOf("u").toString();
            
            // Collect user:
            final String userCN;
            if(options.nonOptionArguments().isEmpty()) {
                System.out.print("Enter user-CN: ");
                userCN = System.console().readLine();
            }
            else {
                userCN = options.nonOptionArguments().get(0);
            }
            
            // Collect password:
            System.err.print("Enter password: ");
            final char[] password = System.console().readPassword();
            
            // Now, execute:
            RequestBean request = new RequestBean();
            request.setLdapUrl(ldapUrl);
            // request.setBaseDN(baseDN);
            request.setUserCN(userCN);
            request.setPassword(new String(password));
            try {
                LdapAuthValidator.validate(request);
                System.out.println("<== SUCCESS ==>");
            }
            catch(NamingException ex) {
                System.out.println(">== FAILED ==<");
                System.err.println(ex.getMessage());
                System.exit(2);
            }
        }
        catch(OptionException ex) {
            System.err.println(ex.getMessage());
            printHelp(System.err);
            System.exit(1);
        }
    }
}
