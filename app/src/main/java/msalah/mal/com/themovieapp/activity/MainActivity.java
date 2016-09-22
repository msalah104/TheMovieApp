package msalah.mal.com.themovieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static final String FULL_LIST = "list";

    public static final String FAVORITES = "fav";

    public volatile static String shownListType = FULL_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new MainFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_settings:
                //Toast.makeText(this, "ADD!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MovieAppPreferencesActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_main_list:
                 MainActivity.shownListType = FULL_LIST;
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new MainFragment()).commit();
                return super.onOptionsItemSelected(item);
            case R.id.menu_main_favorites:
                MainActivity.shownListType = FAVORITES;
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new MainFragment()).commit();
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }


}
