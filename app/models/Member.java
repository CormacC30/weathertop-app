package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)

    //ArrayList of Stations for this member
    public List<Station> stations = new ArrayList<Station>();

    /**
     * Member constructor
     * constructs member object from taking in firstname lastname, email and password parameters.
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     */
    public Member(String firstname, String lastname, String email, String password)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    /**
     * findByEmail methods finds the member object associated with an email
     * @param email
     * @return the member object.
     */
    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    /**
     * checkPassword boolean
     * takes in password String
     * @param password
     * @return true if password matches.
     */
    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }


}
