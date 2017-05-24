package udacty.cyberavanza.news;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import udacty.cyberavanza.news.adapters.NewsListAdapter;
import udacty.cyberavanza.news.adapters.SourcesListAdapter;
import udacty.cyberavanza.news.dataholders.Article;
import udacty.cyberavanza.news.dataholders.Source;

public class MainActivity extends AppCompatActivity {

    private String apiKey = "3a7802822f05495faa60a3074103cdd8";
    private String newsApiUrl = "https://newsapi.org/v1/articles?apiKey="+apiKey;
    private String sourcesUrl = "https://newsapi.org/v1/sources?language=en&apiKey="+apiKey;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading News... Please wait");
        pd.setCancelable(false);
        pd.show();

        Fresco.initialize(this);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(sourcesUrl,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray resultsArray = response.getJSONArray("sources");
                    Gson gson = new Gson();
                    final Source[] mapPlaceLocation = gson.fromJson(resultsArray.toString(),Source[].class);

                    for (int si = 0;si<mapPlaceLocation.length;si++){
                        final int finalSi = si;
                        mapPlaceLocation[si].setClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.e("CLicked",mapPlaceLocation[finalSi].getId());
                                bindResults(mapPlaceLocation[finalSi].getId());
                            }
                        });
                    }

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.newsSources);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
                    RecyclerView.Adapter adapter = new SourcesListAdapter(MainActivity.this,mapPlaceLocation);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                    bindResults(mapPlaceLocation[0].getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
        });
    }

    private void bindResults(String source){
        String curUrl = newsApiUrl;

        if(source != null){
            curUrl += "&source="+source;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(curUrl,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray resultsArray = response.getJSONArray("articles");
                    Gson gson = new Gson();
                    Article[] mapPlaceLocation = gson.fromJson(resultsArray.toString(),Article[].class);

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.placesList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    RecyclerView.Adapter adapter = new NewsListAdapter(MainActivity.this,mapPlaceLocation);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onFinish() {
                pd.hide();
            }
        });
    }
}
