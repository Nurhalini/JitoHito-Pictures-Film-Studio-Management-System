import java.io.*;
import java.util.*;
import javax.swing.*;

public class Management
{
    private String name;
    private String[] position = {"Clerk", "Manager", "CEO"}; // Available Positions in the Company. When registering, any other position than these will be rejected.
    private double salary; 
    private String managementID; //Every Worker in JitoHito has an Account ID, and it is always NOT case sensitive when logging in.
    private String password; //With each Account holder requires a password in order to login.
    
    // Default Constructor
    public Management(){}
    // Normal Constructor
    public Management(String id, String pass){
        this.managementID = id;
        this.password = pass;
    }
    
    // Method used to check if user's input of managementID and password are equal based on adminData.txt's information stored.
    public boolean managementVerify(String id, String pass){
        boolean verifySucceed = false;
        this.password = pass;
        this.managementID = id;
        try {
            FileReader fr = new FileReader("adminData.txt");
            BufferedReader br = new BufferedReader(fr);
            String data;
            
            while ((data = br.readLine()) != null){
                StringTokenizer strToken = new StringTokenizer(data, ",");
                String verifyManagementID = strToken.nextToken();
                String verifyPassword = strToken.nextToken();
                // Reminder: managementID is NOT case sensitive. Password is case sensitive.
                if (this.managementID.equalsIgnoreCase(verifyManagementID) && this.password.equals(verifyPassword)){
                    verifySucceed = true; // If they are equal, user is allowed to proceed with logging into JitoHito System.
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem: " + e.getMessage());
        } catch (IOException ioe) {
            System.out.println("Problem: " + ioe.getMessage());
        } finally {}
        return verifySucceed;
    }
    
    public boolean accountLogin(){
        Scanner scan = new Scanner(System.in);
        boolean verify = false; 
        // Variable used during the Login Process, if ManagementID and Password are not equal from AdminData.txt, 
        // user will have to redo until they get it right.                     
        boolean found = false; 
        // Variable used during Sign Up Process where user enters their job title and if it doesn't exist in the 
        // array, then user is required to redo until a job title exists.         
        boolean accountExists; 
        // Variable used during Sign up, making sure user doesn't type an existing managementID that's based from
        // AdminData.txt
        boolean exit = false; // Returning value if user typed in E to exit the system.
        
        System.out.println("------------------ [JitoHito Pictures] ----------------------");
        System.out.println("Account Login [L], Account Sign-Up [S] or Exit System [E]");
        String userinput = scan.next();
        
        // When User types a value that's NOT L, S or E
        while (!userinput.equalsIgnoreCase("L") && !userinput.equalsIgnoreCase("S") && !userinput.equalsIgnoreCase("E")){
            System.out.println("Error: Unable to Read Code! Try Again:");
            userinput = scan.next();
        }
        
        if (userinput.equalsIgnoreCase("E")){ // If User types E to Exit...
            System.out.println("System Shutting Down...");
            return exit = true;
        } else if (userinput.equalsIgnoreCase("L")){ // If User types L to Login...
            do {
                System.out.println("Enter Account ID:");
                managementID = scan.next();
                System.out.println("Enter Account Password:");
                password = scan.next();
                verify = managementVerify(managementID, password); // Method managementVerify() called to verify if value given is equal or not.
                if (!verify){
                    System.out.println("Login Unsucessful! Please Try Again.");
                } else {
                    System.out.println("Login Successful! Redirecting to Main Menu...");
                }
            } while (!verify);
            return exit;
        } else { // If User types S to Sign-In...
            scan.nextLine();
            System.out.println("[Account Sign-in]");
            System.out.println("Enter Your Full Name:");
            name = scan.nextLine();
            System.out.println("Your Position Here: ");
            String positionInput = scan.nextLine().trim().toLowerCase();
            
            for (String pos : position){ // For Statement to check the Position Array if positionInput exists as a job title in Position
                if (pos.equalsIgnoreCase(positionInput)){
                    found = true;
                }
            }
            
            while (!found){ // If positionInput cant find a matching value in Position, then user is required to try again.
                System.out.println("Sorry! That Job Isn't Available Here. Re-Enter Your Position Again.");
                System.out.println("Your Position Here: ");
                positionInput = scan.nextLine().trim().toLowerCase();
                for (String pos : position){
                    if (pos.equalsIgnoreCase(positionInput)){
                        found = true;
                        break;
                    }
                }   
            }
            
            // This Do-While statement is implemented to make sure whatever Account ID that User has entered is NOT an existing value that exists 
            // in AdminData.txt. If they typed in an existing ID, User needs to re-enter a new Account ID.
            do{
                accountExists = false;
                System.out.println("Create your Account ID Here:");
                managementID = scan.next();
                try { // Try-Catch is to check if managementID already exists in adminData.txt or not.
                    FileReader fr = new FileReader("adminData.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String data;
                    while ((data = br.readLine()) != null){
                        StringTokenizer strToken = new StringTokenizer(data, ",");
                        String verifyManagementID = strToken.nextToken();
        
                        if (managementID.equalsIgnoreCase(verifyManagementID)){ // Reminder: managementID is NOT case sensitive.
                            accountExists = true;
                            break;
                        }
                    }
                    br.close();
                } catch (FileNotFoundException e) {
                System.out.println("Problem: " + e.getMessage());
                } catch (IOException ioe) {
                System.out.println("Problem: " + ioe.getMessage());
                } finally {}
                
                if (accountExists){ // If managementID that's typed is already an exisiting account ID in AdminData.txt
                    System.out.println("This Account ID Already Exist. Please Retry Again.");
                }
            } while (accountExists); // Loop until the managementID that's typed in isn't an existing value in AdminData.txt
            
            System.out.println("Create your Account Password Here:");
            password = scan.next();
            while (password.length() < 8){ // Password Requirement is 8 letters long. If it's less than that, User is required to re-type the password.
                System.out.println("ALERT: Your Password is Too Short!");
                System.out.println("Create your Account Password Here:");
                password = scan.next();
            }
            
            try { // Try-Catch here is to add the new account created into AdminData.txt.                
                FileWriter fw = new FileWriter("adminData.txt", true); // Corrected the filename here
                fw.write("\n" + managementID + "," + password);
                fw.close();
            } catch (FileNotFoundException e) {
            System.out.println("Problem: " + e.getMessage());
            } catch (IOException ioe) {
            System.out.println("Problem: " + ioe.getMessage());
            } finally {}
            
            System.out.println("Account Created Successfully!");
            System.out.println("Redirecting to Main Menu...");
            return exit;
        }
    }
    
    public String managementMenu_1(){
        Scanner scan = new Scanner(System.in);
        try { // 3 Second Delay by using Thread.sleep() method
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        
        System.out.println("\n****  [JitoHito Pictures Actor Menu]  ****");
        System.out.println("1. View Actors        [A]");
        System.out.println("2. Account Settings   [S]");
        System.out.println("3. Logout             [L]");
        String userinput = scan.next();
        while (!userinput.equalsIgnoreCase("A") && !userinput.equalsIgnoreCase("L") && !userinput.equalsIgnoreCase("S")){
            System.out.println("Error: Unable to Read Code! Try Again:");
            userinput = scan.next();
        }
        return userinput;
    }
    
        public String managementMenu_2(){
        Scanner scan = new Scanner(System.in);
        try { // 3 Second Delay by using Thread.sleep() method
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        
        System.out.println("\n****  [JitoHito Pictures Film Menu]  ****");
        System.out.println("1. View Films         [F]");
        System.out.println("2. Account Settings   [S]");
        System.out.println("3. Logout             [L]");
        String userinput = scan.next();
        while (!userinput.equalsIgnoreCase("F") && !userinput.equalsIgnoreCase("L") && !userinput.equalsIgnoreCase("S")){
            System.out.println("Error: Unable to Read Code! Try Again:");
            userinput = scan.next();
        }
        return userinput;
    }

}