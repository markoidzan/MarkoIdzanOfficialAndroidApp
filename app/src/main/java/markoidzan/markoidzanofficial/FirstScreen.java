package markoidzan.markoidzanofficial;
// Special Fragment in code of application
// Imports for source code to work
import android.os.Bundle;
// IMPORTANT: USE THIS Fragment not android.app.Fragment otherwise you should have a bad day with NPE
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * @author Marko Idžan
 * @version 1.0
 */
// Declaring public class with extension of android.support.v4.app.Fragment (again not android.app.Fragment)
public class FirstScreen extends Fragment {

    public FirstScreen() { } // Required empty constructor

    @Override
    // Only method used in Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Expanding code to fragments layout
        View rootView = inflater.inflate(R.layout.fragment_first_screen, container, false);
        // Declaration of variable which needs to be final
        final WebView webStranica;
        // Setting up WebView
        webStranica = (WebView) rootView.findViewById(R.id.activity_main_webview);
        // Loading site which have content, which is this case in assets folder in app root
        webStranica.loadUrl("file:///android_asset/index.html");
        // Standard return of view
        return rootView;
    }
}
