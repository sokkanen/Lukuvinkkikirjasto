package dtt.lukuvinkkikirjasto.demo.bookdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Service
public class IsbnApiCaller {

    @Autowired
    ObjectMapper mapper;

    private static HttpURLConnection con;

    private final String baseUrl = "https://api.finna.fi/v1/search?lookfor=";
    private final String searchParameters = "&type=AllFields&method=getSideFacets&searchClassId=Solr&location=side" +
            "&configIndex=0&query=lookfor%3D952-471-141-9%26type%3DAllFields&enabledFacets[]=free_online_boolean%3A1 " +
            "&enabledFacets[]=format_ext_str_mv";

    public BookDto getBookDataFromIsbn(String isbn) throws IOException {
        String url = baseUrl + isbn + searchParameters;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JsonNode node = mapper.readTree(response);
        return responseParser(node);
    }

    public BookDto responseParser(JsonNode node){
        final JsonNode firstObject = node.get("records").get(0);

        final String title = firstObject.get("title").toString();
        List<String> libraries = parseField(firstObject.get("buildings"), "translated", "");
        List<String> languages = parseField(firstObject.get("languages"), "", "");
        List<String> authors = parseField(firstObject.get("nonPresenterAuthors"), "name", "role");
        List<String> subjects = parseField(firstObject.get("subjects"), "", "");
        List<String> series = parseField(firstObject.get("series"), "name", "");

        return new BookDto(title,authors,languages,libraries,series,subjects);
    }

    public List<String> parseField(JsonNode node, String parameter, String subParameter){
        List<String> parsedObjects = new ArrayList<>();
        if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                if (parameter.equals("")){
                    parsedObjects.add(node.get(i).toString().replace("\"", ""));
                } else if (subParameter.equals("")){
                    parsedObjects.add(node.get(i).get(parameter).toString().replace("\"", ""));
                } else {
                    String secondary = node.get(i).get(subParameter) != null ?
                            node.get(i).get(subParameter).toString().replace("\"", "") :
                            "";
                    parsedObjects.add(node.get(i).get(parameter).toString().replace("\"", "") + ": " + secondary);
                }
            }
        }
        return parsedObjects;
    }
}
