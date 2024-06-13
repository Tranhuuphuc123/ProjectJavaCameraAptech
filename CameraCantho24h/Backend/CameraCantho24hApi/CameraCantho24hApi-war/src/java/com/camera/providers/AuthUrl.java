package com.camera.providers;

import java.util.HashMap;
import java.util.List;



/***interface phan quyen lam class ke thua cho cac api, client web phan quyen ben duoi****/
public interface AuthUrl {
    public List<String> signInUrls();
    public HashMap<String, List<String>> roleUrls();
}
