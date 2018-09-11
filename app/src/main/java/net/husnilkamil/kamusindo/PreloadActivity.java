package net.husnilkamil.kamusindo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PreloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);


    }

    public class loadDataToDb extends AsyncTask<Void, Integer, Void>
    {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            AppConfig appConfig = new AppConfig(getApplicationContext());
            Boolean firstRun = appConfig.getFirstRun();
            if(firstRun){

            }
            return null;
        }
    }
}
