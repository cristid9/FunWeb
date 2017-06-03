package factories;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.GameCharacter;

import static factories.FactoryConfig.API_VERSION;
import static factories.FactoryConfig.REQUEST_ADDRESS;
import static factories.FactoryConfig.REQUEST_PORT;

public class BidirectionalGameCharacterFactory {

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PICTURE_PATH = "picturePath";
    private static final String FIELD_QUESTIONS_NUMBER = "questionsNumber";

    /**
     * Gets the attributes of a game character from de db service.
     * @param id id that identifies this game character.
     * @return A new game character instance.
     * @throws UnirestException
     */
    // TODO: Lacks security
    // overload may be added
    public static GameCharacter newInstance(Long id) throws UnirestException {
        String url = String.format("http://%s:%s/%s/character/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, id.toString());

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        GameCharacter gameCharacter = null;
        try {

            gameCharacter = new GameCharacter();

            gameCharacter.setId(jsonObject.getLong(FIELD_ID));
            gameCharacter.setName(jsonObject.getString(FIELD_NAME));
            gameCharacter.setPicturePath(jsonObject.getString(FIELD_PICTURE_PATH));
            gameCharacter.setQuestionsNumber(jsonObject.getLong(FIELD_QUESTIONS_NUMBER));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return gameCharacter;
    }

    public static void main(String[] args) {
        GameCharacter character = new GameCharacter(10L, "Bongo", "/home", 10L);
        try {
            persist(character);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(GameCharacter gameCharacter) throws UnirestException {
        String url = String.format("http://%s:%s/%s/character/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, gameCharacter.getId().toString());
            jsonObject.put(FIELD_NAME, gameCharacter.getName());
            jsonObject.put(FIELD_PICTURE_PATH, gameCharacter.getPicturePath());
            jsonObject.put(FIELD_QUESTIONS_NUMBER, gameCharacter.getQuestionsNumber());
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
