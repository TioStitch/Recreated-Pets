package org.tiostitch.recreated.pets.database.dataTypes;

import org.bukkit.Bukkit;
import org.tiostitch.recreated.pets.manager.SkyBlockPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IPetData {
    String getURL();

    void startData();

    SkyBlockPlayer getData(String nickname);
    void saveData(SkyBlockPlayer skyBlockPlayer);
    void deleteData(String nickname);

    void createData(String nickname);

    boolean existData(String nickname);
    Connection getConnection();
}
