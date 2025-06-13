/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package final_ca_2025039;

//import the libraries to make the connection with MySQL and InputUtilities

import java.sql.*;
import inpututilities.InputUtilities;

/**
 *
 * @author dav_g
 */
public class FINAL_CA_2025039 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
//That is the code needed to connect with a database in MySQL
       
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/House_renting","root","a5663bef");
            
//A message is displayed to indicate that the connection was successful.
            
            System.out.println("Connected to DB\n");
            
//Create a Statement object to use to execute SQL queries
            
            Statement stmt= con.createStatement();
            
//manu_selection is the variable where I store the user's choice
            
            int menu_selection;

// inside the do-while loop is the main code, the loop is going to run until the user inputs the number 8.            
            
            do{ 
                
// Menu display                 
                
                System.out.println("****MENU SYSTEM****\n");
                System.out.println("""
                                   1. Find all properties rented by specific clients using the first letter of their name.
                                   2. Find all properties owned by specific owners using the first letter of their name.
                                   3. Calculate total money spend by specific clients using the first letter of their name.
                                   4. Find clients who have rented properties during a specific time window.
                                   5. List the owners by the number of properties they own. 
                                   6. Find clients who pay more than a certain amount of money.
                                   7. Show clients who spend above or below the average monthly rent. 
                                   8. Exit
                                   """);
                
//Using InputUtilities everytime the user needs to input any value or character  

                InputUtilities myInput = new InputUtilities();
                
//In the following line the user gives a value to the variable manu_selection                 
                
                menu_selection = myInput.askUserForInt("Please select an option from the menu:");
                
//Switch is the best option, because we are comparing just one value                  
                 
                switch (menu_selection) {
                    
//There are 8 cases                    
                
                case 1 ->{ 
                    
//Asking for a letter to search for a name that starts with it.
                                      
                    String letter = myInput.askUserForText("\nInsert a letter");
                    
//MySQL Code 
                    
                    String myQuery1;
                        myQuery1 = "select " +
                                "clients.clients_ID as C_ID, " +
                                "clients.clients_name as Name, " +
                                "properties.properties_ID as P_ID, " +
                                "properties.properties_address as address " +
                                "from clients " +
                                "INNER JOIN rent_properties ON clients.clients_ID = rent_properties.clients_ID " +
                                "INNER JOIN properties ON rent_properties.properties_ID = properties.properties_ID " +
                                "WHERE clients_name LIKE \'" + letter + "%\'"; 

                    System.out.println("**************");
                    
//Exejuting MySQL code in the following line code 

                    ResultSet queryResults = stmt.executeQuery(myQuery1);
                    
//There is a counter which initiaal value is 0
                    
                    int count1=0;
                    
//Taking all the results from the previous query and show them in the output

                    while (queryResults.next()){

                        System.out.println("Client ID: " + queryResults.getString("C_ID") + " \t Name: " + queryResults.getString("Name") + 
                                " \t Property ID: " + queryResults.getString("P_ID") + " \t Property Address: " + queryResults.getString("address") + "\n");
                        
//Each time the code print a result the counter encrease by 1 
                        
                        count1++;
                    }
                    
//if the counter remains at zero it means that there are no result, and a message is shown.
                    
                    if (count1 == 0){
                        
                        System.out.println("\n There are no clients whose names start with the letter " + letter + "\n");
                        
                    } 
                                        
                }
                
                case 2 -> {
                    
//We ask for a letter to search for a name that starts with it.
                    
                    String letter = myInput.askUserForText("\nInsert a letter");
                    
//MySQL Code
                    
                    String myQuery2;
                        myQuery2 = "select " +
                                "owners.owners_ID as O_ID, " +
                                "owners.owners_name as Name, " +
                                "properties.properties_ID as P_ID, " +
                                "properties.properties_address as address " +
                                "from owners " +
                                "INNER JOIN rent_properties ON owners.owners_ID = rent_properties.owners_ID " +
                                "INNER JOIN properties ON rent_properties.properties_ID = properties.properties_ID " +
                                "WHERE owners_name LIKE \'" + letter + "%\'"; 

                    System.out.println("**************");
                    
//Exejuting MySQL code in the following line code 

                    ResultSet queryResults = stmt.executeQuery(myQuery2);
                    
//There is a counter which initiaal value is 0
                    
                    int count2=0;
                    
//Taking all the results from the previous query and show them in the output

                    while (queryResults.next()){

                        System.out.println("Owner ID: " + queryResults.getString("O_ID") + " \t Name: " + queryResults.getString("Name") + 
                                " \t Property ID: " + queryResults.getString("P_ID") + " \t Property Address: " + queryResults.getString("address") + "\n");
                        
//Each time the code print a result the counter encrease by 1 
                        
                        count2++;
                    }
                    
//if the counter remains at zero it means that there are no result, and a message is shown.
                    
                    if (count2 == 0){
                        
                        System.out.println("\n There are no owners whose names start with the letter " + letter + "\n");
                        
                    } 
                    
                }
                
                case 3 -> {
                    
//Asking for a letter to search for a name that starts with it.
                    
                    String letter = myInput.askUserForText("\nInsert a letter");
                    
//MySQL Code                    
                    
                    String myQuery3;
                        myQuery3 = "select " +
                                "clients.clients_name as Name, " +
                                "properties.properties_ID as P_ID, " +
                                "properties.rent_start as Start_date, " +
                                "properties.rent_finish as Finish_date, " +
                                "(timestampdiff( month, rent_start, rent_finish) + (DAY(rent_finish) - DAY(rent_start))/(30.0)) * monthly_rent AS total " +
                                "from clients " +
                                "INNER JOIN rent_properties ON clients.clients_ID = rent_properties.clients_ID " +
                                "INNER JOIN properties ON rent_properties.properties_ID = properties.properties_ID " +
                                "WHERE clients_name LIKE \'" + letter + "%\'"; 

                    System.out.println("**************");
                    
//Exejuting MySQL code in the following line code                     

                    ResultSet queryResults = stmt.executeQuery(myQuery3);
                    
//There is a counter which initiaal value is 0                    
                    
                    int count3=0;
                    
//Taking all the results from the previous query and show them in the output                   

                    while (queryResults.next()){

                        System.out.println("Name: " + queryResults.getString("Name") + " \t Property ID: " + queryResults.getString("P_ID") + 
                                " \t Start Date: " + queryResults.getString("Start_date") + " \t Finish Date: " + queryResults.getString("finish_date") + 
                                " \t Total Payment: " + queryResults.getInt("total") + "\n");
                        
//Each time the code print a result the counter encrease by 1                         
                        
                        count3++;
                    }
                    
//if the counter remains at zero it means that there are no result, and a message is shown.                   
                    
                    if (count3 == 0){
                        
                        System.out.println("\n There are no clients whose names start with the letter " + letter + "\n");
                        
                    }
                    
                }
                
                case 4 -> {
                    
//Asking for two dates (YYYY-MM-DD format)
//InputUtilities was slightly modified to check whether the user's input has the proper format                     
                    
                    String first_date = myInput.askUserForDate("\nInsert the first date: \n");
                    String second_date = myInput.askUserForDate("\nInsert the second date: \n");
                    
/*
We have to make sure that the first date entered is earlier the second one.
For that, I use "compareTo", and the result is stored in the variable comparison.
Once comparison has a value, the program can choose the proper way in the IF statement                    
*/
                    
                    int comparison = first_date.compareTo(second_date);
                    
//the value of comparison can be -1, 0, 1. if it is -1 the first date is earlier than the second one.

                    if (comparison < 0) {
                        
//MySQL Code
                        
                            String myQuery4;
                            myQuery4 = "select " +
                                        "clients.clients_ID as C_ID, " +
                                        "clients.clients_name as Name, " +
                                        "properties.properties_ID as P_ID, " +
                                        "properties.rent_start as Start_date, " +
                                        "properties.rent_finish as Finish_date " +
                                        "from clients " +
                                        "INNER JOIN rent_properties ON clients.clients_ID = rent_properties.clients_ID " +
                                        "INNER JOIN properties ON rent_properties.properties_ID = properties.properties_ID " +
                                        "WHERE rent_start >= '" + first_date + "' and rent_finish <= '" + second_date + "'"; 

                            System.out.println("\n**************\n");
                            
//Exejuting MySQL code in the following line code 

                            ResultSet queryResults = stmt.executeQuery(myQuery4);
                            
//There is a counter which initiaal value is 0                             

                            int count4=0;
                            
//Taking all the results from the previous query and show them in the output 

                            while (queryResults.next()){

                                System.out.println("Client ID: " + queryResults.getString("C_ID") + " \t Name: " + queryResults.getString("Name") + 
                                        " \t Property ID: " + queryResults.getString("P_ID") + " \t Start Date: " + queryResults.getString("Start_date") + " \t Finish Date: " + 
                                        queryResults.getString("finish_date") + "\n");
                                
//Each time the code print a result the counter encrease by 1
                                
                                count4++;
                            }
                            
//if the counter remains at zero it means that there are no result, and a message is shown.
                            
                            if (count4 == 0){
                        
                                System.out.println("\n There are no clients who rent a property between those dates\n");

                            }
                            
//when comparison is 1, it means that the second date is earlier than the first one, and a message is displayed
                        
                    } else if (comparison > 0) {
                        
                        System.out.println("\n" + first_date + " comes after " + second_date + ". Please change the dates and try it again\n");
                        
//when comparison is 1, it means that both dates are the same, and a message is displayed
                        
                    } else {
                        
                        System.out.println("\n" + first_date + " and " + second_date + " are equal. Please change the dates and try it again\n");
                        
                    }
     
                }
                
                case 5 -> {
                    
//Asking for a number to search for the quantity of properties that an owner has.
                    
                    int number = myInput.askUserForInt("\nInsert a number");
                    
//MySQL Code
                    
                    String myQuery5;
                        myQuery5 = "select " +
                                "owners.owners_ID as O_ID, " +
                                "owners.owners_name as Name, " +
                                "COUNT(properties.properties_ID) as Quantity " +
                                "from owners " +
                                "INNER JOIN rent_properties ON owners.owners_ID = rent_properties.owners_ID " +
                                "INNER JOIN properties ON rent_properties.properties_ID = properties.properties_ID " +
                                "GROUP BY owners.owners_ID, owners.owners_name "+
                                "HAVING Quantity = " + number;

                    System.out.println("**************");
                    
//Exejuting MySQL code in the following line code 

                    ResultSet queryResults = stmt.executeQuery(myQuery5);
                    
//There is a counter which initial value is 0 
                    
                    int count5=0;
                    
//Taking all the results from the previous query and show them in the output

                    while (queryResults.next()){

                        System.out.println("Owner ID: " + queryResults.getString("O_ID") + " \t Name: " + queryResults.getString("Name") + 
                                " \t Quantity: " + queryResults.getString("Quantity") + "\n");
                        
//Each time the code print a result the counter encrease by 1
                        
                        count5++;
                    }
                    
//if the counter remains at zero it means that there are no result, and a message is shown.
                    
                    if (count5 == 0){
                        
                        System.out.println("\n There are no owners who have " + number + " properties\n");
                        
                    }
                }
                
                case 6 -> {
                    
//Asking for an amount of money. it will be used like a limit 
                    
                    int rent = myInput.askUserForInt("\nDeclare the amount of money: \n");
                    
//MySQL Code
                    
                    String myQuery6;
                            myQuery6 = "select " +
                                        "clients.clients_ID as C_ID, " +
                                        "clients.clients_name as Name, " +
                                        "rent_properties.monthly_rent as Rent_payment " +
                                        "from clients " +
                                        "INNER JOIN rent_properties ON clients.clients_ID = rent_properties.clients_ID " +
                                        "HAVING Rent_payment >= " + rent; 

                            System.out.println("\n**************\n");
                            
//Exejuting MySQL code in the following line code

                            ResultSet queryResults = stmt.executeQuery(myQuery6);
                            
//There is a counter which initial value is 0 

                            int count6=0;
                            
//Taking all the results from the previous query and show them in the output

                            while (queryResults.next()){

                                System.out.println("Client ID: " + queryResults.getString("C_ID") + " \t Name: " + queryResults.getString("Name") + 
                                        " \t Monthly Rent: " + queryResults.getString("Rent_payment") + "\n");
                                
//Each time the code print a result the counter encrease by 1
                                
                                count6++;
                            }
                            
//if the counter remains at zero it means that there are no result, and a message is shown.
                            
                            if (count6 == 0){
                        
                                System.out.println("\n There are no clients who pay " + rent + " or more.\n");

                            }
                }
                
                case 7 -> {
                    
//Asking for user's input 
                    
                    int valor = myInput.askUserForInt("""
                                                      If you want to see clients whose rent is above the average, type '1'.
                                                      By default, the program will show you all clients whose rent is below the average monthly amount, 
                                                      type anynumber 
                                                      """);
                    
//Creating a variable call sign. It will be used to decide if the program has to show the clients above or below the avergae monthly amount
                    
                    String sign;
                    
/*
If the user wants to now the clients above the avergae, they have to type number 1, otherwise they can type any number,
and the program shows the clients who are below the average.
*/
                    
                    if (valor == 1){
                        
                        sign = ">";
                        
                    }else{
                        
                        sign = "<";
                                
                    }
                    
//MySQL Code
                    
                    String myQuery7;
                            myQuery7 = "select " +
                                        "clients.clients_ID as C_ID, " +
                                        "clients.clients_name as Name, " +
                                        "rent_properties.monthly_rent as Rent_payment " +
                                        "from clients " +
                                        "INNER JOIN rent_properties ON clients.clients_ID = rent_properties.clients_ID " +
                                        "WHERE monthly_rent " + sign + " (SELECT AVG(monthly_rent) FROM rent_properties)"; 

                            System.out.println("\n**************\n");
                            
//Exejuting MySQL code in the following line code

                            ResultSet queryResults = stmt.executeQuery(myQuery7);
                            
//Taking all the results from the previous query and show them in the output

                            while (queryResults.next()){

                                System.out.println("Client ID: " + queryResults.getString("C_ID") + " \t Name: " + queryResults.getString("Name") + 
                                        " \t Monthly Rent: " + queryResults.getString("Rent_payment") + "\n");
                                
                            }
                    
                }
                
                case 8 -> {
                    
//Case 8 is the only way to get out of the do-while loop
                    
                    System.out.println("\nYou have quit the program successfully.\n");
                    
                }
                
                default -> {
                    
//Typing any other number out of the menu, it will display the message 
                    
                    System.out.println("\nPlease choose one number from the menu\n");
                    
                }
                
               } 
                
//The do-while loop continues until the user types number 8
            
            }while(!(menu_selection==8));
            
//if there is any trouble with the databases or MySQL this block of code will show it.
            
        }catch (SQLException e) {
            
            System.out.println("SQL Error --->");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            
        }
        
//if there is any trouble locating a class this block of code will show it.
        
        catch (ClassNotFoundException e){
            
            System.out.println("Class error -- " + e.getMessage());
            
        }
        
    }
    
}
