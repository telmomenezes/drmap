/**
 * 
 */
package com.telmomenezes.drmap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author telmo
 *
 */
public class DRMapCLI {
    private Options options;
    private CommandLine cline;
    
    private void printHelpMessage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("syn", options);
    }
    
    private void printErrorMessage(String msg) {
        System.err.println(msg);
        printHelpMessage();
    }
    
    public void run(String[] args) {
        args = new String[]{"evo", "-inet", "wiki-Vote.snap", "-odir", "test"};
        //args = new String[]{"emddist", "-inet", "wiki-Vote.snap", "-inet2", "bestnet22_gen362.txt"};
        //args = new String[]{"emddist", "-inet", "wiki-Vote.snap", "-inet2", "bestnet21_gen359.txt"};
        //args = new String[]{"gendrmap", "-inet", "bestnet22_gen362.txt", "-mimg", "dummy.png"};
        
        CommandLineParser parser = new GnuParser();
        options = new Options();
        options.addOption("inet", true, "input net file");
        options.addOption("inet2", true, "second input net file");
        options.addOption("mimg", true, "file path to write map image to");
        options.addOption("odir", true, "output directory");
        options.addOption("prg", true, "generator program file");
        options.addOption("oprg", true, "generator output program file");
        
        try {
            cline = parser.parse(options, args);

            String cmd = args[0];
            Command cmdObj = null;
            
            if (cmd.equals("help")) {
                printHelpMessage();
            }
            else if (cmd.equals("gendrmap")) {
                cmdObj = new GenDRMap();
            }
            else if (cmd.equals("emddist")) {
                cmdObj = new EMDDist();
            }
            else {
                printErrorMessage("Command '" + cmd + "' does not exist.");
            }
            
            if (cmdObj != null) {
                if (!cmdObj.run(cline)) {
                    printErrorMessage(cmdObj.getErrorMessage());
                }
            }
        }
        catch (ParseException e) {
           String msg = e.getMessage();
           if (msg == null) {
               msg = "unkown error";
           }
           printErrorMessage(msg);
        }
        
        System.exit(0);
    }
    
    public static void main(String[] args) {
        (new DRMapCLI()).run(args);
    }

}