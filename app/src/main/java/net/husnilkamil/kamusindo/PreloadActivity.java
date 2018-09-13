package net.husnilkamil.kamusindo;

import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import net.husnilkamil.kamusindo.db.Kamus;
import net.husnilkamil.kamusindo.db.KamusHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PreloadActivity extends AppCompatActivity {

    ProgressBar progressBar;
    KamusHelper kamusHelper;
    AppConfig appConfig;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

        progressBar = findViewById(R.id.progressBarPreload);
        kamusHelper = new KamusHelper(this);
        appConfig = new AppConfig(this);

        new LoadDataToDb().execute();
    }

    private class LoadDataToDb extends AsyncTask<Void, Integer, Void>
    {
        final String TAG = LoadDataToDb.class.getSimpleName();


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Boolean firstRun = appConfig.getFirstRun();
            if(firstRun){

                kamusHelper.open();

                ArrayList<Kamus> wordList = preloadKamusData(KamusHelper.IND);
                progress = 15;
                publishProgress((int) progress);
                Double progressMaxInsert = 50.0;
                Double progressDiff = (progressMaxInsert - progress) / wordList.size();

                kamusHelper.beginTransaction();

                try{
                    for(Kamus word: wordList){
                        kamusHelper.insert(KamusHelper.IND, word);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: " + e.getMessage());
                }

                progress = 50;
                publishProgress((int) progress);
                kamusHelper.setTransactionSuccess();
                kamusHelper.endTransaction();

                wordList = preloadKamusData(KamusHelper.ENG);
                progress = 65;
                publishProgress((int) progress);
                progressMaxInsert = 100.0;
                progressDiff = (progressMaxInsert - progress) / wordList.size();

                kamusHelper.beginTransaction();

                try{
                    for(Kamus word: wordList){
                        kamusHelper.insert(KamusHelper.ENG, word);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: " + e.getMessage());
                }
                kamusHelper.setTransactionSuccess();
                kamusHelper.endTransaction();

                kamusHelper.close();

                publishProgress((int) progress);

                appConfig.setFirstRun(false);

            }
            return null;
        }


    }

    public ArrayList<Kamus> preloadKamusData(int table) {

        ArrayList<Kamus> wordList = new ArrayList<>();

        String line;
        BufferedReader reader;
        try{

            InputStream rawData;
            if(table == KamusHelper.IND)
                rawData = getResources().openRawResource(R.raw.english_indonesia);
            else
                rawData = getResources().openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(rawData));
            do{
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                Kamus kamus = new Kamus(splitstr[0], splitstr[1]);
                wordList.add(kamus);
            }while(line != null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return wordList;
    }
}
