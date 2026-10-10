package com.icr.atlas.sv.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import com.ibm.sureview.application.SureviewPOSData;

public class AtlasJSONUtils {

	public AtlasJSONUtils() {
	}
	public static String hashtableToJson(Hashtable<String,String> table)
    {
        StringBuilder sb=new StringBuilder("{");
        boolean first=true;

        for (Map.Entry<String,String> entry:table.entrySet())
        {
            if (!first) sb.append(",");
            first=false;

            sb.append("\"").append(escapeJson(entry.getKey())).append("\":");
            sb.append("\"").append(escapeJson(entry.getValue())).append("\"");
        }

        sb.append("}");
        return sb.toString();
    }
	
	public static String sureviewPOSDataToJson(SureviewPOSData data) {
		StringBuilder sb=new StringBuilder("{");
		boolean firstTime=true;
		
		Enumeration keys=data.keys();
		while (keys.hasMoreElements()) {
			Object key=keys.nextElement();
			Object value=data.get(key.toString());
			if (!firstTime) {
				sb.append(",");
				
			}
			firstTime=false;
			sb.append("\"").append(escapeJson(key.toString())).append("\":");
			sb.append(valueToJson(value));
		}
		sb.append("}");
		return sb.toString();
		
	}

	private static String valueToJson(Object value) {
		if (value==null) {
			return "null";
		}
		
		if (value instanceof Number || value instanceof Boolean) {
			return value.toString();
		}
//		if (value instanceof String) {
//			return "\""+escapeJson(value.toString())+"\""; 
//		}
//		
//		return "\""+escapeJson(value.getClass().getName())+"\""; 
		
		return "\""+escapeJson(value.toString())+"\"";
		
	}
	
    private static String escapeJson(String s)
    {
        if (s==null) return "";

        return s.replace("\\","\\\\")
                .replace("\"","\\\"")
                .replace("\r","\\r")
                .replace("\n","\\n")
                .replace("\t","\\t");
    }


}
