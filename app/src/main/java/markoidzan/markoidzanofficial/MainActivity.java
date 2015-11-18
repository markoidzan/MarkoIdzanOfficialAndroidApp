package markoidzan.markoidzanofficial;
// Imports for source code to work
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
// IMPORTANT: USE THIS Fragment not android.app.Fragment otherwise you should have a bad day with NPE
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * @author Marko Idžan
 * @version 1.0
 */
// Main Activity which extends one method and implements second method
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Decalaration of variables which are used in code
    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    // Main work is doing here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting content View to be from MainActivity
        setContentView(R.layout.activity_main);
        // Initializing methods for Toolbar and default view
        setToolbar();
        initView();
        // Setting Drawer Toggle aka Hamburger Menu
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerToggle.syncState();
        // Run Main screen as default on every run
        mSelectedId = savedInstanceState == null ? R.id.pocetno : savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);
    }

    // Method for setting Toolbar (check initializing in method above)
    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    // Method for setting default view (check initializing two methods above)
    private void initView() {
        mDrawer = (NavigationView) findViewById(R.id.navigation_view);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
    }

    // Select option from menu method
    private void itemSelection(int mSelectedId) {
        // Use android.support.v4.app.Fragment not android.app.Fragment otherwise there should be problems later on
        // NPE (Null Pointer Exception) because of wrong casting
        Fragment fragment = null;
        // Class inside method for gathering Fragments which are shown up
        Class fragmentClass = null;
        // Classic Switch-Case structure
        switch(mSelectedId) {
            // Explanation of code on one case
            // select which part of our drawer we need to select (R.id.something)
            case R.id.pocetno:
                // When selected close drawer to see what is happening
                mDrawerLayout.closeDrawer(GravityCompat.START);
                // Loading our Fragment which we write separately
                fragmentClass = FirstScreen.class;
                // Break running any more code
                break;
            case R.id.youtube:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentClass = YouTube.class;
                break;
            case R.id.facebook:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentClass = Facebook.class;
                break;
            case R.id.twitter:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentClass = Twitter.class;
                break;
            case R.id.instagram:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentClass = Instagram.class;
                break;
            case R.id.website:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentClass = WebSite.class;
                break;
        }
        // Try-Catch for catching exceptions
        try {
            // If fragment (again android.support.v4.app.Fragment not android.app.Fragment) is setted up for
            // doing new Instance and if there is no exception it would do code below
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e) {
            // Otherwise it would catch exception and write it in console (logcat to be clear)
            e.printStackTrace();
        }
        // Setting up Fragment Manager so it can expand our fragments which we want to load
        FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentManager replace part which is reserved for showing fragment with fragment which is loaded
        // in switch-case above
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }

    // Overriding method onConfigurationChanged to work with new configuration
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    // Method which MUST be overrriden (check implementation part of decalaration of this MainActivity class)
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem){
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    // Method for checking state of drawer and use it when screen rotates and when starting up app
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("SELECTED_ID", mSelectedId);
    }
}
