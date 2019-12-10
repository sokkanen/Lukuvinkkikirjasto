# Loppuraportti ohjelmistotuotanto miniprojekti
## Ryhmä: Drop table teams;
## Jäsenet: Milla Lintunen, Sebastian Sergelius, Pasi Alho, Joel Sokkanen, Milla Kortelainen, Julius Uusinarkaus


### Ennen aloitusta 


Aloitustilanteessa käydyn keskustelun lopputuloksena, päädyimme käyttämään miniprojektimme pääteknologioina Java Spring Bootia ja Thymeleafia, vaikka tästä varoiteltiinkin kurssimateriaalissa. Lopullinen valinnan syy oli se, että kaikilla oli kokemusta Javasta ja muutamilla myös Spring Bootista. Lopullinen ohjelmamme onkin perinteisemmän Javan ja Spring Bootin “hybridi”.

Päätimme yhdessä ryhmän kanssa, monen mutkan kautta, luoda projektistamme web-sovelluksen. Tähän vaikutti niin asiakkaan tahtotila, kuin se, että käytössämme oli tähän vaadittavat teknologiat. Kuten mainittua, osalla ryhmän jäsenistä oli jo kokemusta web-sovellusten luomisesta Spring Bootilla. 

Definition of Done oli helppo sopia, sillä kaikkien ryhmän jäsenten näkemykset ominaisuuden valmiudesta olivat hyvin samankaltaisia. Masteriin mergaaminen tapahtuisi branchien kautta. Hyväksyttyyn mergeen tarvittaisiin yhden ryhmän jäsenen suorittama koodikatselmointi sekä automaattisten CircleCI ja Codecov - testien läpäisy. Mikäli koodi ei vaatinut muutoksia, hyväksyjä mergesi toisen kehittäjän luoman feature-branchin masteriin.

 

### Sprintti 0 (1)


Ensimmäisen viikon jälkeen ryhmän kommunikointi sekä tehokkuus saivat paljon kiitosta. Kaikki vaaditut user storyt saatiin toteutettua Definition of Donen tasolla. Ensimmäisen sprintin osalta taskien jako suoritettiin sprintin aloituksen yhteydessä. Tämä osoittautui myöhemmin työtä hankaloittavaksi, sillä aikataulutuksen vuoksi osa taskeista oli saatava valmiiksi ennen seuraavien aloittamista. Näin ollen syntyi turhaa odottelua, eikä ohjelman kehittäminen edistynyt tasaisesti. Tämä huomio johtikin ketterään taskien uudelleen jakamiseen sprintin aikana. Ensimmäisessä retrossa edellä mainittu havainto nostettiin esille ja sovittiin, että jatkossa kukin ottaa taskeja tehtäväkseen samalla kun aloittaa niiden tekemisen. Tämä osoittautuikin jatkossa huomattavasti toimivammaksi tavaksi. 

Spring aiheutti jonkin verran vaikeuksia, osittain konfiguraatioiden vuoksi ja osittain siksi, että osalle se oli tutumpi kuin toisille. Vaikeuksia aiheutti myös perinteisen Javan käyttäminen Springin luonnollisen tavan rinnalla. Tällä valinnalla kuitenkin saavutettiin se, että kaikille tiimin jäsenille riitti mielekästä tekemistä.

Jos joissakin asioissa tuli ongelmia, ryhmä neuvoi ja tuki toisiaan todella hyvin. Vaikka aluksi oli epäilyksiä päätyykö koodi valitun kehitystavan myötä merge-helvettiin, niin käytännössä kaikilta suuremmilta konflikteilta vältyttiin. Ainoastaan pieniä päällekkäisyyksiä oli muutamia. Myös tämän seikan onnistumisessa keskeisenä tekijänä oli hyvä kommunikaatio; Telegramiin ja backlogiin päivitettiin todella pienellä viiveellä mitä kukin on tekemässä. 



### Sprintti 1 (2)


Hyvän retrospektiivin jälkeen meillä oli yhteinen näkemys niin hyvistä, kuin kehitettävistäkin asioista sekä tulevasta kehityksen suunnasta. User storyjen purkaminen pienemmiksi taskeiksi alkoi olla jo sujuvaa. Myös tässä sprintissä saatiin kaikki suunnitellut taskit toteutettua, joten estimoinnissa onnistuttiin taas hyvin. Taskeja tuli paljon lisää sprintin aikana ja niitä jaettiin nyt sujuvammin. Ongelmia aiheutti edelleen Springin konfigurointi, joskin suurimmat ongelmat koskettivat Cucumber-testien ajamista testitietokannassa. Kaikkiin teknologisiin ongelmiin kuitenkin löydettiin ratkaisut.



### Sprintti 2 (3)


Viimeinen sprintti suunnittelussa näkyi jo jonkinlainen rutiini. Sprintti suunniteltiin nyt selkeästi tarkemmin, kuin aiemmat. Sprintin suunnittelutilaisuuden jälkeen ryhmä lähtikin tekemään ja toteuttamaan asiakkaan toiveita luottavaisin mielin. Aikaisemmin päänvaivaa aiheuttanut, ylimääräinen konfigurointi oli jo saatu hyvälle tolalle, joten nyt voitiin keskittyä lähes kokonaan ohjelman uusien featureiden tekemiseen ja vanhojen täydentämiseen. Viimeisessä sprintissä varauduimme paremmin etukäteen siihen, että uusia taskeja voi tulla paljonkin lisää sprintin kuluessa. Viimeisessä sprintissä asiakkaan vaatimukset kiristyivät ja myönnyimme asiakkaan toiveisiin toteuttaa monta suurta ominaisuutta yhden sprintin aikana. Sprintin kuluessa asiakkaalle annetut lupaukset alkoivat kääntyä meitä vastaan, mikä näkyi sekä osittaisena koodin laadun heikentymisenä, että osan luvatuista ominaisuuksista toteutuksen puuttumisena. Voikin sanoa, että koodiimme jäi viimeisen sprintin päätyttyä jonkin verran teknistä velkaa. Mikäli työtä vielä jatkettaisiin, tulisi seuraavan sprintin työpanoksesta käyttää merkittävä osa uudelleensuunnitteluun ja refaktorointiin. 


