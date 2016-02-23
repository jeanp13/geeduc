package app.repository;

import app.entity.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

	public List<Hospital> findAll();

}
