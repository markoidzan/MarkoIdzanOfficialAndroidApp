package markoidzan.markoidzanofficial;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import markoidzan.markoidzanofficialdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Instagram extends Fragment {


    public Instagram() {
        // Required empty public constructor
    }


    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instagram, container, false);


        final WebView webStranica;

        webStranica = (WebView) rootView.findViewById(R.id.activity_main_webview);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar);

        WebSettings javascriptUkljucen = webStranica.getSettings();

        javascriptUkljucen.setJavaScriptEnabled(true);

        webStranica.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().contains("instagram.com")) {
                    return false;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }


        });


        webStranica.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && progressBar.getVisibility() == ProgressBar.GONE)
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                progressBar.setProgress(progress);
                if (progress == 100)
                    progressBar.setVisibility(ProgressBar.GONE);

            }

        });

        webStranica.loadUrl("https://www.instagram.com/markoidzan/");

        webStranica.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                    }
                }
                return false;
            }
        });

        return rootView;


    }


}
