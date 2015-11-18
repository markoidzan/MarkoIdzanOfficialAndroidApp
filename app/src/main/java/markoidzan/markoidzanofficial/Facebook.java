package markoidzan.markoidzanofficial;
// Note: code for all other fragments is same, so it would be described just once
// (exception is FirstScreen, explain that code separately)
// Imports for source code to work
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
// IMPORTANT: USE THIS Fragment not android.app.Fragment otherwise you should have a bad day with NPE
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

/**
 * @author Marko Idžan
 * @version 1.0
 */
// Declaring public class with extension of android.support.v4.app.Fragment (again not android.app.Fragment)
public class Facebook extends Fragment{

    public Facebook() { } // Required empty constructor

    private ProgressBar progressBar; // Declaration of only special variable used in code

    @Override
    // Only method used in Fragments
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Expanding code to fragments layout
        View rootView = inflater.inflate(R.layout.fragment_facebook, container, false);
        // Declaration of variable which needs to be final
        final WebView webStranica;
        // Setting up WebView
        webStranica = (WebView) rootView.findViewById(R.id.activity_main_webview);
        // Setting up Progress Bar on top of Fragment (and below Toolbar)
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        // Enabling JavaScript
        WebSettings javascriptUkljucen = webStranica.getSettings();
        javascriptUkljucen.setJavaScriptEnabled(true);
        // Override site code if it isn't on setted domain (load it in browser, not in application)
        webStranica.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().contains("markoidzan.from.hr")) {
                    return false;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }


        });
        // Showing up Progress Bar
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
        // Loading site which have content
        webStranica.loadUrl("http://markoidzan.from.hr/facebook");
        // Getting back one page, but if it is last exit application
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
        // Standard return of view
        return rootView;
    }
}
