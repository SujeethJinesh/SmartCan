package app.android.example.com.smartcan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView mTextFieldIDs;
    Button mButtonSunny;
    Button mButtonFoggy;
    Firebase mRef;
    Firebase mRefRoot;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mTextFieldIDs = (TextView) findViewById(R.id.textViewIDs);

        mRefRoot = new Firebase("https://crowdweather-616d0.firebaseio.com");

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

                mTextFieldIDs.setText("Main: "+main+"\n"+
                                            "Compost: "+compost+"\n"+
                                            "Landfill: "+landfill+"\n"+
                                            "Recycling: "+recycling);
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

                mTextFieldIDs.setText("Location: "+location);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        mListView.setAdapter(adapter);


        //For Child events

//        messagesRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String message = dataSnapshot.getValue(String.class);
//                //mTextFieldCondition.setText("added");
//
//                mMessages.add(message);
////                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                String message = dataSnapshot.getValue(String.class);
//                mTextFieldCondition.setText("changed");
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                String message = dataSnapshot.getValue(String.class);
//                mTextFieldCondition.setText("removed");
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String text = dataSnapshot.getValue(String.class);
//                mTextFieldCondition.setText(text);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        mButtonFoggy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mRef.setValue("Foggy");
//            }
//        });
//
//        mButtonSunny.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mRef.setValue("Sunny");
//            }
//        });


        //Fiz up ListView stuff

//        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_expandable_list_item_1, messagesRef) {
//            @Override
//            protected void populateView(View view, String s, int i) {
//                mTextFieldCondition.setText(s);
//            }
//        };
//        mListView.setAdapter(adapter);
    }
}
