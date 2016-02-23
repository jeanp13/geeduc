package app.repository;

import app.entity.Bed;
import app.entity.Stay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface StayRepository extends CrudRepository<Stay, Long> {

	public List<Stay> findAll();

}
