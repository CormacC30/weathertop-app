package controllers;
import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    private static boolean loginFailed = false;

    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        renderArgs.put("loginFailed", loginFailed); // Pass the loginFailed flag to the view
        loginFailed = false; // Reset loginFailed flag
        render("login.html");
    }

    public static void updateUser() {
        render("updateuser.html");
    }

    public static void register(String firstname, String lastname, String email, String password) {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        redirect("/login");
    }

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

    public static void logout() {
        session.clear();
        redirect("/");
    }

    /**
     *
     * @return
     */
public static boolean loginFailed() {
    return loginFailed;
}

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
