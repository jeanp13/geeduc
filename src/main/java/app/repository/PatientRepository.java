package app.repository;

import app.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long> {

	public List<Patient> findAll();

}
