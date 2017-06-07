package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.PendingPasswordReset;
import pojos.Question;
import pojos.User;

import static factories.FactoryConfig.API_VERSION;
import static factories.FactoryConfig.REQUEST_ADDRESS;
import static factories.FactoryConfig.REQUEST_PORT;

public class BidirectionalPendingPasswordResetFactory {

    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_ID = "id";
    private static final String FIELD_TOKEN = "token";

    /**
     * Gets the attributes of an user from de db service.
     * @param token Username that identifies this user.
     * @return A new user instance.
     * @throws UnirestException
     */
    // TODO: Lacks security
    // overload may be added
    public static PendingPasswordReset newInstance(String token) throws UnirestException {
        String url = String.format("http://%s:%s/%s/pendingpasswordreset/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, token);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        PendingPasswordReset pendingPasswordReset = null;
        try {

            pendingPasswordReset = new PendingPasswordReset();
            pendingPasswordReset.setId(jsonObject.getLong(FIELD_ID));
            pendingPasswordReset.setToken(jsonObject.getString(FIELD_TOKEN));
            pendingPasswordReset.setUsername(jsonObject.getString(FIELD_USERNAME));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pendingPasswordReset;
    }

    public static void main(String[] args) {
        try {

            PendingPasswordReset pendingPasswordReset = new PendingPasswordReset();
            pendingPasswordReset.setUsername("Bar");
            pendingPasswordReset.setToken("hfddsfdssf");
            pendingPasswordReset.setId(1l);

            persist(pendingPasswordReset);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(PendingPasswordReset pendingPasswordReset) throws UnirestException {
        String url = String.format("http://%s:%s/%s/pendingpasswordreset/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_USERNAME, pendingPasswordReset.getUsername());
            jsonObject.put(FIELD_TOKEN, pendingPasswordReset.getToken());
            jsonObject.put(FIELD_ID, pendingPasswordReset.getId());

        } catch (JSONException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }

        HttpResponse<String> httpResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .asString();

        if (httpResponse.getStatus() == 200) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

}
