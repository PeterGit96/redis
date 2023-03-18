package co.develhope.redis.services;

import co.develhope.redis.entities.jpa.UserJPA;
import co.develhope.redis.entities.redis.UserRedis;
import co.develhope.redis.repositories.jpa.UserRepositoryJPA;
import co.develhope.redis.repositories.redis.UserRepositoryRedis;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepositoryJPA userRepositoryJPA;

    @Autowired
    private UserRepositoryRedis userRepositoryRedis;

    public UserRedis convertToRedisEntity(@NotNull UserJPA user){
        UserRedis userRedis = new UserRedis();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public UserJPA convertToJPAEntity(@NotNull UserRedis user){
        UserJPA userRedis = new UserJPA();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public UserJPA createUser(@NotNull UserJPA user) {
        user.setId(null);
        return userRepositoryJPA.saveAndFlush(user);
    }

    public List<UserJPA> readAllUsers() {
        return userRepositoryJPA.findAll();
    }

    public UserJPA readUserById(Long id) {
        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if(userRedis.isPresent()) {
            return convertToJPAEntity(userRedis.get());
        } else {
            Optional<UserJPA> userJPA = userRepositoryJPA.findById(id);
            if(userJPA.isPresent()) {
                userRepositoryRedis.save(convertToRedisEntity(userJPA.get()));
                return userJPA.get();
            }
        }
        throw new EntityNotFoundException("User not found");
    }

    public UserJPA updateUserById(Long id, @NotNull UserJPA user) {
        UserJPA userJPA = userRepositoryJPA.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if(user.getFirstName() != null) {
            userJPA.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            userJPA.setLastName(user.getLastName());
        }
        if(user.getEmail() != null) {
            userJPA.setEmail(user.getEmail());
        }
        if(user.getProfilePicture() != null) {
            userJPA.setProfilePicture(user.getProfilePicture());
        }
        if(user.getPasswordEncrypted() != null) {
            userJPA.setPasswordEncrypted(user.getPasswordEncrypted());
        }
        if(user.getDomicileAddress() != null) {
            userJPA.setDomicileAddress(user.getDomicileAddress());
        }
        if(user.getDomicileNumber() != null) {
            userJPA.setDomicileNumber(user.getDomicileNumber());
        }
        if(user.getDomicileCity() != null) {
            userJPA.setDomicileCity(user.getDomicileCity());
        }
        if(user.getDomicileState() != null) {
            userJPA.setDomicileState(user.getDomicileState());
        }
        if(user.getFiscalCode() != null) {
            userJPA.setFiscalCode(user.getFiscalCode());
        }

        userRepositoryJPA.saveAndFlush(userJPA);

        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if(userRedis.isPresent()) {
            userRepositoryRedis.save(convertToRedisEntity(userJPA));
        }

        return userJPA;

    }

    public void deleteUserById(Long id) {
        if(!userRepositoryJPA.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepositoryJPA.deleteById(id);
        userRepositoryRedis.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepositoryJPA.deleteAll();
        userRepositoryRedis.deleteAll();
    }

}
