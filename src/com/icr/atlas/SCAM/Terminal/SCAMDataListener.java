package com.icr.atlas.SCAM.Terminal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class SCAMDataListener implements PropertyChangeListener {
	
	private final Map<String, Consumer<PropertyChangeEvent>> handlers=new HashMap();
	private Map<String,Object> values=new HashMap();

	private final ExecutorService executor=Executors.newSingleThreadExecutor(r -> {
		Thread t=new Thread(r,"AtlasSCAM-EventHandler");
		t.setDaemon(true);
		return t;
	});
	
	public SCAMDataListener() {
		handlers.put("SC_STATE",this::processSC_State);
		handlers.put("SC_SHOPPER_PRESENT", this::processSHOPPER_PRESENT);
		handlers.put("SC_CASHIER_REQUIRED", this::processCASHIER_REQUIRED);
	}
	
    public void shutdown() {
        executor.shutdown();
    }
    
    private void printLog(String data) {
    	String dt=new java.text.SimpleDateFormat("MM/dd/yy HH:mm:ss.SSS").format(new java.util.Date());
    	System.out.println("["+dt+"] "+data);
    }

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		printLog("PropertyChange name:"+event.getPropertyName()+" value:"+event.getNewValue());
		Consumer<PropertyChangeEvent> handler=handlers.get(event.getPropertyName().toUpperCase(java.util.Locale.ROOT));
		if (handler!=null) {
			executor.execute(() -> {
				try {
					handler.accept(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
	}
	
	private void processSC_State(PropertyChangeEvent event) {
		values.put("SC_STATE",event.getNewValue());
		printLog("SC_STATE:"+event.getNewValue());
	}
	private void processSHOPPER_PRESENT(PropertyChangeEvent event) {
		values.put("scamCUSTOMER", Boolean.parseBoolean((String) event.getNewValue()));
	}
	private void processCASHIER_REQUIRED(PropertyChangeEvent event) {
		values.put("scamCASHIER", Boolean.parseBoolean((String)event.getNewValue()));
	}
	
	
	

}
