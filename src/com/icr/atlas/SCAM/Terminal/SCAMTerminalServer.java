package com.icr.atlas.SCAM.Terminal;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SCAMTerminalServer {

	private static SCAMTerminalServer instance=null;
	private int watchPort=6260;
	private HttpServer theServer=null;
	
	public static SCAMTerminalServer getInstance() {
		if (instance==null) {
			instance=new SCAMTerminalServer();
		}
		return instance;
	}

	private SCAMTerminalServer() {
		if (theServer==null) {
			initServer();
		}
	}
	
	public boolean haveServer() {
		return this.theServer!=null;
	}
	
	private void initServer() {
		try {
			this.theServer=HttpServer.create(new InetSocketAddress(this.watchPort),0);
		} catch (IOException e) {
			System.out.println("Can not create server:"+e);
			e.printStackTrace();
			return;
		}
		if (this.theServer==null) {
			System.out.println("theServer is still NULL????");
			return;
		}
		
		this.theServer.createContext("/status",new StatusHandler());
		this.theServer.createContext("/posstatus",new POSStatusHandler());
		this.theServer.setExecutor(null);
		this.theServer.start();
		System.out.println("SCAM Terminal HTTP Server started");
	}
	
	

}
