package org.wiztools.site.s3backup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;

/**
 *
 * @author schandran
 */
public class Main {
    
    private static Options generateOptions(){
        Options options = new Options();
        
        // access key
        Option option = OptionBuilder
                .withLongOpt("accesskey")
                .hasArg()
                .isRequired()
                .create('a');
        options.addOption(option);
        
        // secret key
        option = OptionBuilder
                .withLongOpt("secretkey")
                .hasArg()
                .isRequired()
                .create('s');
        options.addOption(option);
        
        // bucket
        option = OptionBuilder
                .withLongOpt("bucket")
                .hasArg()
                .isRequired()
                .create('b');
        options.addOption(option);
        
        // filename
        option = OptionBuilder
                .withLongOpt("file")
                .hasArg()
                .isRequired()
                .create('f');
        options.addOption(option);
        
        // help
        option = OptionBuilder
                .withLongOpt("help")
                .hasArg(false)
                .isRequired(false)
                .create('h');
        options.addOption(option);
        
        return options;
    }
    
    private static void printCommandLineHelp(Options options){
        HelpFormatter hf = new HelpFormatter();
        String cmdLine = "java -jar s3backup-NN-jar-with-dependencies.jar [options]";
        String descriptor = "AWS S3 backup tool";
        String moreHelp = "http://wiztools.googlecode.com/";
        hf.printHelp(cmdLine, descriptor, options, moreHelp);
    }
    
    public static void main(String[] arg) {
        Options options = generateOptions();
        try{
            CommandLineParser parser = new GnuParser();
            CommandLine cmd = parser.parse(options, arg);
            
            if(cmd.hasOption('h')){
                printCommandLineHelp(options);
                return;
            }
            
            String accessKey = options.getOption("accesskey").getValue();
            String secretKey = options.getOption("secretkey").getValue();
            String bucketName = options.getOption("bucket").getValue();
            String fileName = options.getOption("file").getValue();
            
            AWSCredentials cred = new AWSCredentials(accessKey, secretKey);
            try{
                S3Service service = new RestS3Service(cred);
                S3Bucket bucket = new S3Bucket(bucketName);
                File file = new File(fileName);
                S3Object object = new S3Object(bucket, file);
                service.putObject(bucket, object);
            }
            catch(S3ServiceException ex){
                ex.printStackTrace();
                System.exit(-1);
            }
            catch(NoSuchAlgorithmException ex){
                ex.printStackTrace();
                System.exit(-2);
            }
            catch(FileNotFoundException ex){
                ex.printStackTrace();
                System.exit(-3);
            }
            catch(IOException ex){
                ex.printStackTrace();
                System.exit(-4);
            }
        }
        catch(ParseException ex){
            printCommandLineHelp(options);
            System.exit(-3);
        }
    }
}
