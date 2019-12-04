package dtt.lukuvinkkikirjasto.demo.bookdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;

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

        // Testing...
        System.out.println(node.get("records").get(0));


        return responseParser(node);
    }

    public BookDto responseParser(JsonNode node){
        return null;
    }
}
