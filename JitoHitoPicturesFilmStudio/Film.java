public class Film extends Management
{
    //Data members
    private String title;
    private String director;
    private String producer;
    private String collaborator;
    private String language;
    private String genre;
    private double rating;
    private double revenue;
    private double boxOffice;
    private double budget;
    private double profit;
    private String actor1;
    private String actor2;
    private String actor3;
    
    //Default Constructor
    public Film(){}
    //Normal Constructor
    public Film(String t, String d, String p, String c, String l, String g, double r, double rv, double b, double bg, double pf, String a1, String a2, String a3)
    {
        this.title = t;
        this.director = d;
        this.producer = p;
        this.collaborator = c;
        this.language = l;
        this.genre = g;
        this.rating = r;
        this.revenue = rv;
        this.boxOffice = b;
        this.budget = bg;
        this.profit = pf;
        this.actor1 = a1;
        this.actor2 = a2;
        this.actor3 = a3;
    }
    //Setter
    public void setFilm(String t, String d, String p, String c, String l, String g, double r, double rv, double b, double bg, double pf, String a1, String a2, String a3)
    {
        this.title = t;
        this.director = d;
        this.producer = p;
        this.collaborator = c;
        this.language = l;
        this.genre = g;
        this.rating = r;
        this.revenue = rv;
        this.boxOffice = b;
        this.budget = bg;
        this.profit = pf;
        this.actor1 = a1;
        this.actor2 = a2;
        this.actor3 = a3;
    }
    public void setDirector(String d)
    {
        this.director = d;
    }
    public void setProducer(String p)
    {
        this.producer = p;
    }
    public void setCollaborator(String c)
    {
        this.collaborator = c;
    }
    public void setLanguage(String l)
    {
        this.language = l;
    }
    public void setRating(double r)
    {
        this.rating = r;
    }
    public void setRevenue(double rv)
    {
        this.revenue =rv;
    }
    public void setBoxOffice(double b)
    {
        this.boxOffice = b;
    }
    public void setProfit(double pf)
    {
        this.profit = pf;
    }
    //Getter
    public String getTitle()
    {
        return title;
    }
    public String getDirector()
    {
        return director;
    }
    public String getProducer()
    {
        return producer;
    }
    public String getCollaborator()
    {
        return collaborator;
    }
    public String getLanguage()
    {
        return language;
    }
    public String getGenre()
    {
        return genre;
    }
    public double getRating()
    {
        return rating;
    }
    public double getRevenue()
    {
        return revenue;
    }
    public double getBoxOffice()
    {
        return boxOffice;
    }
    public double getBudget()
    {
        return budget;
    }
    public double getProfit()
    {
        return profit;
    }
    public String getFirstActor()
    {
        return actor1;
    }
    public String getSecondActor()
    {
        return actor2;
    }
    public String getThirdActor()
    {
        return actor3;
    }
    //To display the details of all Films
    public void FilmDetails()
    {
        System.out.println("Film's Title: " + title + " --- Director: " + director + " --- Producer: " + producer + " --- Collaborator: " + collaborator);
        System.out.println("Language: "+ language + " --- Genre: " + genre + " --- Rating: " + rating + "/10");
        System.out.println("Revenue (In Millions): " + revenue + " Million Dollars --- Box Office (In Millions): " + boxOffice + " Million Dollars");
        System.out.println("Budget (In Millions): " + budget + " Million Dollars --- Profit (In Millions): " + profit + " Million Dollars");
        System.out.println("Main Cast: ");
        System.out.println("Main Protagonist: " + actor1 + " --- Deuteragonist: " + actor2 + " --- Antagonist: " + actor3 + "\n\n");
    }
}