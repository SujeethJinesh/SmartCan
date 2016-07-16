package app.android.example.com.smartcan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by lab on 7/16/2016.
 */
public class ListPage extends AppCompatActivity {

//    RecyclerView mRecyclerView;
    Firebase mRefRoot = new Firebase("https://smartcan-1374.firebaseio.com/Can1");
    TextView mTextFieldIDs;
    TextView mTextFieldIsFull;
    TextView mTextFieldLocations;
    ImageButton mImageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        mTextFieldIDs = (TextView) findViewById(R.id.textViewIDs);
        mTextFieldIsFull = (TextView) findViewById(R.id.textViewIsFull);
        mTextFieldLocations = (TextView) findViewById(R.id.textViewLocation);
        mImageButton = (ImageButton) findViewById(R.id.trashCanButton);

        final Intent intent = new Intent(this, ConsumerStatus.class);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });


//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mRefRoot.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String s = dataSnapshot.getValue(String.class);
//                TextView tv = (TextView) mListView.findViewById(R.id.text1);
//                tv.setText(s);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

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

                if (compost.equals("true") || landfill.equals("true") || main.equals("true") || recycling.equals("true")) {
                    //TODO: Add AT&T push to talk here
                    sendMessage("4257853952");
                    return;
                }

                mTextFieldIsFull.setText("Main: "+main+"\n"+
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

                mTextFieldLocations.setText("Location: "+location);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//        FirebaseRecyclerAdapter<String, MessageViewHolder> adapter = new FirebaseRecyclerAdapter<String, MessageViewHolder>(String.class, android.R.layout.two_line_list_item, MessageViewHolder.class, mRefRoot) {
//            @Override
//            protected void populateViewHolder(MessageViewHolder messageViewHolder, String s, int i) {
//                messageViewHolder.mText.setText(s);
//            }
//        };
//        mRecyclerView.setAdapter(adapter);

//        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, mRefRoot) {
//            @Override
//            protected void populateView(View view, String s, int i) {
//                TextView textView = (TextView) view.findViewById(android.R.id.text1);
//                textView.setText(s);
//            }
//        };
//
//        mRecyclerView.setAdapter(adapter);

//        mRefRoot = new Firebase("https://smartcan-1374.firebaseio.com");
//        mListView = (ListView) findViewById(R.id.list_to_populate);
//
//        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, mRefRoot) {
//            @Override
//            protected void populateView(View view, String s, int i) {
//                TextView textView = (TextView) view.findViewById(android.R.id.text1);
//                textView.setText(s);
//            }
//        };
//
//        mListView.setAdapter(adapter);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        public MessageViewHolder(View v) {
            super(v);
            mText = (TextView) v.findViewById(android.R.id.text1);
        }
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
