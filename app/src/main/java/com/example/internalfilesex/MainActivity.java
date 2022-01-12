/**
 * @author		Yuval Navon <yuvalnavon8@gmail.com>
 * @version	1.0
 * @since		12/1/2022
 * The program lets the user input text and write it in the phone's internal storage in a txt file.
 * It also lets him reset the file, exit and read the file upon starting the app again.
 */
package com.example.internalfilesex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    FileOutputStream fos;
    FileInputStream fis;
    OutputStreamWriter osw;
    InputStreamReader isr;
    BufferedWriter bw;
    BufferedReader br;
    StringBuffer sb;
    String text;
    EditText et;
    TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextTextPersonName);
        tv = findViewById(R.id.textView2);
        tv.setText("");
        text = "";



    }

    /**
     * Reads the test.txt file, saves its content to the String text and puts
     * it in the textView tv.
     * @param   None
     * @return	None
     */
    @Override
    protected void onStart() {
        super.onStart();
        try {
            fis= openFileInput("test.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            text=sb.toString();
            br.close();
            tv.setText(text);



        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Adds the user's input into the test.txt file.
     * @param   None
     * @return	None
     */
    public void Saver()
    {
        try {
            fos = openFileOutput("test.txt",MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(text);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the "SAVE" Button is pressed.
     * Uses the Saver function and sets the entire text that the user inputted in the textView tv.
     * @param   None
     * @return	None
     */
    public void Save(View view)
    {
        text += et.getText().toString();
        Saver();
        tv.setText(text);

    }

    /**
     * Called when the "RESET" Button is pressed.
     * Resets the textView tv, String text and uses the Saver function to reset the test.txt file.
     * @param   None
     * @return	None
     */
    public void Reset(View view)
    {
        text = "";
        Saver();
        tv.setText("");
        et.setText("");


    }

    /**
     * Called when the "EXIT" Button is pressed.
     * Uses the Saver function and exits the app.
     * @param   None
     * @return	None
     */
    public void Exit(View view)
    {

        text+=et.getText().toString();
        Saver();
        finish();
    }



    /**
     *  Creates a general OptionsMenu which allows the user to choose which
     *  allows the user to choose which screen they want to use, the main
     *  screen or the credits screen.
     *
     *
     * @param    Menu menu -required for the function to inflate the menu layout into
     *           the Menu object which it creates.
     * @return	true
     */
    public boolean onCreateOptionsMenu (Menu menu)

    {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * Decides which option the user chose and sends them to the other screen
     * if they picked it.
     *
     *
     * @param    MenuItem item - the option which the user chose.
     * @return	true
     */
    public boolean onOptionsItemSelected(MenuItem item)

    {
        int id = item.getItemId();
        if (id == R.id.Credits)
        {
            Intent si = new Intent(this, Credits.class);
            startActivity(si);
        }
        return true;


    }

}
