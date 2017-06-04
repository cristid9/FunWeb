package controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import factories.BidirectionalLoginDataCustomFactory;
import factories.BidirectionalUserFactory;
import funWebMailer.FunWebMailer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pojos.LoginDataCustom;
import pojos.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
public class MainController {


    User loggedInUser = null;

    @RequestMapping(value ="statistics", method = RequestMethod.GET)
    public String getStatistics() {return "statistics";}

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "/recover_password", method = RequestMethod.GET)
    public String getRecoverPasswordPage() { return "recover_password";}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {


        User user = null;
        try {
            user = BidirectionalUserFactory.newInstance(username);
        } catch (UnirestException e) {
            e.printStackTrace();
        }


        String actualPassword = null; // That's not the way it supposed to be

        try {
            actualPassword = BidirectionalLoginDataCustomFactory.getPassword(user.getId());
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (String.valueOf(password.hashCode()).equals(actualPassword)) {
            request.getSession().setAttribute("loggedIn", Boolean.TRUE);

        }

        return new ModelAndView("redirect:/main_menu");
    }


    @RequestMapping(value="/main_menu", method = RequestMethod.GET)
    public String getMainMenuPage(){

        return "main_menu";
    }

    @RequestMapping(value="/chat_room", method = RequestMethod.GET)
    public String getChatRoomPage(){

        return "chat_room";
    }

    @ResponseBody
    @RequestMapping(value = "/checkUsernameAvailable", method = RequestMethod.POST)
    public String checkValidUsername(@RequestParam String username) {
//        JSONObject json = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        String suggestion = dao.checkIfValidUsername(username);
//        if (suggestion != null) {
//            try {
//                json.put("status", "taken");
//                json.put("suggestion", suggestion);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return json.toString();
//        } else {
//            try {
//                json.put("status", "ok");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return json.toString();
//        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/checkPasswordStrength", method = RequestMethod.POST)
    public String checkPasswordStrength(@RequestParam String password) {
//        JSONObject json = new JSONObject();
//
//        int strength = dao.checkPasswordStrengthness(password);
//
//        try {
//            json.put("strength", strength);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return json.toString();
        return null;
    }

    @RequestMapping(value = "/validateRegistration", method = RequestMethod.POST)
    public ModelAndView validateRegistration(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {

        if (email.equals("") || username.equals("") || password.equals("")) {
            ModelAndView modelAndView = new ModelAndView("redirect:/register");
            modelAndView.addObject("error", "Invalid credentials");
            return modelAndView;
        }

        FunWebMailer.sendTextRegisterNotification(username, email);

        try {
            if (BidirectionalUserFactory.newInstance(username) == null) {
                ModelAndView modelAndView = new ModelAndView("redirect:/register");
                modelAndView.addObject("error", "Username already taken");
                return modelAndView;
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }

        User user = new User();

        user.setName(username);
        user.setUserRole("user");
        user.setEmail(email);
        user.setLoginType("custom");
        user.setLevel(0);
        user.setHintsLeft(0);
        user.setGoldLeft(0);
        user.setAvatarPath("/home");
        user.setId(0l);

        try {
            BidirectionalUserFactory.persist(user);
        } catch (UnirestException e) {
            e.printStackTrace();

            ModelAndView modelAndView = new ModelAndView("redirect:/register");
            modelAndView.addObject("error", "Error storing the new user");

        }
        try {
            user = BidirectionalUserFactory.newInstance(username);
        } catch (UnirestException e) {
            ModelAndView modelAndView = new ModelAndView("redirect:/register");
            modelAndView.addObject("error", "Error accessing the db service");
            e.printStackTrace();
        }

        LoginDataCustom loginDataCustom = new LoginDataCustom();
        loginDataCustom.setPassword(String.valueOf(password.hashCode()));
        loginDataCustom.setId(0l);
        loginDataCustom.setUserId(user.getId());

        try {
            BidirectionalLoginDataCustomFactory.persist(loginDataCustom);
        } catch (UnirestException e) {
            ModelAndView modelAndView = new ModelAndView("redirect:/register");
            modelAndView.addObject("error", "Error accesing the db service");
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/");
    }

    @ResponseBody
    @RequestMapping(value = "/weakestChapter", method = RequestMethod.POST)
    public String getWeakestChapter() {
//        JSONObject json = new JSONObject();
//
//        try {
//            json.put("weakestChapter", dao.weakestChapter((int) loggedInUser.getId()));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return json.toString();

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/isRelevant", method = RequestMethod.POST)
    public String getRelevance(@RequestParam(name ="id") Long id){
//        JSONObject json = new JSONObject();
//
//        try{
//            json.put("relevance", qDao.isRelevant(id));
//
//            if (qDao.getError() != null) {
//                json.put("error", "yes");
//                json.put("errorMessage", qDao.getError());
//            } else {
//                json.put("error", "no");
//            }
//
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//
//        return json.toString();

        return null;
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public ModelAndView getAdminPannel() {
        if (!loggedInUser.getUserRole().equals("admin")) {
            return new ModelAndView("redirect:/main_menu");
        }
            return new ModelAndView("admin");

    }

    @ResponseBody
    @RequestMapping(value="/getUsersList" , method = RequestMethod.POST)
    public String getUsersList(){
//        JSONArray jsonArray = new JSONArray();
//
//        ArrayList<String> users = dao.getAllUsers();
//
//        for (String user : users) {
//            JSONObject jsonUser = new JSONObject();
//            try {
//                jsonUser.put("username", user);
//                jsonArray.put(jsonUser);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return jsonArray.toString();

        return null;
    }

    @ResponseBody
    @RequestMapping(value="/banUser" , method = RequestMethod.POST)
    public String banUser(@RequestParam(name = "username") String username) {

//        User targetedForBan = dao.getUser(username);
//        dao.removeUser(targetedForBan);
//
//        return "dummy";

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(@RequestParam(name = "newPassword") String newPassword) {
//        JSONObject json = new JSONObject();
//
//        dao.updateUserPassword(loggedInUser, newPassword);
//
//        try {
//            json.put("status", "success");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return json.toString();

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/checkAlreadyReceived", method = RequestMethod.POST)
    public String checkAlreadyReceived(@RequestParam(name = "id") String id) {

        JSONObject json = new JSONObject();

        try {
            json.put("receivedStatus", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/arena", method = RequestMethod.GET)
    public ModelAndView getArena() {
        return new ModelAndView("arena");
    }
}


