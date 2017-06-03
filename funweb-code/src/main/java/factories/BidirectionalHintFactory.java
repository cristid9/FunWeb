package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
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
     * Gets the attributes of an hint from de db service.
     * @param id identifies this hint.
     * @return A new hint instance.
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
            Hint hint = new Hint(1l, 1l, "am valuta");
            BidirectionalHintFactory.persist(hint);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(Hint hint) throws UnirestException {
        String url = String.format("http://%s:%s/%s/hint/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, hint.getId());
            jsonObject.put(FIELD_QUESTION_ID, hint.getQuestionId());
            jsonObject.put(FIELD_TEXT, hint.getText());
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
