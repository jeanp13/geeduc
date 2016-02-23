package app.repository;

import app.entity.Bed;
import app.entity.Precaution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface PrecautionRepository extends CrudRepository<Precaution, Long> {

	public List<Precaution> findAll();

}
