package org.wiztools.rackspacecloudfiles;

import com.rackspacecloud.client.cloudfiles.FilesClient;
import com.rackspacecloud.client.cloudfiles.FilesContainer;
import com.rackspacecloud.client.cloudfiles.FilesObject;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author subwiz
 */
public class Main {

    private static void listContainers(FilesClient client) throws IOException{
        List<FilesContainer> containers =  client.listContainers();
        for(FilesContainer container: containers){
            System.out.println(container.getName());
        }
    }

    private static void listOrCreateContainer(FilesClient client, String containerName) throws IOException{
        if(client.containerExists(containerName)){
            List<FilesObject> files = client.listObjects(containerName);
            for(FilesObject file: files){
                System.out.println(file.getName());
            }
        }
        else{
            client.createContainer(containerName);
            System.out.println("Successfully created container: " + containerName);
        }
    }

    private static void uploadFileToContainer(FilesClient client,
                String containerName,
                File fileToUpload) throws IOException{
        if(!client.containerExists(containerName)){
            client.createContainer(containerName);
            System.out.println("Successfully created container: " + containerName);
        }
        boolean result = client.storeObject(containerName, fileToUpload, containerName);
        System.out.println("File upload " + fileToUpload + " result: " + result);
    }

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
        client.login();

        switch(args.length){
            case 2:
                listContainers(client);
                break;
            case 3:
                listOrCreateContainer(client, args[2]);
                break;
            case 4:
                uploadFileToContainer(client, args[2] , new File(args[3]));
                break;
        }

    }
}
