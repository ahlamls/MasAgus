package tech.krds.masagus.restartmodem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static tech.krds.masagus.restartmodem.BikinAPKModMandul7Turunan.my_shared_preferences;

public class UpgradeActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    Long bw;
    int modem;
    TextView textView4 , textView6;
    Boolean tamat;
    ImageView imageView3;
    Button apgretModem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        bw = sharedpreferences.getLong("bw", 0L);
        modem = sharedpreferences.getInt("modem", 1);
        apgretModem = findViewById(R.id.button2);
        imageView3 = findViewById(R.id.imageView3);
        refreshBw();
        refreshModem();



        Button pekalongan = findViewById(R.id.pekalonganBtn);
        pekalongan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                citPekalongan();
            }
        });


        apgretModem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int hargaApgret = 69;
                if (modem == 1) {
                    hargaApgret = 200;
                } else if (modem == 2) {
                    hargaApgret = 1000;
                } else if (modem == 3) {
                    hargaApgret = 5000;
                } else if (modem == 4) {
                    hargaApgret = 15000;
                } else if (modem == 5) {
                    hargaApgret = 50000;
                } else if (modem == 6) {
                    Toast.makeText(getApplicationContext(),"Modem kamu sudah berada di level maksimal . tunggu apdet selanjutnya",Toast.LENGTH_LONG).show();
                }

                if (modem != 6 ){

                    if (bw < hargaApgret) {
                        Toast.makeText(getApplicationContext(),"Bandwidth tidak cukup",Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putLong("bw", bw - hargaApgret);
                        editor.putInt("modem", modem + 1);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Sukses Upgrade Modem . Jangan lupa selalu direstart selagi memakai ISP indihomo",Toast.LENGTH_LONG).show();
                        refreshBw();
                        refreshModem();
                    }


                }
            }
        });

        Button gantiISP = findViewById(R.id.button29);
        gantiISP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tamat) {
                    Toast.makeText(getApplicationContext(), "Kamu sudah tamat", Toast.LENGTH_LONG).show();

                } else {
                    if (bw >= 690420) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putLong("bw", bw - 690420);
                        editor.putBoolean("tamat", true);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Selamat! Kamu sudah tamat di game ini! . Selamat merasakan koneksi tanpa lag dan restart modem dengan ISP lain", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sayangnya ISP lain belum masuk ke daerah kamu . alias bandwidth kamu belum cukup", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        ImageView backImage = findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(UpgradeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    void citPekalongan() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(getApplicationContext());
        alert.setMessage("Masukan Cit");
        alert.setTitle("Cit Pekalongan");
        alert.setIcon(R.drawable.masaguslogo);

        alert.setView(edittext);

        alert.setPositiveButton("Masukan Cit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String cit = edittext.getText().toString();
                if (cit.toLowerCase().equals("hesoyam")) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putLong("bw", bw + 250000);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Cheat ini belum diuji di IPB dan ITB",Toast.LENGTH_LONG).show();
                } else if (cit.toLowerCase().equals("motherlode")) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putLong("bw", bw + 50000);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Maklo sayur lodeh",Toast.LENGTH_LONG).show();
                } else if (cit.toLowerCase().equals("177013")) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putLong("bw", bw + 177013);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Bersihkan otaklu dengan antipirus smadav hek satelit",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Cit tidak ditemukan",Toast.LENGTH_LONG).show();
                }
                refreshBw();
                dialog.dismiss();
            }
        });



        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
               dialog.dismiss();
            }
        });

        alert.show();
    }

    void refreshBw() {
        bw = sharedpreferences.getLong("bw", 0L);
        textView4.setText("Bandwidth : " + String.valueOf(bw) + " MB");
    }

    void refreshModem() {
        modem = sharedpreferences.getInt("modem", 1);
        if (modem == 1) {
            imageView3.setBackgroundResource(R.drawable.modem2off);
            textView6.setText("Upgrade Modem ke Lv.2 . Dapatkan Kecepatan Internet Up To 5MBps");
            apgretModem.setText("200 MB");
        } else if (modem == 2) {
            imageView3.setBackgroundResource(R.drawable.modem3off);
            textView6.setText("Upgrade Modem ke Lv.3 . Dapatkan Kecepatan Internet Up To 20MBps");
            apgretModem.setText("1000 MB");
        } else if (modem == 3) {
            imageView3.setBackgroundResource(R.drawable.modem4off);
            textView6.setText("Upgrade Modem ke Lv.4 . Dapatkan Kecepatan Internet Up To 50MBps");
            apgretModem.setText("5000 MB");
        } else if (modem == 4) {
            imageView3.setBackgroundResource(R.drawable.modem5off);
            textView6.setText("Upgrade Modem ke Lv.5 . Dapatkan Kecepatan Internet Up To 100MBps");
            apgretModem.setText("15000 MB");
        } else if (modem == 5) {
            imageView3.setBackgroundResource(R.drawable.modem6off);
            textView6.setText("Upgrade Modem ke Lv.6 . Dapatkan Kecepatan Internet Up To 1GBps");
            apgretModem.setText("50000 MB");
        } else if (modem == 6) {
            imageView3.setBackgroundResource(R.drawable.modem6on);
            textView6.setText("Modem kamu sudah rata kanan pake RTX 2080 Titit nambah stat +8 physical attack");
            apgretModem.setText("RATA KANAN");
        }
    }

}
