package tech.krds.masagus.restartmodem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.Random;

import static tech.krds.masagus.restartmodem.BikinAPKModMandul7Turunan.my_shared_preferences;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    Long bw;
    int modem;
    Boolean tamat;
    Boolean modemnyala = false;
    int increment;
    TextView bw_tv;
    ImageView imageView6;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    Button showAdBtn;
    private RewardedAd rewardedAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        showAdBtn = findViewById(R.id.showAdBtn);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        //test
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        //real
        mInterstitialAd.setAdUnitId("ca-app-pub-1948241097718965/4833913414");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        //test
        //rewardedAd = new RewardedAd(this,
        //        "ca-app-pub-3940256099942544/5224354917");
        //real
        rewardedAd = new RewardedAd(this, "ca-app-pub-1948241097718965/7729817295");
        showAdBtn.setVisibility(View.GONE);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
               showAdBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);


        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        bw = sharedpreferences.getLong("bw", 0L);
        modem = sharedpreferences.getInt("modem", 1);
        tamat = sharedpreferences.getBoolean("tamat", false);
        if (tamat) {
            showDialogx(1);
        }

        imageView6 = findViewById(R.id.imageView6);
        if (modem == 1) {
            increment = 1;
            imageView6.setBackgroundResource(R.drawable.modem1off);
        } else if (modem == 2) {
            increment = 5;
            imageView6.setBackgroundResource(R.drawable.modem2off);
        } else if (modem == 3) {
            increment = 20;
            imageView6.setBackgroundResource(R.drawable.modem3off);
        } else if (modem == 4) {
            increment = 50;
            imageView6.setBackgroundResource(R.drawable.modem4off);
        } else if (modem == 5) {
            increment = 100;
            imageView6.setBackgroundResource(R.drawable.modem5off);
        } else if (modem == 6) {
            increment = 1000;
            imageView6.setBackgroundResource(R.drawable.modem6off);
        }


        showAdBtn.setText("Lihat Iklan (" + String.valueOf(increment * 50) + " MB)");
        showAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedAd.isLoaded()) {
                    Activity activityContext = MainActivity.this;
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            // Ad opened.
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            // Ad closed.
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putLong("bw", bw + (increment * 50));
                            editor.apply();
                            refreshBw();
                            Toast.makeText(getApplicationContext(),"Sukses Mendapatkan Reward . Bandwidth " + String.valueOf(increment * 50) + " MB",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {
                            Toast.makeText(getApplicationContext(),"Gagal Mendapatkan Reward . pastikan menghabiskan videonya sampe beres",Toast.LENGTH_LONG).show();
                        }
                    };
                    rewardedAd.show(activityContext, adCallback);
                } else {
                    Toast.makeText(getApplicationContext(),"Iklan Belum Siap",Toast.LENGTH_LONG).show();
                }
            }
        });



        Button button = (Button) findViewById(R.id.helpBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialogx(2);
            }
        });
        bw_tv = findViewById(R.id.bw_tv);
        refreshBw();

        Button buttonx = (Button) findViewById(R.id.tokoBtn);
        buttonx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,UpgradeActivity.class);
                if (mInterstitialAd.isLoaded()) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(10 - 1) + 1;
                    if (randomNumber < 8) {
                        mInterstitialAd.show();
                    }

                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                startActivity(i);
                finish();
            }
        });


        imageView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (modemnyala) {
                    modemnyala = false;
                    if (modem == 1) {
                        imageView6.setBackgroundResource(R.drawable.modem1off);
                    } else if (modem == 2) {
                        imageView6.setBackgroundResource(R.drawable.modem2off);
                    } else if (modem == 3) {
                        imageView6.setBackgroundResource(R.drawable.modem3off);
                    } else if (modem == 4) {
                        imageView6.setBackgroundResource(R.drawable.modem4off);
                    } else if (modem == 5) {
                        imageView6.setBackgroundResource(R.drawable.modem5off);
                    } else if (modem == 6) {
                        imageView6.setBackgroundResource(R.drawable.modem6off);
                    }
                } else {
                    modemnyala = true;
                    if (modem == 1) {
                        imageView6.setBackgroundResource(R.drawable.modem1on);
                    } else if (modem == 2) {
                        imageView6.setBackgroundResource(R.drawable.modem2on);
                    } else if (modem == 3) {
                        imageView6.setBackgroundResource(R.drawable.modem3on);
                    } else if (modem == 4) {
                        imageView6.setBackgroundResource(R.drawable.modem4on);
                    } else if (modem == 5) {
                        imageView6.setBackgroundResource(R.drawable.modem5on);
                    } else if (modem == 6) {
                        imageView6.setBackgroundResource(R.drawable.modem6on);
                    }
                }

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putLong("bw", bw + increment);
                editor.apply();
                refreshBw();

                if (mInterstitialAd.isLoaded()) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(50 - 1) + 1;
                    if (randomNumber < 2) {
                        mInterstitialAd.show();
                    }

                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });




    }

    void refreshBw() {
        bw = sharedpreferences.getLong("bw", 0L);
        bw_tv.setText(String.valueOf(bw) + " MB");
    }

    private void showDialogx(final int type){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        String message = "";
        String title="";
        if (type == 1) {
            title = "Tamat";
            message = "Kamu Sudah tamat game ini . Selamat kamu sudah berpindah ISP dari indigay dan tidak perlu lagi melakukan restart router . terimakasih sudah bermain Mas Agus Restart Router! . Tujuan dari game ini adalah untuk murtad dari ISP indihomo";
        } else {
            message = "Matikan dan nyalakan modem dengan menyentuh gambar modem untuk mendapatkan bandwidth . bandwidth dapat kamu gunakan untuk membeli item di Toko . tombol toko berada di bawah gambar mas agus";
            title="Tutorial";
        }

        // set title dialo

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(message)
                .setTitle(title)
                .setIcon(R.drawable.masaguslogo)
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                       if (type == 1) {
                          // MainActivity.this.finish();
                           dialog.dismiss();
                       } else {
                           dialog.dismiss();
                       }

                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}
