/**
 * @author		Yuval Navon <yuvalnavon8@gmail.com>
 * @version	1.0
 * @since		12/1/2022
 * Just a credits screen with an OptionsMenu to go back to the main activity.
 */
package com.example.internalfilesex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Credits extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);


    }

    /**
     * Creates a general OptionsMenu which allows the user to choose which
     * allows the user to choose which screen they want to use, the main
     * screen or the credits screen
     *
     *
     * @param    Menu menu -	required for the function to inflate the menu layout into
     *                the Menu object which it creates.
     * @return	true
     */
    public boolean onCreateOptionsMenu (Menu menu)
    {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * The function decides which option the user chose, and acts accordingly.
     * If they chose the Menu option, the function will go back to the first/main activity.
     * Else, nothing happens.
     *
     *
     * @param    MenuItem item - the option which the user chose.
     * @return	true
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.Main)
        {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        return true;

    }
}