package app.Dashboard;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lionsclub.com.directoryapp.Club;


/**
 * Created by ISSLT115-PC on 4/9/2015.
 */
public class ClubDataAdapter implements SpinnerAdapter {
    List<Club> clubs = new ArrayList<Club>();
    Context mContext;

    public ClubDataAdapter(Context context, List<Club> clubs) {
        this.clubs = clubs;
        this.mContext = context;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(android.R.layout.simple_dropdown_item_1line, null,
                false);
        TextView make = (TextView) row.findViewById(android.R.id.text1);


        make.setText(clubs.get(position).getName());
        return row;

    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return clubs.size();
    }

    @Override
    public Club getItem(int i) {
        return clubs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(android.R.layout.simple_list_item_1, null,
                false);
        TextView make = (TextView) row.findViewById(android.R.id.text1);


        make.setText(clubs.get(position).getName());
        return row;


    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
