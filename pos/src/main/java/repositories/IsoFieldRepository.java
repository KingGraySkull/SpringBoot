package repositories;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entities.IsoField;

@Repository
@Transactional
public interface IsoFieldRepository extends JpaRepository<IsoField, Integer> {
	List<IsoField> findAll();
	IsoField findByFieldId(Integer id);
}
