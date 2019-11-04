package oy.com;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdActivity extends AppCompatActivity {

    @BindView(R.id.tv_count)
    TextView tv_count;

    boolean isstop = false;
    Thread thread;

    Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        ButterKnife.bind(this);

        tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thread != null) {
                    thread.stop();
                }
                isstop = true;
                Intent intent = new Intent(AdActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
       /* tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(task);
                Intent intent=new Intent(AdActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/


        /*new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 5; i >= 0; i--) {
                    if (isstop) {
                        return;
                    }
                    SystemClock.sleep(1000);

                    final int count = i;
                     /*runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             tv_count.setText("点击跳转 "+count);
                         }
                     });*/
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_count.setText("点击跳转 " + count);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AdActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();
                    }
                });
            }
        }).start();


    }

    @OnClick(R.id.tv_count)
    public void onViewClicked() {
    }
}
