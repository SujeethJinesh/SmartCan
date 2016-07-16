package app.android.example.com.smartcan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by lab on 7/16/2016.
 */
public class ConsumerStatus extends AppCompatActivity {

    TextView mTextFieldIDs;
    TextView mTextFieldIsFull;
    TextView mTextFieldLocation;
    Firebase mRefRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_status_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();


        mTextFieldIDs = (TextView) findViewById(R.id.textViewIDs);
        mTextFieldIsFull = (TextView) findViewById(R.id.textViewIsFull);
        mTextFieldLocation = (TextView) findViewById(R.id.textViewLocation);


        mRefRoot = new Firebase("https://smartcan-1374.firebaseio.com/Can1");

        final Intent intent = new Intent(this, MapsActivity.class);

        Firebase canIDRef = mRefRoot.child("CanID");
        Firebase isFullRef = mRefRoot.child("IsFull");
        Firebase locationRef = mRefRoot.child("Location");

        //This will be called if the value in the specified parts of the database change

        canIDRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String message = dataSnapshot.getValue(String.class);
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String compost = map.get("Compost");
                String landfill = map.get("Landfill");
                String main = map.get("Main");
                String recycling = map.get("Recycling");

//                mTextFieldIDs.setText("Main: "+main+"\n"+
//                        "Compost: "+compost+"\n"+
//                        "Landfill: "+landfill+"\n"+
//                        "Recycling: "+recycling);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        isFullRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String message = dataSnapshot.getValue(String.class);
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String compost = map.get("Compost");
                String landfill = map.get("Landfill");
                String main = map.get("Main");
                String recycling = map.get("Recycling");


                //number to text: 9162931278
                if (compost.equals("true") || landfill.equals("true") || main.equals("true") || recycling.equals("true")) {
                    //TODO: Add AT&T push to talk here
                    sendMessage("4257853952");
                    return;
                }

                mTextFieldIDs.setText("Main: "+main+"\n"+
                        "Compost: "+compost+"\n"+
                        "Landfill: "+landfill+"\n"+
                        "Recycling: "+recycling);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        locationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String location = dataSnapshot.getValue(String.class);

//                mTextFieldIDs.setText("Location: "+location);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    private void sendMessage(String MDN)
    {
        // Event Type for PTT call is 101.
        String EventType = "108";
        // Call Type for 1-1 PTT call is 0.
        String CallType = "0";
        String pt = "1";
        String message = "Trash can is full";
        String commandString = "";
        commandString = "ET="+ EventType + ";CT="+CallType+";MI="+message+";PT="+pt+";UR="+ MDN +";";
        // Send Broadcast here..
        Intent intent = new Intent();
        // Broadcast action
        intent.setAction("com.kodiak.intent.action.mobileapi");
        // Data - formatted command string
        intent.putExtra("PTTData",commandString);
        sendBroadcast(intent);
    }

}
