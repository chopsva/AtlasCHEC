package com.icr.atlas.sv.util;

import java.rmi.RemoteException;

import com.ibm.retail.AEF.session.SessionStatusEvent;
import com.ibm.retail.AEF.session.SessionStatusListener;
import com.ibm.sureview.factory.BundleFactory;
import com.ibm.sureview.factory.FactoryTable;
import com.ibm.sureview.sc.application.SCSureviewApplication;
import com.ibm.sureview.screen.ScreenManagerTable;
import com.icr.atlas.SCAM.Terminal.SCAMTerminalServer;

public class AtlasSVSessionStatusListener implements SessionStatusListener {

	public AtlasSVSessionStatusListener() {
		// TODO Auto-generated constructor stub
	}
	
//CALLED by system but not in base-------------------------------------------
	public static BundleFactory initPropertiesBundles() {
	      return null;
	}
	public static void updateScreenManagerTable(ScreenManagerTable screenManagerTable) {
	}
	
	public static void process(Object obj) {
	}
	
    public static void appendConfigurableTree() {
    }
	
    public void postTractionManagerInitialization() {
    }
	public static void startUp() {
		System.out.println("AtlasSVSession startUp called");
	}
	public static void updateFactoryTable(FactoryTable factoryTable) {
		
	}
///-----------------------------------------------------------------------
	
	@Override
	public void sessionEnded(SessionStatusEvent arg0) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionReady(SessionStatusEvent arg0) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("AtlasSVSession sessionReady!!!");
		System.out.println("calling SCAMTerminalServer");
		SCAMTerminalServer sts=SCAMTerminalServer.getInstance(); //this should start the server
		System.out.println("Server:"+sts.haveServer());
		
//		Thread checker=new Thread(()->{
//			while(true) {
//				try {
//					System.out.println("AtlasSV checker sleep now");
//					Thread.sleep(15000);
//					if (SCSureviewApplication.getInstance()!=null) {
//						System.out.println(((SCSureviewApplication)SCSureviewApplication.getInstance()).getSCData().dumpTable());
//					} else {
//						System.out.println("no application?!?!?!?!?!");
//					}
//				} catch (InterruptedException e ) {
//					break;
//				}
//			}
//		});
//		System.out.println("Starting checker!");
//		checker.start();
		
	}

	@Override
	public void sessionStatusChanged(SessionStatusEvent arg0) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
