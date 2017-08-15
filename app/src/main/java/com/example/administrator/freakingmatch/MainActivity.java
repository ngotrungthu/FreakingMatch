package com.example.administrator.freakingmatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtsothu1, txtsothu2, txtketqua, txtdiem, txtdapanthu1, txtdapanthu2, txtpheptinh;
    SeekBar seekbarthoigian;
    ArrayList<String> mangpheptinh;
    ArrayList<TextView> mangtextview;
    int ketqua = 0;
    int sthu1 = 0;
    int sthu2 = 0;
    int diem = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        txtsothu2.setText("?");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            seekbarthoigian.getThumb().mutate().setAlpha(0);
        }
        randomnumberandcaculator();
        checkdapan();

    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Bạn đã thua !!. Số điểm bạn đạt được là "+ diem + " điểm");
        builder.setMessage("Bạn có muốn chơi lại");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                randomnumberandcaculator();
                checkdapan();
                diem = 0;
                txtdiem.setText(String.valueOf(0));
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

    private void checkdapan() {
        seekbarthoigian.setMax(4);
         countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {
                seekbarthoigian.setProgress((int) ((5000 - l) /1000));
                txtdapanthu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int ktraketqua = Integer.parseInt(txtdapanthu1.getText().toString());
                        if (ktraketqua == sthu2) {
                            Toast.makeText(MainActivity.this, "Đúng rồi", Toast.LENGTH_SHORT).show();
                            diem++;
                            countDownTimer.cancel();
                            countDownTimer.start();
                            randomnumberandcaculator();
                        } else {
                            countDownTimer.cancel();
                            Toast.makeText(MainActivity.this, "Sai rồi", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Bạn đã thua", Toast.LENGTH_SHORT).show();
                            dialog();
                        }
                        txtdiem.setText(""+diem);
                    }
                });
                txtdapanthu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int ktraketqua = Integer.parseInt(txtdapanthu2.getText().toString());
                        if (ktraketqua == sthu2) {
                            Toast.makeText(MainActivity.this, "Đúng rồi", Toast.LENGTH_SHORT).show();
                            diem++;
                            countDownTimer.cancel();
                            countDownTimer.start();
                            randomnumberandcaculator();
                        } else {
                            countDownTimer.cancel();
                            Toast.makeText(MainActivity.this, "Sai rồi", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Bạn đã thua", Toast.LENGTH_SHORT).show();
                            dialog();
                        }
                        txtdiem.setText(""+diem);
                    }
                });
            }

            @Override
            public void onFinish() {
                seekbarthoigian.setProgress(5);
                Toast.makeText(MainActivity.this, "Hết giờ", Toast.LENGTH_SHORT).show();
                dialog();
            }
        };
        countDownTimer.start();

    }

    private void randomnumberandcaculator() {
        Random random = new Random();

        int index = random.nextInt(mangpheptinh.size());
        String value = mangpheptinh.get(index);

        sthu1 = random.nextInt(10) + 1;
        sthu2 = random.nextInt(10) + 1;


        Collections.shuffle(mangtextview);
        mangtextview.get(0).setText(sthu2 + "");
        mangtextview.get(1).setText((random.nextInt(5) + 1) + sthu2 + "");

        switch (value) {
            case "+":
                ketqua = sthu1 + sthu2;
                txtsothu1.setText(sthu1 + "");
                txtpheptinh.setText(value);
                txtketqua.setText("= " + ketqua);
                break;
            case "-":
                ketqua = sthu1 - sthu2;
                txtpheptinh.setText(value);
                txtsothu1.setText(sthu1 + "");
                txtketqua.setText("= " + ketqua);
                break;
            case "*":
                ketqua = sthu1 * sthu2;
                txtsothu1.setText(sthu1 + "");
                txtpheptinh.setText(value);
                txtketqua.setText("= " + ketqua);
                break;
        }
    }

    private void anhxa() {
        txtsothu1 = (TextView) findViewById(R.id.textviewsothu1);
        txtsothu2 = (TextView) findViewById(R.id.textviewsothu2);
        txtketqua = (TextView) findViewById(R.id.textviewketqua);
        txtdiem = (TextView) findViewById(R.id.textviewdiem);
        txtdapanthu1 = (TextView) findViewById(R.id.textviewgiatri1);
        txtdapanthu2 = (TextView) findViewById(R.id.textviewgiatri2);
        txtpheptinh = (TextView) findViewById(R.id.textviewpheptinh);
        seekbarthoigian = (SeekBar) findViewById(R.id.seekbarthoigian);
        mangpheptinh = new ArrayList<>();
        mangtextview = new ArrayList<>();
        mangtextview.add(txtdapanthu1);
        mangtextview.add(txtdapanthu2);
        mangpheptinh.add("+");
        mangpheptinh.add("-");
        mangpheptinh.add("*");
  }
}
