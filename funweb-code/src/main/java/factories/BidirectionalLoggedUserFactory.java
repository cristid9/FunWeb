package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.LoggedUser;

import static factories.FactoryConfig.API_VERSION;
import static factories.FactoryConfig.REQUEST_ADDRESS;
import static factories.FactoryConfig.REQUEST_PORT;

/**
 * Created by Bogdanel on 06.06.2017.
 */
public class BidirectionalLoggedUserFactory {

    private static final String FIELD_ID = "id";
    private static final String FIELD_USER_NAME = "userName";

    /**
     * Gets the attributes of a loggedUser from de db service.
     * @param name identifies this loggedUser.
     * @return A new loggedUser instance.
     * @throws UnirestException
     */
    // TODO: Lacks security
    // overload may be added
    public static LoggedUser newInstance(String name) throws UnirestException {
        String url = String.format("http://%s:%s/%s/loggeduser/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, name);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        LoggedUser loggedUser = null;
        try {

            loggedUser = new LoggedUser();

            loggedUser.setId(jsonObject.getLong(FIELD_ID));
            loggedUser.setUserName(jsonObject.getString(FIELD_USER_NAME));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loggedUser;
    }

    public static void main(String[] args) {

    }

    public static Boolean persist( LoggedUser loggedUser) throws UnirestException {
        String url = String.format("http://%s:%s/%s/loggeduser/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, loggedUser.getId());
            jsonObject.put(FIELD_USER_NAME, loggedUser.getUserName());
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
