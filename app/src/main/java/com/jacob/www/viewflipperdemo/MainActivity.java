package com.jacob.www.viewflipperdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper notice_vf;
    private int         mCurrPos;
    List<String> titleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 滚动消息栏的显示内容
        titleList.add("1.4折起 viishow男装专场");
        titleList.add("1799!ZUK Z2 性价比无敌了");
        titleList.add("蜂蜜的3种美味吃法");
        titleList.add("全场包邮 非常大牌");
        titleList.add("全场包邮 非常大牌");
        initRollNotice();
    }

    // 上下滚动消息栏
    private void initRollNotice() {
        notice_vf = ((ViewFlipper) findViewById(R.id.vf));
        notice_vf.setOutAnimation(this, R.anim.out_top2bottom);
        notice_vf.setInAnimation(this, R.anim.in_bottom2top);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        moveNext();
                    }
                });

            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 2000);
    }

    private void moveNext() {
        setView(this.mCurrPos, this.mCurrPos + 1);
        notice_vf.showNext();
    }

    private void setView(int curr, int next) {
        View     noticeView = getLayoutInflater().inflate(R.layout.item_notice, null);
        TextView notice_tv  = (TextView) noticeView.findViewById(R.id.tv_notice);
        TextView notice_tv2  = (TextView) noticeView.findViewById(R.id.tv_notice2);
        // 归0
        if (next > (titleList.size() - 1)) {
            next = 0;
        }
        // 设置
        notice_tv.setText(titleList.get(mCurrPos));
        notice_tv2  .setText(titleList.get(mCurrPos));
        if (notice_vf.getChildCount() > 1) {
            notice_vf.removeViewAt(0);
        }
        notice_vf.addView(noticeView, notice_vf.getChildCount());
        mCurrPos = next;
    }
}
