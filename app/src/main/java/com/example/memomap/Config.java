package com.example.memomap;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

/**Clase java que representa al activity de configuración*/
public class Config extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.config);
    }
    @Override
    protected void onStop() {
        super.onStop();
        update();
    }
    public void update(){
        SharedPreferences sp = getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editor = sp.edit();

        String strMemo = ((ListPreference) findPreference("lp_memoria")).getEntry().toString();
        sp_editor.putString("lp_memoria",strMemo);

        sp_editor.commit();
    }
}
