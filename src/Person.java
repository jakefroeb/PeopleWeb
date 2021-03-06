/**
 * Created by jakefroeb on 9/22/16.
 */



public class Person {
    int id;
    String firstName;
    String lastName;
    String email;
    String country;
    String ipAddress;

    public Person(int id, String firstName, String lastName, String email, String country, String ipAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString(){
        return String.format("id: %s, Name: %s %s, Email: %s, Country: %s, IpAddress: %s"
                , id,firstName,lastName,email,country,ipAddress);
    }
}
