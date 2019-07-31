package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import entities.ApiField;
import entities.IsoField;
import entities.ServiceApi;
import entities.TransactionApiDTO;
import repositories.ApiFieldRepository;
import repositories.IsoFieldRepository;
import repositories.ServiceApiRepository;

@RestController
@RequestMapping(value = "produce")
public class ApiProducer {
	@Autowired
	private IsoFieldRepository isoFieldRepo;
	
	@Autowired
	private ApiFieldRepository apiRepo;
	
	@Autowired
	private ServiceApiRepository serviceApiRepo;
	
	@PostMapping("isofield")
	public ResponseEntity<String> addIsoField(@RequestBody String body) {
		Gson gson = new Gson();
		IsoField isofield = gson.fromJson(body, IsoField.class);
		try {
			isoFieldRepo.save(isofield);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Error inserting field",HttpStatus.BAD_REQUEST);
		}
		String json = gson.toJson(isofield, IsoField.class);
		return new ResponseEntity<String>(json,HttpStatus.CREATED);
	}
	
	@PostMapping("serviceApi")
	public ResponseEntity<String> addServiceApi(@RequestBody String body) {
		Gson gson = new Gson();
		ServiceApi serviceApi = gson.fromJson(body, ServiceApi.class);
		try {
			serviceApiRepo.save(serviceApi);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Error inserting field",HttpStatus.BAD_REQUEST);
		}
		String json = gson.toJson(serviceApi, ServiceApi.class);
		return new ResponseEntity<String>(json,HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "serviceApi/{id}")
	public ResponseEntity<String> deleteIsoField(@PathVariable Integer id) {
		String message = "";
		try {
			serviceApiRepo.deleteById(id);
			message = "deleted";
		}
		catch(Exception e) {
			System.out.println(e);
			message = "Error : deleting id "+id;
			new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("transactionApi")
	public ResponseEntity<String> addApiField(@RequestBody String body) {
		Gson gson = new Gson();
		TransactionApiDTO dto = gson.fromJson(body, TransactionApiDTO.class);
		IsoField isoField = isoFieldRepo.findByFieldId(dto.getIsoFieldId());
		if(isoField == null) {
			return new ResponseEntity<String>("Error : Invalid IsoField Id "+dto.getIsoFieldId()+". ",HttpStatus.BAD_REQUEST);
		}
		ServiceApi serviceApi = serviceApiRepo.findByName( dto.getServiceApiName() );
		if(serviceApi == null) {
			return new ResponseEntity<String>("Error : Service Api does not exist "+dto.getServiceApiName()+". ",HttpStatus.BAD_REQUEST);
		}
		ApiField api = new ApiField();
		api.setIsoField( isoField );
		api.setServiceApis(serviceApi);
		api.setPadDirection( dto.getPadDirection());
		api.setPadValue( dto.getPadvalue() );
		api.setRequired( dto.getRequired() );
		api.setDefaultValue( dto.getDefaultValue() );
		
		try {
			apiRepo.save(api);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Error inserting field",HttpStatus.BAD_REQUEST);
		}
		String json = gson.toJson(api, ApiField.class);
		return new ResponseEntity<>(json, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "transactionApi/{id}")
	public ResponseEntity<String> deleteApiField(@PathVariable Integer id) {
		String message = "";
		try {
			apiRepo.deleteById(id);
			message = "deleted";
		}
		catch(Exception e) {
			System.out.println(e);
			message = "Error : deleting id "+id;
			new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("transactionApi")
	public ResponseEntity<String> updateIsoField(@RequestBody String body) {
		Gson gson = new Gson();
		TransactionApiDTO dto = gson.fromJson(body, TransactionApiDTO.class);
		ApiField api = new ApiField();
		IsoField isoField = isoFieldRepo.findByFieldId(dto.getIsoFieldId());
		if(isoField == null) {
			return new ResponseEntity<String>("Error : Invalid IsoField Id "+dto.getIsoFieldId()+". ",HttpStatus.BAD_REQUEST);
		}
		ServiceApi serviceApi = serviceApiRepo.findByName( dto.getServiceApiName() );
		if(serviceApi == null) {
			return new ResponseEntity<String>("Error : Service Api does not exist "+dto.getServiceApiName()+". ",HttpStatus.BAD_REQUEST);
		}
		System.out.println(" Id "+dto.getId());
		api.setId(dto.getId());
		api.setIsoField( isoField );
		api.setServiceApis(serviceApi);
		api.setPadDirection( dto.getPadDirection());
		api.setPadValue( dto.getPadvalue() );
		api.setRequired( dto.getRequired() );
		api.setDefaultValue( dto.getDefaultValue() );
		
		try {
			apiRepo.save(api);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Error inserting field",HttpStatus.BAD_REQUEST);
		}
		String json = gson.toJson(api, ApiField.class);
		return new ResponseEntity<>(json, HttpStatus.CREATED);

	}
}
