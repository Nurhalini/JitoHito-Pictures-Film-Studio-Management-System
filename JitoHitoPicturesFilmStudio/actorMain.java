import java.util.*;
import java.io.*;
public class actorMain{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        LinkedList actorLL = new LinkedList(); // Creating a LinkedList
        
        // Declaration of Actor's Variables:
        String actorName, gender, contract;
        int age;
        char celebrityList;         
        double salary;
        
        // Admin Variables:
        String managementID, password;
        // Adding Values into actorLL and FilmQ
        try {
            FileReader fr = new FileReader("ActorList.txt");
            BufferedReader br = new BufferedReader(fr);
            String data, data1;
            while ((data = br.readLine()) != null)
            {
                StringTokenizer strToken = new StringTokenizer(data, ",");
                actorName = strToken.nextToken();
                gender = strToken.nextToken();
                age = Integer.parseInt(strToken.nextToken());
                String strCelebrityList = strToken.nextToken();
                celebrityList = strCelebrityList.charAt(0);
                salary = Double.parseDouble(strToken.nextToken());
                contract = strToken.nextToken();
                
                Actor act = new Actor(actorName, gender, age, celebrityList, salary, contract);
                actorLL.insertAtFront(act);
            }
            br.close();
        } catch (FileNotFoundException e) 
        {
            System.out.println("Problem: " + e.getMessage());
        } catch (IOException ioe) 
        {
            System.out.println("Problem: " + ioe.getMessage());
        } finally {}
        // Purpose of this program is to make comparison on Queue functionability and LinkedList.
        //.*--------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Start of Program
        Management m = new Management();
        boolean exit = m.accountLogin();
        if (!exit){
            do{
                String section = "Null";
                do {
                    section = m.managementMenu_1(); // Get Value either A to view Actors, F to view Films, or L to Logout.
                    if (section.equalsIgnoreCase("A")){ // Actor Section [LinkedList]
                        int operation = 0;
                        do {
                                System.out.println("\n-------------[ACTOR MANAGEMENT MENU]-------------");
                                System.out.println("1. View All Actors  [1]");
                                System.out.println("2. Search Actor     [2]");
                                System.out.println("3. Edit Actor       [3]");
                                System.out.println("4. Remove Actor     [4]");
                                System.out.println("5. Sort Actors      [5]");
                                System.out.println("6. Insert New Actor [6]");
                                System.out.println("7. View Statistics  [7]");
                                System.out.println("8. Return to Menu   [8]");
                                System.out.println("Enter Code Here:");
                                operation = scan.nextInt(); //Choose among the six management menus
                                if (operation == 1){ // View All Actors
                                        Actor actorView = (Actor) actorLL.getFirst();
                                        int i = 1;
                                        while (actorView != null){
                                            System.out.print(i + ".");
                                            actorView.actorDetails();
                                            actorView = (Actor) actorLL.getNext();
                                            i++;
                                        }
                                        System.out.println("All Actors Information has Been Displayed!");
                                        System.out.println("Returning to Actor Menu...");
                                        // End of View All Actors
                                } else if (operation == 2){ // Search For Actors 
                                        int searchKey = 0;
                                        int remainKey = 0;
                                        do {
                                            Actor actorSearch = (Actor) actorLL.getFirst();
                                            System.out.println("\n1. Search By Name     [1]");
                                            System.out.println("2. Search By Gender   [2]");
                                            System.out.println("3. Search By Grade    [3]");
                                            System.out.println("4. Return             [4]");
                                            System.out.println("Enter Code Here:");
                                            searchKey = scan.nextInt();
                                            
                                            if (searchKey == 1){ // Search By Name
                                                do{
                                                    boolean isFound = false;
                                                    scan.nextLine();
                                                    System.out.println("Type Actor's Name Here:");
                                                    String searchName = scan.nextLine();
                                                    
                                                    Actor actorSearchName = (Actor) actorLL.getFirst();
                                                    while (actorSearchName != null){
                                                        if (actorSearchName.getActorName().equalsIgnoreCase(searchName)){
                                                            System.out.println("\nSearch Found at " + searchName + "!\nActor Details:\n");
                                                            actorSearchName.actorDetails();
                                                            isFound = true;
                                                        }
                                                        actorSearchName = (Actor) actorLL.getNext();
                                                    }
                                                    if (!isFound){
                                                        System.out.println(searchName + " can't be found in the system. Perhaps there may be a typo?");
                                                    }
                                                    System.out.println("Would you like to remain here? (1: Yes | 2: No): ");
                                                    remainKey = scan.nextInt();
                                                    while (remainKey != 1 && remainKey != 2){
                                                        System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                        remainKey = scan.nextInt();
                                                    }
                                                } while (remainKey != 2);
                                                // End of Search by Name
                                            } else if (searchKey == 2){ // Search By Gender
                                                do {
                                                    boolean isFound = false;
                                                    scan.nextLine();
                                                    System.out.println("Type Actor's Gender Here:");
                                                    String searchGender = scan.nextLine();
                                                    
                                                    while (!searchGender.equalsIgnoreCase("Female") && !searchGender.equalsIgnoreCase("Male")){
                                                        if (searchGender.equalsIgnoreCase("F") || searchGender.equalsIgnoreCase("M")){ // In case User inputs either F or M for
                                                                                                                                       // Searching a gender type.
                                                            if (searchGender.equalsIgnoreCase("F")){
                                                                searchGender = "Female";
                                                            } else {
                                                                searchGender = "Male";
                                                            }
                                                        } else { // If not F or M, that means User input a non-gender value. Will be looped until User enters the gender value correctly
                                                            System.out.println("Alert: " + searchGender + " is not a gender. Perhaps you may have mistyped it?");
                                                            System.out.println("Type Actor's Gender Here:");
                                                            searchGender = scan.nextLine();
                                                        }
                                                    }
                                                    
                                                    Actor actorGenderSearch = (Actor) actorLL.getFirst();
                                                    while (actorGenderSearch != null){
                                                        if (actorGenderSearch.getGender().equalsIgnoreCase(searchGender) && isFound == false){
                                                            System.out.println("\nSearch Found at " + searchGender + "!\nActor Details:\n");
                                                            actorGenderSearch.actorDetails();
                                                            isFound = true;
                                                        } else if (actorGenderSearch.getGender().equalsIgnoreCase(searchGender) && isFound == true){
                                                            actorGenderSearch.actorDetails();
                                                        }
                                                        actorGenderSearch = (Actor) actorLL.getNext();
                                                    }
                                                    System.out.println("Would you like to remain here? (1: Yes | 2: No): ");
                                                    remainKey = scan.nextInt();
                                                    while (remainKey != 1 && remainKey != 2){
                                                        System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                        remainKey = scan.nextInt();
                                                    }
                                                } while (remainKey != 2);
                                                // End of Search by Gender
                                            } else if (searchKey == 3){ // Search By Grade
                                                do {
                                                    boolean isFound = false;
                                                    System.out.println("Type Actor's Grade Here:");
                                                    String strSearchGrade = scan.next();
                                                    while (strSearchGrade.length() != 1){ // To Check the Value of strSearchGrade is 1 character only so that it can be put inside 
                                                                                          // a char value for searching.  
                                                        System.out.println("Alert: Value Should Not Be Longer Than 1 Character!");
                                                        System.out.println("Type Actor's Grade Here:");
                                                        strSearchGrade = scan.next();
                                                        
                                                    }
                                                    strSearchGrade = strSearchGrade.toUpperCase(); // Make it in uppercase for consistent searching results
                                                    char searchGrade = strSearchGrade.charAt(0); // Change the value from String to Char
                                                    Actor actorGradeSearch = (Actor) actorLL.getFirst();
                                                    
                                                    while (actorGradeSearch != null){
                                                        if (actorGradeSearch.getCelebrityList() == searchGrade && isFound == false){
                                                            System.out.println("\nSearch Found at " + searchGrade + " Grade!\nActor Details:\n");
                                                            actorGradeSearch.actorDetails();
                                                            isFound = true;
                                                        } else if (actorGradeSearch.getCelebrityList() == searchGrade && isFound == true){
                                                            actorGradeSearch.actorDetails();
                                                        }
                                                        actorGradeSearch = (Actor) actorLL.getNext();
                                                    }
                                                    if (!isFound){
                                                        System.out.println(searchGrade + " Grade can't be found in the system. Perhaps there may be a typo?");
                                                    }
                                                    System.out.println("Would you like to remain here? (1: Yes | 2: No): ");
                                                    remainKey = scan.nextInt();
                                                    while (remainKey != 1 && remainKey != 2){
                                                        System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                        remainKey = scan.nextInt();
                                                    }
                                                } while (remainKey != 2);
                                                // End of Search by Grade
                                            } else if (searchKey == 4){
                                                System.out.println("Are you sure? (1. Return back to Actor Management Menu | 2. Remain here)");
                                                searchKey = scan.nextInt();
                                                while (searchKey != 1 && searchKey != 2){
                                                    System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                    searchKey = scan.nextInt();
                                                }
                                                if (searchKey == 1){
                                                    searchKey = 4; // When the key is 4, automatically leave the menu.
                                                } else {
                                                    searchKey = 0;
                                                }
                                            } else {
                                                System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                searchKey = scan.nextInt();
                                            } 
                                        } while (searchKey != 4);
                                        // End of Search
                                } else if (operation == 3){ //Edit Actor's Information
                                       String selectEdit = "S";
                                       int remainKey = 0;
                                       do{
                                           scan.nextLine();
                                           System.out.println("Which Actor would you like to Edit?\nPlease Type His/Her Full Name Here:");
                                           String editSearch = scan.nextLine();
                                           Actor actorFindEdit = (Actor) actorLL.getFirst();
                                           boolean isFound = false; 
                                           
                                           while (actorFindEdit != null){
                                               if (actorFindEdit.getActorName().equalsIgnoreCase(editSearch)){
                                                   isFound = true;
                                                   do {
                                                       System.out.println("What would you like to edit from " + actorFindEdit.getActorName() + "?");
                                                       System.out.println("Edit Actor's Grade  [G]");
                                                       System.out.println("Edit Actor's Salary [S]");
                                                       System.out.println("Return              [R]");
                                                       selectEdit = scan.next();
                                                       
                                                       while (!selectEdit.equalsIgnoreCase("G") && !selectEdit.equalsIgnoreCase("S") && !selectEdit.equalsIgnoreCase("R")){
                                                           System.out.println("Error: Unable to Read Code! Try Again:");
                                                           selectEdit = scan.next();
                                                       }
                                                       
                                                       if (selectEdit.equalsIgnoreCase("G")){ // Edit Actor's Grade
                                                           do {
                                                               System.out.println("What Grade would you change "+ actorFindEdit.getActorName() +" for? [S/A/B/C/D]:");
                                                               String changeGrade = scan.next();
                                                               while (!changeGrade.equalsIgnoreCase("S") && !changeGrade.equalsIgnoreCase("A") && 
                                                                      !changeGrade.equalsIgnoreCase("B") && !changeGrade.equalsIgnoreCase("C") && !changeGrade.equalsIgnoreCase("D")){
                                                                  System.out.println("Alert: These aren't Valid Grades. Try Again:");
                                                                  changeGrade = scan.next();
                                                               } 
                                                               changeGrade = changeGrade.toUpperCase();
                                                               char finalChangeGrade = changeGrade.charAt(0);
                                                               actorFindEdit.setCelebrityList(finalChangeGrade);
                                                               System.out.println("Actor Grade Changed Successfully!");
                                                               actorFindEdit.actorDetails();
                                                               System.out.println("Would you like to remain here? (1: Yes | 2: No): ");
                                                               remainKey = scan.nextInt();
                                                               while (remainKey != 1 && remainKey != 2){
                                                                    System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                                    remainKey = scan.nextInt();
                                                               }
                                                            } while (remainKey != 2);
                                                       } else if (selectEdit.equalsIgnoreCase("S")){ // Edit Actor's Salary
                                                           do {
                                                               System.out.println("What is "+ actorFindEdit.getActorName() +"'s new Salary now:");
                                                               double changeSalary = scan.nextDouble();
                                                               actorFindEdit.setSalary(changeSalary);
                                                               System.out.println("Actor Salary Changed Successfully!");
                                                               actorFindEdit.actorDetails();
                                                               System.out.println("Would you like to remain here? (1: Yes | 2: No): ");
                                                               remainKey = scan.nextInt();
                                                               while (remainKey != 1 && remainKey != 2){
                                                                    System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                                    remainKey = scan.nextInt();
                                                               }
                                                           } while (remainKey != 2);
                                                       } else if (selectEdit.equalsIgnoreCase("R")){
                                                           System.out.println("Are you sure? Would you like to make changes or return to the Actor Management Menu?\n(N: Go Back to Search Menu |S: On second thought, I want to edit " + actorFindEdit.getActorName() + "'s information | R: Actor Management Menu)");
                                                           selectEdit = scan.next();
                                                           while(!selectEdit.equalsIgnoreCase("R") && !selectEdit.equalsIgnoreCase("N") && !selectEdit.equalsIgnoreCase("S")){
                                                              System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                              selectEdit = scan.next();
                                                           }
                                                       }
                                                       
                                                   } while (!selectEdit.equalsIgnoreCase("R") && !selectEdit.equalsIgnoreCase("N"));
                                               }
                                               actorFindEdit = (Actor) actorLL.getNext();
                                           }
                                           if (!isFound){
                                               System.out.println(editSearch + " can't be found in the system. Perhaps there may be a typo?");
                                           } // End Edit Section
                                           
                                       } while (!selectEdit.equalsIgnoreCase("R"));
                                } else if (operation == 4){ // Remove Actor Information 
                                       int remainKey = 0;
                                       do {
                                            scan.nextLine();
                                            System.out.println("What Actor do you want to Remove: ");
                                            String searchName = scan.nextLine();
                                            boolean isFound = false, contractRenewal = false;
                                            LinkedList tempStore = new LinkedList();
                                        
                                            // Search and Remove Logic
                                            Actor currentActorObj = (Actor) actorLL.getFirst();
                                            while (currentActorObj != null) {
                                                if (currentActorObj.getActorName().equalsIgnoreCase(searchName)) {
                                                    System.out.println("\nSearch Found at " + searchName + "!\nActor Details:\n");
                                                    currentActorObj.actorDetails();
                                                    isFound = true;
                                                    // Check Requirements
                                                    int contractDate = Integer.parseInt(currentActorObj.getContract().substring(6, 10));
                                                    if (currentActorObj.getSalary() < 50 || contractDate <= 2023) {
                                                        System.out.println(currentActorObj.getActorName() + " Meets the Requirements to be Removed.");
                                                        if (contractDate <= 2023){
                                                            System.out.println("However, " + currentActorObj.getActorName() + "'s Contract ended at " + currentActorObj.getContract().substring(6, 10) + ". Do you want to Re-New His/Her Contract? [Y/N]");
                                                            String contractInput = scan.next();
                                                            while (!contractInput.equalsIgnoreCase("Y") && !contractInput.equalsIgnoreCase("N")){
                                                                System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                                contractInput = scan.next();                                                            
                                                            }
                                                            
                                                            if (contractInput.equalsIgnoreCase("Y")){
                                                                boolean keyInvalidKey;
                                                                System.out.println("When do you want to Re-New " + currentActorObj.getActorName() + "'s Contract?");
                                                                System.out.println("Enter Date Here [DD/MM/YYYY]:");
                                                                String newContractDate = scan.next();
                                                                
                                                                do {
                                                                    keyInvalidKey = false; // Reset the flag for each iteration
                                                                
                                                                    // Check length
                                                                    if (newContractDate.length() != 10) {
                                                                        keyInvalidKey = true;
                                                                    } else {
                                                                            int day = Integer.parseInt(newContractDate.substring(0, 2));
                                                                            int month = Integer.parseInt(newContractDate.substring(3, 5));
                                                                            int year = Integer.parseInt(newContractDate.substring(6, 10));
                                                                            int monthCheck[] = {2,4,7,9,11};
                                                                            // Validate month
                                                                            if (month < 1 || month > 12) {
                                                                                keyInvalidKey = true;
                                                                            } else {
                                                                                // Validate day based on the month
                                                                                if (day < 1 || day > 31){
                                                                                    keyInvalidKey = true;
                                                                                }
                                                                                for (int i = 0; i < 5; i ++){
                                                                                    if (day == 31 && month == monthCheck[i]){
                                                                                        keyInvalidKey = true;
                                                                                    }
                                                                                }
                                                                            }
                                                                    }
                                                                
                                                                    // If invalid, prompt again
                                                                    if (keyInvalidKey) {
                                                                        System.out.println("Invalid Date! Please Be Reminded the Format should be in DD/MM/YYYY.");
                                                                        System.out.println("Enter Date Here [DD/MM/YYYY]:");
                                                                        newContractDate = scan.next();
                                                                    }
                                                                } while (keyInvalidKey);
                                                                System.out.println("Contract Re-newed Successfully!");
                                                                currentActorObj.setContract(newContractDate);
                                                                tempStore.insertAtBack(currentActorObj); // Save in tempStore
                                                                currentActorObj = (Actor) actorLL.getNext();
                                                                actorLL.removeFromFront();
                                                                
                                                            } else if (contractInput.equalsIgnoreCase("N")) { // When the answer is N
                                                                actorLL.removeFromFront();
                                                                System.out.println("Actor Has Been Successfully Removed.");
                                                                currentActorObj = (Actor) actorLL.getNext();
                                                            }
                                                            
                                                        } else { 
                                                            actorLL.removeFromFront();
                                                            System.out.println("Actor Has Been Successfully Removed.");
                                                            currentActorObj = (Actor) actorLL.getNext();
                                                        }
                                                    } else {
                                                        System.out.println("Actor Does NOT Meet The Requirements to be Let Go.");
                                                        tempStore.insertAtBack(currentActorObj); // Save in tempStore
                                                        currentActorObj = (Actor) actorLL.getNext();
                                                    }
                                                } else {
                                                    tempStore.insertAtBack(currentActorObj); // Save in tempStore
                                                    currentActorObj = (Actor) actorLL.getNext();
                                                    actorLL.removeFromFront();
                                                }
                                            }
                                        
                                            // Handle Case When Actor Is Not Found
                                            if (!isFound) {
                                                System.out.println(searchName + " can't be found in the system. Perhaps there may be a typo?");
                                            }
                                        
                                            // Restore Remaining Actors Back to actorLL
                                            currentActorObj = (Actor) tempStore.getFirst();
                                            while (currentActorObj != null) {
                                                actorLL.insertAtBack(currentActorObj);
                                                currentActorObj = (Actor) tempStore.getNext();
                                                tempStore.removeFromFront();
                                            }
            
                                            System.out.println("Would you like to remain here? (1: Yes | 2: No): ");
                                            remainKey = scan.nextInt();
                                            while (remainKey != 1 && remainKey != 2) {
                                                    System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                    remainKey = scan.nextInt();
                                            }
                                        } while (remainKey != 2);
                                } else if (operation == 5){ // Sorting Actor either by Ascending or Descending
                                        String sortKey = "N";
                                        do {
                                            System.out.println("Sort Ascendingly (by Name) [A]");
                                            System.out.println("Sort Decendingly (by Name) [B]");
                                            System.out.println("Return                     [R]");
                                            System.out.println("Enter Code Here:");
                                            sortKey = scan.next();
                                            while (!sortKey.equalsIgnoreCase("R") && !sortKey.equalsIgnoreCase("A") && !sortKey.equalsIgnoreCase("B")){
                                                System.out.println("Error: Unable to Read Code! Try Again:");
                                                sortKey = scan.next();
                                            }
                                            LinkedList sortedList = new LinkedList(); // Temporary sorted list
                                            Actor current = (Actor) actorLL.getFirst(); // Start with the first element
                                            if (sortKey.equalsIgnoreCase("A")) { // Sort Ascendingly
                                                while (current != null) {
                                                    Actor currentActor = (Actor) current;
                                                    // Insert `currentActor` into its correct position in `sortedList`
                                                    if (sortedList.isEmpty()) {
                                                        sortedList.insertAtFront(currentActor); // First element, add to the front
                                                    } else {
                                                        LinkedList tempSorted = new LinkedList();
                                                        boolean inserted = false;
                                                        Actor temp = (Actor) sortedList.getFirst();
                                                        while (temp != null) {
                                                            Actor tempActor = (Actor) temp;
                                                            if (!inserted && currentActor.getActorName().compareTo(tempActor.getActorName()) < 0) {
                                                                tempSorted.insertAtBack(currentActor); // Insert before the current tempActor
                                                                inserted = true;
                                                            }
                                                            tempSorted.insertAtBack(tempActor); // Add the tempActor to the new list
                                                            temp = (Actor) sortedList.getNext();
                                                        }
                                                        // If not yet inserted, add to the back
                                                        if (!inserted) {
                                                            tempSorted.insertAtBack(currentActor);
                                                        }
                                                        // Replace the sortedList with the newly constructed tempSorted
                                                        sortedList = tempSorted;
                                                    }
                                                    current = (Actor) actorLL.getNext(); // Move to the next element in original list
                                                }
                                            
                                                // Print the sorted list
                                                Actor sortedActor = (Actor) sortedList.getFirst();
                                                while (sortedActor != null) {
                                                    sortedActor.actorDetails(); // Print actor details
                                                    sortedActor = (Actor) sortedList.getNext();
                                                }
                                            } else if (sortKey.equalsIgnoreCase("B"))  {
                                                while (current != null) {
                                                    Actor currentActor = (Actor) current;
                                                    // Insert `currentActor` into its correct position in `sortedList`
                                                    if (sortedList.isEmpty()) {
                                                        sortedList.insertAtFront(currentActor); // First element, add to the front
                                                    } else {
                                                        LinkedList tempSorted = new LinkedList();
                                                        boolean inserted = false;
                                            
                                                        Actor temp = (Actor) sortedList.getFirst();
                                                        while (temp != null) {
                                                            Actor tempActor = (Actor) temp;
                                                            if (!inserted && currentActor.getActorName().compareTo(tempActor.getActorName()) > 0) {
                                                                tempSorted.insertAtBack(currentActor); // Insert before the current tempActor
                                                                inserted = true;
                                                            }
                                                            tempSorted.insertAtBack(tempActor); // Add the tempActor to the new list
                                                            temp = (Actor) sortedList.getNext();
                                                        }
                                                        // If not yet inserted, add to the back
                                                        if (!inserted) {
                                                            tempSorted.insertAtBack(currentActor);
                                                        }
                                                        // Replace the sortedList with the newly constructed tempSorted
                                                        sortedList = tempSorted;
                                                    }
                                                    current = (Actor) actorLL.getNext(); // Move to the next element in original list
                                                }
                                            
                                                // Print the sorted list
                                                Actor sortedActor = (Actor) sortedList.getFirst();
                                                while (sortedActor != null) {
                                                    sortedActor.actorDetails(); // Print actor details
                                                    sortedActor = (Actor) sortedList.getNext();
                                                }
                                            } else if (sortKey.equalsIgnoreCase("R")){
                                                System.out.println("Are you sure you would want to return to Actor Management Menu? (R: Yes | N: No): ");
                                                sortKey = scan.next();
                                                while (!sortKey.equalsIgnoreCase("R") && !sortKey.equalsIgnoreCase("N")){
                                                    System.out.println("Alert: These aren't Valid Codes. Try Again:");
                                                    sortKey = scan.next();
                                                }
                                            }
                                        } while (!sortKey.equalsIgnoreCase("R"));
                                } else if (operation == 6){
                                    scan.nextLine();
                                    System.out.println("Enter Actor Name:");
                                    String newActorName = scan.nextLine();
                                    System.out.println("Enter Actor's Age:");
                                    int newActorAge = scan.nextInt();
                                    while (newActorAge < 0){
                                        System.out.println("Alert: Invalid Age Detected. Please Try Again:");
                                        newActorAge = scan.nextInt();
                                    }
                                    System.out.println("Enter Actor's Gender [Female/Male]:");
                                    String newActorGender = scan.next();
                                    while (!newActorGender.equalsIgnoreCase("Female") && !newActorGender.equalsIgnoreCase("Male")){
                                        System.out.println("Alert: Invalid Gender Detected. Please Try Again:");
                                        newActorGender = scan.next();
                                    }
                                    System.out.println("What is their Celebrity Grade? [S/A/B/C/D]:");
                                    String strNewRanking = scan.next();
                                    while (!strNewRanking.equalsIgnoreCase("S") && !strNewRanking.equalsIgnoreCase("A") && !strNewRanking.equalsIgnoreCase("B") && !strNewRanking.equalsIgnoreCase("C") && !strNewRanking.equalsIgnoreCase("D")){
                                        System.out.println("Alert: Invalid Celebrity Grade Detected. Please Try Again:");
                                        strNewRanking = scan.next();
                                    }
                                    strNewRanking = strNewRanking.toUpperCase();
                                    char newRanking = strNewRanking.charAt(0);
                                    System.out.println("How much is their Salary going to be (in Millions):");
                                    double newSalary = scan.nextDouble();
                                    System.out.println("Assign a Contract for this Actor (Format: DD/MM/YYYY):");
                                    String newContract = scan.next();
                                    
                                    int dayVerify = Integer.parseInt(newContract.substring(0, 2));
                                    int monthVerify = Integer.parseInt(newContract.substring(3, 5));
                                    int yearVerify = Integer.parseInt(newContract.substring(6, 10));
                                    
                                    boolean dateVerified = false;
                                    int monthCheck[] = {2,4,7,9,11};
                                    while (!dateVerified && yearVerify < 2024){
                                        if (dayVerify < 1 || dayVerify > 31){
                                            dateVerified = false;
                                        } else {
                                            if (monthVerify < 1 || monthVerify > 12){
                                                dateVerified = false;
                                            } else {
                                                for (int j = 0; j < 5; j++){
                                                    if (dayVerify == 31 && monthVerify == monthCheck[j]){
                                                        dateVerified = false;
                                                        break;
                                                    } else {
                                                        dateVerified = true;
                                                    }
                                                }
                                            }
                                        }
                                        
                                        if (!dateVerified){
                                            System.out.println("Alert: Invalid Date Entered. Please Try Again:");
                                            newContract = scan.next();
                                        } else if (yearVerify < 2024) {
                                            System.out.println("Alert: Contract should be after 2024. Please Extend the Contract:");
                                            newContract = scan.next();
                                        }
                                    }
                                    Actor newActor = new Actor(newActorName, newActorGender, newActorAge, newRanking, newSalary, newContract);
                                    actorLL.insertAtFront(newActor);
                                    System.out.println("Actor Added Successfully!");
                                    newActor.actorDetails();
                                } else if (operation == 7){
                                    Actor actorStat = new Actor();
                                    actorStat.actorStatistics(actorLL);
                                } else if (operation < 1 && operation > 8) {
                                    System.out.println("The code is unidentified. Please try again:");
                                }
                        } while (operation != 8);
                    }
                    else if (section.equalsIgnoreCase("S")) // Account Settings
                    {
                        System.out.println("Change Password [P]");
                        System.out.println("Change Username [U]");
                        System.out.println("Remove Account  [R]");
                        System.out.println("Enter Key Here: ");
                        String input = scan.next();
                        
                        while (!input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("U") && !input.equalsIgnoreCase("R")){
                            System.out.println("Alert: These aren't Valid Codes. Try Again:");
                            input = scan.next();
                        }
                        
                        if (input.equalsIgnoreCase("P")) {
                            boolean newPasswordConfirm = false;
                            scan.nextLine();
                            String oldPassword, newPassword;
                        
                            do {
                                System.out.println("Enter Username:");
                                String username = scan.nextLine();
                                System.out.println("Enter Old Password:");
                                oldPassword = scan.nextLine();
                                System.out.println("Enter New Password:");
                                newPassword = scan.nextLine();
                        
                                boolean checkOldPassword = m.managementVerify(username, oldPassword);
                                while (!checkOldPassword) {
                                    System.out.println("Incorrect Password! Please Try Again.");
                                    System.out.println("Enter Username:");
                                    username = scan.nextLine();
                                    System.out.println("Enter Old Password:");
                                    oldPassword = scan.nextLine();
                                    System.out.println("Enter New Password:");
                                    newPassword = scan.nextLine();
                                    checkOldPassword = m.managementVerify(username, oldPassword);
                                }
                        
                                if (oldPassword.equals(newPassword)) {
                                    System.out.println("Alert: New Password is the same as Old Password. Please Add a New Password.");
                                } else if (newPassword.length() < 8) {
                                    System.out.println("Alert: Your Password is Too Short! Please Try Again.");
                                } else {
                                    newPasswordConfirm = true;
                                }
                            } while (!newPasswordConfirm);
                        
                            // Update password in file
                            try {
                                File tempFile = new File("tempAdminData.txt");
                                File inputFile = new File("adminData.txt");
                        
                                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
                        
                                String line;
                                while ((line = br.readLine()) != null) {
                                    StringTokenizer strToken = new StringTokenizer(line, ",");
                                    String tokenManagementID = strToken.nextToken();
                                    String tokenPassword = strToken.nextToken();
                        
                                    if (tokenPassword.equals(oldPassword)) {
                                        tokenPassword = newPassword;
                                    }
                                    bw.write(tokenManagementID + "," + tokenPassword);
                                    bw.newLine();
                                }
                        
                                br.close();
                                bw.close();
                                if (!inputFile.delete()) {
                                    System.out.println("Could not delete original file");
                                }
                                if (!tempFile.renameTo(inputFile)) {
                                    System.out.println("Could not rename temporary file");
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("Problem: " + e.getMessage());
                            } catch (IOException ioe) {
                                System.out.println("Problem: " + ioe.getMessage());
                            }
                            
                            System.out.println("Edited Password Successfully!");
                        } else if (input.equalsIgnoreCase("U")){
                            boolean usernameVerify = false;
                            scan.nextLine();
                            String oldUsername, newUsername, checkPassword;
                            do {
                                System.out.println("Enter Old Username:");
                                oldUsername = scan.nextLine();
                                System.out.println("Enter New Username:");
                                newUsername  = scan.nextLine();
                                System.out.println("Enter Password:");
                                checkPassword  = scan.nextLine();
                        
                                boolean checkOldPassword = m.managementVerify(oldUsername, checkPassword);
                                while (!checkOldPassword) {
                                    System.out.println("Incorrect Password! Please Try Again.");
                                    System.out.println("Enter Old Username:");
                                    oldUsername = scan.nextLine();
                                    System.out.println("Enter Password:");
                                    checkPassword = scan.nextLine();
                                    checkOldPassword = m.managementVerify(oldUsername, checkPassword);
                                }
                        
                                if (oldUsername.equals(newUsername)) {
                                    System.out.println("Alert: New Username is the same as Old Username. Please Add a New Username.");
                                } else {
                                    usernameVerify = true;
                                }
                            } while (!usernameVerify);
                            
                            // Update username in file
                            try {
                                File tempFile = new File("tempAdminData.txt");
                                File inputFile = new File("adminData.txt");
                                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
                        
                                String line;
                                while ((line = br.readLine()) != null) {
                                    StringTokenizer strToken = new StringTokenizer(line, ",");
                                    String tokenManagementID = strToken.nextToken();
                                    String tokenPassword = strToken.nextToken();
                        
                                    if (tokenManagementID.equalsIgnoreCase(oldUsername)) {
                                        tokenManagementID = newUsername;
                                    }
                                    bw.write(tokenManagementID + "," + tokenPassword);
                                    bw.newLine();
                                }
                        
                                br.close();
                                bw.close();
                                if (!inputFile.delete()) {
                                    System.out.println("Could not delete original file");
                                }
                                if (!tempFile.renameTo(inputFile)) {
                                    System.out.println("Could not rename temporary file");
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("Problem: " + e.getMessage());
                            } catch (IOException ioe) {
                                System.out.println("Problem: " + ioe.getMessage());
                            }
                            
                            System.out.println("Edited Username Successfully!");
                        } else if (input.equalsIgnoreCase("R")){
                                scan.nextLine();
                                boolean removalVerify;
                                String removeUsername, removePassword;
                            
                                do {
                                    System.out.println("Enter Username:");
                                    removeUsername = scan.nextLine();
                                    System.out.println("Enter Password:");
                                    removePassword = scan.nextLine();
                                    removalVerify = m.managementVerify(removeUsername, removePassword);
                                    if (!removalVerify) {
                                        System.out.println("Unable to find that account. Perhaps there may be a typo?");
                                    }
                                } while (!removalVerify);
                                System.out.println("Are you Sure? You can't Undo This Process. [Y/N]:");
                                String removalApprove = scan.next();
                                if (removalApprove.equalsIgnoreCase("N")){
                                    System.out.println("Account Removal Denied.");
                                } else if (!removalApprove.equalsIgnoreCase("Y")){
                                    System.out.println("Alert: Unable to read Code. Account Removal Denied Automatically.");
                                } else { 
                                    File inputFile = new File("adminData.txt");
                                    File tempFile = new File("tempAdminData.txt");
                                
                                    boolean userFound = false;
                                
                                    try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
                                        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                                        String line;
                                        while ((line = br.readLine()) != null) {
                                            StringTokenizer strToken = new StringTokenizer(line, ",");
                                            String tokenManagementID = strToken.nextToken();
                                            String tokenPassword = strToken.nextToken();
                                
                                            if (tokenManagementID.equalsIgnoreCase(removeUsername) && tokenPassword.equals(removePassword)) {
                                                userFound = true; // Skip writing this line
                                            } else {
                                                bw.write(line); // Write the entire line
                                                bw.newLine();
                                            }
                                        }
                                
                                        if (userFound) {
                                            System.out.println("Account successfully removed.");
                                            section = "L";
                                        }
                                    } catch (FileNotFoundException e) {
                                        System.out.println("File not found: " + e.getMessage());
                                    } catch (IOException ioe) {
                                        System.out.println("Error reading or writing file: " + ioe.getMessage());
                                    }
                                
                                    // Replace original file with temp file
                                    if (userFound) {
                                        if (inputFile.delete()) {
                                            if (!tempFile.renameTo(inputFile)) {
                                                System.out.println("Error renaming temp file to original file.");
                                            }
                                        } else {
                                            System.out.println("Error deleting original file.");
                                        }
                                    } else {
                                        tempFile.delete(); // Remove the temp file if no changes were made
                                    }
                                }
                            
                            }
                    }
                } while (!section.equalsIgnoreCase("L"));
                System.out.println("Logging out...");
                exit = m.accountLogin();
            } while (!exit);
        }
        
        try { // 3 Second Delay by using Thread.sleep() method
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("System Terminated Successfully!");
        
    }
}