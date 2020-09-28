package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

public class HFPStatus {
    private Context a;
    private BroadcastReceiver b = null;
    private Intent c = null;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public AudioManager e = null;
    /* access modifiers changed from: private */
    public int f = a.a;

    enum a {
        ;

        static {
            d = new int[]{a, b, c};
        }
    }

    public HFPStatus(Context context) {
        this.a = context;
        this.e = (AudioManager) this.a.getSystemService("audio");
        initHFPStatusJni();
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    public final void a() {
        deinitHFPStatusJni();
    }

    /* access modifiers changed from: protected */
    public boolean getHFPStat() {
        return this.f == a.b;
    }

    /* access modifiers changed from: protected */
    public void requestHFPStat() {
        this.b = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                if (intExtra == -1) {
                    return;
                }
                if (intExtra == 0) {
                    if (HFPStatus.this.d) {
                        HFPStatus.this.e.setMode(0);
                    }
                    boolean unused = HFPStatus.this.d = false;
                } else if (intExtra == 1) {
                    int unused2 = HFPStatus.this.f = a.b;
                    if (!HFPStatus.this.d) {
                        HFPStatus.this.e.stopBluetoothSco();
                    } else {
                        HFPStatus.this.e.setMode(3);
                    }
                } else if (intExtra == 2) {
                    if (HFPStatus.this.f == a.b) {
                        boolean unused3 = HFPStatus.this.d = true;
                    } else {
                        int unused4 = HFPStatus.this.f = a.c;
                    }
                }
            }
        };
        this.c = this.a.registerReceiver(this.b, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        try {
            this.e.startBluetoothSco();
        } catch (NullPointerException unused) {
            f.Log(5, "startBluetoothSco() failed. no bluetooth device connected.");
        }
    }
}
