package gr.gt.gvapi.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThematicCategories {

    public static Map<String, List<String>> getCategoriesMap() {

        final String politicalParty = "Political Party";
        final String politics = "Politics";
        final String farRight = "Far Right";
        final String rightWing = "Right Wing";
        final String center = "Center";
        final String centerRight = "Center Right";
        final String centerLeft = "Center Left";
        final String leftWing = "Left Wing";
        final String journalist = "Journalist";
        final String openness = "Openness";
        final String technology = "Technology";
        final String service = "Service";
        final String sport = "Sport";
        final String league = "League";
        final String soccer = "Soccer";
        final String basketball = "Basketball";
        final String football = "Football";
        final String tennis = "Tennis";
        final String racing = "Racing";
        final String news = "News";
        final String press = "Press";
        final String broadcaster = "Broadcaster";
        final String artist = "Artist";
        final String celebrity = "Celebrity";
        final String athlete = "Athlete";
        final String agency = "Agency";
        final String place = "Place";
        final String company = "Company";
        final String food = "Food";
        final String beverage = "Beverage";
        final String manufacturer = "Manufacturer";
        final String telecom = "Telecommunication";
        final String retail = "Retail";
        final String clothing = "Clothing";
        final String airline = "Airline";
        final String entertainment = "Entertainment";
        final String music = "Music";
        final String tv = "TV";
        final String charity = "Charity";
        final String economics = "Economics";
        final String religion = "Religion";
        final String space = "Space";
        final String travel = "Travel";
        final String leasing = "Leasing";

        // set the thematic categories of specific usernames (weighted??)
        Map<String, List<String>> categoriesMap = new HashMap<String, List<String>>();

        // 408 accounts
        categoriesMap.put("4newsgr", Arrays.asList(news)); // ran
        categoriesMap.put("9gag", Arrays.asList(service, technology));
        categoriesMap.put("actorbrianwhite", Arrays.asList(celebrity, artist));// ran
        categoriesMap.put("adamschefter", Arrays.asList(celebrity));
        categoriesMap.put("adiamantopoulou", Arrays.asList(politics, centerLeft));
        categoriesMap.put("adonisgeorgiadi", Arrays.asList(politics, rightWing));
        categoriesMap.put("aegeanairlines", Arrays.asList(company, airline));
        categoriesMap.put("agnescpoirier ", Arrays.asList(journalist));
        categoriesMap.put("airbnb", Arrays.asList(company, service));
        categoriesMap.put("alexdmitchell", Arrays.asList(journalist));
        categoriesMap.put("aliciakeys", Arrays.asList(celebrity, artist));
        categoriesMap.put("alpha_bank", Arrays.asList(company, economics));
        categoriesMap.put("americanair", Arrays.asList(company, airline));
        categoriesMap.put("americanexpress", Arrays.asList(company, economics));
        categoriesMap.put("ammanatidou", Arrays.asList(politics, leftWing));
        categoriesMap.put("androiddev", Arrays.asList(service, technology));
        categoriesMap.put("anexartitoi", Arrays.asList(politics, rightWing, politicalParty));
        categoriesMap.put("annetakavadia", Arrays.asList(politics, leftWing));
        categoriesMap.put("antarsyapress", Arrays.asList(politics, leftWing));
        categoriesMap.put("ap", Arrays.asList(news));
        categoriesMap.put("applebees", Arrays.asList(company, food));
        categoriesMap.put("applenws", Arrays.asList(news, technology));
        categoriesMap.put("appstore", Arrays.asList(service, technology));
        categoriesMap.put("arduino", Arrays.asList(openness, service, technology));
        categoriesMap.put("arianagrande", Arrays.asList(celebrity, artist));
        categoriesMap.put("aris_spiliotop", Arrays.asList(politics, rightWing));
        categoriesMap.put("armscontrolwonk", Arrays.asList(celebrity, artist));
        categoriesMap.put("arsenal", Arrays.asList(sport, soccer));
        categoriesMap.put("ashleyrparker", Arrays.asList(journalist, politics));
        categoriesMap.put("athensvoice", Arrays.asList(news));
        categoriesMap.put("atsipras", Arrays.asList(politics, leftWing));
        categoriesMap.put("audi", Arrays.asList(company, manufacturer));
        categoriesMap.put("avramopoulos", Arrays.asList(politics, rightWing));
        categoriesMap.put("azpresident", Arrays.asList(politics));
        categoriesMap.put("bankingnewsgr", Arrays.asList(news, economics));
        categoriesMap.put("barackobama", Arrays.asList(politics));
        categoriesMap.put("barbarousis", Arrays.asList(politics, farRight));
        categoriesMap.put("bbc", Arrays.asList(news, broadcaster));
        categoriesMap.put("bbcnews", Arrays.asList(news, broadcaster));
        categoriesMap.put("bbcsport", Arrays.asList(news, sport, broadcaster));
        categoriesMap.put("beatport", Arrays.asList(company, music));
        categoriesMap.put("beatsbydre", Arrays.asList(company, music));
        categoriesMap.put("beatsbydrebra", Arrays.asList(company, music));
        categoriesMap.put("beatsbydrede", Arrays.asList(company, music));
        categoriesMap.put("beatsbydrefr", Arrays.asList(company, music));
        categoriesMap.put("beatsbydreuk", Arrays.asList(company, music));
        categoriesMap.put("belindapop", Arrays.asList(celebrity, artist));
        categoriesMap.put("beyonce", Arrays.asList(celebrity, artist));
        categoriesMap.put("bi_europe", Arrays.asList(news));
        categoriesMap.put("billclinton", Arrays.asList(politics));
        categoriesMap.put("billgates", Arrays.asList(celebrity, technology, charity));
        categoriesMap.put("bloombergathens", Arrays.asList(news, economics));
        categoriesMap.put("bmw", Arrays.asList(company, manufacturer));
        categoriesMap.put("borowitzreport ", Arrays.asList(celebrity, artist));
        categoriesMap.put("britneyspears", Arrays.asList(celebrity, artist));
        categoriesMap.put("brunomars", Arrays.asList(celebrity, artist));
        categoriesMap.put("businessinsider", Arrays.asList(news));
        categoriesMap.put("buzzfeed", Arrays.asList(company));
        categoriesMap.put("bwgovernment", Arrays.asList(politics));
        categoriesMap.put("chelseafc", Arrays.asList(sport, soccer));
        categoriesMap.put("chevrolet", Arrays.asList(company, manufacturer));
        categoriesMap.put("chicagobulls", Arrays.asList(sport, basketball));
        categoriesMap.put("chinabriefjt", Arrays.asList(news));
        categoriesMap.put("chrisbrown", Arrays.asList(celebrity, artist));
        categoriesMap.put("chrysogelos", Arrays.asList(politics, center));
        categoriesMap.put("cnn", Arrays.asList(news, broadcaster));
        categoriesMap.put("cnnbrk", Arrays.asList(news, broadcaster));
        categoriesMap.put("cocacolaco", Arrays.asList(company, beverage));
        categoriesMap.put("commoncrawl", Arrays.asList(company, technology));
        categoriesMap.put("compal", Arrays.asList(company, food, beverage));
        categoriesMap.put("comradewong", Arrays.asList(journalist));
        categoriesMap.put("conanobrien", Arrays.asList(celebrity, tv));
        categoriesMap.put("contragr", Arrays.asList(news, sport));
        categoriesMap.put("coolsweng", Arrays.asList(openness, technology));
        categoriesMap.put("cosmote", Arrays.asList(company, telecom));
        categoriesMap.put("coursera", Arrays.asList(service));
        categoriesMap.put("cristiano", Arrays.asList(celebrity, athlete, soccer));
        categoriesMap.put("cstaikouras", Arrays.asList(politics, rightWing));
        categoriesMap.put("cytahellascare", Arrays.asList(company, telecom));
        categoriesMap.put("d_ougarezos", Arrays.asList(celebrity));
        categoriesMap.put("dalailama", Arrays.asList(celebrity, religion));
        categoriesMap.put("dannysullivan", Arrays.asList(celebrity, athlete));
        categoriesMap.put("davidguetta", Arrays.asList(celebrity, artist));
        categoriesMap.put("ddlovato", Arrays.asList(celebrity, artist));
        categoriesMap.put("dei_gr", Arrays.asList(company));
        categoriesMap.put("dell", Arrays.asList(celebrity, artist));
        categoriesMap.put("delta", Arrays.asList(company, airline));
        categoriesMap.put("desdelamoncloa", Arrays.asList(place, politics));
        categoriesMap.put("diavgeia", Arrays.asList(openness, service));
        categoriesMap.put("dimiourgiaxana", Arrays.asList(politics, center, politicalParty));
        categoriesMap.put("dimokratiki", Arrays.asList(politics, centerLeft, politicalParty));
        categoriesMap.put("dioti_thessalia", Arrays.asList(politics, leftWing));
        categoriesMap.put("disney", Arrays.asList(company, entertainment));
        categoriesMap.put("djokernole", Arrays.asList(celebrity, athlete, tennis));
        categoriesMap.put("dora_bakoyannis", Arrays.asList(politics, rightWing));
        categoriesMap.put("drassi", Arrays.asList(politics, center));
        categoriesMap.put("dropbox", Arrays.asList(company, service, technology));
        categoriesMap.put("drupalcon", Arrays.asList(openness, service, technology));
        categoriesMap.put("dspinellis", Arrays.asList(openness, technology));
        categoriesMap.put("ecogreens", Arrays.asList(politics, politicalParty, center));
        categoriesMap.put("eellak", Arrays.asList(openness, technology));
        categoriesMap.put("egovbahrain", Arrays.asList(agency));
        categoriesMap.put("electroniki", Arrays.asList(company, retail));
        categoriesMap.put("eleni_alphatv", Arrays.asList(celebrity, artist));
        categoriesMap.put("ellinofreneia", Arrays.asList(tv));
        categoriesMap.put("elysee", Arrays.asList(place, politics));
        categoriesMap.put("epamhellas", Arrays.asList(politics, politicalParty, centerRight));
        categoriesMap.put("espn", Arrays.asList(sport, news, broadcaster));
        categoriesMap.put("eternity_games_", Arrays.asList(entertainment));
        categoriesMap.put("ethnosgr", Arrays.asList(news, press));
        categoriesMap.put("etsy", Arrays.asList(company, artist));
        categoriesMap.put("eu_commission", Arrays.asList(politics));
        categoriesMap.put("eucopresident", Arrays.asList(politics));
        categoriesMap.put("eurobank_group", Arrays.asList(company, economics));
        categoriesMap.put("evakaili", Arrays.asList(politics, centerLeft));
        categoriesMap.put("evangantonaros", Arrays.asList(politics, centerLeft));
        categoriesMap.put("evenizelos", Arrays.asList(politics, centerLeft));
        categoriesMap.put("evichrist", Arrays.asList(politics, centerLeft));
        categoriesMap.put("f1grid", Arrays.asList(sport, racing));
        categoriesMap.put("facebook", Arrays.asList(company, service, technology));
        categoriesMap.put("fcbarcelona", Arrays.asList(sport, soccer));
        categoriesMap.put("fcbayern", Arrays.asList(sport, soccer));
        categoriesMap.put("fcnl", Arrays.asList(agency));
        categoriesMap.put("fhollande", Arrays.asList(politics));
        categoriesMap.put("fiba", Arrays.asList(sport, basketball));
        categoriesMap.put("fifaworldcup", Arrays.asList(sport, soccer));
        categoriesMap.put("financialtimes", Arrays.asList(news, press, economics));
        categoriesMap.put("finkd", Arrays.asList(celebrity, service));
        categoriesMap.put("follifolliegr", Arrays.asList(company, manufacturer));
        categoriesMap.put("forbes", Arrays.asList(news, press, economics));
        categoriesMap.put("ford", Arrays.asList(company, manufacturer));
        categoriesMap.put("foreignpolicy", Arrays.asList(news, press));
        categoriesMap.put("fortalezapr", Arrays.asList(place, politics));
        categoriesMap.put("forthnet", Arrays.asList(company, telecom));
        categoriesMap.put("fortunemagazine", Arrays.asList(news, press, economics));
        categoriesMap.put("fotinipipili", Arrays.asList(politics, rightWing, journalist));
        categoriesMap.put("fotiskouvelis", Arrays.asList(politics, centerLeft));
        categoriesMap.put("foxnews", Arrays.asList(news, broadcaster));
        categoriesMap.put("g_papandreou", Arrays.asList(politics, centerLeft));
        categoriesMap.put("gabriel_athens", Arrays.asList(politics, leftWing));
        categoriesMap.put("gameofthrones", Arrays.asList(tv));
        categoriesMap.put("gap", Arrays.asList(company, clothing));
        categoriesMap.put("garylineker", Arrays.asList(celebrity, athlete, soccer));
        categoriesMap.put("garyparrishcbs", Arrays.asList(journalist));
        categoriesMap.put("gdimaras", Arrays.asList(politics, centerLeft));
        categoriesMap.put("giannismanolis", Arrays.asList(politics, rightWing));
        categoriesMap.put("gizmodo", Arrays.asList(news));
        categoriesMap.put("gm", Arrays.asList(company, artist));
        categoriesMap.put("gmourout", Arrays.asList(politics, rightWing));
        categoriesMap.put("gobiernodechile", Arrays.asList(politics));
        categoriesMap.put("goodmanespn", Arrays.asList(journalist));
        categoriesMap.put("google", Arrays.asList(company, service, technology));
        categoriesMap.put("googlemaps", Arrays.asList(service, technology));
        categoriesMap.put("gossiptvgr", Arrays.asList(news));
        categoriesMap.put("govgr", Arrays.asList(agency));
        categoriesMap.put("gpantzas", Arrays.asList(politics, leftWing));
        categoriesMap.put("grpsarianos", Arrays.asList(politics, center));
        categoriesMap.put("guardiannews", Arrays.asList(news, press));
        categoriesMap.put("harry_styles", Arrays.asList(celebrity, artist));
        categoriesMap.put("harryklynn", Arrays.asList(celebrity, artist));
        categoriesMap.put("hellenicpolice", Arrays.asList(agency));
        categoriesMap.put("hertz", Arrays.asList(company, leasing));
        categoriesMap.put("hillaryclinton", Arrays.asList(politics));
        categoriesMap.put("historicalpics", Arrays.asList(celebrity, artist));
        categoriesMap.put("hm", Arrays.asList(company, clothing));
        categoriesMap.put("honda", Arrays.asList(company, manufacturer));
        categoriesMap.put("hotdoc_mag", Arrays.asList(news));
        categoriesMap.put("htheoharis", Arrays.asList(politics, center));
        categoriesMap.put("hyundai", Arrays.asList(company, manufacturer));
        categoriesMap.put("ianmckellen ", Arrays.asList(celebrity, artist));
        categoriesMap.put("ibm", Arrays.asList(company, service, technology));
        categoriesMap.put("iefimerida", Arrays.asList(news));
        categoriesMap.put("ihs", Arrays.asList(company, artist));
        categoriesMap.put("iliaskasidiaris", Arrays.asList(politics, farRight));
        categoriesMap.put("imvkohli", Arrays.asList(celebrity, athlete));
        categoriesMap.put("in_gr", Arrays.asList(news));
        categoriesMap.put("independent", Arrays.asList(news, press));
        categoriesMap.put("indiegogo", Arrays.asList(service));
        categoriesMap.put("infopresidencia", Arrays.asList(politics));
        categoriesMap.put("instagram", Arrays.asList(service, technology));
        categoriesMap.put("intel", Arrays.asList(company, service, technology));
        categoriesMap.put("israelipm", Arrays.asList(politics));
        categoriesMap.put("james_h_fowler", Arrays.asList(technology));
        categoriesMap.put("jamesdrodriguez", Arrays.asList(celebrity, athlete, soccer));
        categoriesMap.put("jamestowntweets", Arrays.asList(agency));
        categoriesMap.put("jamieoliver", Arrays.asList(celebrity));
        categoriesMap.put("java", Arrays.asList(service));
        categoriesMap.put("jimmyfallon", Arrays.asList(celebrity, artist));
        categoriesMap.put("jimmykimmel", Arrays.asList(celebrity, artist));
        categoriesMap.put("jk_rowling", Arrays.asList(celebrity, artist));
        categoriesMap.put("jlo", Arrays.asList(celebrity, artist));
        categoriesMap.put("john_milios", Arrays.asList(politics, leftWing));
        categoriesMap.put("jtimberlake", Arrays.asList(celebrity, artist));
        categoriesMap.put("junckereu", Arrays.asList(politics));
        categoriesMap.put("justinbieber", Arrays.asList(celebrity, artist));
        categoriesMap.put("justinguarini", Arrays.asList(celebrity, artist));
        categoriesMap.put("kaminisg", Arrays.asList(politics, centerLeft));
        categoriesMap.put("kanyewest", Arrays.asList(celebrity, artist));
        categoriesMap.put("karounos", Arrays.asList(openness, technology));
        categoriesMap.put("kathimerini_gr", Arrays.asList(news, press));
        categoriesMap.put("katia_makri", Arrays.asList(politics, rightWing));
        categoriesMap.put("katyperry", Arrays.asList(celebrity, artist));
        categoriesMap.put("kendalljenner", Arrays.asList(celebrity));
        categoriesMap.put("kickstarter", Arrays.asList(company, technology));
        categoriesMap.put("kimkardashian", Arrays.asList(celebrity));
        categoriesMap.put("kingjames", Arrays.asList(celebrity, athlete, basketball));
        categoriesMap.put("kingsthings", Arrays.asList(celebrity, journalist));
        categoriesMap.put("kkegreece", Arrays.asList(politics, politicalParty, leftWing));
        categoriesMap.put("klout", Arrays.asList(service));
        categoriesMap.put("kmitsotakis", Arrays.asList(politics, rightWing));
        categoriesMap.put("kodak", Arrays.asList(company, manufacturer));
        categoriesMap.put("kostasgioulekas", Arrays.asList(politics, rightWing));
        categoriesMap.put("kouroumplis", Arrays.asList(politics, leftWing));
        categoriesMap.put("kouti_pandoras", Arrays.asList(news));
        categoriesMap.put("kremlinrussia", Arrays.asList(place, politics));
        categoriesMap.put("kronprinsparet", Arrays.asList(politics, place));
        categoriesMap.put("ladygaga", Arrays.asList(celebrity, artist));
        categoriesMap.put("laos_hellas", Arrays.asList(politics, politicalParty, rightWing));
        categoriesMap.put("leftgr", Arrays.asList(politics, leftWing));
        categoriesMap.put("levis", Arrays.asList(company, clothing));
        categoriesMap.put("lfc", Arrays.asList(sport, soccer));
        categoriesMap.put("livanis", Arrays.asList(company, retail));
        categoriesMap.put("manutd", Arrays.asList(sport, soccer));
        categoriesMap.put("mariadamanakieu", Arrays.asList(politics, centerLeft));
        categoriesMap.put("mariahcarey", Arrays.asList(celebrity, artist));
        categoriesMap.put("marianorajoy", Arrays.asList(politics));
        categoriesMap.put("marscuriosity", Arrays.asList(space));
        categoriesMap.put("martinfowler", Arrays.asList(celebrity, technology));
        categoriesMap.put("martinschulz", Arrays.asList(politics));
        categoriesMap.put("marvel", Arrays.asList(company, entertainment));
        categoriesMap.put("matteorenzi", Arrays.asList(politics));
        categoriesMap.put("matthewkeyslive", Arrays.asList(journalist));
        categoriesMap.put("mcdonalds", Arrays.asList(company, food));
        categoriesMap.put("mcfc", Arrays.asList(sport, soccer));
        categoriesMap.put("megatvofficial", Arrays.asList(broadcaster, tv));
        categoriesMap.put("megovernment", Arrays.asList(politics));
        categoriesMap.put("melissakchan", Arrays.asList(journalist));
        categoriesMap.put("messi10stats", Arrays.asList(sport));
        categoriesMap.put("michael_shank", Arrays.asList(celebrity, artist));
        categoriesMap.put("microsoft", Arrays.asList(company, service, technology));
        categoriesMap.put("mignatiou", Arrays.asList(journalist));
        categoriesMap.put("mileycyrus", Arrays.asList(celebrity, artist));
        categoriesMap.put("mindevgr", Arrays.asList(agency, economics));
        categoriesMap.put("minpres", Arrays.asList(politics));
        categoriesMap.put("mls", Arrays.asList(sport, league, soccer));
        categoriesMap.put("msftnews", Arrays.asList(news, technology));
        categoriesMap.put("mvarvitsiotis", Arrays.asList(politics, rightWing));
        categoriesMap.put("naftemporikigr", Arrays.asList(news, press, economics));
        categoriesMap.put("nasosa8anasiou", Arrays.asList(politics, leftWing));
        categoriesMap.put("nataliagermanou", Arrays.asList(celebrity, artist));
        categoriesMap.put("navalny", Arrays.asList(politics));
        categoriesMap.put("nba", Arrays.asList(sport, league, basketball));
        categoriesMap.put("nchatzinikolaou", Arrays.asList(journalist, politics));
        categoriesMap.put("neademokratia", Arrays.asList(politics, rightWing, politicalParty));
        categoriesMap.put("neeliekroeseu", Arrays.asList(politics));
        categoriesMap.put("news247gr", Arrays.asList(news));
        categoriesMap.put("newsbombgr", Arrays.asList(news));
        categoriesMap.put("newsthissecond", Arrays.asList(news));
        categoriesMap.put("neymarjr", Arrays.asList(celebrity, athlete, soccer));
        categoriesMap.put("nfl", Arrays.asList(sport, league, football));
        categoriesMap.put("niallofficial", Arrays.asList(celebrity, artist));
        categoriesMap.put("nickiminaj", Arrays.asList(celebrity, artist));
        categoriesMap.put("nike", Arrays.asList(company, sport));
        categoriesMap.put("nikefootball", Arrays.asList(company, sport));
        categoriesMap.put("nikefootballita", Arrays.asList(company, sport));
        categoriesMap.put("nikefootballza", Arrays.asList(company, sport));
        categoriesMap.put("nikesoccer", Arrays.asList(company, sport));
        categoriesMap.put("niknikolopoulos", Arrays.asList(politics, rightWing));
        categoriesMap.put("nikosdendias", Arrays.asList(politics, rightWing));
        categoriesMap.put("nikosevagelatos", Arrays.asList(journalist));
        categoriesMap.put("nikosfilis1", Arrays.asList(politics, leftWing));
        categoriesMap.put("nikospappas16", Arrays.asList(politics, leftWing));
        categoriesMap.put("nkaklamanis", Arrays.asList(politics, rightWing));
        categoriesMap.put("nrjhitmusiconly", Arrays.asList(music, broadcaster));
        categoriesMap.put("number10gov", Arrays.asList(politics));
        categoriesMap.put("nytimes", Arrays.asList(news, press));
        categoriesMap.put("nytimeskrugman", Arrays.asList(journalist));
        categoriesMap.put("nytimesworld", Arrays.asList(news, press));
        categoriesMap.put("odihq", Arrays.asList(openness, technology));
        categoriesMap.put("okefalogianni", Arrays.asList(politics, centerLeft));
        categoriesMap.put("olgagerovasili", Arrays.asList(politics, leftWing));
        categoriesMap.put("onedirection", Arrays.asList(celebrity, artist));
        categoriesMap.put("oneplus", Arrays.asList(company, manufacturer));
        categoriesMap.put("onned", Arrays.asList(politics, rightWing, politicalParty));
        categoriesMap.put("oprah", Arrays.asList(celebrity, charity));
        categoriesMap.put("oracle", Arrays.asList(company, service, technology));
        categoriesMap.put("orange", Arrays.asList(company, telecom));
        categoriesMap.put("orange_france", Arrays.asList(company, telecom));
        categoriesMap.put("oreo", Arrays.asList(company, food));
        categoriesMap.put("pablo_iglesias_", Arrays.asList(politics));
        categoriesMap.put("panoskammenos", Arrays.asList(politics, rightWing));
        categoriesMap.put("papadimoulis", Arrays.asList(politics, leftWing));
        categoriesMap.put("pasok", Arrays.asList(politics, centerLeft, politicalParty));
        categoriesMap.put("pattonoswalt", Arrays.asList(celebrity, artist));
        categoriesMap.put("pepsi", Arrays.asList(company, beverage));
        categoriesMap.put("pink", Arrays.asList(celebrity, artist));
        categoriesMap.put("pinterest", Arrays.asList(service, technology));
        categoriesMap.put("piratepartygr", Arrays.asList(politics, politicalParty, openness));
        categoriesMap.put("plaisioofficial", Arrays.asList(company, technology, retail));
        categoriesMap.put("playstation", Arrays.asList(service, technology));
        categoriesMap.put("pontifex", Arrays.asList(celebrity, religion));
        categoriesMap.put("potus", Arrays.asList(politics));
        categoriesMap.put("poulakis_k", Arrays.asList(politics, leftWing));
        categoriesMap.put("premierleague", Arrays.asList(sport, league, soccer));
        categoriesMap.put("prensapalacio", Arrays.asList(politics));
        categoriesMap.put("presidencia", Arrays.asList(politics));
        categoriesMap.put("presidencia_ec", Arrays.asList(politics));
        categoriesMap.put("presidencia_sv", Arrays.asList(politics));
        categoriesMap.put("presidenciacr", Arrays.asList(politics));
        categoriesMap.put("presidencialven", Arrays.asList(politics));
        categoriesMap.put("presidenciamx", Arrays.asList(politics));
        categoriesMap.put("presidenciard", Arrays.asList(celebrity, artist));
        categoriesMap.put("primeministergr", Arrays.asList(politics));
        categoriesMap.put("proedrosek", Arrays.asList(politics, center));
        categoriesMap.put("protothema", Arrays.asList(news, press));
        categoriesMap.put("publicstores", Arrays.asList(company, retail, technology));
        categoriesMap.put("pwc_llp", Arrays.asList(company));
        categoriesMap.put("pyrosvestiki", Arrays.asList(agency));
        categoriesMap.put("rachelmakri", Arrays.asList(politics, rightWing));
        categoriesMap.put("radiohead", Arrays.asList(celebrity, artist));
        categoriesMap.put("real_gr", Arrays.asList(news, press, broadcaster));
        categoriesMap.put("real_liam_payne", Arrays.asList(celebrity, artist));
        categoriesMap.put("realmadrid", Arrays.asList(sport));
        categoriesMap.put("redbull", Arrays.asList(company, beverage));
        categoriesMap.put("renadourou", Arrays.asList(politics, leftWing));
        categoriesMap.put("richarddawkins", Arrays.asList(celebrity));
        categoriesMap.put("rigas_pils", Arrays.asList(politics));
        categoriesMap.put("rihanna", Arrays.asList(celebrity, artist));
        categoriesMap.put("rockstargames", Arrays.asList(company, service, technology));
        categoriesMap.put("rww", Arrays.asList(news));
        categoriesMap.put("s_leoutsakos", Arrays.asList(politics, leftWing));
        categoriesMap.put("samaras_antonis", Arrays.asList(politics, rightWing));
        categoriesMap.put("samsungportugal", Arrays.asList(company, technology));
        categoriesMap.put("scpresidenciauy", Arrays.asList(politics));
        categoriesMap.put("selenagomez", Arrays.asList(celebrity, artist));
        categoriesMap.put("semyorka", Arrays.asList(celebrity, artist));
        categoriesMap.put("shakira", Arrays.asList(celebrity, artist));
        categoriesMap.put("singaporeair", Arrays.asList(company, airline));
        categoriesMap.put("skaigr", Arrays.asList(news, broadcaster));
        categoriesMap.put("skroutzit", Arrays.asList(service));
        categoriesMap.put("skylakakis", Arrays.asList(politics, center));
        categoriesMap.put("sochi2014pr", Arrays.asList(sport, league));
        categoriesMap.put("sochiontinder", Arrays.asList(sport, league));
        categoriesMap.put("sonypictures", Arrays.asList(company, entertainment));
        categoriesMap.put("sport24", Arrays.asList(news, sport));
        categoriesMap.put("starbucks", Arrays.asList(company, food, beverage));
        categoriesMap.put("steam_games", Arrays.asList(company, service, technology));
        categoriesMap.put("stefanosmanos", Arrays.asList(politics, rightWing));
        categoriesMap.put("stikoudikaterin", Arrays.asList(celebrity));
        categoriesMap.put("stratoulisd", Arrays.asList(politics, leftWing));
        categoriesMap.put("stupefylisa", Arrays.asList(celebrity, artist));
        categoriesMap.put("sumol", Arrays.asList(company, food, beverage));
        categoriesMap.put("syriza_gr", Arrays.asList(politics, leftWing, politicalParty));
        categoriesMap.put("tacobell", Arrays.asList(company, food));
        categoriesMap.put("tasoskourakis", Arrays.asList(politics, leftWing));
        categoriesMap.put("taxheaven", Arrays.asList(service, economics));
        categoriesMap.put("taylorswift13", Arrays.asList(celebrity, artist));
        categoriesMap.put("terensquick", Arrays.asList(politics, rightWing, journalist));
        categoriesMap.put("thanosplevris", Arrays.asList(politics, rightWing));
        categoriesMap.put("thanostzimeros", Arrays.asList(politics));
        categoriesMap.put("theanofotiou", Arrays.asList(politics, leftWing));
        categoriesMap.put("theellenshow", Arrays.asList(celebrity, artist));
        categoriesMap.put("therealfanatix", Arrays.asList(sport, service));
        categoriesMap.put("thoughtworks", Arrays.asList(company, service));
        categoriesMap.put("timberners_lee", Arrays.asList(celebrity, openness, technology));
        categoriesMap.put("time", Arrays.asList(news, press));
        categoriesMap.put("timoreilly", Arrays.asList(celebrity, openness));
        categoriesMap.put("tokinima", Arrays.asList(politics, centerLeft, politicalParty));
        categoriesMap.put("topotami", Arrays.asList(politics, center, politicalParty));
        categoriesMap.put("tripadvisor", Arrays.asList(company, travel));
        categoriesMap.put("tsakalotos", Arrays.asList(politics, leftWing));
        categoriesMap.put("turtlerock", Arrays.asList(company, entertainment));
        categoriesMap.put("twitter", Arrays.asList(company, service, technology));
        categoriesMap.put("tzitzikostas", Arrays.asList(politics, rightWing));
        categoriesMap.put("tzoumakas", Arrays.asList(politics, centerLeft));
        categoriesMap.put("uaemgov", Arrays.asList(politics));
        categoriesMap.put("ubuntu", Arrays.asList(openness, service, technology));
        categoriesMap.put("unicef", Arrays.asList(charity));
        categoriesMap.put("unsomalia", Arrays.asList(agency));
        categoriesMap.put("v_meimarakis", Arrays.asList(politics, rightWing));
        categoriesMap.put("valavaninadia", Arrays.asList(politics, leftWing));
        categoriesMap.put("velopky", Arrays.asList(politics, rightWing));
        categoriesMap.put("victoriassecret", Arrays.asList(company));
        categoriesMap.put("vine", Arrays.asList(service, technology));
        categoriesMap.put("vkikilias", Arrays.asList(politics, rightWing));
        categoriesMap.put("vodafone_gr", Arrays.asList(company, telecom));
        categoriesMap.put("vouliwatch", Arrays.asList(politics, service));
        categoriesMap.put("vozemberg", Arrays.asList(politics, rightWing));
        categoriesMap.put("washingtonpost", Arrays.asList(news, press));
        categoriesMap.put("waynerooney", Arrays.asList(celebrity, athlete, soccer));
        categoriesMap.put("wendys", Arrays.asList(company, food));
        categoriesMap.put("whitehouse", Arrays.asList(place, politics));
        categoriesMap.put("wikileaks", Arrays.asList(news));
        categoriesMap.put("wikipedia", Arrays.asList(news));
        categoriesMap.put("winbank_tweets", Arrays.asList(company, economics));
        categoriesMap.put("windhellas", Arrays.asList(company, telecom));
        categoriesMap.put("worldofcocacola", Arrays.asList(company, beverage));
        categoriesMap.put("xtina", Arrays.asList(celebrity, artist));
        categoriesMap.put("yahoo", Arrays.asList(company, service, technology));
        categoriesMap.put("yanisvaroufakis", Arrays.asList(politics, leftWing, economics));
        categoriesMap.put("yannis_maniatis", Arrays.asList(politics, leftWing));
        categoriesMap.put("ydragasakis", Arrays.asList(politics, leftWing));
        categoriesMap.put("youranonnews", Arrays.asList(agency));
        categoriesMap.put("youtube", Arrays.asList(service, technology));
        categoriesMap.put("yp_paideias", Arrays.asList(agency));
        categoriesMap.put("zeldawilliams", Arrays.asList(celebrity, artist));
        categoriesMap.put("zetta_makri", Arrays.asList(politics, rightWing));
        categoriesMap.put("zooeydeschanel", Arrays.asList(celebrity, artist));

        return categoriesMap;
    }

}
