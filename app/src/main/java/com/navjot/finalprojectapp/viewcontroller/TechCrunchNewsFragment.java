//package com.navjot.finalprojectapp.viewcontroller;
//
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.navjot.finalprojectapp.R;
//import android.app.ProgressDialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;
//
//import com.navjot.finalprojectapp.MyNewsService;
//import com.navjot.finalprojectapp.adapter.NewsAdapter;
//import com.navjot.finalprojectapp.model.TechCrunchNews;
//import com.navjot.finalprojectapp.R;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//
//
//public class TechCrunchNewsFragment extends Fragment implements AdapterView.OnItemClickListener {
//
//    StringBuilder builder;
//    TechCrunchNewsActivity.FetchNewsTask task;
//
//    ArrayList<TechCrunchNews> newsList;
//    NewsAdapter adapter;
//
//    ListView listView;
//
//    ProgressDialog progressDialog;
//
//    TechCrunchNewsActivity.MyReceiver myReceiver; // Reference Variable of BroadcastReceiver
//
//    public TechCrunchNewsFragment() {
//        // Required empty public constructor
//    }
//
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tech_crunch_news, container, false);
//
//       // listView = findViewById(R.id.listView);
//        // Events | Built In Events | List of Events
//
//
//        // Below is for User Defined with LocalBroadcastManager API
//        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, filter);
//
//        Intent intent = new Intent(TechCrunchNewsFragment.this, MyNewsService.class);
//        // Forward Passing
//        intent.putExtra("keyUrl", "https://newsapi.org/v2/top-headlines?country=in&apiKey=31c21508fad64116acd229c10ac11e84");
//        startService(intent); // Execute the Code in the Background !!
//
//
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        TechCrunchNews news = newsList.get(i);
//        Intent intent = new Intent(TechCrunchNewsFragment.this, DetailedNewsActivity.class);
//        intent.putExtra("keyUrl", news.url);
//        startActivity(intent);
//
//    }
//
//
//
//
//    class MyReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            if(intent.getAction().equals("com.auribises.gw2019android1.newsresponse")) {
//
//                String response = intent.getStringExtra("keyResponse");
//                Log.i("TechCrunchNewsActivity", "==onReceive==" + response);
//
//                builder = new StringBuilder();
//                builder.append(response);
//                parseJSONResponse();
//            }
//
//
//
//            if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
//
//            }
//
//            if(intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)){
//
//            }
//
//        }
//    }
//
//    // 1. Fetch JSON data from Web Service
//    // Nested Class
//    class FetchNewsTask extends AsyncTask{
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//
//            try{
//
//
//                //URL url = new URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=31c21508fad64116acd229c10ac11e84");
//                URL url = new URL("https://newsapi.org/v2/top-headlines?country=in&apiKey=31c21508fad64116acd229c10ac11e84");
//
//                URLConnection connection = url.openConnection();
//
//                InputStream inputStream = connection.getInputStream();
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line = "";
//
//                builder = new StringBuilder();
//
//                while((line = reader.readLine()) !=null){
//                    builder.append(line);
//                }
//
//                // We now need to display the data in Toast or Log
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog.show();
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            //Toast.makeText(getApplicationContext(), "Response:"+builder.toString(), Toast.LENGTH_LONG).show();
//            Log.i("TechCrunchNewsActivity", builder.toString());
//
//            parseJSONResponse();
//        }
//    }
//
//    // 2. Extract meaningful or required data from JSON Data
//    void parseJSONResponse(){
//
//        try{
//
//            // Create JSON Object from JSON Response
//            JSONObject jObj = new JSONObject(builder.toString());
//            JSONArray array = jObj.getJSONArray("articles");
//
//            newsList = new ArrayList<>();
//
//            for(int i=0; i<array.length();i++){
//                JSONObject obj = array.getJSONObject(i);
//
//                /*String author = obj.getString("author");
//                String title = obj.getString("title");
//                String urlToImage = obj.getString("urlToImage");
//                String publishedAt = obj.getString("publishedAt");*/
//
//                // 3. Meaningful data from JSON Data is represented as an Object in Java
//                TechCrunchNews news = new TechCrunchNews();
//
//                news.author = obj.getString("author");
//                news.title = obj.getString("title");
//                news.url = obj.getString("url");
//                news.urlToImage = obj.getString("urlToImage");
//                news.publishedAt = obj.getString("publishedAt");
//
//                newsList.add(news);
//
//                Log.i("TechCrunchNewsActivity", news.toString());
//            }
//
//           // adapter = new NewsAdapter(TechCrunchNewsFragment.this, R.layout.news_list_item, newsList);
//            listView.setAdapter(adapter);
//            listView.setOnItemClickListener(TechCrunchNewsFragment.this);
//            progressDialog.dismiss();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
//
//
//
//
