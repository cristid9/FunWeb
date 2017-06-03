package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.User;

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

    public static void main(String[] args) {
        try {

            User user = new User();
            user.setId(1l);
            user.setAvatarPath("/home");
            user.setGoldLeft(100);
            user.setHintsLeft(10);
            user.setLevel(0);
            user.setLoginType("custom");
            user.setEmail("bossdeboss@mafiotunr1.xxx");
            user.setUserRole("user");
            user.setName("Geovani");

            persist(user);
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
