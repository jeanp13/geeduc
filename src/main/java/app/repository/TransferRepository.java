package app.repository;

import app.entity.Bed;
import app.entity.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface TransferRepository extends CrudRepository<Transfer, Long> {

	public List<Transfer> findAll();

}
