package org.wiztools.site.s3backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
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
        
        // AWS credentials
        Option option = OptionBuilder
                .withLongOpt("aws-creds-file")
                .hasArg()
                .create('k');
        options.addOption(option);
        
        // access key
        option = OptionBuilder
                .withLongOpt("accesskey")
                .hasArg()
                .create('a');
        options.addOption(option);
        
        // secret key
        option = OptionBuilder
                .withLongOpt("secretkey")
                .hasArg()
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
        String moreHelp = "Format of `aws-creds-file': \n"
                + "\tAWSAccessKeyId=XXX\n"
                + "\tAWSSecretKey=XXX";
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

            String awsCredsFile = options.getOption("aws-creds-file").getValue();
            String accessKey = options.getOption("accesskey").getValue();
            String secretKey = options.getOption("secretkey").getValue();
            String bucketName = options.getOption("bucket").getValue();
            String fileName = options.getOption("file").getValue();
            
            if(awsCredsFile == null && (accessKey == null || secretKey == null)) {
                System.err.println("Either -k or (-a and -s) options are mandatory.");
                printCommandLineHelp(options);
                System.exit(1);
            }
            
            if(awsCredsFile != null && (accessKey != null && secretKey != null)) {
                System.err.println("Options -k and (-a and -s) cannot coexist.");
                printCommandLineHelp(options);
                System.exit(2);
            }
            
            if(awsCredsFile != null) {
                Properties p = new Properties();
                try {
                    p.load(new FileInputStream(new File(awsCredsFile)));
                    
                    accessKey = p.getProperty("AWSAccessKeyId");
                    secretKey = p.getProperty("AWSSecretKey");
                }
                catch(IOException ex) {
                    System.err.println("Cannot read AWS property file.");
                    ex.printStackTrace(System.err);
                    System.exit(3);
                }
            }
            
            AWSCredentials cred = new AWSCredentials(accessKey, secretKey);
            try{
                S3Service service = new RestS3Service(cred);
                S3Bucket bucket = new S3Bucket(bucketName);
                File file = new File(fileName);
                S3Object object = new S3Object(bucket, file);
                service.putObject(bucket, object);
            }
            catch(S3ServiceException ex){
                ex.printStackTrace(System.err);
                System.exit(4);
            }
            catch(NoSuchAlgorithmException ex){
                ex.printStackTrace(System.err);
                System.exit(5);
            }
            catch(FileNotFoundException ex){
                ex.printStackTrace(System.err);
                System.exit(6);
            }
            catch(IOException ex){
                ex.printStackTrace(System.err);
                System.exit(7);
            }
        }
        catch(ParseException ex){
            printCommandLineHelp(options);
            System.exit(8);
        }
    }
}
