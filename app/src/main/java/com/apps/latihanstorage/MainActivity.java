package com.apps.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FILENAME = "namafile.txt";
    Button buatFile, ubahFile, bacaFile, deleteFile;
    TextView textbaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buatFile = findViewById(R.id.buttonbuat);
        ubahFile = findViewById(R.id.buttonubah);
        bacaFile = findViewById(R.id.buttonbaca);
        deleteFile = findViewById(R.id.buttonhapus);
        textbaca = findViewById(R.id.textBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);
    }

    void buatFile(){
        String isiFile ="Coba Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void ubahFile() {
        String ubah = "Update Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
    void bacaFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);

        if (file.exists()) { //Pengecekan file
            StringBuilder text = new StringBuilder(); //Penampung file saat mengambil file nanti
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            textbaca.setText(text.toString());
        }
    }
    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) { //untuk mengecek already file yg exists
            file.delete();
        }
    }
    public void  jalankanPerintah(int id) {
        switch (id) {
            case R.id.buttonbuat:buatFile();break;
            case R.id.buttonubah:ubahFile();break;
            case R.id.buttonbaca:bacaFile();break;
            case R.id.buttonhapus:hapusFile();break;
        }
    }
    @Override
    public void onClick(View view) {
        jalankanPerintah(view.getId());

    }
}
