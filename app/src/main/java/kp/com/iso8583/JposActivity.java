package kp.com.iso8583;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class JposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpos);

        Button pack = (Button) findViewById(R.id.pack);
        pack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ISO8583.sampleTest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button unpack = (Button) findViewById(R.id.unpack);
        unpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
