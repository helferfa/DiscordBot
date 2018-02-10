package api;

import java.io.*;
import java.net.*;
import java.util.Arrays;


import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import util.STATIC;

import javax.json.Json;

public class JsonSimple {

    public static void main(String[] args) throws Exception {
        System.out.println("     p/play : Fügt einen Song der Playlist hinzu -- Usage !m p [Url/YT Search Terms] \n \b     " +
                "q/queue : Gibt aus welche Songs sich in der Queue befinden \n \b     " +
                "now/info : Gibt den aktuellen ong wieder \n \b     " +
                "s/stop/quit : Stoppt die Wiedergabe und verlässt den Channel, wenn dies nicht funktioniert benutzt quit, falls dies auch nicht funktioniert ist der Bot in einen Kritischen Fehler gelaufen und muss neu gestartet werden \n \b     " +
                "shuffle : Shuffled die Songs in der Queue (might not work) \n \b     " +
                "[User Short] Startet die Persönliche Soundboard Wiedergabe -- Usage !m [User Short] [Sound Short] für die bessere/einfachere Nutzung: https://helferfa.bplaced.net/Discord/index.html");
    }

    private static int randomInt(int high)
    {
        int t = (int) Math.floor(Math.random() * high);
        System.out.println("Random Int : " + t);
        return t;
    }


    public static String call_me(String api, String query) throws Exception {

        String url;
        //String test = "{\"username\":\"Peter\"}";

        switch (api) {
            case "500px":
                if(Arrays.stream(STATIC.INTS).parallel().anyMatch(query::contains))
                {
                    switch(query) {
                        case "1": query="Celebrties"; break;
                        case "2": query="Film"; break;
                        case "3": query="Journalism"; break;
                        case "4": query="Nude"; break;
                        case "5": query="black+and+white"; break;
                        case "6": query="still+life"; break;
                        case "7": query="People"; break;
                        case "8": query="Landscapes"; break;
                        case "9": query="city+%26+architecture"; break;
                        case "10": query="Abstract"; break;
                        case "11": query="Animals"; break;
                        case "12": query="Macro"; break;
                        case "13": query="Travel"; break;
                        case "14": query="Fashion"; break;
                        case "15": query="Commercial"; break;
                        case "16": query="Concert"; break;
                        case "17": query="Sport"; break;
                        case "18": query="Nature"; break;
                        case "19": query="performing+arts"; break;
                        case "20": query="Family"; break;
                        case "21": query="Street"; break;
                        case "22": query="Underwater"; break;
                        case "23": query="Food"; break;
                        case "24": query="Fine+Art"; break;
                        case "25": query="Wedding"; break;
                        case "26": query="Transportation"; break;
                        case "27": query="Urban+Exlporation"; break;
                        case "30": query="Night"; break;
                        case "29": query="Aerial"; break;
                        default: query="Nude"; break;
                    }
                }
                System.out.println("Showing Image from 500px Category: " + query);
                url = "https://api.500px.com/v1/photos?feature=popular&only=" + query + "&nsfw=true&sort=created_at&image_size=3&include_store=store_download&include_states=voted&consumer_key=h3VhLzB1fbAhDi8ksBQ6uoNDweBOSOYJaVeBVYxX";
                break;
            case "Bing":
                String image = bing();
                System.out.println(image);
                return image;
            case "pinterest":
                url = "https://api.pinterest.com/v1/boards/124482445889528340/?access_token=AY1YbT9MODLzkC7LdNVxecl0H4XBFOim1UUyCk5EWt4-y0A3hgAAAAA&fields=id%2Cname%2Curl";
                break;
            case "Google":
                url = "https://www.googleapis.com/customsearch/v1?q=test&cx=010462616168426708530%3Azncsipihpi8&key=AIzaSyBc4dhc8T6Te4zsCjXmkxduUn-pExmEUQw";
                break;
            case "oBoobs":
                url = "http://api.oboobs.ru/boobs/" + randomInt(5000) + "/1/rank";
                break;
            case "oButts":
                url = "http://api.obutts.ru/butts/" + randomInt(5000) + "/1/rank";
                break;
            case "LoLGET":
                url = "https://euw1.api.riotgames.com/lol/summoner/v3/summoners/70637474?api_key=RGAPI-5e63f887-9e55-43d4-aef1-fd7cd4bb7ace";
            break;
            case "GameStat":
                url = "https://euw1.api.riotgames.com/lol/spectator/v3/active-games/by-summoner/31387316?api_key=RGAPI-5161d817-149d-4aa7-831d-d0f125f93687";
                break;
            case "LoL":
                url = "https://euw1.api.riotgames.com/lol/summoner/v3/summoners/70637474?api_key=RGAPI-5e63f887-9e55-43d4-aef1-fd7cd4bb7ace";
                break;
            default: url = "https://api.pinterest.com/v1/boards/124482445889528340/?access_token=AY1YbT9MODLzkC7LdNVxecl0H4XBFOim1UUyCk5EWt4-y0A3hgAAAAA&fields=id%2Cname%2Curl";
                break;
        }
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

        int ran = (int) (Math.random() * 10);
        System.out.println(ran);

        switch (api) {
            case "500px":

                //Hier holt sich das Programm random images aus 500px api json text
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(response.toString());
                //System.out.println(json.get("photos"));
                JSONArray msg = (JSONArray) json.get("photos");
                JSONObject iterator = (JSONObject) msg.get(ran++);
                //System.out.println(iterator.toString());
                JSONArray mgs2 = (JSONArray) iterator.get("images");
                JSONObject iterator2 = (JSONObject) msg.get(ran);
                System.out.println(iterator2.get("image_url"));

                return (String) iterator2.get("image_url");




            case "Pinterest":

                return response.toString();


            case "oBoobs":
                JSONParser bparser = new JSONParser();
                JSONArray bjson = (JSONArray) bparser.parse(response.toString());
                System.out.println(bjson.toString());
                JSONObject boobs = (JSONObject) bjson.get(0);
                String erg = "http://media.oboobs.ru/" + boobs.get("preview").toString();
                return erg;
            case "oButts":
                System.out.println(response.toString());
                JSONParser buttparser = new JSONParser();
                JSONArray buttjson = (JSONArray) buttparser.parse(response.toString());
                System.out.println(buttjson.toString());
                JSONObject butts = (JSONObject) buttjson.get(0);
                String butterg = "http://media.obutts.ru/" + butts.get("preview").toString();
                return butterg;

            case "LoL":
                JSONParser profile = new JSONParser();
                JSONObject profileJSON = (JSONObject) profile.parse(response.toString());
                String ret = (String) profileJSON.get("profileIconId").toString() + " " + profileJSON.get("name").toString();
                return ret;
            case "GameStat":

                return response.toString();



            case "Google":
                JSONParser google_parser = new JSONParser();
                JSONObject google_json = (JSONObject) google_parser.parse(response.toString());

                //System.out.println(json.get("photos"));
                JSONArray google_items = (JSONArray) google_json.get("items");
                JSONObject items = (JSONObject) google_items.get(0);
                System.out.println(items.toString());
                //JSONObject giterator = (JSONObject) gmsg.get(0);
                //System.out.println(iterator.toString());

                JSONArray urls = (JSONArray) items.get("pagemap");
                System.out.println(urls.toString());
                //return (String) gmgs2.toString();

        }
    return "error";
    }

    private static String bing() throws UnsupportedEncodingException {
        String erg = "";
        HttpClient httpclient = new DefaultHttpClient();

        String accountKey = ":9e4eb536aa1d41df9bf8997385d6fa20";
        byte[] accountKeyBytes = Base64.encodeBase64(accountKey.getBytes());
        String accountKeyEnc = new String(accountKeyBytes);

        try {
            HttpGet httpget = new HttpGet("https://api.datamarket.azure.com/Data.ashx/Bing/Search/Web?Query=%27Datamarket%27&$top=10&$format=Json");
            httpget.setHeader("Authorization", "Basic " + accountKeyEnc);

            System.out.println("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            erg = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(erg);
            System.out.println("----------------------------------------");


        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();

        }
        return erg;


    }
        //print in String
        /*JSONObject jsnobject = new JSONObject(response);
        jsnobject = jsnobject.getJSONObject("photos");
        JSONArray jsonArray = jsnobject.getJSONArray("photos");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            System.out.println(explrObject.getJSONObject("image_url"));
        }*/

        //JSONObject jsn = new JSONObject(response);
        //System.out.println(jsn.getJSONObject("username");
        //System.out.println(jsn.getString("url"));
        //Read JSON response and print
        //.out.println(response.toString());



}