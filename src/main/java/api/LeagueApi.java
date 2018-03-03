package api;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LeagueApi {

    private static String api_key = "RGAPI-5e63f887-9e55-43d4-aef1-fd7cd4bb7ace";
    public String summoner, ID;
    public static String url;

    public static void main(String[] args) {
        String summoner_test = "TinaFuchs";
        LeagueApi lol = new LeagueApi();
        try {
            lol.process(summoner_test);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        summoner = name;
        JSONObject profile = ping("https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summoner + "?api_key=" + api_key, false);

        String[] list = new String[10];
        list[0]= profile.get("name").toString();

        JSONObject rank = ping("https://euw1.api.riotgames.com/lol/league/v3/positions/by-summoner/" + profile.get("id").toString() + "?api_key=" + api_key, true);

        list[1] = rank.get("tier").toString() + " " +  rank.get("rank").toString();

        list[2] = rank.get("tier").toString();

        list[3] = profile.get("profileIconId").toString();
        return list;
    }
}
