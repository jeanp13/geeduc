package app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Role;


@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role findByName(String name);
	
}
