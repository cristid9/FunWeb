package controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import factories.BidirectionalLoginDataCustomFactory;
import factories.BidirectionalPendingPasswordResetFactory;
import factories.BidirectionalUserFactory;
import funWebMailer.FunWebMailer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojos.LoginDataCustom;
import pojos.PendingPasswordReset;
import pojos.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@SessionAttributes(value = "username")
public class MainController {


    User loggedInUser = null;

    @RequestMapping(value ="statistics", method = RequestMethod.GET)
    public String getStatistics(HttpServletRequest request) {

        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return "error";
        }

        return "statistics";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(HttpServletRequest request) {

        return "register";
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.GET)
    public String getChangePassword(HttpServletRequest request) {

        return "change_password";
    }


    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public ModelAndView postChangePassword(
            @RequestParam(name = "current_password") String current_passsword,
            @RequestParam(name = "new_password") String new_password,
            @RequestParam(name = "new_password2") String new_password2,
            HttpServletRequest request) {

        User user = null;
        try {
            user = BidirectionalUserFactory.newInstance((String) request.getSession().getAttribute("username"));
        } catch (UnirestException e) {
            e.printStackTrace();
        }


        String loginDataCustomPassword = null;
        try {
            loginDataCustomPassword = BidirectionalLoginDataCustomFactory.getPassword(user.getId());
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (!String.valueOf(current_passsword.hashCode()).equals(loginDataCustomPassword)) {
            return new ModelAndView("redirect:/error");
        }

        if (!new_password.equals(new_password2)) {
            return new ModelAndView("redirect:/error");
        }

        try {
            BidirectionalLoginDataCustomFactory.update(user.getId(), String.valueOf(new_password.hashCode()));
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return new ModelAndView("change_password_success");
    }

    @RequestMapping(value = "/recover_password", method = RequestMethod.GET)
    public String getRecoverPasswordPage() {
        return "recover_password";
    }

    @RequestMapping(value = "/add_hint", method = RequestMethod.GET)
    public String getHintGeneratorPage(HttpServletRequest request)
    {

        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return "error";
        }
        return "add_new_hint";
    }

    @RequestMapping(value = "/rankings", method = RequestMethod.GET)
    public ModelAndView getRankingsPage(HttpServletRequest request) {
        List<String> users = null;
        try {
           users = BidirectionalUserFactory.getAll();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView("ranking");

        modelAndView.addObject("rankings" , users);
        return modelAndView;
    }



    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfilePage(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return new ModelAndView("error");
        }

        User user = null;

        try {
            user = BidirectionalUserFactory.newInstance(username);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("username", user.getName());
        modelAndView.addObject("email", user.getEmail());
        modelAndView.addObject("role", user.getUserRole());
        modelAndView.addObject("hints_left", user.getHintsLeft());
        modelAndView.addObject("login_type", user.getLoginType());
        modelAndView.addObject("level", user.getLevel());

        return modelAndView;


    }

    @RequestMapping(
            value = "/recoverPassword",
            method = RequestMethod.POST
    )
    public ModelAndView postRecoverPassword(
            @RequestParam(name = "username") String username) {

        User user = null;

        try {
            user = BidirectionalUserFactory.newInstance(username);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        String recoverUrlToken = UUID.randomUUID().toString();
        // send mail with reset link for the password
        String recoverUrl = String.format("localhost:8089/reset_password/%s", recoverUrlToken);

        PendingPasswordReset pendingPasswordReset = new PendingPasswordReset();
        pendingPasswordReset.setId(0l); // Dummy
        pendingPasswordReset.setToken(recoverUrlToken);
        pendingPasswordReset.setUsername(user.getName());

        try {
            BidirectionalPendingPasswordResetFactory.persist(pendingPasswordReset);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        FunWebMailer.setResetPasswordLink(user.getName(), user.getEmail(), recoverUrl);

        return new ModelAndView("success_recover");
    }

    @RequestMapping(value = "reset_password/{token}", method = RequestMethod.GET)
    public ModelAndView getResetPassword(@PathVariable String token) {

        PendingPasswordReset pendingPasswordReset = null;

        try {
            pendingPasswordReset = BidirectionalPendingPasswordResetFactory.newInstance(token);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (pendingPasswordReset == null) {
            return null; // some error page
        }

        return new ModelAndView("reset_password");
    }

    @RequestMapping(value = "reset_password/{token}", method = RequestMethod.POST)
    public ModelAndView postResetPassword(
            @PathVariable String token,
            @RequestParam(name = "new_password1") String newPassword1,
            @RequestParam(name = "new_password2") String newPassword2) {

        if (!newPassword1.equals(newPassword2)) {
            return null;
        }

        PendingPasswordReset pendingPasswordReset = null;
        try {
            pendingPasswordReset = BidirectionalPendingPasswordResetFactory.newInstance(token);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        User user = null;

        try {
            user = BidirectionalUserFactory.newInstance(pendingPasswordReset.getUsername());
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        try {
            BidirectionalLoginDataCustomFactory.update(user.getId(), String.valueOf(newPassword1.hashCode()));
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return new ModelAndView("reset_password_success");
    }
    
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
            request.getSession().setAttribute("username", user.getName());

        }

        return new ModelAndView("redirect:/main_menu");
    }

    @RequestMapping(value="/pvp", method = RequestMethod.GET)
    public ModelAndView getPvpPage(HttpServletRequest request) {

        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return new ModelAndView("error");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", request.getSession().getAttribute("username"));

        return modelAndView;
    }

    @RequestMapping(value="/main_menu", method = RequestMethod.GET)
    public String getMainMenuPage(HttpServletRequest request){

        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return "error";
        }

        return "main_menu";
    }

    @RequestMapping(value="/chat_room", method = RequestMethod.GET)
    public String getChatRoomPage(HttpServletRequest request){

        String username = (String) request.getSession().getAttribute("username");
        if (request.getSession().getAttribute("username").equals("")) {
            return "error";
        }

        return "chat_room";
    }

    @RequestMapping(value="/add_question", method = RequestMethod.GET)
    public String getAddQuestionPage(HttpServletRequest request) {

        if (request.getSession().getAttribute("username").equals("")) {
            return "error";
        }

        return "add_question";
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
    public ModelAndView getAdminPannel(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return new ModelAndView("error");
        }

        return new ModelAndView("admin");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {

        request.getSession().removeAttribute("username");

        return new ModelAndView("register");
    }

    @ResponseBody
    @RequestMapping(value="/getUsersList" , method = RequestMethod.POST)
    public String getUsersList(){
           JSONArray jsonArray = new JSONArray();
        List<String> users = new ArrayList<String>();
        try {
             users = BidirectionalUserFactory.getAll();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

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

        User toBan = new User();
        toBan.setName(username);
        System.out.println(toBan.getName());
        try {
            BidirectionalUserFactory.remove(toBan);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
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
    public ModelAndView getArena(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return new ModelAndView("error");
        }

        return new ModelAndView("arena");
    }

    @RequestMapping(value = "/quick_chat", method = RequestMethod.GET)
    public ModelAndView quickChatPage(HttpServletRequest request, HttpServletResponse response) {

        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            return new ModelAndView("error");
        }

        ModelAndView modelAndView = new ModelAndView("quick_chat");
        modelAndView.addObject("username", username);

        return modelAndView;
    }

}


