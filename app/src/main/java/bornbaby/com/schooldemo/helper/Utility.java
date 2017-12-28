package bornbaby.com.schooldemo.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by madhu on 28-Dec-17.
 */

public class Utility {


    public static String saveBitmap(Context context, Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/.SchoolDemo");
        if (myDir.exists()) {
            boolean statusFolder = myDir.delete();
            Log.v("FOLDER:--", "FOLDER STATUS:" + statusFolder);
        } else {
            boolean status = myDir.mkdirs();
            Log.v("FOLDER:--", "FOLDER STATUS:" + status);
        }

        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String fname = "Photo_" + ts + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) {
            boolean status = file.delete();
            Log.v("DELETE:--", "DELETE STATUS:" + status);
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

}
