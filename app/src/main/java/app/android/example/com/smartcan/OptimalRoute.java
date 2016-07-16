package app.android.example.com.smartcan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by lab on 7/16/2016.
 */
public class OptimalRoute extends AppCompatActivity{

    private String[] locationsArray = {"(37.7789797, -122.3909473)",
            "(37.7772103, -122.39317199999999)",
            "(37.7793813, -122.39837940000001)",
            "(37.779081, -122.39561709999998)",
            "(37.7798165, -122.39464909999998)",
            "(37.7818984, -122.3920387)",
            "(37.7798146, -122.3928692)",
            "(37.7792948, -122.38848610000002)",
            "(37.7789797, -122.3909473)"};
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optimal_route);

        String url = "http://gebweb.net/optimap/";
        webView = (WebView) this.findViewById(R.id.webView);
        assert webView != null;
        webView.loadUrl("javascript: fillUpForm(inputId,inputValue)");
//        webView.getSettings().seeJavaScriptEnable(true);
        webView.loadUrl(url);

    }
}
