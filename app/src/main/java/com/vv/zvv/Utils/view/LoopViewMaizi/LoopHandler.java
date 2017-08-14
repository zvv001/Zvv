package com.vv.zvv.Utils.view.LoopViewMaizi;

import android.os.Handler;
import android.os.Message;

/**
 * Created by zvv on 2017/8/14 13:45.
 */
public abstract class LoopHandler extends Handler {
    boolean loop = false;
    long loopTime = 0;
    static final int msgid = 1000;
    Message msg = createMsg();


    public LoopHandler(int time) {
        this.loopTime = time;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what = msgid) {
            case msgid:
                if (loop) {
                    du();
                    sendMsg();
                }
                break;
        }
        super.handleMessage(msg);
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        if (loop) {
            sendMsg();
        } else {
            try {
                removeMessages(msgid);
            } catch (Exception e) {
            }
        }
    }

    private void sendMsg() {
        try {
            removeMessages(msgid);
        } catch (Exception e) {
        }
        msg = createMsg();
        this.sendMessageDelayed(msg, loopTime);
    }

    public Message createMsg() {
        Message msg = new Message();
        msg.what = msgid;
        return msg;
    }


    public void setLoopTime(long loopTime) {
        this.loopTime = loopTime;
    }


    public boolean isLoop() {
        return loop;
    }

    public abstract void du();
}
