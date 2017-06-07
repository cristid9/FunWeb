package factories;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
        Chapter chapter = new Chapter(Long.valueOf(6), "Hi");
        try {
           //b =  persist(chapter);
           // newInstance(10L);
            remove(chapter);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static Boolean remove(Chapter chapter) throws  UnirestException{
        String url = String.format("http://%s:%s/%s/chapter/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, chapter.getId().toString());
            jsonObject.put(FIELD_CHAPTER_NAME, chapter.getChapterName());
        } catch (JSONException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        HttpResponse<String> httpResponse = Unirest.delete(url)
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .asString();
        if (httpResponse.getStatus() == 201) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;

    }



    public static Boolean persist(Chapter chapter) throws UnirestException {
        String url = String.format("http://%s:%s/%s/chapter/",
                REQUEST_ADDRESS, REQUEST_PORT, API_VERSION);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put(FIELD_ID, chapter.getId().toString());
            jsonObject.put(FIELD_CHAPTER_NAME, chapter.getChapterName());
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

