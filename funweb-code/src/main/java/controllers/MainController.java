package controllers;

import daos.QuestionDAO;
import daos.UserDAO;
import db.DBConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import user.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class MainController {

    private DBConnection connection = new DBConnection();
    private UserDAO dao = new UserDAO(connection);
    private QuestionDAO qDao = new QuestionDAO(connection);
    User loggedInUser = null;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {

      // dao.createUser(null);
       
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password) {

        User user = dao.getUser(username);

        if (dao.getUser(username) != null && dao.checkIfUserMatchesPassword(username, password)) {

            Cookie loggedIn = new Cookie("username", username);
            response.addCookie(loggedIn);
            ModelAndView mainMenu = new ModelAndView();

            mainMenu.setViewName("main_menu");
            mainMenu.addObject("Username", username);
            mainMenu.addObject("Level", user.getLevel());
            mainMenu.addObject("Title", user.getLoginType());
            mainMenu.addObject("Gold", user.getGoldLeft());

            this.loggedInUser = user;

            return mainMenu;
        } else {
            return new ModelAndView("register") ;
        }
    }


    @RequestMapping(value="/main_menu", method = RequestMethod.GET)
    public String getMainMenuPage(){

        return "main_menu";
    }

    @ResponseBody
    @RequestMapping(value = "/checkUsernameAvailable", method = RequestMethod.POST)
    public String checkValidUsername(@RequestParam String username) {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        String suggestion = dao.checkIfValidUsername(username);
        if (suggestion != null) {
            try {
                json.put("status", "taken");
                json.put("suggestion", suggestion);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return json.toString();
        } else {
            try {
                json.put("status", "ok");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return json.toString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkPasswordStrength", method = RequestMethod.POST)
    public String checkPasswordStrength(@RequestParam String password) {
        JSONObject json = new JSONObject();

        int strength = dao.checkPasswordStrengthness(password);

        try {
            json.put("strength", strength);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    @RequestMapping(value = "/validateRegistration", method = RequestMethod.POST)
    public ModelAndView validateRegistration(@RequestParam(name = "email") String email,
                                       @RequestParam(name = "username") String username,
                                       @RequestParam(name = "password") String password) {

        if (email.equals("") || username.equals("") || password.equals("")) {
            return new ModelAndView("redirect:/register");
        }


        User registeredUser = new User(0,
        username,
        "user",
        email,
        "normal",
        1,
        2,
        300,
        "/home");

        // missing error check
        int id = dao.createUser(registeredUser);
        dao.registerPassword(Long.valueOf(id), password);


        return new ModelAndView("redirect:/");
    }

    @ResponseBody
    @RequestMapping(value = "/weakestChapter", method = RequestMethod.POST)
    public String getWeakestChapter() {
        JSONObject json = new JSONObject();

        try {
            json.put("weakestChapter", dao.weakestChapter((int) loggedInUser.getId()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    @RequestMapping(value = "/adminPannel", method = RequestMethod.GET)
    public ModelAndView getAdminPannel() {

        if (!loggedInUser.getUserRole().equals("admin"))  {
            return new ModelAndView("redirect:/main_menu");
        }
        return new ModelAndView("admin");
    }

    @ResponseBody
    @RequestMapping(value="/getUsersList" , method = RequestMethod.POST)
    public String getUsersList(){
        JSONArray jsonArray = new JSONArray();

        ArrayList<String> users = dao.getAllUsers();

        for (String user : users) {
            JSONObject jsonUser = new JSONObject();
            try {
                jsonUser.put("username", user);
                jsonArray.put(jsonUser);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray.toString();
    }

    @ResponseBody
    @RequestMapping(value="/banUser" , method = RequestMethod.POST)
    public String banUser(@RequestParam(name = "username") String username) {

        User targetedForBan = dao.getUser(username);
        dao.removeUser(targetedForBan);

        return "dummy";
    }
    
}


