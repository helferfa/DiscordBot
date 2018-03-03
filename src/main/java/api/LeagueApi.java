package api;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class LeagueApi {

    private static String api_key = "RGAPI-5e63f887-9e55-43d4-aef1-fd7cd4bb7ace";
    public String summoner, ID;
    public static String url;
    public MessageReceivedEvent e;

    public static void main(String[] args) {
        String summoner_test = "freshddumb";
        LeagueApi lol = new LeagueApi(null);
        try {
            String[] profileinfos = lol.profileMessage("freshddumb");
            for (String s : profileinfos
                 ) {
                System.out.println(s);
            }
            //System.out.println(profileinfos.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LeagueApi(MessageReceivedEvent e) {
        this.e = e;
    }

    public void process(String name) throws Exception {
        this.summoner = name;
        String sumID = getSummonerID();
        System.out.println("Summoner ID:" + sumID);
        getMatch(sumID);
    }

            public String getSummonerID() throws Exception {
                JSONObject obj = ping("https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summoner + "?api_key=" + api_key, false);
                System.out.println(obj.toString());
                return obj.get("id").toString();
            }

            public void getMatch(String ID) throws Exception {
                this.ID = ID;
                JSONObject match = ping("https://euw1.api.riotgames.com/lol/spectator/v3/active-games/by-summoner/" + this.ID + "?api_key=" + api_key, false);
                System.out.println(match.toString());

            }

                public JSONObject ping(String url, boolean arr) throws Exception {
                    this.url = url;
                    if (arr) {
                        return getResponseARR();
                    }
                    else {
                        return getResponse();
                    }
                    //"https://euw1.api.riotgames.com/lol/summoner/v3/summoners/70637474?api_key=RGAPI-5e63f887-9e55-43d4-aef1-fd7cd4bb7ace";
                }


                public JSONObject getResponse() throws Exception{
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    // optional default is GET
                    con.setRequestMethod("GET");
                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
                    if(responseCode==429) {
                        e.getTextChannel().sendMessage(new MessageBuilder().setContent(":warning: Rate limit exceeded").build()).queue();
                    }
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();


                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(response.toString());
                    return json;
                }

                public JSONObject getResponseARR() throws Exception{
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    // optional default is GET
                    con.setRequestMethod("GET");
                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
                    if(responseCode==429) {
                        e.getTextChannel().sendMessage(new MessageBuilder().setContent(":warning: Rate limit exceeded").build()).queue();
                    }
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();


                    JSONParser parser = new JSONParser();
                    JSONArray json = (JSONArray) parser.parse(response.toString());
                    return (JSONObject) json.get(0);
                }

    public String[] profileMessage(String name) throws Exception {
        JSONParser p = new JSONParser();
        JSONObject j = new JSONObject();
        summoner = name;
        JSONObject profile = ping("https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summoner + "?api_key=" + api_key, false);

        String[] list = new String[10];
        list[0]= profile.get("name").toString();

        String id = profile.get("id").toString();
        try {
            JSONObject rank = ping("https://euw1.api.riotgames.com/lol/league/v3/positions/by-summoner/" + id + "?api_key=" + api_key, true);

            list[1] = rank.get("tier").toString() + " " +  rank.get("rank").toString();

            list[2] = rank.get("tier").toString();
            } catch (Exception e)
        {
            list[1] = "unranked";
            list[2] = "silver";
        }

        String profileIconID = profile.get("profileIconId").toString();
        JsonReader js = new JsonReader();
        JSONObject stream = js.readJsonFromUrl("http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/profileicon.json");
        JSONObject icons = (JSONObject) stream.get("data");
        System.out.println(profileIconID);
        try {
            System.out.println(icons.get(profileIconID));
            if(icons.get(profileIconID)==null) {
                list[3]="7";
            } else {
                list[3] = profileIconID;
            }
        } catch (Exception e) {
            e.printStackTrace();
            list[3] = "7";

        }
        JSONObject mastery = ping("https://euw1.api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/" + id + "?api_key=" + api_key, true);
        System.out.println(mastery.toString());
        String champID = mastery.get("championId").toString();

        try {
            JSONObject champ = ping("https://euw1.api.riotgames.com/lol/static-data/v3/champions/" + champID + "?locale=en_US&api_key=" + api_key, false);
            list[4] = champ.get("key").toString();
        } catch (Exception e) {
            list[4] = "ratelimitexceeded";
        }

        return list;
    }

    public static class JsonReader {

        private String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        public JSONObject readJsonFromUrl(String url) throws IOException, JSONException, ParseException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONParser p = new JSONParser();
                JSONObject json = (JSONObject) p.parse(jsonText);
                return json;
            } finally {
                is.close();
            }
        }

        public JsonReader() throws IOException, JSONException, ParseException {

        }
    }
}

