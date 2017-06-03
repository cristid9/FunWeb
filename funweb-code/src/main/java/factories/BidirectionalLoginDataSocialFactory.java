package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.LoginDataSocial;

import static factories.FactoryConfig.*;

/**
 * Created by Marius on 6/3/2017.
 */
public class BidirectionalLoginDataSocialFactory {

    private static final String FIELD_ID = "id";
    private static final String FIELD_AUTH_HASH = "authHash";
    private static final String FIELD_USER_ID = "userId";

    /**
     * Gets the attributes of an user from de db service.
     * @param userId optionId that identifies an user.
     * @return A new logindatasocial instance.
     * @throws UnirestException
     */

    public static String getAuthHash(String userId) throws UnirestException {
        String url = String.format("http://%s:%s/%s/social/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, userId);

        HttpResponse<String> response = Unirest.get(url).asString();
        String authHash = response.getBody();


        return authHash;
    }

    public static void main(String[] args) {
        try {

            LoginDataSocial loginDataSocial = new LoginDataSocial(2l , "mariusautohash" , 1l);
            BidirectionalLoginDataSocialFactory.persist(loginDataSocial);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(LoginDataSocial loginDataSocial) throws UnirestException {
        String url = String.format("http://%s:%s/%s/social/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, loginDataSocial.getId());
            jsonObject.put(FIELD_AUTH_HASH, loginDataSocial.getAuthHash());
            jsonObject.put(FIELD_USER_ID , loginDataSocial.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }


        HttpResponse<String> httpResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .asString();

        if (httpResponse.getStatus() == 201) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}

