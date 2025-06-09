import java.util.*;
import java.io.*;
public class filmMain
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Queue FilmQ = new Queue(); // Creating a Queue
    
        //Film: Declare Film's variable
        String title, director, producer, collaborator, language, genre, actor1, actor2, actor3;
        double revenue, rating, boxOffice, budget, profit;
        
        // Admin Variables:
        String managementID, password;
        // Adding Values into actorLL and FilmQ
        try {
            FileReader fr = new FileReader("FilmList.txt");
            BufferedReader br = new BufferedReader(fr);
            String data;
            while ((data = br.readLine()) != null)
            {
                StringTokenizer fromFilm = new StringTokenizer(data, ",");
                title = fromFilm.nextToken() ;
                director = fromFilm.nextToken();
                producer = fromFilm.nextToken();
                collaborator = fromFilm.nextToken();
                language = fromFilm.nextToken();
                genre = fromFilm.nextToken();
                rating = Double.parseDouble(fromFilm.nextToken());
                revenue = Double.parseDouble(fromFilm.nextToken());
                boxOffice = Double.parseDouble(fromFilm.nextToken());
                budget = Double.parseDouble(fromFilm.nextToken());
                profit = Double.parseDouble(fromFilm.nextToken());
                actor1 = fromFilm.nextToken();
                actor2 = fromFilm.nextToken();
                actor3 = fromFilm.nextToken();
                
                Film f = new Film(title, director, producer, collaborator, language, genre, rating, revenue, boxOffice, budget, profit, actor1, actor2, actor3);
                FilmQ.enqueue(f);
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
                    section = m.managementMenu_2(); // Get Value either A to view Actors, F to view Films, or L to Logout.
                    if (section.equalsIgnoreCase("F")) //Film Section [Queue]
                    {
                      Queue storage = new Queue();
                      int choice = 0;
                      do {
                       System.out.println("\n-------------[FILM MANAGEMENT MENU]-------------");
                       System.out.println("1. View All Films  [1]");
                       System.out.println("2. Search Films    [2]");
                       System.out.println("3. Edit Film       [3]");
                       System.out.println("4. Remove Film     [4]");
                       System.out.println("5. Sort Films      [5]");
                       System.out.println("6. Insert New Film [6]");
                       System.out.println("7. View Statistics [7]");
                       System.out.println("8. Exit            [8]");
                       System.out.println("Enter Code Here:");
                       choice = input.nextInt(); //Choose among the six management menus
                       if (choice == 1) //View information of all films
                       {
                           int i = 1;
                           while(!FilmQ.isEmpty())
                           {
                               Film f = (Film) FilmQ.dequeue();
                               storage.enqueue(f);
                               System.out.print(i + ".");
                               f.FilmDetails();
                               i++;
                           }
                           System.out.println("All Films Information has Been Displayed!");
                           System.out.println("Returning to Film Menu...");
                           // End of View All Actors and now time to store information!
                           while(!storage.isEmpty()) //Storing the films back to it's original storage()
                           {
                               Film f = (Film) storage.dequeue();
                               FilmQ.enqueue(f);
                           }
                       }
                       else if (choice == 2) //Search for Films
                       {
                           int search = 0;
                           boolean searchScreen = false;
                           do
                           {
                                       System.out.println("\n1. Search By Title                   [1]");
                                       System.out.println("2. Search By Film Crew or Cast       [2]");
                                       System.out.println("3. Search By Genre                   [3]");
                                       System.out.println("4. Search By Rating                  [4]");
                                       System.out.println("5. Return                            [5]");
                                       System.out.println("Enter Code Here:");
                                       search = input.nextInt();
                                       boolean isFound = false;
                                       int answer = 0;
                                       boolean retry = false;
                                       boolean remain = true;
                                       while (search < 1 && search > 5)
                                       {
                                           System.out.println("The search code you have given is unavailable. Please try again: ");
                                           search = input.nextInt();
                                       }
                                       if (search == 1) //Search for Title
                                       {
                                               while (remain == true)
                                               {
                                                   input.nextLine();
                                                   System.out.println("Type Film's Title Here:");
                                                   String searchTitle = input.nextLine();
                                                   int i = 0;
                                                   while(!FilmQ.isEmpty())
                                                   {
                                                      Film filmSearch = (Film) FilmQ.dequeue();
                                                      storage.enqueue(filmSearch);
                                                      if (filmSearch.getTitle().equalsIgnoreCase(searchTitle))
                                                      {
                                                         System.out.println("\nSearch Found at " + filmSearch.getTitle() + "!\nFilm Details:\n");
                                                         filmSearch.FilmDetails();
                                                         isFound = true;
                                                      }  
                                                   }
                                                   while(!storage.isEmpty())
                                                   {
                                                       Film f = (Film) storage.dequeue();
                                                       FilmQ.enqueue(f);
                                                   }
                                                   if (!isFound)
                                                   {
                                                       System.out.println(searchTitle + " can't be found in the system. Perhaps there may be a typo?");
                                                   }
                                                   System.out.println("Would you like to remain here? (1: Yes | 2: Return to Searchbar | 3: No): ");
                                                   answer = input.nextInt();
                                                   while ( answer < 1 || answer > 3)
                                                   {
                                                        System.out.println("The code is unidentified. Please try again: ");
                                                        answer = input.nextInt();
                                                   }
                                                   if (answer == 1)
                                                   {
                                                        remain = true;
                                                        isFound = false;
                                                   }
                                                   else if (answer == 2)
                                                   {
                                                       remain = false;
                                                       searchScreen = true;
                                                   }
                                                   else if (answer == 3)
                                                   {
                                                        remain = false;
                                                        searchScreen = false;
                                                   }
                                               }
                                       }else if (search == 2)//Search for films based on the name of producer, director, collaborator, actor or actress
                                       {
                                           while(remain == true) //looks for the name of producer, director, collaborator, actor or actress to look for films
                                           {
                                               input.nextLine();
                                               System.out.println("Type Member of Film Crew's Name Here:");
                                               String searchNameCrew = input.nextLine();
                                               int i = 0;
                                               while (!FilmQ.isEmpty())
                                               {
                                                   Film filmSearch = (Film) FilmQ.dequeue();
                                                   storage.enqueue(filmSearch);
                                                   if (filmSearch.getDirector().equalsIgnoreCase(searchNameCrew))
                                                   {
                                                       while (i != 1)
                                                       {
                                                           System.out.println("\nSearch Found at " + filmSearch.getDirector() + " under the category 'Director'.");
                                                           if (filmSearch.getProducer().equalsIgnoreCase(searchNameCrew))
                                                           {
                                                               System.out.println("Search Found at " + filmSearch.getDirector() + " under the category 'Producer'.");
                                                               System.out.println(filmSearch.getDirector() + " is a director and producer directing and producing films with information as below:\n");
                                                           }
                                                           else
                                                           {
                                                               System.out.println(filmSearch.getDirector() + " is a director directing films with information as below:\n");
                                                           }
                                                           i++;
                                                           isFound = true;
                                                       }
                                                       filmSearch.FilmDetails(); //Display films that are directed by the searched producer
                                                   }
                                                   else if (filmSearch.getProducer().equalsIgnoreCase(searchNameCrew))
                                                   {
                                                       while (i != 1)
                                                       {
                                                           System.out.println("\nSearch Found at " + filmSearch.getProducer() + " under the category 'Producer'.");
                                                           if (filmSearch.getDirector().equalsIgnoreCase(searchNameCrew))
                                                           {
                                                               System.out.println("Search Found at " + filmSearch.getProducer() + " under the category 'Director'.");
                                                               System.out.println(filmSearch.getProducer() + " is a producer and director producing and directing films with information as below:\n");
                                                           }
                                                           else
                                                           {
                                                               System.out.println(filmSearch.getProducer() + " is a producer producing films with information as below:\n");
                                                           }
                                                           i++;
                                                           isFound = true;
                                                       }
                                                       filmSearch.FilmDetails(); //Display films that are directed by the searched producer
                                                   }
                                                   else if (filmSearch.getCollaborator().equalsIgnoreCase(searchNameCrew))
                                                   {
                                                       while (i != 1)
                                                       {
                                                           if (searchNameCrew.equalsIgnoreCase("JitoHito Pictures"))
                                                           {
                                                              System.out.println("\nSearch Found at " + filmSearch.getCollaborator() + " under the category 'Collaborator'.");
                                                              System.out.println(filmSearch.getCollaborator() + " is a film studio in collaborate with the it's film studio's branches to create films as below: ");
                                                              i++;
                                                              isFound = true;
                                                           }
                                                           else
                                                           {
                                                             System.out.println("\nSearch Found at " + filmSearch.getCollaborator() + " under the category 'Collaborator'.");
                                                             System.out.println(filmSearch.getCollaborator() + " is a film studio collaborating with JitoHito Pictures to create films as below: ");
                                                             i++;
                                                             isFound = true;
                                                           }
                                                       }
                                                       filmSearch.FilmDetails(); //Display films that are directed by the searched producer, director or collaborator
                                                   }
                                                   else if (filmSearch.getFirstActor().equalsIgnoreCase(searchNameCrew))
                                                   {
                                                       while (i != 1)
                                                       {
                                                           System.out.println("\nSearch Found at " + filmSearch.getFirstActor() + " under the category 'Protagonist'.");
                                                           if (filmSearch.getSecondActor().equalsIgnoreCase(searchNameCrew))
                                                           {
                                                               System.out.println("Search Found at " + filmSearch.getFirstActor() + " under the category 'Deuteragonist'.");
                                                               System.out.println(filmSearch.getFirstActor() + " once acts as protagonist and deuteragonist in films with information as below:\n\n");
                                                           }
                                                           else if (filmSearch.getSecondActor().equalsIgnoreCase(searchNameCrew) && filmSearch.getThirdActor().equalsIgnoreCase(searchNameCrew))
                                                           {
                                                               System.out.println("Search Found at " + filmSearch.getFirstActor() + " under the category 'Deuteragonist'.");
                                                               System.out.println("Search Found at " + filmSearch.getFirstActor() + " under the category 'Antagonist'.");
                                                               System.out.println(filmSearch.getFirstActor() + " once acts as protagonist, deuteragonist and antagonist in films with information as below:\n\n");
                                                           }
                                                           else
                                                           {
                                                               System.out.println(filmSearch.getFirstActor() + " is the protagonist in films with information as below: \n\n");
                                                           }
                                                           i++;
                                                           isFound = true;
                                                       }
                                                       filmSearch.FilmDetails(); //Display films that are actors part of
                                                    }   
                                               }
                                               while(!storage.isEmpty())
                                               {
                                                       Film f = (Film) storage.dequeue();
                                                       FilmQ.enqueue(f);
                                               }
                                               if (!isFound)
                                               {
                                                       System.out.println(searchNameCrew + " can't be found in the system. Perhaps there may be a typo?");
                                               }
                                               System.out.println("Would you like to remain here? (1: Yes | 2: Return to Searchbar | 3: No): ");
                                               answer = input.nextInt();
                                               while ( answer < 1 || answer > 3)
                                               {
                                                   System.out.println("The code is unidentified. Please try again: ");
                                                   answer = input.nextInt();
                                               }
                                               if (answer == 1)
                                               {
                                                      remain = true;
                                                      isFound = false;
                                               }
                                               else if (answer == 2)
                                               {
                                                      remain = false;
                                                      searchScreen = true;
                                               }
                                               else if (answer == 3)
                                               {
                                                      remain = false;
                                                      searchScreen = false;
                                               }
                                           }
                                       }
                                       else if (search == 3) //Search for films by Genre
                                       {
                                           while (remain == true)
                                           {
                                               input.nextLine();
                                               System.out.println("Type Film's Genre Here:");
                                               String searchGenre = input.nextLine();
                                               int i = 0;
                                               while(!FilmQ.isEmpty())
                                               {
                                                    Film filmSearch = (Film) FilmQ.dequeue();
                                                    storage.enqueue(filmSearch);
                                                    if (filmSearch.getGenre().equalsIgnoreCase(searchGenre))
                                                    {
                                                        while(i == 0)
                                                       {
                                                           System.out.println("\nSearch Found at Genre: " + filmSearch.getGenre() + "!\nFilm Details:\n");;
                                                           i++;
                                                       }
                                                       filmSearch.FilmDetails();
                                                       isFound = true;
                                                    }
                                                   }
                                               while(!storage.isEmpty())
                                               {
                                                    Film f = (Film) storage.dequeue();
                                                    FilmQ.enqueue(f);
                                               }
                                               if (!isFound)
                                               {
                                                    System.out.println(searchGenre + " can't be found in the genres of the system. Perhaps there may be a typo?");
                                               }
                                               System.out.println("Would you like to remain here? (1: Yes | 2: Return to Searchbar | 3: No): ");
                                               answer = input.nextInt();
                                               while ( answer < 1 || answer > 3)
                                               {
                                                   System.out.println("The code is unidentified. Please try again: ");
                                                   answer = input.nextInt();
                                               }
                                               if (answer == 1)
                                               {
                                                    remain = true;
                                                    isFound = false;
                                               }
                                               else if (answer == 2)
                                               {
                                                    remain = false;
                                                    searchScreen = true;
                                               }
                                               else if (answer == 3)
                                               {
                                                    remain = false;
                                                    searchScreen = false;
                                               }
                                           }
                                       }
                                       else if (search == 4) //Search for film by rating
                                       {
                                           while (remain == true)
                                           {
                                               input.nextLine();
                                               System.out.println("Type Film's Rating Here From [Input 1] To [Input2]:");
                                               System.out.println("[Input 1]: ");
                                               double searchRatingLess = input.nextDouble();
                                               System.out.println("[Input 2]:");
                                               double searchRatingMore = input.nextDouble();
                                               int i = 0;
                                               while(!FilmQ.isEmpty())
                                               {
                                                   Film filmSearch = (Film) FilmQ.dequeue();
                                                   storage.enqueue(filmSearch);
                                                   
                                                   if (filmSearch.getRating() >= searchRatingLess && filmSearch.getRating() <= searchRatingMore)
                                                   {
                                                       while(i == 0)
                                                       {
                                                           System.out.println("Film Rating From " + searchRatingLess + " To " + searchRatingMore + "\n");
                                                           System.out.println("List of Film: \n\n");
                                                           i++;
                                                       }
                                                       filmSearch.FilmDetails();
                                                       isFound = true;
                                                   }
                                               }
                                               while(!storage.isEmpty())
                                               {
                                                    Film f = (Film) storage.dequeue();
                                                    FilmQ.enqueue(f);
                                               }
                                               if (!isFound)
                                               {
                                                    System.out.println("There's no fil founded between " + searchRatingLess + " to " + searchRatingMore + ". Please try again later.");
                                               }
                                               System.out.println("Would you like to remain here? (1: Yes | 2: Return to Searchbar | 3: No): ");
                                               answer = input.nextInt();
                                               while ( answer < 1 || answer > 3)
                                               {
                                                   System.out.println("The code is unidentified. Please try again: ");
                                                   answer = input.nextInt();
                                               }
                                               if (answer == 1)
                                               {
                                                    remain = true;
                                                    isFound = false;
                                               }
                                               else if (answer == 2)
                                               {
                                                    remain = false;
                                                    searchScreen = true;
                                               }
                                               else if (answer == 3)
                                               {
                                                    remain = false;
                                                    searchScreen = false;
                                               }
                                           }
                                       }
                                       else if (search == 5)
                                       {
                                           searchScreen = false;
                                       }
                                   }
                                   while (search != 5 && searchScreen == true); //End of Search
                               }
                               else if (choice == 3) //Edit Film's information
                               {
                                  boolean isExist = false;
                                  boolean remainLook = true;
                                  do
                                  {
                                      input.nextLine();
                                      System.out.println("Which Film would you like to Edit?\nPlease Type the Title of the Film:");
                                      String lookTitle = input.nextLine();
                                      //Look for Title through Queue
                                      while (!FilmQ.isEmpty())
                                      {
                                          Film filmEdit = (Film) FilmQ.dequeue();
                                          storage.enqueue(filmEdit);
                                          if (filmEdit.getTitle().equalsIgnoreCase(lookTitle)) //Look Title
                                          {
                                              isExist = true; //Book exists, found the title, time to look for which to edit
                                              char selectEdit = ' ';
                                              char convertChar; //convert String to Char
                                              char selectEditChar; //Char as Answer
                                              boolean remainEdit = true;
                                              do
                                              {
                                                  System.out.println("What would you like to edit from " + filmEdit.getTitle() + "?");
                                                  System.out.println("Edit Film's Director      [D]");
                                                  System.out.println("Edit Film's Producer      [P]");
                                                  System.out.println("Edit Film's Collaborator  [C]");
                                                  System.out.println("Edit Film's Language      [L]");
                                                  System.out.println("Edit Film's Rating        [T]");
                                                  System.out.println("Edit Film's Revenue       [V]");
                                                  System.out.println("Edit Film's Box Office    [B]");
                                                  System.out.println("Edit Film's Profit        [F]");
                                                  System.out.println("Return                    [R]");
                                                  selectEdit = input.next().charAt(0);
                                                  selectEditChar = Character.toUpperCase(selectEdit);
                                                  if (selectEditChar == 'D') 
                                                  { // Edit Director
                                                      input.nextLine();
                                                      System.out.println("Who would you like to replace " + filmEdit.getDirector() + " with?");
                                                      String changeDirector = input.nextLine();
                                                      while (changeDirector.isEmpty()) 
                                                      {
                                                          System.out.println("Alert: There's no input. Try Again:");
                                                          changeDirector = input.nextLine();
                                                      }
                                                      filmEdit.setDirector(changeDirector);
                                                      System.out.println("Film's Director Changed Successfully!");
                                                      filmEdit.FilmDetails();
                                                  } 
                                                  else if (selectEditChar == 'P') 
                                                  { // Edit Producer
                                                      input.nextLine();
                                                      System.out.println("Who would you like to replace " + filmEdit.getProducer() + " with?");
                                                      String changeProducer = input.nextLine();
                                                      while (changeProducer.isEmpty()) 
                                                      {
                                                          System.out.println("Alert: There's no input. Try Again:");
                                                          changeProducer = input.nextLine();
                                                      }
                                                      filmEdit.setProducer(changeProducer);
                                                      System.out.println("Film's Producer Changed Successfully!");
                                                      filmEdit.FilmDetails();
                                                  } 
                                                  else if (selectEditChar == 'C') 
                                                  { // Edit Collaborator
                                                       System.out.println("Who would you like to replace " + filmEdit.getCollaborator() + " with?");
                                                       String changeCollaborator = input.nextLine();
                                                       while (changeCollaborator.isEmpty()) 
                                                       {
                                                          System.out.println("Alert: There's no input. Try Again:");
                                                          changeCollaborator = input.nextLine();
                                                       }
                                                       filmEdit.setCollaborator(changeCollaborator);
                                                       System.out.println("Film's Collaborator Changed Successfully!");
                                                       filmEdit.FilmDetails();
                                                  }
                                                  else if (selectEditChar == 'L') 
                                                  { // Edit Language
                                                      System.out.println("Which language is now implemented on the film from " + filmEdit.getLanguage() + "?");
                                                      String changeLanguage = input.nextLine();
                                                      while (changeLanguage.isEmpty()) 
                                                      {
                                                          System.out.println("Alert: There's no input. Try Again:");
                                                          changeLanguage = input.nextLine();
                                                      }
                                                      filmEdit.setLanguage(changeLanguage);
                                                      System.out.println("Film's Collaborator Changed Successfully!");
                                                      filmEdit.FilmDetails();
                                                  }
                                                  else if (selectEditChar == 'T') 
                                                  {// Edit Rating
                                                      System.out.println("From 0 to 10, please input the change of rating for " + filmEdit.getTitle() + ":");
                                                      double changeRating = input.nextDouble();
                                                      while (changeRating == 0)
                                                      {
                                                         System.out.println("Alert: Invalid input. Try Again:");
                                                         changeRating = input.nextDouble();
                                                      }
                                                      filmEdit.setRating(changeRating);
                                                      System.out.println("Film's Rating Changed Successfully!");
                                                      filmEdit.FilmDetails();
                                                  }
                                                  else if (selectEditChar == 'V') 
                                                  {// Edit Revenue
                                                       System.out.println("What is the updated Revenue for " + filmEdit.getTitle() + ":");
                                                       double changeRevenue = input.nextDouble();
                                                       filmEdit.setRevenue(changeRevenue);
                                                       System.out.println("Film's Revenue Changed Successfully!");
                                                       filmEdit.FilmDetails();
                                                  }
                                                  else if (selectEditChar == 'B') 
                                                  {// Edit Box Office
                                                       System.out.println("What is the updated Box Office for " + filmEdit.getTitle() + ":");
                                                       double changeBoxOffice = input.nextDouble();
                                                       filmEdit.setBoxOffice(changeBoxOffice);
                                                       System.out.println("Film's Box Office Changed Successfully!");
                                                       filmEdit.FilmDetails();
                                                  }
                                                  else if (selectEditChar == 'F')
                                                  {// Edit Profit
                                                       System.out.println("What is the updated Profit for " + filmEdit.getTitle() + ":");
                                                       double changeProfit = input.nextDouble();
                                                       filmEdit.setProfit(changeProfit);
                                                       System.out.println("Film's Profit Changed Successfully!");
                                                       filmEdit.FilmDetails();
                                                  }
                                                  else if (selectEditChar == 'R')
                                                  {
                                                      remainEdit = false;
                                                  }
                                                  else
                                                  {
                                                      System.out.println("Error: Unable to Read Code! Please try again later.");
                                                  }
                                                  if (selectEditChar != 'R')
                                                  {
                                                      System.out.println("Would you like to make changes in Edit Menu? 1: Yes | 2: No");
                                                      int retry = input.nextInt();
                                                      while (retry > 2 || retry < 1)
                                                      {   
                                                         System.out.print("Unavailable choice. Please try again: ");
                                                         retry = input.nextInt();
                                                      }
                                                      if(retry == 1)  
                                                      {
                                                           remainEdit = true;
                                                      }
                                                      else if (retry == 2)
                                                      {   
                                                           remainEdit = false;
                                                      }
                                                  }
                                              }
                                              while (remainEdit == true);
                                          }
                                      }
                                      if (isExist == false)
                                      {
                                          System.out.println("The " + lookTitle + " can't be found. Please try again later.");
                                      }
                                      while(!storage.isEmpty())
                                      {
                                              Film searchStorage = (Film) storage.dequeue();
                                              FilmQ.enqueue(searchStorage);
                                      }
                                      System.out.println("Are you sure? Would you like to make changes or return to the Film Management Menu? (1: On second thought, I want to edit a film | 2: Film Management Menu)");
                                      int answer = input.nextInt();
                                      while (answer > 2 || answer < 1)
                                      {
                                        System.out.print("Unavailable choice. Please try again: ");
                                      }
                                      if(answer == 1)
                                      {
                                            remainLook = true;
                                            isExist = false;
                                      }
                                      else if (answer == 2)
                                      {
                                            remainLook = false;
                                            isExist = false;
                                      }
                                  }
                                  while (remainLook == true);
                               }
                               else if (choice == 4) //Remove Film information
                               {
                                   boolean RemoveFilm = false;
                                   do
                                   {
                                       input.nextLine();
                                       System.out.println("Which Film Would You Like to Remove? Please Input the Title of the Film You Would Like to Remove: ");
                                       String removeTitle = input.nextLine();
                                       boolean isFound = false;
                                       while(!FilmQ.isEmpty())
                                       {
                                            Film FilmRemove = (Film) FilmQ.dequeue();
                                            if (FilmRemove.getTitle().equalsIgnoreCase(removeTitle))
                                            {
                                                 isFound = true;
                                                 System.out.println("Film Found: ");
                                                 FilmRemove.FilmDetails();
                                                 int confirm = 0;
                                                 while (confirm == 0)
                                                 {
                                                    System.out.println("Are you confirm you would like to delete the film? (1: Yes | 2: No): ");
                                                    int confirmAnsw = input.nextInt();
                                                    while(confirmAnsw < 1 || confirmAnsw > 2)
                                                    {
                                                        System.out.print("The code is unidentified. Please try again: ");
                                                        confirmAnsw = input.nextInt();
                                                    }
                                                    if (confirmAnsw == 1)
                                                    {
                                                       System.out.println("Film Removed Sucessfully! The System will send you back to the Removal Searchbar.");
                                                       confirm++;
                                                       RemoveFilm = true;
                                                    }
                                                    else if (confirmAnsw == 2)
                                                    {
                                                        System.out.println("Noted. The system will send you back to the Removal Searchbar.");
                                                        confirm++;
                                                        RemoveFilm = true;
                                                        storage.enqueue(FilmRemove);
                                                    }
                                                 }
                                            }
                                            else if (!FilmRemove.getTitle().equalsIgnoreCase(removeTitle))
                                            {
                                                 storage.enqueue(FilmRemove);
                                            }
                                       }
                                       while(!storage.isEmpty())
                                       {
                                               Film store = (Film) storage.dequeue();
                                               FilmQ.enqueue(store);
                                       }
                                       if (!isFound)
                                       {
                                               System.out.println(removeTitle + " can't be found in the system for deletion. Perhaps there may be a typo?");
                                       }
                                       System.out.println("Would you like to perform deletion? (1: Yes | 2: No): ");
                                       int answer = input.nextInt();
                                       while (answer < 1 || answer > 2)                  
                                       {
                                             System.out.println("The code is unidentified. Please try again: ");
                                             answer = input.nextInt();
                                       }
                                       if (answer == 1)
                                       {
                                              RemoveFilm = true;
                                       }
                                       else if (answer == 2)
                                       {
                                              System.out.println("Noted. Sending you back to the Film Management Menu...");
                                              RemoveFilm = false;
                                       }
                                  }
                                  while (RemoveFilm == true);
                               }
                               else if (choice == 5) //Sorting Film From Ascending to Descending
                               {
                                   String sortKey = "N";
                                   do {
                                        System.out.println("Sort Ascendingly (by Title)      [A]");
                                        System.out.println("Sort Decendingly (by Title)      [B]");
                                        System.out.println("Sort Ascendingly (by Box Office) [C]");
                                        System.out.println("Sort Decendingly (by Box Office) [D]");
                                        System.out.println("Return to Film Menu              [R]");
                                        System.out.println("Enter Code Here:");
                                        sortKey = input.next();
                                        while (!sortKey.equalsIgnoreCase("R") && !sortKey.equalsIgnoreCase("A") && !sortKey.equalsIgnoreCase("B") && !sortKey.equalsIgnoreCase("C") && !sortKey.equalsIgnoreCase("D")){
                                            System.out.println("Error: Unable to Read Code! Try Again:");
                                            sortKey = input.next();
                                        }
                                        
                                        LinkedList sortedList = new LinkedList(); // Temporary sorted list
                                        Film current = (Film) FilmQ.getFirst(); // Start with the first element
                                        if (sortKey.equalsIgnoreCase("A")) { // Sort Ascendingly
                                            while (current != null) {
                                                Film currentFilm = (Film) current;
                                                    // Insert `currentActor` into its correct position in `sortedList`
                                                if (sortedList.isEmpty()) {
                                                        sortedList.insertAtFront(currentFilm); // First element, add to the front
                                                } else {
                                                        LinkedList tempSorted = new LinkedList();
                                                        boolean inserted = false;
                                                        Film temp = (Film) sortedList.getFirst();
                                                        while (temp != null) {
                                                            Film tempFilm = (Film) temp;
                                                            if (!inserted && currentFilm.getTitle().compareTo(tempFilm.getTitle()) < 0) {
                                                                tempSorted.insertAtBack(currentFilm); // Insert before the current tempActor
                                                                inserted = true;
                                                            }
                                                            tempSorted.insertAtBack(tempFilm); // Add the tempActor to the new list
                                                            temp = (Film) sortedList.getNext();
                                                        }
                                                        // If not yet inserted, add to the back
                                                        if (!inserted) {
                                                            tempSorted.insertAtBack(currentFilm);
                                                        }
                                                        // Replace the sortedList with the newly constructed tempSorted
                                                        sortedList = tempSorted;
                                                }
                                                current = (Film) FilmQ.getNext(); // Move to the next element in original list
                                            }
                                            
                                            // Print the sorted list
                                            Film sortedActor = (Film) sortedList.getFirst();
                                            while (sortedActor != null) {
                                                    sortedActor.FilmDetails(); // Print actor details
                                                    sortedActor = (Film) sortedList.getNext();
                                             }
                                        } else if (sortKey.equalsIgnoreCase("B")){
                                            while (current != null) {
                                                    Film currentFilm = (Film) current;
                                                    // Insert `currentActor` into its correct position in `sortedList`
                                                    if (sortedList.isEmpty()) {
                                                        sortedList.insertAtFront(currentFilm); // First element, add to the front
                                                    } else {
                                                        LinkedList tempSorted = new LinkedList();
                                                        boolean inserted = false;
                                                        Film temp = (Film) sortedList.getFirst();
                                                        while (temp != null) {
                                                            Film tempFilm = (Film) temp;
                                                            if (!inserted && currentFilm.getTitle().compareTo(tempFilm.getTitle()) > 0) {
                                                                tempSorted.insertAtBack(currentFilm); // Insert before the current tempActor
                                                                inserted = true;
                                                            }
                                                            tempSorted.insertAtBack(tempFilm); // Add the tempActor to the new list
                                                            temp = (Film) sortedList.getNext();
                                                        }
                                                        // If not yet inserted, add to the back
                                                        if (!inserted) {
                                                            tempSorted.insertAtBack(currentFilm);
                                                        }
                                                        // Replace the sortedList with the newly constructed tempSorted
                                                        sortedList = tempSorted;
                                                    }
                                                    current = (Film) FilmQ.getNext(); // Move to the next element in original list
                                                }
                                            
                                            // Print the sorted list
                                            Film sortedActor = (Film) sortedList.getFirst();
                                            while (sortedActor != null) {
                                                    sortedActor.FilmDetails(); // Print actor details
                                                    sortedActor = (Film) sortedList.getNext();
                                            }
                                        } else if (sortKey.equalsIgnoreCase("C")){
                                            while (current != null) {
                                                    Film currentFilm = (Film) current;
                                                    // Insert `currentActor` into its correct position in `sortedList`
                                                    if (sortedList.isEmpty()) {
                                                        sortedList.insertAtFront(currentFilm); // First element, add to the front
                                                    } else {
                                                        LinkedList tempSorted = new LinkedList();
                                                        boolean inserted = false;
                                                        Film temp = (Film) sortedList.getFirst();
                                                        while (temp != null) {
                                                            Film tempFilm = (Film) temp;
                                                            if (!inserted && currentFilm.getBoxOffice() < tempFilm.getBoxOffice()) {
                                                                tempSorted.insertAtBack(currentFilm); // Insert before the current tempActor
                                                                inserted = true;
                                                            }
                                                            tempSorted.insertAtBack(tempFilm); // Add the tempActor to the new list
                                                            temp = (Film) sortedList.getNext();
                                                        }
                                                        // If not yet inserted, add to the back
                                                        if (!inserted) {
                                                            tempSorted.insertAtBack(currentFilm);
                                                        }
                                                        // Replace the sortedList with the newly constructed tempSorted
                                                        sortedList = tempSorted;
                                                    }
                                                    current = (Film) FilmQ.getNext(); // Move to the next element in original list
                                                }
                                            
                                            // Print the sorted list
                                            Film sortedActor = (Film) sortedList.getFirst();
                                            while (sortedActor != null) {
                                                sortedActor.FilmDetails(); // Print actor details
                                                sortedActor = (Film) sortedList.getNext();
                                            }
                                        } else if (sortKey.equalsIgnoreCase("D")){
                                            while (current != null) {
                                                    Film currentFilm = (Film) current;
                                                    // Insert `currentActor` into its correct position in `sortedList`
                                                    if (sortedList.isEmpty()) {
                                                        sortedList.insertAtFront(currentFilm); // First element, add to the front
                                                    } else {
                                                        LinkedList tempSorted = new LinkedList();
                                                        boolean inserted = false;
                                                        Film temp = (Film) sortedList.getFirst();
                                                        while (temp != null) {
                                                            Film tempFilm = (Film) temp;
                                                            if (!inserted && currentFilm.getBoxOffice() > tempFilm.getBoxOffice()) {
                                                                tempSorted.insertAtBack(currentFilm); // Insert before the current tempActor
                                                                inserted = true;
                                                            }
                                                            tempSorted.insertAtBack(tempFilm); // Add the tempActor to the new list
                                                            temp = (Film) sortedList.getNext();
                                                        }
                                                        // If not yet inserted, add to the back
                                                        if (!inserted) {
                                                            tempSorted.insertAtBack(currentFilm);
                                                        }
                                                        // Replace the sortedList with the newly constructed tempSorted
                                                        sortedList = tempSorted;
                                                    }
                                                    current = (Film) FilmQ.getNext(); // Move to the next element in original list
                                                }
                                            
                                            // Print the sorted list
                                            Film sortedActor = (Film) sortedList.getFirst();
                                            while (sortedActor != null) {
                                                sortedActor.FilmDetails(); // Print actor details
                                                sortedActor = (Film) sortedList.getNext();
                                            }
                                        }
                                   } while (!sortKey.equalsIgnoreCase("R"));
                               } else if (choice == 6) {
                                       boolean addAgain = false;
                                       do
                                       {
                                           Queue displayFilm = new Queue();
                                           for(int i = 0; i < 1; i++)
                                           {
                                               input.nextLine();
                                               System.out.println("Title of the Film: ");
                                               title = input.nextLine();
                                               System.out.println("Name of the Director: ");
                                               director = input.nextLine();
                                               System.out.println("Name of the Producer: ");
                                               producer = input.nextLine();
                                               System.out.println("Is there any involvement of a collaborator? (1: Yes || 2: No): ");
                                               int answer = input.nextInt();
                                               while (answer < 1 || answer > 2)
                                               {
                                                   System.out.println("The code is unidentified. Please try again: ");
                                                   answer = input.nextInt();
                                               }
                                               if (answer == 1)
                                               {
                                                    input.nextLine();
                                                    System.out.println("Name of the Collaborator: ");
                                                    collaborator = input.nextLine();
                                               }
                                               else
                                               {
                                                    collaborator = null;
                                                    input.nextLine();
                                               }
                                               System.out.println("Language of the Film: ");
                                               language = input.nextLine();
                                               System.out.println("Genre of the Film: ");
                                               genre = input.nextLine();
                                               System.out.println("Rating of the Film: [input / 10] ");
                                               rating = input.nextDouble();
                                               while(rating < 0 || rating > 10)
                                               {
                                                   System.out.println("The rating should be from 0 to 10. Please try again: ");
                                                   rating = input.nextDouble();
                                               }
                                               System.out.println("Revenue of the Film (In millions): " );
                                               revenue = input.nextDouble();
                                               while(revenue < 0)
                                               {
                                                   System.out.println("Error. Negative value is detected. Please try again: ");
                                                   revenue = input.nextDouble();
                                               }
                                               System.out.println("Box Office of the Film (In millions): " );
                                               boxOffice = input.nextDouble();
                                               while(boxOffice < 0)
                                               {
                                                    System.out.println("Error. Negative value is detected. Please try again: ");
                                                    boxOffice = input.nextDouble();
                                               }
                                               System.out.println("Budget of the Film (In millions): " );
                                               budget = input.nextDouble();
                                               while(budget < 0)
                                               {
                                                    System.out.println("Error. Negative value is detected. Please try again: ");
                                                    budget = input.nextDouble();
                                               }
                                               System.out.println("Profit of the Film (In millions): " );
                                               profit = input.nextDouble();
                                               while(profit < 0)
                                               {
                                                    System.out.println("Error. Negative value is detected. Please try again: ");
                                                    profit = input.nextDouble();
                                               }
                                               input.nextLine();
                                               System.out.println("Name of Main Protagonist of the Film: ");
                                               String mainPro = input.nextLine();
                                               System.out.println("Name of Deuteragonist of the Film: ");
                                               String deutera = input.nextLine();
                                               System.out.println("Name of the Antagonist of the Film: ");
                                               String antagonist = input.nextLine();
                                               
                                               Film addFilm = new Film(title, director, producer, collaborator, language, genre, rating, revenue, boxOffice, budget, profit, mainPro, deutera, antagonist);
                                               addFilm.FilmDetails();
                                               displayFilm.enqueue(addFilm);
                                               FilmQ.enqueue(addFilm);
                                               System.out.println("Would you like to add another film? (1: Yes | 2: No): ");
                                               int isAdd = input.nextInt();
                                               while (isAdd < 1 || isAdd > 2)
                                               {
                                                   System.out.println("The code is unidentified. Please try again: ");
                                                   isAdd = input.nextInt();
                                               }
                                               if (isAdd == 1)
                                               {
                                                   addAgain = true;
                                               }
                                               else
                                               {
                                                   System.out.println("Noted. Sending you back to the Film Management Menu.");
                                                   addAgain = false;
                                               }
                                           }
                                       }
                                       while (addAgain == true);

                               } else if (choice == 7){
                                  int totalFilms = 0;
                                  double totalNetProfit = 0.0;
                                  double totalGrossRevenue = 0.0;
                                  double totalBudget = 0.0;
                                  int totalCollab = 0;
                                  double ROI = 0.0;
                                  double profitMargin = 0.0;
                                  double NetWorth = 0.0;
                                  Queue collabQ = new Queue();
                                  while(!FilmQ.isEmpty())
                                  {
                                      Film f = (Film) FilmQ.dequeue();
                                      storage.enqueue(f);
                                      totalFilms ++;
                                      totalNetProfit += f.getProfit();
                                      totalBudget += f.getBudget();
                                      totalGrossRevenue += f.getRevenue();
                                      if (f.getCollaborator().equalsIgnoreCase("JitoHito Pictures"))
                                      {
                                          totalCollab++;
                                          collabQ.enqueue(f);
                                      }
                                  }
                                  while(!storage.isEmpty())
                                  {
                                      Film store = (Film) storage.dequeue();
                                      FilmQ.enqueue(store);
                                  }
                                  ROI = totalNetProfit / totalBudget;
                                  profitMargin = totalNetProfit / totalGrossRevenue;
                                  System.out.println("[JitoHito Actor Statistics:]\n\nThere are " + totalFilms + " films produced by JitoHito Pictures.");
                                  System.out.println("\nJitoHito Pictures has collaborated with several worldwide famous studios, producing total of " + totalCollab + " films");
                                  System.out.println("Including:");
                                  int i = 1;
                                  while (!collabQ.isEmpty()){
                                      Film f = (Film) collabQ.dequeue();
                                      System.out.print(i + ". ");
                                      f.FilmDetails();
                                      i++;
                                  }
                                  System.out.println("JitoHito Picture's Total Net Profit: " + String.format("%.2f", totalNetProfit) + " Million"); 
                                  System.out.println("JitoHito Picture's Total Gross Revenue: " + String.format("%.2f", totalGrossRevenue) + " Million");
                                  System.out.println("JitoHito Picture's Return of Investment: " + String.format("%.2f", ROI) + " Million");
                                  System.out.println("JitoHito Picture's Profit Margin: " +  String.format("%.2f", profitMargin) + " Million");
                               } else if (choice > 8 && choice < 1) {
                                   System.out.println("The code is unidentified. Please try again: ");
                               }
                       }while (choice != 8);
                    } else if (section.equalsIgnoreCase("S")) // Account Settings
                    {
                        System.out.println("Change Password [P]");
                        System.out.println("Change Username [U]");
                        System.out.println("Remove Account  [R]");
                        System.out.println("Enter Key Here: ");
                        String answer = input.next();
                        
                        while (!answer.equalsIgnoreCase("P") && !answer.equalsIgnoreCase("U") && !answer.equalsIgnoreCase("R")){
                            System.out.println("Alert: These aren't Valid Codes. Try Again:");
                            answer = input.next();
                        }
                        
                        if (answer.equalsIgnoreCase("P")) {
                            boolean newPasswordConfirm = false;
                            input.nextLine();
                            String oldPassword, newPassword;
                        
                            do {
                                System.out.println("Enter Username:");
                                String username = input.nextLine();
                                System.out.println("Enter Old Password:");
                                oldPassword = input.nextLine();
                                System.out.println("Enter New Password:");
                                newPassword = input.nextLine();
                        
                                boolean checkOldPassword = m.managementVerify(username, oldPassword);
                                while (!checkOldPassword) {
                                    System.out.println("Incorrect Password! Please Try Again.");
                                    System.out.println("Enter Username:");
                                    username = input.nextLine();
                                    System.out.println("Enter Old Password:");
                                    oldPassword = input.nextLine();
                                    System.out.println("Enter New Password:");
                                    newPassword = input.nextLine();
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
                        } else if (answer.equalsIgnoreCase("U")){
                            boolean usernameVerify = false;
                            input.nextLine();
                            String oldUsername, newUsername, checkPassword;
                            do {
                                System.out.println("Enter Old Username:");
                                oldUsername = input.nextLine();
                                System.out.println("Enter New Username:");
                                newUsername  = input.nextLine();
                                System.out.println("Enter Password:");
                                checkPassword  = input.nextLine();
                        
                                boolean checkOldPassword = m.managementVerify(oldUsername, checkPassword);
                                while (!checkOldPassword) {
                                    System.out.println("Incorrect Password! Please Try Again.");
                                    System.out.println("Enter Old Username:");
                                    oldUsername = input.nextLine();
                                    System.out.println("Enter Password:");
                                    checkPassword = input.nextLine();
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
                        } else if (answer.equalsIgnoreCase("R")){
                                input.nextLine();
                                boolean removalVerify;
                                String removeUsername, removePassword;
                            
                                do {
                                    System.out.println("Enter Username:");
                                    removeUsername = input.nextLine();
                                    System.out.println("Enter Password:");
                                    removePassword = input.nextLine();
                                    removalVerify = m.managementVerify(removeUsername, removePassword);
                                    if (!removalVerify) {
                                        System.out.println("Unable to find that account. Perhaps there may be a typo?");
                                    }
                                } while (!removalVerify);
                                System.out.println("Are you Sure? You can't Undo This Process. [Y/N]:");
                                String removalApprove = input.next();
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
     
