package app.repository;

import app.entity.Bed;
import app.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface BedRepository extends CrudRepository<Bed, Long> {

	public List<Bed> findAll();

}
