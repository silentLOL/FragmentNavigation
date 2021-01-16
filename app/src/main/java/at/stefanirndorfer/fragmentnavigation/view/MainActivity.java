package at.stefanirndorfer.fragmentnavigation.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import at.stefanirndorfer.fragmentnavigation.AFragment;
import at.stefanirndorfer.fragmentnavigation.BFragment;
import at.stefanirndorfer.fragmentnavigation.R;
import at.stefanirndorfer.fragmentnavigation.input.NavigationListener;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NavigationListener {


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {

            MainScreenFragment mainScreenFragment = new MainScreenFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_fragment_container, mainScreenFragment)
                    .commit();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Timber.d("Option item home pressed");
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void navigateToFragmentA() {
        Fragment fragmentA = AFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragmentA)
                .addToBackStack(fragmentA.getClass().getCanonicalName())
                .commit();
    }

    @Override
    public void navigateToFragmentB() {
        Fragment fragmentB = BFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragmentB)
                .addToBackStack(fragmentB.getClass().getCanonicalName())
                .commit();
    }

    @Override
    public void showHomeButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void hideHomeButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}