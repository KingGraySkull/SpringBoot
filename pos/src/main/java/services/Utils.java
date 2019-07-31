package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.GenericPackager;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import entities.CustomField;
import entities.IsoField;
import entities.ServiceApi;

@Service
public class Utils {
	private static final Logger LOG = LogManager.getLogger(Utils.class);
	private static final String isoFilePath = "src\\main\\resources\\iso87ascii.xml";
	public static final Map<String , IsoField> isoFieldMap = new HashMap<>();
	public static final Map<String, ServiceApi> serviceApiMap = new HashMap<>();
	public static final Map<String, List<CustomField>> apiMap = new HashMap<>();
	
	public static ISOMsg createIsoMsg() {
		GenericPackager packager = null;
		ISOMsg isomsg = null;
		try {
			packager = new GenericPackager(isoFilePath);
			isomsg = new ISOMsg();
			isomsg.setPackager(packager);
		} 
		catch (ISOException e) {
			LOG.error(e);
		}
		return isomsg;
	}
	
	public static void mapJson(Map<String, String> jsonMap, JSONObject jsonBody) {
		Iterator<String> iterator = jsonBody.keys();
		while(iterator.hasNext()) {
			String k = iterator.next();
			String v = String.valueOf(jsonBody.get(k));
			jsonMap.put(k, v);
		}
	}
	
	public static String parseDateTime(String time, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String response = null;
		try {
			Date date = formatter.parse(time);
			response = formatter.format(date);
		} catch (ParseException e) {
			LOG.error(e);
		}
		return response;
	}
	
	public static String pad(String content, int maxLength, CustomField field) {
		String value = null;
		if(field.getPadDirection() == 'l') {
			value = padLeft(content, maxLength, field.getPadValue());
		}
		else {
			value = padRight(content, maxLength, field.getPadValue());
		}
		return value;
	}
	
	public static String padLeft(String content, int maxLength, char padCharacter) {
		String value = null;
		try {
			value = ISOUtil.padleft(content, maxLength, padCharacter);
		} catch (ISOException e) {
			LOG.error(" error padLeft : "+e);
		}
		return value;
	}
	
	public static String padRight(String content, int maxLength, char padCharacter) {
		String value = null;
		try {
			value = ISOUtil.padright(content, maxLength, padCharacter);
		} catch (ISOException e) {
			LOG.error(" error padRight : "+e);
		}
		return value;
	}
}
