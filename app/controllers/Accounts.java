package controllers;
import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    //boolean variable for if login is unsuccessful - used in triggering if statement in the view which renders a "failedauth" partial.
    private static boolean loginFailed = false;

    /**
     * signup() renders the signup.html view
     */
    public static void signup() {
        render("signup.html");
    }

    /**
     * login() renders the login.html view
     */
    public static void login() {
        renderArgs.put("loginFailed", loginFailed); // Pass the loginFailed flag to the view
        loginFailed = false; // Reset loginFailed flag
        render("login.html");
    }

    /**
     * updateuser() renders the updateuser.html
     */
    public static void updateUser() {
        render("updateuser.html");
    }

    /**
     * register new user, creates new member object,
     * called by registering the signup form in signup.html
     * user inputs new firstname, lastname etc.
     *
     * @param firstname User's first name
     * @param lastname User's last name
     * @param email User's email
     * @param password User's password
     */
    public static void register(String firstname, String lastname, String email, String password) {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        redirect("/login");
    }

    /**
     * takes in the email and password on the login page.
     * If password and email match a member account it to start a session for that member
     * @param email
     * @param password
     */
    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member;
        member = Member.findByEmail(email);
        if((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            loginFailed = true;
            login();
        }
    }

    /**
     * logout() clears the session and redirects to the start page.
     */
    public static void logout() {
        session.clear();
        redirect("/");
    }

    /**
     * getLoggedInMember returns the member object for current session.
     * @return
     */

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    /**
     * UpdateAccount() Used to update a user's credentials. takes in four parameters and saves it
     * to loggedInMember in current session.
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     */
    public static void updateAccount(String firstname, String lastname, String email, String password){

        Member member = getLoggedInMember();

        Logger.info("Updating User Account Details for: " + member.firstname + member.lastname);
        member.firstname = firstname;
        member.lastname = lastname;
        member.email = email;
        member.password = password;
        member.save();
        redirect("/dashboard");
    }

}
