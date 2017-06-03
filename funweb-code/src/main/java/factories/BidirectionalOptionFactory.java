package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.Option;

import static factories.FactoryConfig.*;

/**
 * Created by Marius on 6/3/2017.
 */
public class BidirectionalOptionFactory {

    private static final String FIELD_ID = "id";
    private static final String FIELD_ENUNCIATION = "enunciation";
    private static final String FIELD_CORRECTNESS = "correctness";
    private static final String FIELD_QUESTION_ID = "questionId";

    /**
     * Gets the attributes of an option from de db service.
     * @param optionId optionId that identifies a possible answer.
     * @return A new option instance.
     * @throws UnirestException
     */

    public static Option newInstance(String optionId) throws UnirestException {
        String url = String.format("http://%s:%s/%s/option/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, optionId);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        Option option = null;
        try {

            option = new Option();

            option.setId(jsonObject.getLong(FIELD_ID));
            option.setEnunciation(jsonObject.getString(FIELD_ENUNCIATION));
            option.setCorrectness(jsonObject.getBoolean(FIELD_CORRECTNESS));
            option.setQuestionId(jsonObject.getLong(FIELD_QUESTION_ID));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return option;
    }

    public static void main(String[] args) {
        try {
            newInstance("1");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(Option option) {
        return null;
    }
}

