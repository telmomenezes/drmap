package com.telmomenezes.drmap;

import org.apache.commons.cli.CommandLine;


/**
 * @author telmo
 *
 */
public class EMDDist extends Command {

    @Override
    public boolean run(CommandLine cline) {
        if(!cline.hasOption("inet")) {
            setErrorMessage("input network file must be specified");
            return false;
        }
        
        if(!cline.hasOption("inet2")) {
            setErrorMessage("second input network file must be specified");
            return false;
        }
        
        String netfile1 = cline.getOptionValue("inet");
        String netfile2 = cline.getOptionValue("inet2");
        
        Net net1 = Net.load(netfile1);
        Net net2 = Net.load(netfile2);
  
        net1.computePageranks();
        net2.computePageranks();
        
        DRMap drmap1 = net1.getDRMapWithLimit(10, -7, 7, -7, 7);
        drmap1.logScale();
        drmap1.normalizeMax();
        
        DRMap drmap2 = net2.getDRMapWithLimit(10, -7, 7, -7, 7);
        drmap2.logScale();
        drmap2.normalizeMax();
        
        System.out.println("total 1: " + drmap1.total());
        System.out.println("total 2: " + drmap2.total());
        System.out.println("emd distance: " + drmap1.emdDistance(drmap2));
        
        return true;
    }

}
