package com.example.internalfilesex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    String text, saved;
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextTextPersonName);
        tv = findViewById(R.id.textView2);
        text = "";
        saved = "";
        try {
            fos = openFileOutput("test.txt",MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write("hello");
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void Save(View view)
    {
        text = et.getText().toString();
        try {
            bw.write(saved + text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            saved =sb.toString();
            tv.setText(saved);
            br.close();


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void Reset(View view)
    {
        try {
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tv.setText("");
        et.setText("");
        text = "";
        saved = "";
    }

    public void Exit(View view)
    {

    }



    public boolean onCreateOptionsMenu (Menu menu)
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
    {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    /**
     * Decides which option the user chose and sends them to the other screen
     * if they picked it.
     *
     *
     * @param    MenuItem item - the option which the user chose.
     * @return	true
     */
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
