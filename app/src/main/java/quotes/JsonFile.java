package quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


import java.io.FileReader;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

public class JsonFile {

    String author;
    String text;

    public JsonFile(){
        this.author=author;
        this.text=text;
    }


    public static void quotesFromJson() throws IOException{
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("../quotes/data.json"));




        Type jsonCasting = new TypeToken<List<JsonFile>>() {
        }.getType();
        List<JsonFile> jsonList = gson.fromJson(reader, jsonCasting);

        Random random = new Random();
        int randomsQuotes = random.nextInt(jsonList.size());


        System.out.println(jsonList.get(randomsQuotes).toString());
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String auther) {
        this.author = auther;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JsonFile{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
