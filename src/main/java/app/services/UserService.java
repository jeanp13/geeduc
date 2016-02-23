package app.services;

import app.commons.AppMessages;
import app.entity.User;
import app.exception.AppException;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements AbstractServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        validate(user);
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validate(User user){

        // VALIDATION
        if( userRepository.findByUsername(user.getUsername()) != null ){
            throw new AppException(AppMessages.USER_EXISTS.getMessage());
        }
        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new AppException(AppMessages.USER_EXISTS.getMessage());
        }


    }

}
