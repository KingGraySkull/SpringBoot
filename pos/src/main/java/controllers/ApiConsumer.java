package controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value = "consume")
public class ApiConsumer {
	
	@Autowired
	private IsoFieldRepository isoFieldRepo;
	
	@Autowired
	private ApiFieldRepository apiRepo;
	
	@Autowired
	private ServiceApiRepository serviceApiRepo;
	
	@RequestMapping(value = "isofield/{id}", method = RequestMethod.GET)
	public String getIsoField(@PathVariable Integer id) {
		Gson gson = new Gson();
		IsoField field = isoFieldRepo.findByFieldId(id);
		String json = gson.toJson(field);
		return json;
	}
	
	@RequestMapping(value = "isofields", method = RequestMethod.GET)
	public String getIsoFields() {
		Gson gson = new Gson();
		List<IsoField> isoFields = isoFieldRepo.findAll();
		String json = gson.toJson(isoFields);
		return json;
	}
	
	@RequestMapping(value = "api/{id}", method = RequestMethod.GET)
	public String getServiceApi(@PathVariable Integer id) {
		Gson gson = new Gson();
		ServiceApi serviceApi = serviceApiRepo.findById(id).get();
		String json = gson.toJson(serviceApi);
		return json;
	}
	
	@RequestMapping(value = "apis", method = RequestMethod.GET)
	public String getServiceApi() {
		Gson gson = new Gson();
		List<ServiceApi> serviceApi = serviceApiRepo.findAll();
		String json = gson.toJson(serviceApi);
		return json;
	}
	
	@GetMapping(value = "transactionApi/{id}")
	@Transactional
	public String getTransactionApi(@PathVariable Integer id) {
		Gson gson = new Gson();
		TransactionApiDTO apiDto = new TransactionApiDTO();
		Optional<ApiField> apiFieldO = apiRepo.findById(id);
		if(apiFieldO.isPresent()) {
			ApiField apiField = apiFieldO.get();
			apiDto.setId(apiField.getId());
			apiDto.setServiceApiId(apiField.getServiceApis().getId());
			apiDto.setServiceApiName(apiField.getServiceApis().getName());
			apiDto.setIsoFieldId(apiField.getIsoField().getFieldId());
			apiDto.setIsoFieldKey(apiField.getIsoField().getKey());
			apiDto.setIsoFieldLength(apiField.getIsoField().getLength());
			apiDto.setIsoFieldName(apiField.getIsoField().getName());
			apiDto.setIsoFieldType(apiField.getIsoField().getType());
			apiDto.setPadvalue( apiField.getPadValue() );
			apiDto.setPadDirection(apiField.getPadDirection());
			apiDto.setRequired(apiField.getRequired());
			apiDto.setDefaultValue( apiField.getDefaultValue() );
		}
		String json = gson.toJson(apiDto);
		return json;
	}
	
	@GetMapping(value = "transactionApis")
	@Transactional
	public String getTransactionApi() {
		Gson gson = new Gson();
		List<ApiField> apiFields = apiRepo.findAll();
		List<TransactionApiDTO> apiDtos = new LinkedList<>();
		for(ApiField apiField : apiFields) {
			TransactionApiDTO apiDto = new TransactionApiDTO();
			apiDto.setId(apiField.getId());
			apiDto.setServiceApiId(apiField.getServiceApis().getId());
			apiDto.setServiceApiName(apiField.getServiceApis().getName());
			apiDto.setIsoFieldId(apiField.getIsoField().getFieldId());
			apiDto.setIsoFieldKey(apiField.getIsoField().getKey());
			apiDto.setIsoFieldLength(apiField.getIsoField().getLength());
			apiDto.setIsoFieldName(apiField.getIsoField().getName());
			apiDto.setIsoFieldType(apiField.getIsoField().getType());
			apiDtos.add(apiDto);
		}
		String json = gson.toJson(apiDtos);
		return json;
	}
}
