package be.intec.forzajuno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import be.intec.forzajuno.model.Kalender;
import be.intec.forzajuno.model.Speler;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, SpelersFragment.CallBacks, SpelersDetailFragment.CallBacksUpdaten, KalenderFragment.CallBacksKalender {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Hide softKeyboard in case its open:
//        InputMethodManager inputMethodManager = (InputMethodManager)  this.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();

        if (position == 0) {
            loadFragment(new PlaceholderFragment());
            onSectionAttached(1);
        } else if (position == 1) {
            loadFragment(new SpelersFragment());
            onSectionAttached(2);
        }
        else if(position==2){
            loadFragment(new KalenderFragment());
            onSectionAttached(3);
        }
        else if (position == 4) {
            loadFragment(new ContactFragment());
            onSectionAttached(5);
        }
    }

    private void loadFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Passing data between SpelersFragment and SpelersDetailFragment with Activity!
    @Override
    public void onItemSelected(String volledigeNaam, int position) {
        SpelersDetailFragment df = new SpelersDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("vn", volledigeNaam);
        args.putInt("pos", position);
        df.setArguments(args);
        getSupportActionBar().setTitle(volledigeNaam);
        fragmentManager.beginTransaction().replace(R.id.container, df).commit();
    }

    // Passing data between SpelerDetailFragment and SpelerUpdatenFragment with Activity!
    @Override
    public void spelerUpdaten(Speler speler) {
        SpelerUpdatenFragment updatenFragment = new SpelerUpdatenFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putSerializable("speler", speler);
        updatenFragment.setArguments(args);
        getSupportActionBar().setTitle(speler.getVoornaam() + " " + speler.getAchternaam());
        fragmentManager.beginTransaction().replace(R.id.container,updatenFragment).commit();
    }

    // Passing data between KalenderFragment and KalenderDetailFragment with Activity:
    @Override
    public void onItemSelected(Kalender kalender) {
        KalenderDetailFragment detailFragment = new KalenderDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putSerializable("kalender", kalender);
        detailFragment.setArguments(args);
        getSupportActionBar().setTitle(kalender.getMatch());
        fragmentManager.beginTransaction().replace(R.id.container, detailFragment).commit();

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
