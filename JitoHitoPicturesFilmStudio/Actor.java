public class Actor extends Film
{
    //Data members
    private String actorName;
    private String gender;
    private int age;
    private char celebrityList;         /*'S': Global Icon; 'A': Highly Acclaimed;*/
    private double salary;       /*'B': Well-liked; 'C': Recognizable but less prominent 'D': Obscure, minimal public recognition*/
    private String contract;
    
    //Default Constructor
    public Actor(){}
    //Normal Constructor
    public Actor(String an, String g, int a, char cl, double s, String c)
    {
        this.actorName = an;
        this.gender = g;
        this.age = a;
        this.celebrityList = cl;
        this.salary = s;
        this.contract = c;
    }
    //Setter
    public void setActor(String an, String g, int a, char cl, double s, String c)
    {
        this.actorName = an;
        this.gender = g;
        this.age = a;
        this.celebrityList = cl;
        this.salary = s;
        this.contract = c;
    }
    public void setCelebrityList(char cl){
        this.celebrityList = cl;
    }
    public void setSalary(double s){
        this.salary = s;
    }
    public void setContract(String c){
        this.contract = c;
    }
    //Getter
    public String getActorName()
    {
        return actorName;
    }
    public String getGender()
    {
        return gender;
    }
    public int getAge()
    {
        return age;
    }
    public char getCelebrityList()
    {
        return celebrityList;
    }
    public double getSalary()
    {
        return salary;
    }
    public String getContract(){
        return contract;
    }
    
    // Method Definitions
    // to check Leap Years in Contract !
    public boolean leapYear(int y){
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }
    
    
    // To display Actor Details !
    public void actorDetails(){
        System.out.println("Actor Name: " + actorName + " --- Gender [Female/Male]: " + gender + " --- Age: " + age + " Years Old");
        System.out.println("Celebrity List [S/A/B/C/D]: " + celebrityList + " Grade --- Salary (In Millions): "+ salary + " Million Dollars\n");
    }
    
    // To display statistics of actors in JitoHito !
    public void actorStatistics(LinkedList a){
        LinkedList actor = a;
        Actor act = (Actor) actor.getFirst();
        int counter = 0;
        while (act != null){
            counter++;
            act = (Actor) actor.getNext();
        }
        System.out.println("[JitoHito Actor Statistics:]\nTotal Number of Actors in JitoHito Pictures: " + counter + " Actors");
        
        int countFemale = 0, countMale = 0;
        act = (Actor) actor.getFirst();
        while (act != null){
            if (act.getGender().equalsIgnoreCase("Female")){
                countFemale++;
            } else if (act.getGender().equalsIgnoreCase("Male")){
                countMale++;
            }
            act = (Actor) actor.getNext();
        }
        System.out.println("\nThere are " + countMale + " actors and " + countFemale + " actresses in JitHito Pictures.");
        int countS = 0, countA = 0, countB = 0, countC = 0, countD = 0;
        act = (Actor) actor.getFirst();
        while (act != null){
            if (act.getCelebrityList() == 'S'){
                countS++;
            } else if (act.getCelebrityList() == 'A'){
                countA++;
            } else if (act.getCelebrityList() == 'B'){
                countB++;
            } else if (act.getCelebrityList() == 'C'){
                countC++;
            } else if (act.getCelebrityList() == 'D'){
                countD++;
            }
            act = (Actor) actor.getNext();
        }
        System.out.println("\nThere are 5 Grade-Leveled Actors in JitoHito Pictures:\nS Grade: " + countS + " Actors\nA Grade: " + countA + " Actors\nB Grade: " + countB + " Actors\nC Grade: " + countC + " Actors\nD Grade: " + countD + " Actors");
        
        double salaryA = 0, salaryB = 0, salaryC = 0, salaryD = 0, salaryS = 0;
        act = (Actor) actor.getFirst();
        while (act != null){
            if (act.getCelebrityList() == 'S'){
                salaryS = salaryS + act.getSalary();
            } else if (act.getCelebrityList() == 'A'){
                salaryA = salaryA + act.getSalary();
            } else if (act.getCelebrityList() == 'B'){
                salaryB = salaryB + act.getSalary();
            } else if (act.getCelebrityList() == 'C'){
                salaryC = salaryC + act.getSalary();
            } else if (act.getCelebrityList() == 'D'){
                salaryD = salaryD + act.getSalary();
            }
            act = (Actor) actor.getNext();
        }
        
        System.out.println("\nSalary Divided by Actors in Grade-Level:\nS Grade: " + salaryS + " Million\nA Grade: " + salaryA + " Million\nB Grade: " + salaryB + " Million\nC Grade: " + salaryC + " Million\nD Grade: " + salaryD + " Million");
        System.out.println("\nTotal Salary From All Actors: " + (salaryS + salaryA + salaryB + salaryC + salaryD) + " Million");
        
    }
}