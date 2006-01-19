package org.rti.webgenome.client;

import java.rmi.RemoteException;

/**
 * @author Ram Bhattaru
 */

/**
 * This defines Home interface for BioAssayMgr
 */
public interface BioAssayMgrHome {
    BioAssayMgr create() throws RemoteException, javax.ejb.CreateException;
}
