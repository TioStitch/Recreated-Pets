package org.tiostitch.recreated.pets.database.dataTypes;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.tiostitch.recreated.pets.Main;
import org.tiostitch.recreated.pets.manager.SkyBlockPlayer;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

@RequiredArgsConstructor
public final class PetSQLite
implements IPetData {

    private final Main main;

    @Override
    public void startData() {

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS pet_storage (id TEXT PRIMARY KEY, data TEXT)")) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SkyBlockPlayer getData(String nickname) {

        Connection connection = getConnection();
        if (connection == null) return null;

        try (PreparedStatement st = connection.prepareStatement(
                "SELECT * FROM pet_storage "
                        + "WHERE "
                        + "(id = ?)")) {

            st.setString(1, nickname);

            SkyBlockPlayer skyBlockPlayer = new SkyBlockPlayer();

            try (final ResultSet rs = st.executeQuery()) {
                if (rs.next()) {

                    skyBlockPlayer.setName(nickname);
                    skyBlockPlayer.setStored_pets(new ArrayList<>());

                    return skyBlockPlayer;
                }
            }

            connection.close();
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public void saveData(SkyBlockPlayer skyBlockPlayer) {
        String base64 = "";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "REPLACE INTO pet_storage (id, data) VALUES (?, ?)")) {
            statement.setString(1, skyBlockPlayer.getName());
            statement.setString(2, base64);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteData(String nickname) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM pet_storage WHERE (id = ?)")) {

            statement.setString(1, nickname);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createData(String nickname) {
        if (existData(nickname)) {
            main.getLoaded_players().put(nickname, getData(nickname));
            return;
        }

        Connection connection = getConnection();
        try (PreparedStatement st = connection.prepareStatement(
                "INSERT INTO pet_storage "
                        + "(id, data)"
                        + "VALUES "
                        + "(?, ?)")) {

            st.setString(1, nickname);
            st.setString(2, "");

            st.execute();
            connection.close();
        } catch (SQLException e) {
            return;
        }

        Bukkit.getScheduler().runTaskLaterAsynchronously(main, () -> {
            main.getLoaded_players().put(nickname, getData(nickname));
        }, 2 * 20L);
    }

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(getURL());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existData(final String nickname) {
        String sqlCMD = "SELECT COUNT(*) FROM pet_storage WHERE id = ?";

        Connection connection = getConnection();
        if (connection == null) return false;

        try (PreparedStatement st = connection.prepareStatement(sqlCMD)) {
            st.setString(1, nickname);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

            connection.close();
        } catch(SQLException e) {
            return false;
        }
        return false;
    }

    @Override
    public String getURL() {
        final File dataFolder = main.getDataFolder();

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        final File databaseFile = new File(dataFolder, "pet-storage.db");
        return "jdbc:sqlite:" + databaseFile.getAbsolutePath();
    }
}
