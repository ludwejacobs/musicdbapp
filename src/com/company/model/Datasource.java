
package com.company.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//hjsdhjdshjadshjsdajgit add
public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:/Users/Graham/IdeaProjects/MUSIC/music.db" + DB_NAME;


    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_ARTIST = "artists";
    public static final String COLUMN_ARTIST_ID = "id";
    public static final String COLUMN_ARTSIST_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    //    public static final COLUMN_SONGS_ID = "id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Coudn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println("Couldn't close connection" + e.getMessage());
        }
    }

        public List<Artists> queryArtists() {

            try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ARTIST)) {


                List<Artists> artists = new ArrayList<>();
                while(results.next()){
                    Artists artist = new Artists();
                    artist.setId(results.getInt(COLUMN_ARTIST_ID));
                    artist.setName(results.getString(COLUMN_ALBUM_NAME));
                    artists.add(artist);
                }
                return artists;


            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return null;
            }

        }
}
