package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.Question;

import static factories.FactoryConfig.*;

/**
 * Created by Marius on 6/3/2017.
 */
public class BidirectionalQuestionFactory {

    private static final String FIELD_QUESTION_ID = "id";
    private static final String FIELD_ENUNCIATION = "enunciation";
    private static final String FIELD_REWARD = "reward";
    private static final String FIELD_CHAPTER_ID = "chapterId";
    private static final String FIELD_CHARACTERS_ID = "characterId";

    /**
     * Gets the attributes of a question from de db service.
     * @param questionId questionId that identifies this question.
     * @return A new question instance.
     * @throws UnirestException
     */

    public static Question newInstance(String questionId) throws UnirestException {
        String url = String.format("http://%s:%s/%s/question/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, questionId);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        Question question = null;
        try {

            question = new Question();

            question.setId(jsonObject.getLong(FIELD_QUESTION_ID));
            question.setEnunciation(jsonObject.getString(FIELD_ENUNCIATION));
            question.setReward(jsonObject.getLong(FIELD_REWARD));
            question.setChapterId(jsonObject.getLong(FIELD_CHAPTER_ID));
            question.setCharacterId(jsonObject.getLong(FIELD_CHARACTERS_ID));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return question;
    }

    public static void main(String[] args) {
        try {

            Question question = new Question(3l, "Vrei sa stii TW ?" , 10l , 1l , 3l);
            BidirectionalQuestionFactory.persist(question);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(Question question) throws UnirestException {
        String url = String.format("http://%s:%s/%s/question/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_QUESTION_ID, question.getId());
            jsonObject.put(FIELD_ENUNCIATION, question.getEnunciation());
            jsonObject.put(FIELD_REWARD, question.getReward());
            jsonObject.put(FIELD_CHAPTER_ID, question.getChapterId());
            jsonObject.put(FIELD_CHARACTERS_ID , question.getCharacterId());
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
