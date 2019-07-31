package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ApiFieldRepository;
import repositories.IsoFieldRepository;
import repositories.ServiceApiRepository;

@Service
public class DbService {

	@Autowired
	private ApiFieldRepository apiRepo;
	
	@Autowired
	private IsoFieldRepository isoRepo;
	
	@Autowired
	private ServiceApiRepository serviceRepo;
	
}
