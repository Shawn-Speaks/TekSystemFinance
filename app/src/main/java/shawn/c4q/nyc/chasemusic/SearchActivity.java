package shawn.c4q.nyc.chasemusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {


    String s = "http://lyrics.wikia.com/api.php?func=getSong&artist=Tom+Waits&song=new+coat+of+paint&fmt=json";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        button = (Button) findViewById(R.id.testBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetLyrics().execute();
            }
        });

//        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LyricsAPI.BASE_URL);
//        Retrofit retrofit = builder.build();
//        LyricsAPI lyricsAPISerivce = retrofit.create(LyricsAPI.class);
//
//
//        HashMap<String, String> myMap = new HashMap<>();
//        myMap.put("func", "getSong");
//        myMap.put("artist", "Tom+Waits");
//        myMap.put("song", "new+coat_of_paint");
//        myMap.put("fmt", "json");

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LyricsAPI.BASE_URL);
//                Retrofit retrofit = builder.build();
//                LyricsAPI lyricsAPISerivce = retrofit.create(LyricsAPI.class);
//
//                button = (Button) findViewById(R.id.testBtn);
//
//                HashMap<String, String> myMap = new HashMap<>();
//                myMap.put("func", "getSong");
//                myMap.put("artist", "Tom+Waits");
//                myMap.put("song", "new+coat_of_paint");
//                myMap.put("fmt", "json");
//                Call<JSONObject> lyricResponseObjCall = lyricsAPISerivce.lyricResponseRequest(myMap);
//
//                lyricResponseObjCall.enqueue(new Callback<JSONObject>() {
//                    @Override
//                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                        String s = response.raw().toString();
//                        Log.d("DEBUG TOOL", s);
//                    }
//
//                    @Override
//                    public void onFailure(Call<JSONObject> call, Throwable t) {
//
//                    }
//                });
//            }
//        });










    }
}
