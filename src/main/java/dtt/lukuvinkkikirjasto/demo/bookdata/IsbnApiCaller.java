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

    public void getBookDataFromIsbn(String isbn) throws IOException {
        String url = "https://api.finna.fi/v1/search?lookfor=" + isbn;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JsonNode node = mapper.readTree(response);

        System.out.println(node.get("records").get(0));
    }
}
