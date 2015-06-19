package app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by ISSLT115-PC on 4/8/2015.
 */
public class ExportDatabaseFileTask extends AsyncTask<String, Void, Boolean> {

    Context mContext;
    private ProgressDialog dialog = null;

    public ExportDatabaseFileTask(Context context) {
        this.mContext = context;
        dialog = new ProgressDialog(mContext);
    }

    // can use UI thread here
    protected void onPreExecute() {
        this.dialog.setMessage("Exporting database...");
        this.dialog.show();
    }

    // automatically done on worker thread (separate from UI thread)
    protected Boolean doInBackground(final String... args) {

        File dbFile =
                new File(Environment.getDataDirectory() + "/data/lionsclub.com.directoryapp/databases/directory.db");

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, dbFile.getName());

        try {
            file.createNewFile();
            this.copyFile(dbFile, file);
            return true;
        } catch (IOException e) {
            Log.e("mypck", e.getMessage(), e);
            return false;
        }
    }

    // can use UI thread here
    protected void onPostExecute(final Boolean success) {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (success) {
            Toast.makeText(mContext, "Export successful!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Export failed", Toast.LENGTH_SHORT).show();
        }
    }

    void copyFile(File src, File dst) throws IOException {
        FileChannel inChannel = new FileInputStream(src).getChannel();
        FileChannel outChannel = new FileOutputStream(dst).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }

}

