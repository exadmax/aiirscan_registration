package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi {
    private static AtomicReference a = new AtomicReference();

    private GoogleVrApi() {
    }

    static void a() {
        a.set((Object) null);
    }

    static void a(e eVar) {
        a.compareAndSet((Object) null, new GoogleVrProxy(eVar));
    }

    static GoogleVrProxy b() {
        return (GoogleVrProxy) a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) a.get();
    }
}
