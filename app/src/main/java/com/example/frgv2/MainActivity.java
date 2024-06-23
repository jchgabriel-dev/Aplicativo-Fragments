package com.example.frgv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainCallbacks{
    private Fragment_A fragment_a;
    private Fragment_B fragment_b;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        fragment_a = Fragment_A.newInstance("","");
        ft.add(R.id.frg_contenedor_A, fragment_a);
        ft.commit();

    }

    @Override
    public void onMsgFromFragtoMain(String sender, String strValue) {
        if (sender.equals("A-FRAG")) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("ww",strValue);

                ft = getSupportFragmentManager().beginTransaction();
                fragment_b = Fragment_B.newInstance("","");
                fragment_b.setArguments(bundle);

                ft.replace(R.id.frg_contenedor_A, fragment_b);
                ft.commit();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
        if (sender.equals("B-FRAG")) { }
    }
}