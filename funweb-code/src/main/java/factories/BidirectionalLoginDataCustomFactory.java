package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import pojos.GameCharacter;
import pojos.LoginDataCustom;

import java.util.HashMap;
import java.util.Map;

import static factories.FactoryConfig.API_VERSION;
import static factories.FactoryConfig.REQUEST_ADDRESS;
import static factories.FactoryConfig.REQUEST_PORT;

public class BidirectionalLoginDataCustomFactory {
    private static final String FIELD_ID = "id";
    private static final String FIELD_USER_ID = "userId";
    private static final String FIELD_PASSWORD = "password";

    /**
     * Gets the password of an user.
     * @param id id that identifies the user.
     * @return A string representing the password.
     * @throws UnirestException
     */
    // TODO: Lacks security
    // overload may be added
    public static String getPassword(Long id) throws UnirestException {
        String url = String.format("http://%s:%s/%s/custom/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, id.toString());

        HttpResponse<String> response = Unirest.get(url).asString();
        String password = response.getBody();

        return password;
    }

    public static void main(String[] args) {

        LoginDataCustom l = new LoginDataCustom(Long.valueOf(2), Long.valueOf(10), "test");

        try {
            persist(l);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(LoginDataCustom login) throws UnirestException{
        String url = String.format("http://%s:%s/%s/custom/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, login.getId());
            jsonObject.put(FIELD_USER_ID, login.getUserId());
            jsonObject.put("newPassword", login.getPassword());
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
