package iss.nus.server39.repositories;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import iss.nus.server39.models.Character;
import iss.nus.server39.utils.Utils;

@Repository
public class RedisRepository {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void insertDetails(Character character) {
        redisTemplate.opsForValue().set(character.getId(), Utils.toJson(character).toString(), 60, TimeUnit.MINUTES);
        return;
    }

    public Optional<String> getDetail(String id) {
        Object res = redisTemplate.opsForValue().get(id);
        return (null == res) ? Optional.empty() : Optional.of((String) res);
    }

}
