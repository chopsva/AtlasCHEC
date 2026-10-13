package com.icr.atlas.sv.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;


import com.ibm.retail.AEF.session.SessionStatusEvent;
import com.ibm.retail.AEF.session.SessionStatusListener;
import com.ibm.sureview.application.POSData;
import com.ibm.sureview.factory.BundleFactory;
import com.ibm.sureview.factory.FactoryTable;
import com.ibm.sureview.sc.application.SCData;
import com.ibm.sureview.sc.application.SCSureviewApplication;
import com.ibm.sureview.screen.ScreenManagerTable;
import com.icr.atlas.SCAM.Terminal.SCAMDataListener;
import com.icr.atlas.SCAM.Terminal.SCAMTerminalServer;

public class AtlasSVSessionStatusListener implements SessionStatusListener, PropertyChangeListener {

	private SCData scData=null;
	private POSData posData=null;
	private SCAMDataListener dataListener=null;
	
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
//		System.out.println("try to get AEFSession");
//		AEFSession session=SCSureviewApplication.getInstance().getSession();
//		System.out.println("AEF SESSION:"+session);
//		POSDataProvider dp=null;
//		try  {
//			dp= session.getPOSDataProvider();
//		} catch (Throwable e) {
//			e.printStackTrace();
//			return;
//		}
//		if (dp==null) {
//			System.out.println("No DP");
//			return;
//		}
//			System.out.println("have data provider:"+dp);
//			try {
//				dp.addAEFPropertyChangeListener(this);
//				System.out.println("linked");
//			} catch (Exception e) {
//				System.out.println("listener error:"+e);
//			}
//		System.out.println("HEY!!!!");
		
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
		System.out.println("TRYING LISTENER");
		dataListener=new SCAMDataListener();
		scData=((SCSureviewApplication)SCSureviewApplication.getInstance()).getSCData();
		if (scData!=null) {
			scData.addAllPropertyChangeListener(dataListener);
			System.out.println("SCDATA Connected");

		} else {
			System.out.println("scData was null");
		}
		posData=((SCSureviewApplication)SCSureviewApplication.getInstance()).getPOSData();
		if (posData!=null) {
			posData.addAllPropertyChangeListener(dataListener);
			System.out.println("POSDATA connected");
		} else {
			System.out.println("posData was null");
		}
		
		System.out.println("YO!");
		
	}

	@Override
	public void sessionStatusChanged(SessionStatusEvent arg0) throws RemoteException {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void propertyChanged(AEFPropertyChangeEvent event) throws RemoteException {
//		// TODO Auto-generated method stub
//		System.out.println("Atlas PropChanged:"+event.getCategoryName()+":"+event.getPropertyName()+"="+event.getNewValue());
//		
//		
//	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Atlas PropChanged:"+event.getPropertyName()+"="+event.getNewValue());
//		
		
	}

}
