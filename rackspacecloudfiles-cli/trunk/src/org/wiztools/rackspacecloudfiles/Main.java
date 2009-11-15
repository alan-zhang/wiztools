package org.wiztools.rackspacecloudfiles;

import com.rackspacecloud.client.cloudfiles.FilesClient;

/**
 *
 * @author subwiz
 */
public class Main {
    public static void main(String[] args) throws Exception{
        if(args.length < 2){
            System.err.println("Command-line arguments:");
            System.err.println("\t1. Username");
            System.err.println("\t2. API key");
            System.err.println("\t3. Container [Optional]: if container exists, displays content, else creates Container");
            System.err.println("\t4. File [Optional]: Uploads the specified file (if file exists in cloud, overwrites it!)");
            System.exit(1);
        }

        final String username = args[0];
        final String apiKey = args[1];

        FilesClient client = new FilesClient(username, apiKey);

        
    }
}
