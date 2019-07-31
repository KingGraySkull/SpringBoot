package repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entities.ServiceApi;

@Repository
@Transactional
public interface ServiceApiRepository extends JpaRepository<ServiceApi, Integer> {

	ServiceApi findByName(String name);
}
