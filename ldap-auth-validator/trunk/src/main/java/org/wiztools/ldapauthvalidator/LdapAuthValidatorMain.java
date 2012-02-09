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
        out.println("\t-c /path/to/config.properties [user-cn]");
        out.println();
        out.println("Content of sample config.properties:");
        out.println("\tldap.url = ldap://localhost:1389/");
        out.println("\tbase.dn = dc=wiztools,dc=org");
    }
    
    public static void main(String[] arg) throws IOException {
        OptionParser parser = new OptionParser("hc:");
        try {
            OptionSet options = parser.parse(arg);
            
            if(options.has("h")) { // display help
                printHelp(System.out);
                System.exit(0);
            }
            
            if(!options.has("c")) {
                System.err.println("Mandatory parameter -c missing.");
                printHelp(System.err);
                System.exit(1);
            }
            
            if(options.nonOptionArguments().size() > 1) {
                System.err.println("Multiple user-CN arguments given.");
                printHelp(System.err);
                System.exit(1);
            }
            
            final String configFilePath = options.valueOf("c").toString();
            final File configFile = new File(configFilePath);
            if(!configFile.canRead()) {
                System.err.println("Cannot read config file.");
                printHelp(System.err);
                System.exit(1);
            }
            
            Properties props = new Properties();
            props.load(new FileInputStream(configFile));
            
            final String ldapUrl = props.getProperty("ldap.url");
            final String baseDN = props.getProperty("base.dn");
            
            if(ldapUrl == null || baseDN == null) {
                System.err.println("Required property not available in conf file.");
                printHelp(System.err);
                System.exit(1);
            }
            
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
            request.setBaseDN(baseDN);
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
