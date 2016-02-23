package app.repository;

import app.entity.Bed;
import app.entity.HealthInsurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface HealthInsuranceRepository extends CrudRepository<HealthInsurance, Long> {

	public List<HealthInsurance> findAll();

}
