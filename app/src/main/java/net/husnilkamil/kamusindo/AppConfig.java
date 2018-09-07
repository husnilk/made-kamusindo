package net.husnilkamil.kamusindo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppConfig {

    SharedPreferences preferences;
    Context context;

    public AppConfig(Context context){
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Boolean getFirstRun(){
        String key = context.getResources().getString(R.string.pref_app_first_run);
        return preferences.getBoolean(key, true);
    }

    public void setFirstRun(Boolean status){
        SharedPreferences.Editor editor = preferences.edit();
        String key = context.getResources().getString(R.string.pref_app_first_run);
        editor.putBoolean(key, status);
        editor.commit();
    }
}
