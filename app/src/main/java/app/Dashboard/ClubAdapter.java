package app.Dashboard;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lionsclub.com.directoryapp.Club;
import lionsclub.com.directoryapp.R;

/**
 * Created by Yaju on 5/21/2015.
 */
public class ClubAdapter extends ArrayAdapter<Club> {

    private Dashboard activity;
    private ViewHolder mHolder;
    public List<Club> item = new ArrayList<Club>();


    public ClubAdapter(Dashboard activity, int textViewResourceId, List<Club> items) {
        super(activity, textViewResourceId, items);
        this.item = items;
        this.activity = activity;


    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.club_list, null);
            mHolder = new ViewHolder();


            mHolder.tv_club = (TextView) v.findViewById(R.id.tv_club_name);
            v.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) v.getTag();
        }

        final Club club = item.get(position);

        if (club != null) {
            mHolder.tv_club.setText(club.getName() + " (" + club.getMemberList().size() + ")");

        }

        mHolder.tv_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.callBack(item.get(position).getMemberList());
            }
        });


        return v;
    }

    class ViewHolder {
        public TextView tv_club;


    }


}
