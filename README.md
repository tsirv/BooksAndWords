# BooksAndWords

Huom! Olen itse toteuttanut BookImplementation.java ja Word.java sisältämät toteutukset!!

Sovelluksen toiminta:
1. Lukee tekstitiedoston, joka sisältää suuren määrän tekstiä (Book-tiedosto).
2. Lukee toisen pienemmän tekstitiedoston, joka sisältää sanoja jotka jätetään huomiotta (Ignore-tiedosto).
3. Lukee sanat Book-tiedostosta jättäen huomiotta Ignore-tiedoston sisältämät sanat. Laskee samalla uniikkien sanojen määrän ja montako kertaa 
sanat esiintyvät Book-tiedostossa.
4. Tulostaa 100 eniten ilmaantunutta sanaa laskevassa järjestyksessä.

Toteutusratkaisuista ja algoritmeistä:
Tarkoituksena on tehdä isosta tekstitiedostosta nopeasti suoriutuva algoritmi. Olen itse kirjoittanut BookImplementation.java ja Word.java sisältämät toteutukset.
Muuten koodi on koulutehtävästä. 

Tietorakenteeksi sanojen säilyttämiseen valitsin hash-taulukon. Toteutin Word-luokan, jossa 
attribuuttina oli sanojen laskuri. Sanoja säilyttävä hash-taulukko oli Word-luokkainen. Hash-funktiossa asetetaan hash-arvoksi ensin 31, 
jonka jälkeen se kerrotaan luvulla 31 ja lisätään sanan jokaisen kirjaimen merkkiarvo. 
Näin tehdään jokaiselle kirjaimelle ja luku 31 kerrotaan aina sen hetkisellä hash-arvolla.
Hash-taulukon lisäksi käytin myös normaalia String-taulukkoa ignoorattavien sanojen tallettamiseen.
Käytin lajittelualgoritmina Quicksort-algoritmia.

Mitä opin tästä tehtävästä?
Tämän projektin avulla opin paljon hyödyllisiä asioita. Opin soveltamaan tällä ja myös edeltävillä kursseilla
 oppimiani asioita. Opin syvemmin ymmärtämään hash tablea ja sen käyttöä, sekä lajittelualgoritmeja ja 
  etenkin quicksorttia. Edellisillä kursseilla käydyistä asioista kehitin koodaustaitojani Javalla. Opin myös metodien
 "pilkkomisen" merkityksen, eli isojen toiminnallisuuskokonaisuuksien jakamisen erillisiin metodeihin. Se
 selkeyttää koodia huomattavasti. Koska projektin aikana tuli paljon virheitä vastaan, opin hyödyntämään debuggausta 
 ja sen toimintaperiaatetta. 

Tämän esimerkin tarkoituksena ei ole toimiva sovellus, vaan antaa esimerkki koodaustaitoihin liittyen.

