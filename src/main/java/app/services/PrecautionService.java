package app.services;

import app.entity.Precaution;
import app.repository.PrecautionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecautionService implements AbstractServiceInterface<Precaution> {

    @Autowired
    private PrecautionRepository precautionRepository;


    @Override
    public void save(Precaution precaution) {
        precautionRepository.save(precaution);
    }

    @Override
    public void update(Precaution precaution) {
        precautionRepository.save(precaution);
    }

    @Override
    public void delete(long id) {
        precautionRepository.delete(id);
    }

    @Override
    public List<Precaution> list() {
        return precautionRepository.findAll();
    }

    @Override
    public Precaution findById(long id) {
        return precautionRepository.findOne(id);
    }


}
