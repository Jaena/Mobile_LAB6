package org.androidtown.lab6;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.txtData);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        File sdCard = Environment.getExternalStorageDirectory();
        final File file = new File(sdCard, "textfile.txt");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    file.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter out = new OutputStreamWriter(fOut);
                    out.write(editText.getText().toString());
                    out.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"에러가 났어!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"에러가 났어!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"1. WRITE SD FILE success",Toast.LENGTH_LONG).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            editText.setText("");
                Toast.makeText(getApplicationContext(),"2. CLEAR SCREEN ",Toast.LENGTH_LONG).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fIn = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fIn);
                    BufferedReader reader = new BufferedReader(isr);

                    String str = "";
                    StringBuffer buf = new StringBuffer();

                    while((str = reader.readLine())!=null)
                    {
                        buf.append(str+"\n");
                    }
                    isr.close();
                    editText.setText(buf.toString());
                } catch (Throwable t)
                {
                    Toast.makeText(getApplicationContext(),"에러가 났어!",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),"3. READ SD FILE success",Toast.LENGTH_LONG).show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"4. FINISH success",Toast.LENGTH_LONG).show();
            finish();
            }
        });
    }

}
