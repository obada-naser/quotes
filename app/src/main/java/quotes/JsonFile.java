package quotes;

import com.google.gson.Gson;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonFile {

    String author;
    String text;
    String quoteText;
    String quoteAuthor;

    public JsonFile(String author,String text,String quoteText,String quoteAuthor){
        this.author=author;
        this.text=text;
        this.quoteText=quoteText;
        this.quoteAuthor=quoteAuthor;
    }




    public static void quotesFromJson() throws IOException{
        String urlString="http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";


        try {



             URL url=new URL(urlString);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();

//            System.out.println(connection.getResponseCode());

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            if(connection.getResponseCode()==404){

                jsonFileReading();












            }
            else {

                InputStream inputStream =connection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader inputReader=new BufferedReader(inputStreamReader);




                String line=inputReader.readLine();


                inputReader.close();

                Gson gson = new Gson();
                System.out.println(line);



                JsonFile gettingQuotes=gson.fromJson(line,JsonFile.class);

                String quoteText=gettingQuotes.quoteText;
                String quoteAuthor=gettingQuotes.quoteAuthor;

                JsonFile addingWithJson = new JsonFile(quoteAuthor,quoteText,null,null);

                BufferedWriter writer = Files.newBufferedWriter(Paths.get("../quotes/data.json"));


                Map<String, Object> quotesMap = new HashMap<>();

                quotesMap.put("text", quoteText);
                quotesMap.put("author", quoteAuthor);



                writer.write(gson.toJson(quotesMap));

                writer.close();







            }

        }
        catch (MalformedURLException e){
            System.out.println("Sorry there was a problem creating url object and the problem was:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Sorry there was a problem opening the connection url object and the problem was:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static double jsonFileReading() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("data.json"));

//        Type jsonCasting = new TypeToken<List<JsonFile>>() {
//        }.getType();
//        List<JsonFile> jsonList = gson.fromJson(reader, jsonCasting);
//
//        Random random = new Random();

//        int randomsQuotes = random.nextInt(jsonList.size());

        BufferedReader inputReader=new BufferedReader(reader);
        String line=inputReader.readLine();
        while(line!=null){
                    System.out.println(line);
                    line=inputReader.readLine();
                }
        reader.close();



//        System.out.println(jsonList.get(randomsQuotes).toString());
        return 0;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JsonFile{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", quoteText='" + quoteText + '\'' +
                ", quoteAuthor='" + quoteAuthor + '\'' +
                '}';
    }
}
