package org.lovebing.reactnative.baidumap.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * 本地图片展示工具类
 * <p>
 * Created by Letto. on 2017/7/5.
 */

public class FileUtil {
    private static final String TAG = "FileUtil";

    public static File getCacheFile(String imageUri) {
        File cacheFile = null;
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File sdCardDir = Environment.getExternalStorageDirectory();
                String fileName = getFileName(imageUri);
                File dir = new File(sdCardDir.getCanonicalPath()
                        + AsynImageLoader.CACHE_DIR);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                cacheFile = new File(dir, fileName);
                Log.i(TAG, "exists:" + cacheFile.exists() + ",dir:" + dir + ",file:" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getCacheFileError:" + e.getMessage());
        }

        return cacheFile;
    }

    public static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }
}
