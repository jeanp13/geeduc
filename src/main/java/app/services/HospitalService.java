package app.services;

import app.entity.Hospital;
import app.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService implements AbstractServiceInterface<Hospital> {

    @Autowired
    private HospitalRepository hospitalRepository;


    @Override
    public void save(Hospital obj) {}

    @Override
    public void update(Hospital obj) {}

    @Override
    public void delete(long id) {}

    @Override
    public List<Hospital> list() {
        return  hospitalRepository.findAll();
    }

    @Override
    public Hospital findById(long id) {
        return hospitalRepository.findOne(id);
    }
}
