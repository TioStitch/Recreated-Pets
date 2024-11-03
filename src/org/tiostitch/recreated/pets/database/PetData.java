package org.tiostitch.recreated.pets.database;

import lombok.RequiredArgsConstructor;
import org.tiostitch.recreated.pets.Main;
import org.tiostitch.recreated.pets.database.dataTypes.IPetData;
import org.tiostitch.recreated.pets.database.dataTypes.PetMySQL;
import org.tiostitch.recreated.pets.database.dataTypes.PetSQLite;

@RequiredArgsConstructor
public final class PetData {

    //*
    // # Database Types
    //*
    //*-----------------------------------------------------------------------
    //* MySQL is somewhat efficient!
    //*
    //* This is great for being able to have Pets on several servers as soon
    //* as the player starts their connection or to have test servers.
    //*-----------------------------------------------------------------------
    //* SQLite is very fast!
    //*
    //* We use it to register without the need for a MySQL database, and also for solo servers.
    //* To use Bungeecord, it's mandatory to use MySQL, so it makes your job easier.
    //*-----------------------------------------------------------------------
    //*

    private final Main main;

    public IPetData openConnection() {
        if (main.isMySQL()) {
            return new PetMySQL(main);
        }

        return new PetSQLite(main);
    }
}
