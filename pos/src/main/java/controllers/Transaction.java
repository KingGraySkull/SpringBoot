package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import entities.CustomField;
import entities.IsoField;
import entities.ServiceApi;
import services.Utils;

@RestController
@RequestMapping(value = "banking",method = RequestMethod.POST)
public class Transaction {
	private static final Logger LOG = LogManager.getLogger(Transaction.class);
			
	@PostMapping("{api}")
	public ResponseEntity<String> parse(@PathVariable String api, @RequestBody String body) {
		
		System.out.println(" Requesting api : "+api);
		ResponseEntity<String> response = new ResponseEntity<String>("Accepted", HttpStatus.BAD_REQUEST);
		ServiceApi serviceApi = Utils.serviceApiMap.get(api);
		List<String> requiredFields = new ArrayList<>();
		byte[] data = null;
		
		if(serviceApi == null) {
			return new ResponseEntity<String>("Bad Gateway",HttpStatus.BAD_GATEWAY);
		}
		else {
			final Map<String, String> jsonMap = new HashMap<String, String>();
			JSONObject jsonBody = null;		
			try {
				jsonBody = new JSONObject(body);
			} catch(JSONException e) {
				LOG.error("Invalid JSON : "+body+" \n "+e);
				response = new ResponseEntity<String>("invalid json ",HttpStatus.BAD_REQUEST);
			}
			//map json key value pair
			Utils.mapJson(jsonMap,jsonBody);
			ISOMsg msg = Utils.createIsoMsg();
			
			//retrieve all fields for api
			List<CustomField> listOfFields = Utils.apiMap.get(api);		
			if(listOfFields != null) {
				int size = listOfFields.size();
				for(int i = 0; i < size; i++) {
					CustomField field = listOfFields.get(i);
					IsoField isoField = Utils.isoFieldMap.get(field.getFieldName());  
					String key = field.getFieldName();
					System.out.println(" Custom field name : "+key);
					
					//check for mandatory fields present in the message
					if(field.isRequired()) {
						if(!jsonMap.containsKey(key)) {
							requiredFields.add(key);
							System.out.println(" field "+key+ " not present");
						}
						else {							
							String content = jsonMap.get(key);
							content = content == null ? "" : content;
							String value = Utils.pad(content, isoField.getLength(), field);
							msg.set(isoField.getFieldId(), value);
						}
					}
					else {
						if(field.isDefaultValue()) {
							String content = jsonMap.get(key);
							if(content == null || content.isEmpty()) {
								return new ResponseEntity<String>(" Error : Field "+key+" value empty ",HttpStatus.BAD_REQUEST);
							}
							else {
								String value = Utils.pad(content, isoField.getLength(), field);
								msg.set(isoField.getFieldId(), value);
							}

						}
						else {
							String value =  jsonMap.get(key);
							value = (value == null || value == "") ? "" : value;
							value = Utils.pad(value, isoField.getLength(), field);
							msg.set(isoField.getFieldId(), value);
						}
					}//end else
				}//end for loop
			}//end if	
			if(requiredFields.size() > 0) {
				return new ResponseEntity<String>("Required Fields : "+requiredFields+" not present ", HttpStatus.BAD_REQUEST);
			}
			else {
				try {
					data = msg.pack();
					response = new ResponseEntity<String>(new String(data), HttpStatus.ACCEPTED);
				} 
				catch (ISOException e) {
					LOG.error(e);
					response = new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
				}
			}
		}	
		return response;
	}
}
