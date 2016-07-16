package app.android.example.com.smartcan;

import com.firebase.client.Firebase;

/**
 * Created by lab on 7/15/2016.
 */
public class SmartCanDB extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
<<<<<<< HEAD
//        Intent intent = new Intent(this, FrontPage.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
=======
>>>>>>> 9ff27d0b29d1a412b1e2bd2d361e67078e433c53
    }
}
