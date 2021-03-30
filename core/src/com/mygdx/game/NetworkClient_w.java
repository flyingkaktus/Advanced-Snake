package com.mygdx.game;

/**
 * Networking
 * @author Maciej
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NetworkClient_w {

    public Score score0;
    public Score dbglobalscore;
    private Connection conn = null;

    private static final long serialVersionUID = 1L;

    public NetworkClient_w(Score score0) throws SQLException {
        this.score0 = score0;
        dbglobalscore = new Score();

        //conn = DriverManager.getConnection("jdbc:mariadb://alphalambdaunited.eu.mysql:3306/alphalambdaunited_eu_non_commercial", "alphalambdaunited_eu_non_commercial", "qwert1234qwert");	// Parameters for Connection to Database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advanced_snake_db", "root", ""); // Alternative connection in case the online database does not work.
    }


    public void TransferScore() {
        try {
        Statement stmt01 = conn.createStatement();
        stmt01.executeUpdate("INSERT INTO `highscores` (`id`, `highscore`)" + "VALUES (default, '"+ score0.getScore_highest() +"')");	// Value transfer from Object to DB table
        } catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
        }
    }

    public void getDB() {
            try {
            String query = "SELECT * FROM `highscores`";
            Statement stmt02 = conn.createStatement();
            ResultSet rs = stmt02.executeQuery(query);
            while (rs.next()) {
                    int db_id = rs.getInt("id");
                    int db_highscore = rs.getInt("highscore");
                    dbglobalscore.listA.add(db_highscore);
                    dbglobalscore.sortthatlist();
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception:" + e.getMessage());
            }
        }
}