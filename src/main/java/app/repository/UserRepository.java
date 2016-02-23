package app.repository;

import app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findAll();

	public User findByUserId(int user_id);
	
	public User findByEmail(String email);
	
	public User findByUsername(String username);

}
