package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static factories.FactoryConfig.*;

public class BidirectionalUserFactory {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_ID = "id";
    private static final String FIELD_USER_ROLE = "userRole";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_LOGIN_TYPE = "loginType";
    private static final String FIELD_LEVEL = "level";
    private static final String FIELD_HINTS_LEFT = "hintsLeft";
    private static final String FIELD_GOLD_LEFT = "goldLeft";
    private static final String FIELD_AVATAR_PATH = "avatarPath";

    /**
     * Gets the attributes of an user from de db service.
     * @param userName Username that identifies this user.
     * @return A new user instance.
     * @throws UnirestException
     */
    // TODO: Lacks security
    // overload may be added
    public static User newInstance(String userName) throws UnirestException {
        String url = String.format("http://%s:%s/%s/user/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, userName);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        User user = null;
        try {

            user = new User();

            user.setId(jsonObject.getLong(FIELD_ID));
            user.setName(jsonObject.getString(FIELD_NAME));
            user.setUserRole(jsonObject.getString(FIELD_USER_ROLE));
            user.setEmail(jsonObject.getString(FIELD_EMAIL));
            user.setLoginType(jsonObject.getString(FIELD_LOGIN_TYPE));
            user.setLevel(jsonObject.getInt(FIELD_LEVEL));
            user.setHintsLeft(jsonObject.getInt(FIELD_HINTS_LEFT));
            user.setGoldLeft(jsonObject.getInt(FIELD_GOLD_LEFT));
            user.setAvatarPath(jsonObject.getString(FIELD_AVATAR_PATH));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return user;
    }

    public static List<String> getAll() throws UnirestException{
        String url = String.format("http://%s:%s/%s/user/all",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);

        HttpResponse<String> httpResponse = Unirest.post(url)
                .header("Content-Type", "application/json").asString();

        Pattern listAsString = Pattern.compile("^\\[?([^\\[\\]]*)\\]?$");
        Matcher matcher = listAsString.matcher((String) httpResponse.getBody().replaceAll("[\"]", ""));

        if(matcher.matches()){
            String[] split = matcher.group(matcher.groupCount()).split("\\s*,\\s*");
            return new ArrayList<String> (Arrays.asList(split));
        }
        return Collections.emptyList();
    }

    public static boolean remove (User user) throws UnirestException{
        String url = String.format("http://%s:%s/%s/user/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, user.getName());
        HttpResponse<JsonNode> response = Unirest.delete(url).asJson();

        if(response.getStatus() == 200)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        User u = new User();
        u.setName("Sefu");

        try {
            remove(u);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    public static Boolean persist(User user) throws UnirestException {
        String url = String.format("http://%s:%s/%s/user/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, user.getId());
            jsonObject.put(FIELD_NAME, user.getName());
            jsonObject.put(FIELD_EMAIL, user.getEmail());
            jsonObject.put(FIELD_USER_ROLE, user.getUserRole());
            jsonObject.put(FIELD_LOGIN_TYPE, user.getLoginType());
            jsonObject.put(FIELD_LEVEL, user.getHintsLeft());
            jsonObject.put(FIELD_HINTS_LEFT, user.getHintsLeft());
            jsonObject.put(FIELD_GOLD_LEFT, user.getGoldLeft());
            jsonObject.put(FIELD_AVATAR_PATH, user.getAvatarPath());
        } catch (JSONException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }


        HttpResponse<JsonNode> httpResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .asJson();

        if (httpResponse.getStatus() == 201) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

}
