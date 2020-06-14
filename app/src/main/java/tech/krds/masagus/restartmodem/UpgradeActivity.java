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
    int modem,bg,hp,chars;
    TextView textView4 , textView6,textView61,textView62;
    Boolean tamat,ubur,pras;
    ImageView imageView3,imageView31,imageView32;
    Button apgretModem,apgretBg,skinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView61 = findViewById(R.id.textView61);
        textView62 = findViewById(R.id.textView62);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        bw = sharedpreferences.getLong("bw", 0L);
        modem = sharedpreferences.getInt("modem", 1);
        chars = sharedpreferences.getInt("char", 1);
        pras = sharedpreferences.getBoolean("pras",false);
        ubur = sharedpreferences.getBoolean("ubur",false);
        apgretModem = findViewById(R.id.button2);
        apgretBg  = findViewById(R.id.button21);
        imageView3 = findViewById(R.id.imageView3);
        imageView31 = findViewById(R.id.imageView31);
        imageView32 = findViewById(R.id.imageView32);
        skinBtn = findViewById(R.id.button22);
        refreshBw();
        refreshModem();
        refreshBg();
        refreshChar();



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

        apgretBg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int hargaApgret = 69;
                if (bg == 1) {
                    hargaApgret = 100;
                } else if (bg == 2) {
                    hargaApgret = 1000;
                } else if (bg == 3) {
                    hargaApgret = 10000;
                } else if (bg == 4) {
                    hargaApgret = 100000;
                } else if (bg == 5) {
                    Toast.makeText(getApplicationContext(),"Background kamu sudah berada di level maksimal . tunggu apdet selanjutnya",Toast.LENGTH_LONG).show();
                }

                if (bg != 5 ){

                    if (bw < hargaApgret) {
                        Toast.makeText(getApplicationContext(),"Bandwidth tidak cukup",Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putLong("bw", bw - hargaApgret);
                        editor.putInt("bg", bg + 1);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Sukses Upgrade Background",Toast.LENGTH_LONG).show();
                        refreshBw();
                        refreshBg();
                    }


                }
            }
        });

        skinBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int hargaPras = 69420;

                if (!pras){

                    if (bw < hargaPras) {
                        Toast.makeText(getApplicationContext(),"Bandwidth tidak cukup",Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putLong("bw", bw - hargaPras);
                        editor.putBoolean("pras", true);
                        editor.putInt("char",2);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Sukses Membeli Mas Pras",Toast.LENGTH_LONG).show();
                        refreshBw();
                        refreshChar();

                    }


                } else {

                    if (chars == 1) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt("char",2);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Sukses Mengubah Karakter menjadi Mas Pras",Toast.LENGTH_LONG).show();
                        refreshChar();
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt("char",1);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Sukses Mengubah Karakter menjadi Mas Agus",Toast.LENGTH_LONG).show();
                        refreshChar();
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
                        editor.putLong("bw", bw - 6969420);
                        editor.putBoolean("tamat", true);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Selamat! Kamu sudah tamat di game ini! . Selamat merasakan koneksi tanpa lag dan restart modem dengan ISP lain", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sayangnya ISP lain belum masuk ke daerah kamu . alias bandwidth kamu belum cukup", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        final Button uburBtn = findViewById(R.id.button24);
        TextView textView64 = findViewById(R.id.textView64);
        if (ubur) {
            uburBtn.setText("Sudah Dibeli");
            textView64.setText("Kamu Sudah Memiliki Ubur-Ubur . Mas Agus dan Pras Memiliki bagian yang berbeda");
        } else {
            textView64.setText("Kamu Belum Memiliki Ubur-Ubur ");
        }
        uburBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ubur) {
                    Toast.makeText(getApplicationContext(), "Kamu sudah Memiliki Ubur Ubur", Toast.LENGTH_LONG).show();

                } else {
                    if (bw >= 690420) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putLong("bw", bw - 690420);
                        editor.putBoolean("ubur", true);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Selamat! Kamu Sukses Membeli Ubur-ubur", Toast.LENGTH_LONG).show();
                        uburBtn.setText("Sudah Dibeli");
                    } else {
                        Toast.makeText(getApplicationContext(), "Sayangnya bandwidth kamu belum cukup buat beli ubur-ubur", Toast.LENGTH_LONG).show();
                    }
                }

                ubur = sharedpreferences.getBoolean("ubur",false);
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
                } else if (cit.toLowerCase().equals("misqueen")) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putLong("bw", 0);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Pitur awto misqueen aktif",Toast.LENGTH_LONG).show();
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

    void refreshBg() {
        bg = sharedpreferences.getInt("bg", 1);
        if (bg == 1) {
            imageView31.setBackgroundResource(R.drawable.floorlv2);
            textView61.setText("Upgrade Background ke Lv.2 . Dapatkan Pengganda Bandwidth 2x");
            apgretBg.setText("100 MB");
        } else if (bg == 2) {
            imageView31.setBackgroundResource(R.drawable.floorlv3);
            textView61.setText("Upgrade Background ke Lv.3 . Dapatkan Pengganda Bandwidth 3x");
            apgretBg.setText("1000 MB");
        } else if (bg == 3) {
            imageView31.setBackgroundResource(R.drawable.floorlv4);
            textView61.setText("Upgrade Background ke Lv.4 . Dapatkan Pengganda Bandwidth 4x");
            apgretBg.setText("10000 MB");
        } else if (bg == 4) {
            imageView31.setBackgroundResource(R.drawable.floorlv5);
            textView61.setText("Upgrade Background ke Lv.5 . Dapatkan Pengganda Bandwidth 5x + RGB warna warni");
            apgretBg.setText("100000 MB");
        } else if (bg == 5) {
            imageView31.setBackgroundResource(R.drawable.floorlv5);
            textView61.setText("Background kamu sudah rata kanan pake R9 3950X nambah stat +100 HP");
            apgretBg.setText("RATA KANAN");
        }
    }

    void refreshChar() {
        chars = sharedpreferences.getInt("char", 1);
        pras = sharedpreferences.getBoolean("pras",false);
        if (chars == 1) {
            //agus
            imageView32.setImageResource(R.drawable.maspras);
            if (pras) {
                skinBtn.setText("Ganti Menjadi Mas Pras");
            } else {
                skinBtn.setText("69420 MB");
            }
            textView62.setText("Ganti Skin Karakter menjadi Mas Pras . Tidak Menambah stat seperti moba analog dan tanpa pintu");
        } else {
            //pras
            imageView32.setImageResource(R.drawable.masagus);
            skinBtn.setText("Ganti Menjadi Mas Agus");
            textView62.setText("Ganti Skin Karakter menjadi Mas Agus . Tidak Menambah stat seperti moba analog dan tanpa pintu");

        }
    }

}
