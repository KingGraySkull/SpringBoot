package repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entities.ApiField;
import entities.IsoField;

@Repository
@Transactional
public interface ApiFieldRepository extends JpaRepository<ApiField, Integer> {
	
	List<ApiField> findByServiceApiId(Integer id);
	
	List<ApiField> findByIsoField(IsoField isoField);
}
