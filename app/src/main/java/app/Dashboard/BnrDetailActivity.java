package app.Dashboard;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import lionsclub.com.directoryapp.R;

/**
 * Created by Yaju on 5/28/2015.
 */
public class BnrDetailActivity extends Activity {
    private String BNR;
    private JSONArray bnrArr;

    int val = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.bnr);
Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            val = bundle.getInt("val");
        }

        try {
            BNR =  getSharedPreferences(getString(R.string.app_name), 1).getString("bnr", "1234");
            System.out.println(BNR);
            bnrArr = new JSONArray(BNR);

            JSONObject jobj = bnrArr.getJSONObject(val);
            ((TextView)findViewById(R.id.tvTitle)).setText(jobj.getString("title"));
            ((TextView)findViewById(R.id.tvDesc)).setText(jobj.getString("desc"));
            File mProfileImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/DirectoryOnline/BannerPhoto",jobj.getString("image"));

            if (mProfileImage.exists()) {
                BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                bmpOptions.inSampleSize = 2;
                Bitmap imageBitmap = BitmapFactory.decodeFile(mProfileImage.getAbsolutePath(), bmpOptions);
                if (imageBitmap != null) {
                    ((ImageView)findViewById(R.id.ivbnr)).setImageBitmap(imageBitmap);

                }

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
