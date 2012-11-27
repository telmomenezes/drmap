/**
 * 
 */
package com.telmomenezes.drmap;


/**
 * @author telmo
 *
 */
public abstract class NetFile {
    abstract public Net load(String filePath);
    abstract public void save(Net net, String filePath);
    
    public static Net loadNet(String filePath) {
        int dotpos = filePath.lastIndexOf(".");
        String ext = "";
        if (dotpos > 0) {
            ext = filePath.substring(dotpos + 1, filePath.length()); 
        }
        ext = ext.toLowerCase();
            
        Net net = null;
        if (ext.equals("snap")) {
            net = (new SNAPNetFile()).load(filePath);
            return net;
        }
        else if (ext.equals("mat")) {
            net = (new MatrixFile()).load(filePath);
            return net;
        }
        else if (ext.equals("gml")) {
            net = (new GMLNetFile()).load(filePath);
            return net;
        }
        else {
            return null;
        }
    }
    
    public static void saveNet(Net net, String filePath, NetFileType fileType) {
        switch (fileType) {
        case SNAP:
            (new SNAPNetFile()).save(net, filePath);
            break;
        case MAT:
            (new MatrixFile()).save(net, filePath);
            break;
        case GML:
            (new GMLNetFile()).save(net, filePath);
            break;
        default:
            break;
        }
    }
}
