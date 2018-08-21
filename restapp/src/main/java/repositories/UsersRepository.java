package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Users;

@Repository
@Transactional
public interface UsersRepository extends CrudRepository<Users, Integer> 
{
	Users findByEmail(String email);
	
	List<Users> findAll();
}
