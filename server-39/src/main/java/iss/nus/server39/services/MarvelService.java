package iss.nus.server39.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import iss.nus.server39.utils.Utils;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

import iss.nus.server39.models.Character;
import iss.nus.server39.repositories.RedisRepository;

@Service
public class MarvelService {

    @Autowired
    RedisRepository redisRepo;

    private final String URL = "http://gateway.marvel.com";
    private final String PUBKEY = "f458ccd62e74c46d060ba4af21b89449";
    private final String PVTKEY  = "ee36680336f69a0c5f7bdddfb0498398f0e13a64";


    public String getDetails(String id) {

        // cache
        Optional<String> opt = redisRepo.getDetail(id);

        // cache-hit
        if (opt.isPresent()) {
            return opt.get();
        }

        // cache-miss
        String ts = ""+Instant.now().toEpochMilli();
        String hash = getHash(ts, PVTKEY, PUBKEY);

        String uri = UriComponentsBuilder.fromUriString(URL + "/v1/public/characters/" + id)
                                .queryParam("ts", ts)
                                .queryParam("apikey", PUBKEY)
                                .queryParam("hash", hash)
                                .build()
                                .toUriString();

        RequestEntity<Void> request = RequestEntity
                                        .get(uri)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(request, String.class);

        // System.out.println(response.getBody());

        // JsonObject json = Utils.toJson(response.getBody());
        // JsonArray results = json.getJsonObject("data").getJsonArray("results");
        // List<Character> characters = results.stream()
        //                             .map(x -> (JsonObject) x)
        //                             .map(x -> Utils.toCharacter(x))
        //                             .toList();

        // Character character = characters.get(0);

        
        JsonObject json = Utils.toJson(response.getBody());
        JsonObject result = json.getJsonObject("data")
                                .getJsonArray("results")
                                .getJsonObject(0);

        Character character = Utils.toCharacter(result);

        // cache
        redisRepo.insertDetails(character);

        return Utils.toJson(character).toString();
    }

    public List<Character> getCharacters(String name, Integer limit, Integer offset) {

        String ts = ""+Instant.now().toEpochMilli();
        String hash = getHash(ts, PVTKEY, PUBKEY);

        String uri = UriComponentsBuilder.fromUriString(URL + "/v1/public/characters")
                                .queryParam("ts", ts)
                                .queryParam("apikey", PUBKEY)
                                .queryParam("hash", hash)
                                .queryParam("limit", limit)
                                .queryParam("offset", offset)
                                .queryParam("nameStartsWith", name)
                                .build()
                                .toUriString();

        RequestEntity<Void> request = RequestEntity
                                        .get(uri)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(request, String.class);

        // System.out.println(response.getBody());

        JsonObject json = Utils.toJson(response.getBody());
        JsonArray results = json.getJsonObject("data").getJsonArray("results");
        List<Character> characters = results.stream()
                                    .map(x -> (JsonObject) x)
                                    .map(x -> Utils.toCharacter(x))
                                    .toList();

        // System.out.println(characters);

        // cache
        characters.forEach(x -> redisRepo.insertDetails(x));

        return characters;
    }

    private String getHash(String ts, String pvt, String pub) {

        String md5Hex = DigestUtils.md5DigestAsHex((ts+pvt+pub).getBytes());

        return md5Hex;
    }

}
