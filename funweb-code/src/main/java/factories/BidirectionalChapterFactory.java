package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.Chapter;

import static factories.FactoryConfig.API_VERSION;
import static factories.FactoryConfig.REQUEST_ADDRESS;
import static factories.FactoryConfig.REQUEST_PORT;

public class BidirectionalChapterFactory {

    private static final String FIELD_CHAPTER_NAME = "chapterName";
    private static final String FIELD_ID = "id";

    /**
     * Gets the attributes of a chapter from de db service.
     * @param id id that identifies this chapter.
     * @return A new chapter instance.
     * @throws UnirestException
     */
    // TODO: Lacks security
    // overload may be added
    public static Chapter newInstance(Long id) throws UnirestException {
        String url = String.format("http://%s:%s/%s/chapter/%s",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION, id.toString());

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject jsonObject = response.getBody().getObject();

        Chapter chapter = null;
        try {

            chapter = new Chapter();

            chapter.setId(jsonObject.getLong(FIELD_ID));
            chapter.setChapterName(jsonObject.getString(FIELD_CHAPTER_NAME));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return chapter;
    }

    public static void main(String[] args) {
        try {
            newInstance(Long.valueOf(1));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean persist(Chapter chapter) {
        return null;
    }

}

