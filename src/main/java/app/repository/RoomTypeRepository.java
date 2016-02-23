package app.repository;

import app.entity.Room;
import app.entity.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {

	public List<RoomType> findAll();

}
