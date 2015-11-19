package markoidzan.markoidzanofficial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import markoidzan.markoidzanofficialdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstScreen extends Fragment {


    public FirstScreen() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first_screen, container, false);

        final WebView webStranica;

        webStranica = (WebView) rootView.findViewById(R.id.activity_main_webview);

        webStranica.loadUrl("file:///android_asset/index.html");


        return rootView;


    }


}
