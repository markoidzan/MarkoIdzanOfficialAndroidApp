package markoidzan.markoidzanofficial;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import markoidzan.markoidzanofficialdemo.R;

/**
 * Built by Marko in 2015
 * Updated in 2017
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private  ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setToolbar();
        initView();

        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState ==null ? R.id.pocetno: savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);

    }

    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void initView() {
        mDrawer= (NavigationView) findViewById(R.id.navigation_view);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer);
    }

    private void itemSelection(int mSelectedId) {



        Fragment fragment = null;

        Class fragmentClass = null;
        switch(mSelectedId){
            case R.id.pocetno:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentClass = WebSite.class;
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
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

    }




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId=menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID", mSelectedId);
    }

}
