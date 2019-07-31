package services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import entities.ApiField;
import entities.CustomField;
import entities.IsoField;
import entities.ServiceApi;
import repositories.ApiFieldRepository;
import repositories.IsoFieldRepository;
import repositories.ServiceApiRepository;

@Service
public class InitService {
	@Autowired
	private IsoFieldRepository isoRepo;
	
	@Autowired
	protected PlatformTransactionManager txManager;
	
	@Autowired
	private ServiceApiRepository serviceApiRepo;
	
	@Autowired
	private ApiFieldRepository apiRepo;
	
	@PostConstruct
	public void loadMapping() {
		
		TransactionTemplate tx = new TransactionTemplate(txManager);
		tx.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				//load iso field
				List<IsoField> isoFields = isoRepo.findAll();
				for(IsoField isoField : isoFields) {
					Utils.isoFieldMap.put( isoField.getKey(), isoField);
				}
				
				//load service apis and custom fields
				List<ServiceApi> serviceApis = serviceApiRepo.findAll();
				for(ServiceApi api : serviceApis) {
					Utils.serviceApiMap.put(api.getName(), api);
					List<ApiField> apifields = apiRepo.findByServiceApiId(api.getId());
					List<CustomField> customFields = new ArrayList<>();
					
					if(apifields.size() > 0) {
						for(ApiField apiField : apifields) {
							CustomField cf = new CustomField();
							cf.setFieldName( apiField.getIsoField().getKey() );
							cf.setRequired( apiField.getRequired() );
							cf.setDefaultValue( apiField.getDefaultValue() );
							cf.setPadDirection( apiField.getPadDirection() );
							cf.setPadValue( apiField.getPadValue() );
							customFields.add(cf);
						}
						Utils.apiMap.put(api.getName(), customFields);
						customFields = null;
					}
				}
			}
		});
	}
}
