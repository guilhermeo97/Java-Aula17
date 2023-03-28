import java.sql.*;
import java.util.Scanner;

import org.postgresql.core.ResultCursor;

public class T3Database {
    private Scanner myScanner = new Scanner(System.in);
    private Connection myConnection;
    private PreparedStatement insertStatement;
    private PreparedStatement selectStatement;
    private PreparedStatement deleteStatement;

    public T3Database() throws SQLException {
        // URL do BD "meu_banco"
        String url = "jdbc:postgresql://localhost:5432/meu_db";

        // pede o usuario administrador do banco
        System.out.print("Enter admin username: ");
        String adminUsername = myScanner.nextLine();

        // pede a senha do administrador do banco
        System.out.print("Enter admin password: ");
        String password = myScanner.nextLine();

        // cria a conexão
        myConnection = DriverManager.getConnection(url, adminUsername, password);
        // prepara o statement insertStatement
        insertStatement = myConnection.prepareStatement("INSERT INTO clients VALUES (?, ?, ?, ?, ?)");
        selectStatement = myConnection.prepareStatement("SELECT * FROM clients");
        deleteStatement = myConnection.prepareStatement("DELETE FROM clients WHERE username = ?");
    }

    public void insertOperation() {
        System.out.println("Enter client info");

        try {
            // insere o usuario no index 1 do statement
            System.out.print("Enter client username: ");
            insertStatement.setString(1, myScanner.nextLine());
            // insere o nome completo no index 2 do statement
            System.out.print("Enter client full name: ");
            insertStatement.setString(2, myScanner.nextLine());
            // insere o email no index 3 do statement
            System.out.print("Enter client e-mail: ");
            insertStatement.setString(3, myScanner.nextLine());
            // insere o celular no index 4 do statement
            System.out.print("Enter client phone: ");
            insertStatement.setString(4, myScanner.nextLine());
            // insere a idade no index 5 do statement
            System.out.print("Enter client age: ");
            insertStatement.setInt(5, myScanner.nextInt());
            // executo o meu statement insert
            int response = insertStatement.executeUpdate();
            System.out.println("Lines Inserted: " + response);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectOperation() {
        System.out.println("List of clients:");

        try {
            ResultSet tableResult = selectStatement.executeQuery();

            while (tableResult.next()) {
                Client oneClient = new Client(
                        tableResult.getString(1).trim(),
                        tableResult.getString(2).trim(),
                        tableResult.getString(3).trim(),
                        tableResult.getString(4).trim(),
                        tableResult.getInt(5));

                System.out.println(oneClient.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteOperation() {
        System.out.println("Enter the username of the client to be deleted: ");
        try {
            String username = myScanner.nextLine();
            deleteStatement.setString(1, username);
            int results = deleteStatement.executeUpdate();

            if (results == 1) {
                System.out.println(username + " was removed from the clients table.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
