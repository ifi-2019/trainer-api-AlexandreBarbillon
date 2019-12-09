package com.ifi.trainer_api.controller;

import com.ifi.trainer_api.bo.Pokemon;
import com.ifi.trainer_api.bo.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Test
    void trainerController_shouldBeInstanciated(){
        assertNotNull(controller);
    }

    @Test
    void getTrainers_shouldThrowAnUnauthorized(){
        var responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(responseEntity);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }


    @Test
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());

        assertEquals(25, ash.getTeam().get(0).getPokemonTypeId());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }

    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {
        var trainers = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);
        assertNotNull(trainers);
        assertEquals(2, trainers.length);

        assertEquals("Ash", trainers[0].getName());
        assertEquals("Misty", trainers[1].getName());
    }

    @Test
    void createTrainer_shouldAddBrock(){
        var brock = new Trainer();
        var pokemons = new ArrayList<Pokemon>();
        var geodude = new Pokemon(74,12);
        var onix = new Pokemon(95,14);
        pokemons.add(geodude);
        pokemons.add(onix);
        brock.setName("Brock");
        brock.setTeam(pokemons);
        this.controller.createTrainer(brock);
        var new_brock = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Brock", Trainer.class);
        assertNotNull(new_brock.getName());
        assertNotNull(new_brock.getTeam());
        assertEquals("Brock", new_brock.getName());
        assertEquals(2, new_brock.getTeam().size());

        assertEquals(74, new_brock.getTeam().get(0).getPokemonTypeId());
        assertEquals(12, new_brock.getTeam().get(0).getLevel());
        assertEquals(95, new_brock.getTeam().get(1).getPokemonTypeId());
        assertEquals(14, new_brock.getTeam().get(1).getLevel());
    }
    @Test
    void removeTrainer_ShouldRemoveAsh(){
        this.controller.removeTrainer("Ash");
        var ash = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNull(ash);

    }
}
