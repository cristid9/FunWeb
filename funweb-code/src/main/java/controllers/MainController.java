package controllers;

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

@Controller
public class MainController {

    private UserDAO dao = new UserDAO(new DBConnection());

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {


        return "register";
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
    public String checkPasswordStrengths(@RequestParam String password) {
        return "dummy";
    }
 }


