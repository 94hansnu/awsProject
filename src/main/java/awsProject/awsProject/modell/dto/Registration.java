package awsProject.awsProject.modell.dto;

public class Registration {
    private String username;

    //Lösenord för registrering
    private String password;

    public Registration(){
        super();
    }

    public Registration(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    //  toString-metod för att skapa en strängrepresentation av RegistrationDTO
    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }
}
