package base;

import database.ConnectToSqlDB;
import org.testng.annotations.DataProvider;


import java.util.ArrayList;

public class DataProviders extends ConnectToSqlDB {

    // Test Case 1
    @DataProvider(name = "Twitter usernames")
    public static Object[][] readSQLUsernames() throws Exception {
        ArrayList<String> lst = (ArrayList<String>) readDataBase("twitter", "usernames");
        return lst.stream()
                .map(student -> new Object[]{student})
                .toArray(Object[][]::new);
    }

    // Test Case 2
    @DataProvider(name = "Twitter ids")
    public static Object[][] readSQLTwitterIds() throws Exception {
        ArrayList<String> lst = (ArrayList<String>) readDataBase("twitter", "ID");
        return lst.stream()
                .map(student -> new Object[]{student})
                .toArray(Object[][]::new);
    }

    // Test Case 4
    @DataProvider(name = "Tweet ids")
    public static Object[][] readSQLTweetIds() throws Exception {
        ArrayList<String> lst = (ArrayList<String>) readDataBase("twitter", "tweetIds");
        return lst.stream()
                .map(student -> new Object[]{student})
                .toArray(Object[][]::new);
    }


    // Test Case 6
    @DataProvider(name = "Twitter places")
    public static Object[][] readSQLPlaces() throws Exception {
        ArrayList<String> lst = (ArrayList<String>) readDataBase("twitter", "places");
        return lst.stream()
                .map(student -> new Object[]{student})
                .toArray(Object[][]::new);

    }
}