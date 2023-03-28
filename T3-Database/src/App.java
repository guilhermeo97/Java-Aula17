import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        T3Database myDatabase = null;
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Welcome to T3 DataBase");
        System.out.println();

        while (myDatabase == null) {
            try {
                myDatabase = new T3Database();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

                System.out.println("Try again? y/n");
                char tryAgain = myScanner.nextLine().charAt(0);

                if (tryAgain == 'n' || tryAgain == 'N') {
                    System.exit(0);
                }
            }
        }
        // conexão
        System.out.println("Connected");
        System.out.println();

        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("Select a option: ");
            System.out.println("-----------------");
            System.out.println("C - Create");
            System.out.println("R - Read");
            System.out.println("U - Update");
            System.out.println("D - Delete");
            System.out.println("0 - Exit");
            System.out.println("-----------------");
            System.out.println();

            char option = myScanner.nextLine().charAt(0);
            // Executa a opção seleciona pelo switch
            switch (option) {
                case 'c':
                case 'C':
                    System.out.println("Selected: C - Create");
                    myDatabase.insertOperation();
                    break;
                case 'r':
                case 'R':
                    System.out.println("Selected: R - Read");
                    myDatabase.selectOperation();
                    break;
                case 'u':
                case 'U':
                    System.out.println("Selected: U - Update");
                    break;
                case 'd':
                case 'D':
                    System.out.println("Selected: D - Delete");
                    myDatabase.deleteOperation();
                    break;
                case '0':
                    System.out.println("Selected: 0 - Exit");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Selected: " + option + " - Invalid");
                    break;
            }

            System.out.println();
            System.out.println("--------------------------");
            System.out.println("Press Enter to Continue...");
            myScanner.nextLine();

        }
    }
}
