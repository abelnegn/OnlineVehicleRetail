package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
	    //Create database table with name cars
        createCar(Connect.connect());

        //Insert cars1 with the following values
        insertCar(Connect.connect(), "Toyota", "Corolla", "2007", "Automatic", "Used", 830000);

        //Insert cars2 with the following values
        insertCar(Connect.connect(), "Nissan", "Kicks", "2019", "Automatic", "New", 2000000);

        //Read all the cars from the database
        readAllCars(Connect.connect());


        //Update the value of car1
        updateCar(Connect.connect(), 1, 820000);


        //Read Updated Car
        readSelectedCar(Connect.connect(), 1);


        //Delete car2 from the cars
        deleteCar(Connect.connect(), 2);

        //Show all cars in the database
        readAllCars(Connect.connect());
    }


    private static void createCar(Connection conn){
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "CREATE TABLE CARS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " MAKE           CHAR(50)    NOT NULL, " +
                    " MODEL          CHAR(50)     NOT NULL, " +
                    " YEAR        CHAR(4), " +
                    " TRANSMISSION         CHAR(50), " +
                    " CONDITION        CHAR(20), " +
                    " PRICE            NUMERIC";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    private static void insertCar(Connection conn, String make, String model, String year, String transmission, String condition, double price){
        Statement stmt = null;
        String operation;
        try {
            stmt = conn.createStatement();
            operation = "INSERT INTO cars (Make, Model, Year, Transmission, Condition, Price) VALUES (" + make + ", " + model + ", " + year + ", '" + transmission + "', " + condition + ", " + price + ");";
            stmt.executeUpdate(operation);
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    private static void readAllCars(Connection conn){
        String sql = "SELECT id, Make, Model, Year, Transmission, Condition, Price FROM cars";

        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("Id") +  "\t" +
                        rs.getString("Make") + "\t" +
                        rs.getString("Model") + "\t" +
                        rs.getString("Year") + "\t" +
                        rs.getString("Transmission") + "\t" +
                        rs.getString("Condition") + "\t" +
                        rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateCar(Connection conn, int id, double price){
        Statement stmt = null;
        String query = "";
        try {
            stmt = conn.createStatement();
            query = "update cars set Price = "+ price +" where Id = "+ id +"";
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    private static void readSelectedCar(Connection conn, int id){
        String sql = "SELECT id, Make, Model, Year, Transmission, Condition, Price FROM cars WHERE Id = "+ id + "";

        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("Id") +  "\t" +
                        rs.getString("Make") + "\t" +
                        rs.getString("Model") + "\t" +
                        rs.getString("Year") + "\t" +
                        rs.getString("Transmission") + "\t" +
                        rs.getString("Condition") + "\t" +
                        rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteCar(Connection conn, int id){
        Statement stmt = null;
        String query = "";
        try {
            stmt = conn.createStatement();
            query = "DELETE FROM cars WHERE Id = "+ id + "";
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
