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
    }
}
