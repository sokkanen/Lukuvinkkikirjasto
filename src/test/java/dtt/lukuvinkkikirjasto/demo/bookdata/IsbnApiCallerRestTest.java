package dtt.lukuvinkkikirjasto.demo.bookdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(MockitoJUnitRunner.class)
public class IsbnApiCallerRestTest {

    static String jsonString = "{resultCount:2,records:[{buildings:[{value:0/Helmet/,tr"+
            "anslated:Helmet-kirjastot},{value:1/Helmet/e/,translated:Espoo},{value:1/He"+
            "lmet/h/,translated:Helsinki},{value:1/Helmet/k/,translated:Kaunia"+
            "inen},{value:2/Helmet/e/e10/,translated:Tapiola},{value:2/Hel"+
            "met/e/e23/,translated:Iso Omena},{value:2/Helmet/h/h01/,translat"+
            "ed:Pasila},{value:2/Helmet/h/h13/,translated:Rikhardinkatu},{value:2/Helm"+
            "et/h/h20/,translated:Lauttasaari},{value:2/Helmet/h/h33/,translated:Munk"+
            "kiniemi},{value:2/Helmet/h/h37/,translated:Pitu00e4ju00e4nmu00e4ki},{val"+
            "ue:2/Helmet/h/h64/,translated:Oulunkylu00e4},{value:2/Helmet/k/k01/,tra"+
            "nslated:Kauniainen}],formats:[{value:0/Book/,translated:Kirja},{value:1/B"+
            "ook/Book/,translated:Kirja}],id:helmet.1620712,images:[],languages:[fin],non"+
            "PresenterAuthors:[{name:Politkovskaja, Anna},{name:Mallinen, Jukka,role:ku00e4u00"+
            "e4ntu00e4ju00e4}],onlineUrls:[],presenters:{presenters:[],details:[]},rating:{ave"+
            "rage:0,count:0},series:[{name:Pystykorvakirja}],subjects:[[sodat],[sotatoimet],[i"+
            "hmisoikeudet],[rikkomukset],[sisu00e4llissodat],[taistelut],[miehitys],[terro"+
            "rismi],[Tu0161etu0161enia],[Venu00e4ju00e4]],title:Toinen Tshetshenian sota},{buil"+
            "dings:[{value:0/3AMK/,translated:3AMK-kirjastot},{value:1/3AMK/LAULEP/,trans"+
            "lated:Laurea Leppu00e4vaara},{value:1/3AMK/METARA/,translated:Metropolia Ara"+
            "bia},{value:2/3AMK/LAULEP/YLEIS/,translated:YLEIS},{value:2/3AMK/MET"+
            "ARA/YLEIS/,translated:YLEIS}],formats:[{value:0/Book/,translated:Ki"+
            "rja},{value:1/Book/Book/,translated:Kirja}],id:3amk.50082,ima"+
            "ges:[],languages:[fin],nonPresenterAuthors:[{name:Politkovskaja, Anna},{na"+
            "me:Politkovskau00e2, Anna},{name:Mallinen, Jukka},{name:Suomen rauhan"+
            "puolustajat}],onlineUrls:[],presenters:{presenters:[],details:[]},rating:{aver"+
            "age:0,count:0},series:[{name:Pystykorvakirja}],subjects:[[sodat],[sotato"+
            "imet],[ihmisoikeudet],[rikkomukset],[sisu00e4llis"+
            "sodat],[Venu00e4ju00e4],[Tu0161etu0161enia],[Ceu010dnu00e2]],title:Toi"+
            "nen Tshetshenian sota}],status:OK}";

    @Test
    public void IsbnApiCallerReturnsCorrectObject() throws Exception{
        IsbnApiCaller isbnApiCaller = new IsbnApiCaller();
        RestTemplate restTemplate = new RestTemplate();
        isbnApiCaller.setRestTemplate(restTemplate);
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        ObjectMapper mapper = new ObjectMapper();

        Book b = new Book("Raimo", "Raimon vuosi", "123-123-123", false);

        mockServer.expect(ExpectedCount.once(),
                requestTo("http://localhost:8080/123-123-123/allInfo"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(jsonString)
                );
        String dto = isbnApiCaller.doTheCall(
                "http://localhost:8080/123-123-123/allInfo");
        Assert.assertTrue(dto.contains("Pystykorvakirja"));
        Assert.assertTrue(dto.contains("Toinen Tshetshenian sota"));
        Assert.assertTrue(dto.contains("Anna"));
        mockServer.verify();
    }
}
