package controllers;

import daos.UserDAO;
import db.DBConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    UserDAO dao = new UserDAO(new DBConnection());

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {

        dao.createUser(null);

        return "register";
    }


    @ResponseBody
    @RequestMapping(value = "/checkUsernameAvailable", method = RequestMethod.POST)
    public String checkValidUsername(@RequestParam String username) {



        return "dummy";
    }

    @ResponseBody
    @RequestMapping(value = "/checkPasswordStrength", method = RequestMethod.POST)
    public String checkPasswordStrengths(@RequestParam String password) {
        return "dummy";
    }
 }


