package app.utils;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lionsclub.com.directoryapp.R;

/**
 * Created by ISSLT115-PC on 4/13/2015.
 */
public class ShowMessage2User {

    public static void display(Context context, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(context.getString(R.string.app_name));
        alert.setMessage(message);
        alert.show();


    }

}
