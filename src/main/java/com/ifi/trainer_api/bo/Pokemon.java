package com.ifi.trainer_api.bo;

import javax.persistence.Embeddable;

@Embeddable
public class Pokemon {

    private int pokemonTypeId;

    private int level;

    public Pokemon() {
    }

    public Pokemon(int pokemonType, int level) {
        this.pokemonTypeId = pokemonType;
        this.level = level;
    }

    public int getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(int pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
