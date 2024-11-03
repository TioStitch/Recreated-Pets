package org.tiostitch.recreated.pets.manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@NoArgsConstructor
public final class SkyBlockPlayer {

    private String name;
    private ArrayList<Pet> stored_pets;
    private int id;

}
