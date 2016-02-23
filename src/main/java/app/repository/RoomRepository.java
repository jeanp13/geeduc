package app.repository;

import app.entity.Room;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface RoomRepository extends CrudRepository<Room, Long> {
//public interface RoomRepository extends JpaRepository<Room, Long> {

	public List<Room> findAll();

/*	@Query("select r from Room r where r.hospital_id = ?1 ")
	public List<Room> findByHospital(long id);*/

}
