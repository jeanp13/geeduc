package app.services;

import app.entity.Room;
import app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements AbstractServiceInterface<Room> {

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void update(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void delete(long id) {
        roomRepository.delete(id);
    }

    @Override
    public List<Room> list() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(long id) {
        return roomRepository.findOne(id);
    }

/*
    public List<Room> findByHospital(long id) {
        return roomRepository.findByHospital(id);
    }

*/

}
