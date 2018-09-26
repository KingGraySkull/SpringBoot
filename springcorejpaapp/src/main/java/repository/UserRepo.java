package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pojos.User;

@Repository
@Transactional
public interface UserRepo extends CrudRepository<User,Integer>{

}
