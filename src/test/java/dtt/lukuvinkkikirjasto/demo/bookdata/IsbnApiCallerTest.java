package dtt.lukuvinkkikirjasto.demo.bookdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtt.lukuvinkkikirjasto.demo.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsbnApiCallerTest extends BaseTest {

    @Autowired
    IsbnApiCaller isbnApiCaller;

    @Autowired
    ObjectMapper objectMapper;

    static final String jsonString = "{\"resultCount\":2,\"records\":[{\"buildings\":[{\"value\":\"0\\/Helmet\\/\",\"tr"+
            "anslated\":\"Helmet-kirjastot\"},{\"value\":\"1\\/Helmet\\/e\\/\",\"translated\":\"Espoo\"},{\"value\":\"1\\/He"+
            "lmet\\/h\\/\",\"translated\":\"Helsinki\"},{\"value\":\"1\\/Helmet\\/k\\/\",\"translated\":\"Kaunia"+
            "inen\"},{\"value\":\"2\\/Helmet\\/e\\/e10\\/\",\"translated\":\"Tapiola\"},{\"value\":\"2\\/Hel"+
            "met\\/e\\/e23\\/\",\"translated\":\"Iso Omena\"},{\"value\":\"2\\/Helmet\\/h\\/h01\\/\",\"translat"+
            "ed\":\"Pasila\"},{\"value\":\"2\\/Helmet\\/h\\/h13\\/\",\"translated\":\"Rikhardinkatu\"},{\"value\":\"2\\/Helm"+
            "et\\/h\\/h20\\/\",\"translated\":\"Lauttasaari\"},{\"value\":\"2\\/Helmet\\/h\\/h33\\/\",\"translated\":\"Munk"+
            "kiniemi\"},{\"value\":\"2\\/Helmet\\/h\\/h37\\/\",\"translated\":\"Pit\\u00e4j\\u00e4nm\\u00e4ki\"},{\"val"+
            "ue\":\"2\\/Helmet\\/h\\/h64\\/\",\"translated\":\"Oulunkyl\\u00e4\"},{\"value\":\"2\\/Helmet\\/k\\/k01\\/\",\"tra"+
            "nslated\":\"Kauniainen\"}],\"formats\":[{\"value\":\"0\\/Book\\/\",\"translated\":\"Kirja\"},{\"value\":\"1\\/B"+
            "ook\\/Book\\/\",\"translated\":\"Kirja\"}],\"id\":\"helmet.1620712\",\"images\":[],\"languages\":[\"fin\"],\"non"+
            "PresenterAuthors\":[{\"name\":\"Politkovskaja, Anna\"},{\"name\":\"Mallinen, Jukka\",\"role\":\"k\\u00e4\\u00"+
            "e4nt\\u00e4j\\u00e4\"}],\"onlineUrls\":[],\"presenters\":{\"presenters\":[],\"details\":[]},\"rating\":{\"ave"+
            "rage\":0,\"count\":0},\"series\":[{\"name\":\"Pystykorvakirja\"}],\"subjects\":[[\"sodat\"],[\"sotatoimet\"],[\"i"+
            "hmisoikeudet\"],[\"rikkomukset\"],[\"sis\\u00e4llissodat\"],[\"taistelut\"],[\"miehitys\"],[\"terro"+
            "rismi\"],[\"T\\u0161et\\u0161enia\"],[\"Ven\\u00e4j\\u00e4\"]],\"title\":\"Toinen Tshetshenian sota\"},{\"buil"+
            "dings\":[{\"value\":\"0\\/3AMK\\/\",\"translated\":\"3AMK-kirjastot\"},{\"value\":\"1\\/3AMK\\/LAULEP\\/\",\"trans"+
            "lated\":\"Laurea Lepp\\u00e4vaara\"},{\"value\":\"1\\/3AMK\\/METARA\\/\",\"translated\":\"Metropolia Ara"+
            "bia\"},{\"value\":\"2\\/3AMK\\/LAULEP\\/YLEIS\\/\",\"translated\":\"YLEIS\"},{\"value\":\"2\\/3AMK\\/MET"+
            "ARA\\/YLEIS\\/\",\"translated\":\"YLEIS\"}],\"formats\":[{\"value\":\"0\\/Book\\/\",\"translated\":\"Ki"+
            "rja\"},{\"value\":\"1\\/Book\\/Book\\/\",\"translated\":\"Kirja\"}],\"id\":\"3amk.50082\",\"ima"+
            "ges\":[],\"languages\":[\"fin\"],\"nonPresenterAuthors\":[{\"name\":\"Politkovskaja, Anna\"},{\"na"+
            "me\":\"Politkovska\\u00e2, Anna\"},{\"name\":\"Mallinen, Jukka\"},{\"name\":\"Suomen rauhan"+
            "puolustajat\"}],\"onlineUrls\":[],\"presenters\":{\"presenters\":[],\"details\":[]},\"rating\":{\"aver"+
            "age\":0,\"count\":0},\"series\":[{\"name\":\"Pystykorvakirja\"}],\"subjects\":[[\"sodat\"],[\"sotato"+
            "imet\"],[\"ihmisoikeudet\"],[\"rikkomukset\"],[\"sis\\u00e4llis"+
            "sodat\"],[\"Ven\\u00e4j\\u00e4\"],[\"T\\u0161et\\u0161enia\"],[\"Ce\\u010dn\\u00e2\"]],\"title\":\"Toi"+
            "nen Tshetshenian sota\"}],\"status\":\"OK\"}";

    @Test
    public void JSONIsParsedCorrectly() throws Exception{
        JsonNode node = objectMapper.readTree(jsonString);
        BookDto bookDto = isbnApiCaller.responseParser(node);
        assertEquals("\"Toinen Tshetshenian sota\"", bookDto.getTitle());
        assertEquals(2, bookDto.getAuthors().size());
        assertEquals("Politkovskaja, Anna: ", bookDto.getAuthors().get(0));
        assertEquals(1, bookDto.getLanguages().size());
        assertEquals("fin", bookDto.getLanguages().get(0));
        assertEquals(13, bookDto.getLibraries().size());
        assertEquals(1, bookDto.getSeries().size());
        assertEquals("Pystykorvakirja", bookDto.getSeries().get(0));
        assertEquals(10, bookDto.getSubjects().size());
    }

    @Test
    public void emptyJSONReturnsSpecialBookDto(){
        JsonNode node = objectMapper.createObjectNode();
        BookDto bookDto = isbnApiCaller.responseParser(node);
        Assert.assertEquals("Additional information not found", bookDto.getTitle());
    }
}
