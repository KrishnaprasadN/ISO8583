package kp.com.iso8583;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class VikActivity extends AppCompatActivity {

    private Spinner mSpinner;

    private TextView mMti;
    private TextView mBitmap;
    private TextView mDataElements;

    //private static final String MSG_1 = "0200B23A800128A180180000000014000000650000000000002050042813271000057813271004280428042803456174591700012340000=000000230579A1B2C3D4E5";
    private static final String MSG_1 = "080020200000008000000000000000013239313130303031";
    private static final String MSG_2 =  "0400F23A4001084182020000004000000000191111111110000000000180000000000030000090806465100331613451909080909601006000200000000000343000394803808110012000004096565733200000003000001360030003317000394809080646510000000003132020000331609080645190000000020000000000000";//"020042000400000000021612345678901234560609173030123456789ABC1000123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
    private static final String MSG_3 = "0200323A4001084180103800000000000000000420050805011392120805042004225132072000001000000115604080041101251146333156336000299";//"08002020000000800000000000000001";
    private static final String MSG_4 = "0200B2200000001000000000000000800000201234000000010000110722183012345606A5DFGR021ABCDEFGHIJ1234567890"; //"080020200000008000000000000000013239313130303031";
    private static final String MSG_5 = "0800823A0000000000000400000000000000042009061390000109061304200420001"; //"080020200000008000000000000000013239313130303031";

    private HashMap<String, String> mMsgMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgMap();

        mSpinner = (Spinner) findViewById(R.id.spinner);
        mMti = (TextView) findViewById(R.id.mti);
        mBitmap = (TextView) findViewById(R.id.bitmap);
        //mDataElements = (TextView) findViewById(R.id.data);

        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(VikActivity.this, adapterView.getSelectedItem().toString() + " clicked", Toast.LENGTH_SHORT).show();
                try {
                    mBitmap.setText(mMsgMap.get(adapterView.getSelectedItem().toString()));
                    //ISO8583.sampleTest();
                    HashMap<String, String> parsedMsgs = ISO8583.unpackIsoMsg(mMsgMap.get(adapterView.getSelectedItem().toString()));
                    Map<String, String> sortedMap = new TreeMap<String, String>(parsedMsgs);
                    StringBuilder stringBuilder = new StringBuilder();

                    Set<String> keys = sortedMap.keySet();
                    for(String key: keys) {
                        stringBuilder.append(key + ": " + parsedMsgs.get(key) + "\n");
                    }

                    mMti.setText(stringBuilder.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(VikActivity.this, "Nothing is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMsgMap() {
        String[] msgs = getResources().getStringArray(R.array.Messages);

        mMsgMap.put(msgs[0], MSG_1);
        mMsgMap.put(msgs[1], MSG_2);
        mMsgMap.put(msgs[2], MSG_3);
        mMsgMap.put(msgs[3], MSG_4);
        mMsgMap.put(msgs[4], MSG_5);


    }
}
