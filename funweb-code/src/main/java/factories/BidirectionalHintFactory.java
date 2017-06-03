package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.Hint;
import pojos.User;

import static factories.FactoryConfig.API_VERSION;
import static factories.FactoryConfig.REQUEST_ADDRESS;
import static factories.FactoryConfig.REQUEST_PORT;

/**
 * Created by cristi on 03.06.2017.
 */
public class BidirectionalHintFactory {


    private static final String FIELD_TEXT = "text";
    private static final String FIELD_ID = "id";
    private static final String FIELD_QUESTION_ID = "questionId";

    /**
     * Gets the attributes of an user from de db service.
     * @param userName Username that identifies this user.
     * @return A new user instance.
     * @throws UnirestException
     */
    // TODO: Lacks security
    // overload may be added
    public static Hint newInstance(Long id) throws UnirestException {
        String url = String.format("http://%s:%s/%s/hint/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, id);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        Hint hint = null;
        try {

            hint = new Hint();

            hint.setId(jsonObject.getLong(FIELD_ID));
            hint.setQuestionId(jsonObject.getLong(FIELD_QUESTION_ID));
            hint.setText(jsonObject.getString(FIELD_TEXT));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return hint;
    }

    public static void main(String[] args) {
        try {
            newInstance(1l);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(Hint hint) {
        return null;
    }
}
