package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.Training;

import static factories.FactoryConfig.API_VERSION;
import static factories.FactoryConfig.REQUEST_ADDRESS;
import static factories.FactoryConfig.REQUEST_PORT;

public class BidirectionalTrainingFactory {
    private static final String FIELD_ID = "id";
    private static final String FIELD_USER_NAME = "userName";
    private static final String FIELD_SCORE = "score";

    public static Double getAvgScore(String userName) throws UnirestException{
        String url =  String.format("http://%s:%s/%s/training/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, userName);
        HttpResponse<String> response = Unirest.get(url).asString();

        return Double.parseDouble(response.getBody());
    }

    public static Boolean persist(Training training) throws UnirestException {
        String url = String.format("http://%s:%s/%s/training/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, training.getId());
            jsonObject.put(FIELD_USER_NAME, training.getUserName());
            jsonObject.put(FIELD_SCORE, training.getScore());
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
