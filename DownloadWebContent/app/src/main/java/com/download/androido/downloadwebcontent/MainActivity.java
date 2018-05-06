package com.download.androido.downloadwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // Class to Download Stuff on the Backgound Thread
    // String, Void: defines what things can be passed on into this class
    // String, Void, String: means we can pass in a string and take out a string
    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            // String: array of strings
            //Log.i("URL", strings[0]);

            String result = "";
            URL url;
            // Grab the HTML and show on browser
            HttpURLConnection urlConnection = null;

            // Grab the URL
            try {
                url = new URL(urls[2]);
                // Make URL Connection
                urlConnection = (HttpURLConnection) url.openConnection();
                // Input Stream: Gather data coming using url
                InputStream in = urlConnection.getInputStream();
                // Reader to read the data
                InputStreamReader reader = new InputStreamReader(in);
                // Read Data from Stream
                int data = reader.read();
                while (data != -1){
                    // Get characters
                    char current = (char)data;
                    // join characters to form words and sentences
                    result += current;
                    // Read next line till end of data
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "Fetching Failed...";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView fetchedData = (TextView)findViewById(R.id.textView);

        // Create DownloadTask Object
        DownloadTask task = new DownloadTask();

        String result = null;

        try {
            // Put this in try catch as server may or may not respond
            result = task.execute("http://zappycode.com/","https://www.google.com","https://www.facebook.com").get();
            fetchedData.setText(result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Log.i("Result",result);
    }
}
