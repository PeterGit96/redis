package co.develhope.redis.repositories.redis;

import co.develhope.redis.entities.redis.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryRedis extends CrudRepository<UserRedis, Long> {

}
