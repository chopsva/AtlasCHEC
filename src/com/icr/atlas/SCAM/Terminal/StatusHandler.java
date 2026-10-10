package com.icr.atlas.SCAM.Terminal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.ibm.sureview.sc.application.SCData;
import com.ibm.sureview.sc.application.SCSureviewApplication;
import com.icr.atlas.sv.util.AtlasJSONUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StatusHandler implements HttpHandler {

	public StatusHandler() {
		//noting special on create
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
				exchange.sendResponseHeaders(405, -1);
			}
			String responseText="UNKNOWN";
			if (SCSureviewApplication.getInstance()!=null) {
				SCData scData=((SCSureviewApplication)SCSureviewApplication.getInstance()).getSCData();
				responseText=AtlasJSONUtils.sureviewPOSDataToJson(scData);
				//responseText=((SCSureviewApplication)SCSureviewApplication.getInstance()).getSCData().dumpTable();
			}
			byte[] response=responseText.getBytes(StandardCharsets.UTF_8);
			exchange.getResponseHeaders().set("Content-Type","application/json; charset=utf-8");
			exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			exchange.sendResponseHeaders(200,response.length);
			OutputStream os=exchange.getResponseBody();
			os.write(response);
			os.close();
		} finally {
			exchange.close();
		}
		
	}
}
