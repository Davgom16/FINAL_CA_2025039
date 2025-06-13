/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inpututilities;

import java.util.Scanner;

/**
 * HDIP Feb 2025
 * 
 * InputUtiiities
 * 
 *  1) askUserForText(String) -- prompt user for text input & validate
 * 
 *  2) askUserForInt (String) -- prompt user for an integer value & validate
 * 
 * 
 * @author kheal
 */
public class InputUtilities {
    
    /**
     * ask the user to enter some text - no numbers!
     * @param prompt is the question or prompt for the user
     * @return valid text entered by the user 
     */
    public String askUserForText(String prompt){
        
        Scanner myKB = new Scanner(System.in);
        String userInput;
        
        do{
            // issue prompt to user and get input
            System.out.println(prompt + "\n");
            userInput = myKB.nextLine();
            
            //keep going while input not valid
        }while (!userInput.matches("[a-zA-Z!.?,-]"));
        
        //input must be valid text
        return (userInput);
    }
    
    public String askUserForDate(String prompt){
        
        Scanner myKB = new Scanner(System.in);
        String userInput;
        
        do{
            // issue prompt to user and get input
            System.out.println("Remember to use the YYYY-MM-DD format.\n");
            System.out.println(prompt);
            userInput = myKB.nextLine();
            
            //keep going while input not valid. That is a RegEx to macht a date with YYYY-MM-DD format
        }while (!userInput.matches("(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])"));
        
        //input must be valid text
        return (userInput);
    }
    
    /**
     * ask the user to enter an integer value
     * @param prompt is the question or prompt to the user
     * @return a valid int entered by the user
     */
    public int askUserForInt(String prompt){
        
        Scanner myKB = new Scanner(System.in);
        String userInput;
        
        do{
            // issue prompt to user and get input
            System.out.println(prompt);
            System.out.println("You must enter an integer value.\n");
            userInput = myKB.nextLine();
            
            //keep going while input not valid
        }while (!userInput.matches("[0-9]+"));
        
        //input must be valid int
        return (Integer.parseInt(userInput));
    }
}
