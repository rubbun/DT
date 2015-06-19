/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package app.Dashboard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.apache.http.io.SessionOutputBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import app.Login.LoginActivity;
import app.Member.MemberActivity;
import app.Repositories.CRequestRepository;
import app.Repositories.ClubRepository;
import app.Repositories.DistrictRepository;
import app.Repositories.MemberRepository;
import app.WebService.BDService;
import app.WebService.BannerInfo;
import app.WebService.CReqMem;
import app.WebService.CredentialsRequestsListResponse;
import app.WebService.MemberProfile;
import app.WebService.MergedResponse;
import app.WebService.VectorBannerInfo;
import app.WebService.VectorString;
import app.WebService.xClub;
import app.admin.CredentialRequest;
import lionsclub.com.directoryapp.Club;
import lionsclub.com.directoryapp.CredentialRequestQueue;
import lionsclub.com.directoryapp.District;
import lionsclub.com.directoryapp.Member;
import lionsclub.com.directoryapp.R;

public class Dashboard extends ActionBarActivity implements DashBoardView, View.OnClickListener {

    enum Tabs {
        CLUBS, MEMBERS, WISHES, MESSAGE
    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    //private ListView listView;
    ListView recyclerView;
    boolean isAdmin = false;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    // nav drawer title
    private CharSequence mDrawerTitle;
    // used to store app title
    private CharSequence mTitle;
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private Spinner clubsDataSpinner;
    private ProgressBar progressBar;
    private Spinner clubsData;
    //private ImageView searchMember;
    private LinearLayout search_Layout;
    //private EditText name_searchedit, mobile_searchedit, mail_searchedit;
    private DashBoardPresenter presenter;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private List<Member> members, tempMembers;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;
    private EditText et_search;
    private LinearLayout ll_clubs, ll_members, ll_wish, ll_message;
    private TextView tv_club, tv_member, tv_wish, tv_message;
    private ImageView iv_club, iv_member, iv_wish, iv_message;
    String MemId;
    private ListView lv_clubs;
    List<Club> clubs = new ArrayList<Club>();
    public ClubAdapter clubAdapter;

    private int search = 1;
    private boolean isBannerDelete = false;

    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    private String username, password;

    private String destMemid;
    long destId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isBannerDelete = bundle.getBoolean("isBanner");
        }


        lv_clubs = (ListView) findViewById(R.id.lv_clubs);
        lv_clubs.setVisibility(View.GONE);
        isAdmin = getSharedPreferences(getString(R.string.app_name), 1).getBoolean("isAdmin", false);
        MemId = getSharedPreferences(getString(R.string.app_name), 1).getString("MemId", "1234");
        username = getSharedPreferences(getString(R.string.app_name), 1).getString("u1", "1234");
        password = getSharedPreferences(getString(R.string.app_name), 1).getString("u2", "1234");
//Toast.makeText(getApplicationContext(), MemId, Toast.LENGTH_LONG).show();
        mViewFlipper = (ViewFlipper) findViewById(R.id.bannerImageFlipper);
        et_search = (EditText) findViewById(R.id.et_search);
        ll_clubs = (LinearLayout) findViewById(R.id.ll_clubs);
        ll_clubs.setOnClickListener(this);

        ll_members = (LinearLayout) findViewById(R.id.ll_members);
        ll_members.setOnClickListener(this);

        ll_wish = (LinearLayout) findViewById(R.id.ll_wish);
        ll_wish.setOnClickListener(this);

        ll_message = (LinearLayout) findViewById(R.id.ll_message);
        ll_message.setOnClickListener(this);

        tv_club = (TextView) findViewById(R.id.tv_club);
        tv_member = (TextView) findViewById(R.id.tv_member);
        tv_wish = (TextView) findViewById(R.id.tv_wish);
        tv_message = (TextView) findViewById(R.id.tv_message);

        iv_club = (ImageView) findViewById(R.id.iv_club);
        iv_member = (ImageView) findViewById(R.id.iv_member);
        iv_wish = (ImageView) findViewById(R.id.iv_wish);
        iv_message = (ImageView) findViewById(R.id.iv_message);


//animation listener
        mAnimationListener = new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                //animation started event
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                //TODO animation stopped event
            }
        };

        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                //Toast.makeText(getApplicationContext(), ""+mViewFlipper.getDisplayedChild(),Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mViewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ((LinearLayout) findViewById(R.id.ll_blanck)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, BnrDetailActivity.class);
                intent.putExtra("val", mViewFlipper.getDisplayedChild());
                startActivity(intent);
            }
        });

        setFlipperImage();


        mViewFlipper.setAutoStart(true);

        mViewFlipper.setFlipInterval(10000);

        mViewFlipper.startFlipping();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (ListView) findViewById(R.id.memberListView);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress);
        clubsDataSpinner = (Spinner) findViewById(R.id.clubsList);
        // searchMember=(ImageView) findViewById(R.id.searchMember);
        //name_searchedit= (EditText) findViewById(R.id.name_searchedit);
        // mobile_searchedit= (EditText) findViewById(R.id.mobile_searchedit);
        // mail_searchedit= (EditText) findViewById(R.id.mail_searchedit);

        // searchMember.setOnClickListener(this);
        presenter = new DashBoardPresenterImpl(this, Dashboard.this);

        presenter.onResume();


        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        List<Member> members12 = MemberRepository.getAllMembers(getApplicationContext());
                        for (int j = 0; j < members12.size(); j++) {
                            if (members12.get(j).getUserName().equalsIgnoreCase(MemId)) {
                               /* Toast.makeText(getApplicationContext(), "TES " + members.get(j).getUserName(), Toast.LENGTH_LONG).show();
                                Log.e("Name ", members.get(j).getName());*/
                                System.out.println("MEMBER ID "+MemId);

                                Member m = members12.get(j);
                               /* Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                                intent.putExtra("memberData", members12.get(j).getId());

                                intent.putExtra("cre", false);
                                startActivity(intent);*/


                                destMemid = MemId;
                                destId = members12.get(j).getId();

                                if(isOnline()){
                                    new UpdateMemberInfo().execute();
                                }else{
                                    Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                                    intent.putExtra("memberData", members12.get(j).getId());

                                    intent.putExtra("cre", false);
                                    startActivity(intent);
                                }


                                break;

                            }
                        }


                        break;
                    case 1:

                        if (isAdmin) {

                        Intent credentialRequest = new Intent(Dashboard.this, CredentialRequest.class);
                        startActivity(credentialRequest);

                    }else{

                            if(!isOnline()){
                                new AlertDialog.Builder(Dashboard.this)
                                        .setCancelable(false)
                                        .setTitle("Alert")
                                        .setMessage("You do not have internet connection")
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        })
                                        .show();
                                return;
                            }
                            DistrictRepository.clearDistrict(Dashboard.this);
                            MemberRepository.clearMember(Dashboard.this);
                            ClubRepository.clearClub(Dashboard.this);
                            new LoginTask().execute();
                        }
                        break;



                    case 2:
                        if(isAdmin){
                            if(!isOnline()){
                                new AlertDialog.Builder(Dashboard.this)
                                        .setCancelable(false)
                                        .setTitle("Alert")
                                        .setMessage("You do not have internet connection")
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        })
                                        .show();
                                return;
                            }
                            DistrictRepository.clearDistrict(Dashboard.this);
                            MemberRepository.clearMember(Dashboard.this);
                            ClubRepository.clearClub(Dashboard.this);
                            new LoginTask().execute();
                        }else{
                            new AlertDialog.Builder(Dashboard.this)
                                    .setCancelable(false)
                                    .setTitle("Alert")
                                    .setMessage("Do you want to logout?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            DistrictRepository.clearDistrict(Dashboard.this);
                                            MemberRepository.clearMember(Dashboard.this);
                                            ClubRepository.clearClub(Dashboard.this);
                                            File memberPhotoDirectory = new File(Environment.getExternalStorageDirectory(), "DirectoryOnline/");
                                            DeleteRecursive(memberPhotoDirectory);
                                            getSharedPreferences(getString(R.string.app_name), 1).edit().clear().commit();
                                            Intent login = new Intent(Dashboard.this, LoginActivity.class);
                                            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(login);
                                            finish();
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .show();
                        }


                        break;

                    case 3:

                        new AlertDialog.Builder(Dashboard.this)
                                .setCancelable(false)
                                .setTitle("Alert")
                                .setMessage("Do you want to logout?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        DistrictRepository.clearDistrict(Dashboard.this);
                                        MemberRepository.clearMember(Dashboard.this);
                                        ClubRepository.clearClub(Dashboard.this);
                                        File memberPhotoDirectory = new File(Environment.getExternalStorageDirectory(), "DirectoryOnline/");
                                        DeleteRecursive(memberPhotoDirectory);
                                        getSharedPreferences(getString(R.string.app_name), 1).edit().clear().commit();
                                        Intent login = new Intent(Dashboard.this, LoginActivity.class);
                                        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(login);
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();


                        break;
                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mDrawerToggle.syncState();
                invalidateOptionsMenu();
            }
        });

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People

        if (isAdmin)
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (search == 1) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                        display(Tabs.MEMBERS);
                        if (et_search.getText().toString().equals("")) {
                            lv_clubs.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            members.clear();
                            members.addAll(tempMembers);
                            //
                            recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, R.layout.member_list, members));
                            adapter.notifyDataSetChanged();
                        } else {
                            lv_clubs.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            members.clear();
                            members.addAll(filterMemberList(2));
                            Log.e("member size :", members.size() + "");
                            Log.e("member size1 :", filterMemberList(2).size() + "");
                            //
                            recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, R.layout.member_list, members));
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        InputMethodManager imm = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                        if (et_search.getText().toString().equals("")) {
                            display(Tabs.CLUBS);

                            lv_clubs.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            System.out.println("Size " + clubs.size());
                            clubAdapter = new ClubAdapter(Dashboard.this, R.layout.club_list, clubs);
                            lv_clubs.setAdapter(clubAdapter);
                        } else {
                            display(Tabs.CLUBS);

                            lv_clubs.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            System.out.println("Size " + clubs.size());
                            List<Club> search_club = new ArrayList<Club>();
                            String st = et_search.getText().toString().trim();
                            for (int i = 0; i < clubs.size(); i++) {
                                if (clubs.get(i).getName().toLowerCase().contains(st.toLowerCase())) {
                                    search_club.add(clubs.get(i));
                                }
                            }
                            clubAdapter = new ClubAdapter(Dashboard.this, R.layout.club_list, search_club);
                            lv_clubs.setAdapter(clubAdapter);
                        }
                    }

                    return true;
                }
                return false;
            }
        });
        et_search.setHint("Search: Enter Member Name.");
        display(Tabs.MEMBERS);

        if (isBannerDelete) {

            System.out.println("reach");

            if (isOnline()) {
                File memberPhotoDirectory1 = new File(Environment.getExternalStorageDirectory(), "DirectoryOnline//BannerPhoto");

                DeleteRecursive(memberPhotoDirectory1);
                new BannerAsynctask().execute();
            }


        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Member> members) {
        this.members = members;

        Collections.sort(members, new Comparator<Member>() {
            public int compare(Member v1, Member v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        this.tempMembers = new ArrayList<>();
        if (members != null)
            tempMembers.addAll(members);
        Log.e("tempMembers1 ", "" + tempMembers.size());

        recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, R.layout.member_list, members));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadClubs(List<Club> clubs) {
        try {
            this.clubs = clubs;
            //clubsDataSpinner.setAdapter(new ClubDataAdapter(Dashboard.this, clubs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navigateToMemberProfile(String mInt) {
        startActivity(new Intent(this, Member.class));
    }

   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);

    }*/

    public List<Member> filterMemberList(int i) {
        List<Member> tempMemberList = new ArrayList<Member>();
        Log.e("tempMembers11 ", "" + tempMembers.size());
        for (Member member : tempMembers) {
            try {


                if (i == 1) {
                    if (member.getClubName().toLowerCase().contains(et_search.getText().toString().toLowerCase().trim())) {
                        tempMemberList.add(member);
                    }
                } else {
                    if (member.getName().toLowerCase().contains(et_search.getText().toString().toLowerCase().trim())) {
                        tempMemberList.add(member);
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempMemberList;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_clubs:

                search = 2;
                display(Tabs.CLUBS);
                et_search.setText("");
                et_search.setHint("Search: Enter Club Name.");
                lv_clubs.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                System.out.println("Size " + clubs.size());

                clubAdapter = new ClubAdapter(Dashboard.this, R.layout.club_list, clubs);
                lv_clubs.setAdapter(clubAdapter);
                /*if (et_search.getText().toString().equals("")) {
                    members.clear();
                    members.addAll(tempMembers);
                    //
                    recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, members));
                    adapter.notifyDataSetChanged();
                } else {
                    members.clear();
                    members.addAll(filterMemberList(1));
                    Log.e("member size :", members.size() + "");
                    Log.e("member size1 :", filterMemberList(1).size() + "");
                    //
                    recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, members));
                    adapter.notifyDataSetChanged();
                }*/
                break;

            case R.id.ll_members:

                search = 1;
                et_search.setText("");
                et_search.setHint("Search: Enter Member Name.");
                lv_clubs.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                display(Tabs.MEMBERS);
                if (et_search.getText().toString().equals("")) {
                    members.clear();
                    members.addAll(tempMembers);
                    //
                    recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, R.layout.member_list, members));
                    adapter.notifyDataSetChanged();
                } else {
                    members.clear();
                    members.addAll(tempMembers);
                    //  members.addAll(filterMemberList(2));
                    Log.e("member size :", members.size() + "");
                    Log.e("member size1 :", filterMemberList(2).size() + "");
                    //
                    recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, R.layout.member_list, members));
                    adapter.notifyDataSetChanged();
                }
                break;

            case R.id.ll_wish:
                display(Tabs.WISHES);
                new AlertDialog.Builder(Dashboard.this)
                        .setTitle("Alert")
                        .setMessage("This Feature is Locked")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .show();
                //Toast.makeText(getApplicationContext()," This Feature is Locked.",Toast.LENGTH_LONG).show();
                break;
            case R.id.ll_message:
                display(Tabs.MESSAGE);
                new AlertDialog.Builder(Dashboard.this)
                        .setTitle("Alert")
                        .setMessage("This Feature is Locked")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
        }
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.left_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.right_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    private void setFlipperImage() {
        FilenameFilter filter = null;
        File[] imagesFileArray = listFiles(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DirectoryOnline/BannerPhoto", filter);
        if (imagesFileArray != null && imagesFileArray.length > 0) {
            for (File imageFile : imagesFileArray) {
                try {
                    Bitmap mBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                    if (mBitmap != null) {
                        ImageView image = new ImageView(getApplicationContext());
                        image.setImageBitmap(mBitmap);
                        mViewFlipper.addView(image);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public File[] listFiles(String directoryPath, FilenameFilter filter) {

        File[] fileArray = null;
        File file = new File(directoryPath);

        if (filter != null) {
            if (file.exists()) {
                fileArray = file.listFiles();
            }
        } else {
            if (file.exists()) {
                fileArray = file.listFiles();
            }
        }

        return fileArray;
    }

    public void display(Tabs tab) {
        switch (tab) {
            case CLUBS:
                iv_club.setImageResource(R.drawable.clubs_active);
                iv_member.setImageResource(R.drawable.members_search_inactive);
                iv_wish.setImageResource(R.drawable.wishes_inactive);
                iv_message.setImageResource(R.drawable.message_inactive);

                tv_club.setTextColor(Color.parseColor("#06b0f2"));
                tv_member.setTextColor(Color.parseColor("#000000"));
                tv_wish.setTextColor(Color.parseColor("#000000"));
                tv_message.setTextColor(Color.parseColor("#000000"));

                break;
            case MEMBERS:

                iv_club.setImageResource(R.drawable.clubs_inactive);
                iv_member.setImageResource(R.drawable.members_search_active);
                iv_wish.setImageResource(R.drawable.wishes_inactive);
                iv_message.setImageResource(R.drawable.message_inactive);

                tv_club.setTextColor(Color.parseColor("#000000"));
                tv_member.setTextColor(Color.parseColor("#06b0f2"));
                tv_wish.setTextColor(Color.parseColor("#000000"));
                tv_message.setTextColor(Color.parseColor("#000000"));
                break;
            case WISHES:

                iv_club.setImageResource(R.drawable.clubs_inactive);
                iv_member.setImageResource(R.drawable.members_search_inactive);
                iv_wish.setImageResource(R.drawable.wishes_active);
                iv_message.setImageResource(R.drawable.message_inactive);

                tv_club.setTextColor(Color.parseColor("#000000"));
                tv_member.setTextColor(Color.parseColor("#000000"));
                tv_wish.setTextColor(Color.parseColor("#06b0f2"));
                tv_message.setTextColor(Color.parseColor("#000000"));
                break;
            case MESSAGE:

                iv_club.setImageResource(R.drawable.clubs_inactive);
                iv_member.setImageResource(R.drawable.members_search_inactive);
                iv_wish.setImageResource(R.drawable.wishes_inactive);
                iv_message.setImageResource(R.drawable.message_active);

                tv_club.setTextColor(Color.parseColor("#000000"));
                tv_member.setTextColor(Color.parseColor("#000000"));
                tv_wish.setTextColor(Color.parseColor("#000000"));
                tv_message.setTextColor(Color.parseColor("#06b0f2"));
                break;
        }
    }

    void DeleteRecursive(File dir) {
        Log.d("DeleteRecursive", "DELETEPREVIOUS TOP" + dir.getPath());
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File temp = new File(dir, children[i]);
                if (temp.isDirectory()) {
                    Log.d("DeleteRecursive", "Recursive Call" + temp.getPath());
                    DeleteRecursive(temp);
                } else {
                    Log.d("DeleteRecursive", "Delete File" + temp.getPath());
                    boolean b = temp.delete();
                    if (b == false) {
                        Log.d("DeleteRecursive", "DELETE FAIL");
                    }
                }
            }

        }
        dir.delete();
    }

    public void callBack(List<Member> members1) {
        lv_clubs.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        display(Tabs.MEMBERS);
        Collections.sort(members1, new Comparator<Member>() {
            public int compare(Member v1, Member v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        recyclerView.setAdapter(new MemberRecyclerAdapter(Dashboard.this, R.layout.member_list, members1));
        adapter.notifyDataSetChanged();
    }

    private void dowloadBannerImage(VectorBannerInfo bnr) {
        if (bnr != null) {
            File dirFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DirectoryOnline/BannerPhoto");
            if (!dirFile.exists()) {
                dirFile.mkdirs();

            }
            JSONArray jArr = new JSONArray();
            for (BannerInfo binf : bnr)
            // for(int i=0;i<bnr.getPropertyCount();i++)
            {
                try {

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("title", binf.title);
                    jsonObject.put("image", binf.image);
                    jsonObject.put("uRL", binf.uRL);
                    jsonObject.put("desc", binf.desc);
                    jArr.put(jsonObject);
                    System.out.println("banner ====>>  " + binf.uRL + "/" + binf.image);
                    URL url = new URL("http://alphatest.in/uploads/DTApp_slideimages/" + binf.image);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();

                    OutputStream output = new FileOutputStream(new File(dirFile, binf.image));
                    try {
                        byte[] buffer = new byte[2048];
                        int bytesRead = 0;
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } finally {
                        output.close();
                        input.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ;
                }

            }

            System.out.println(jArr.toString());
            SharedPreferences appPreference2 = getSharedPreferences(getString(R.string.app_name), 1);
            SharedPreferences.Editor appPrefEditor2 = appPreference2.edit();
            appPrefEditor2.putString("bnr", jArr.toString());
            appPrefEditor2.commit();
        }
    }

    public class BannerAsynctask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            BDService service = new BDService();
            dowloadBannerImage(service.BNR(username, password));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    private class LoginTask extends AsyncTask<Void, Integer, Boolean> {


        MergedResponse response;
       // int j = 0;

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Dashboard.this);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();



        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pd.setProgress((int) (values[0]));
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                BDService service = new BDService();
                System.out.println("USER  "+username+"  "+password);
                response = service.MRGDRSPNS(username, password);


                if (response != null && response.authenticated) {
                    SharedPreferences appPreference1 = getSharedPreferences(getString(R.string.app_name), 1);
                    SharedPreferences.Editor appPrefEditor1 = appPreference1.edit();
                    appPrefEditor1.putString("MemId", response.MemId);

                    System.out.println("MemId   "+ response.MemId);

                    appPrefEditor1.commit();


                    if (response.isAdmin) {

                        SharedPreferences appPreference = getSharedPreferences(getString(R.string.app_name), 1);
                        SharedPreferences.Editor appPrefEditor = appPreference.edit();
                        appPrefEditor.putBoolean("isAdmin", response.isAdmin);
                        appPrefEditor.commit();

                        CredentialsRequestsListResponse credentialsRequestsListResponse = service.GCREREQSLST(username, password);

                        if (credentialsRequestsListResponse.success) {
                            System.out.println("credentialsRequestsListResponse.credentialRequestsList " + credentialsRequestsListResponse.credentialRequestsList.size());
                            for (CReqMem cReqMem : credentialsRequestsListResponse.credentialRequestsList) {
                                CredentialRequestQueue credentialRequest = new CredentialRequestQueue();

                                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

                                credentialRequest.setMemInt(Integer.parseInt(cReqMem.mIntNo));
                                credentialRequest.setDateTimeRequested(cReqMem.dT_Requested);
                                credentialRequest.setIsProcessed(cReqMem.processed);
                                CRequestRepository.insertOrUpdate(getApplicationContext(), credentialRequest);

                            }

                        }

                    }
                    District district = new District();
                    district.setDistId("ld01");
                    district.setDistName("Adilabad");
                    DistrictRepository.insertOrUpdate(getApplicationContext(), district);
                    int i = 0;

                    if (response.allClubs != null)
                        for (xClub club : response.allClubs) {
                            for (app.WebService.Member member : club.membersIn) {
                               // j++;
                            }
                        }


                    for (xClub club : response.allClubs) {
                        lionsclub.com.directoryapp.Club clubEntity = new lionsclub.com.directoryapp.Club();
                        clubEntity.setDistId(district.getDistId());
                        clubEntity.setName(club.name);
                        clubEntity.setClubId(club.iD);
                        clubEntity.setMemcount(club.mems);
                        ClubRepository.insertOrUpdate(getApplicationContext(), clubEntity);

                        for (app.WebService.Member member : club.membersIn) {

                            i++;
                            publishProgress(i);
                            lionsclub.com.directoryapp.Member entityMember = new lionsclub.com.directoryapp.Member();

                            entityMember.setClubId(member.clubId);
                            entityMember.setDistId(member.distID);
                            entityMember.setName(member.mName);
                            entityMember.setProfileImage(member.profile.photo);
                            entityMember.setMobile(member.mobile);
                            entityMember.setCanEdit(member.canEdit);
                            entityMember.setEmail(member.email);
                            entityMember.setOnline(member.oL);
                            entityMember.setAboutMe(member.profile.aboutMe);
                            entityMember.setAltEmail(member.profile.alternateEmail);
                            entityMember.setAltMobile(member.profile.alternateMobile);
                            entityMember.setAltPhone(member.profile.alternatePhone);
                            entityMember.setCity(member.profile.city);
                            entityMember.setClubId(member.clubId);
                            entityMember.setClubName(member.profile.clubName);
                            entityMember.setBusinessAddress(member.profile.businessAddress);
                            entityMember.setDistId(member.distID);
                            entityMember.setDistrict(member.profile.district);
                            entityMember.setDob(member.profile.dateOfBirth);
                            entityMember.setJoinDate(member.profile.joinDate);
                            entityMember.setMarriageDate(member.profile.marriageDate);
                            entityMember.setMIntNum(Long.parseLong(member.profile.memberNumber));
                            entityMember.setUserName(member.memId);
                           /* entityMember.setPassword(member.profile.password);*/
                            entityMember.setPassword(member.profile.userName + "~" + member.profile.password);
                            entityMember.setPhone(member.profile.phone);
                            entityMember.setProfession(member.profile.profession);


                            MemberRepository.insertOrUpdate(getApplicationContext(), entityMember);

                        }

                    }


                } else {

                }
                return response.authenticated;
            } catch (Exception e) {
                return false;
            }

        }


        @Override
        protected void onPostExecute(Boolean isAuthenticated) {
            super.onPostExecute(isAuthenticated);
            System.out.println("isAuthenticated "+isAuthenticated);
            pd.dismiss();
            presenter.onResume();


        }
    }

    public class UpdateMemberInfo extends AsyncTask<Void,Void,MemberProfile>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            doShowLoading();
        }

        @Override
        protected MemberProfile doInBackground(Void... voids) {
            BDService service = new BDService();
           return  service.GPI(username,password,destMemid);

        }

        @Override
        protected void onPostExecute(MemberProfile member) {
            super.onPostExecute(member);
            doRemoveLoading();

            if(member!=null){

              Member  member_details = MemberRepository.getMemberForId(Dashboard.this, destId);
                Member  memberDataToBeUpdated = new Member();
                memberDataToBeUpdated.setId(destId);

                memberDataToBeUpdated.setAboutMe(member.aboutMe);
                memberDataToBeUpdated.setAltEmail(member.alternateEmail);

                memberDataToBeUpdated.setAltPhone(member.alternatePhone);
                memberDataToBeUpdated.setAltMobile(member.alternateMobile);

                memberDataToBeUpdated.setBusinessAddress(member.businessAddress);
                memberDataToBeUpdated.setCanEdit(member_details.getCanEdit());

                memberDataToBeUpdated.setCity(member.city);

                memberDataToBeUpdated.setClubId(member_details.getClubId());


                memberDataToBeUpdated.setClubName(member.clubName);

                memberDataToBeUpdated.setDistId(member_details.getDistId());

                memberDataToBeUpdated.setDistrict(member.district);

                memberDataToBeUpdated.setDob(member.dateOfBirth);

                memberDataToBeUpdated.setEmail(member.email);

                memberDataToBeUpdated.setIsProfileDownloaded(true);

                memberDataToBeUpdated.setJoinDate(member.joinDate);

                memberDataToBeUpdated.setMIntNum(member_details.getMIntNum());


                memberDataToBeUpdated.setMarriageDate(member.marriageDate);


                memberDataToBeUpdated.setName(member.name);
                memberDataToBeUpdated.setMobile(member.mobile);

                memberDataToBeUpdated.setOnline(member_details.getOnline());


                memberDataToBeUpdated.setPassword(member_details.getPassword());

                memberDataToBeUpdated.setPhone(member.phone);

                memberDataToBeUpdated.setProfession(member.profession);

                memberDataToBeUpdated.setProfileImage(member.photo);

                memberDataToBeUpdated.setProfilePicUrl(member.photo);


                memberDataToBeUpdated.setSpouse(member.spouse);
                memberDataToBeUpdated.setResidentialAddress(member.residentialAddress);

                memberDataToBeUpdated.setDistrict(member.district);
                memberDataToBeUpdated.setAboutMe(member.aboutMe);


                memberDataToBeUpdated.setUserName(destMemid);

                /*lionsclub.com.directoryapp.Member entityMember = new lionsclub.com.directoryapp.Member();
                Member member_details = MemberRepository.getMemberForId(Dashboard.this, destId);
                entityMember.setClubId(member_details.getClubId());
                entityMember.setDistId(member_details.getDistId());
                entityMember.setCanEdit(member_details.getCanEdit());
                entityMember.setName(member.name);
                entityMember.setProfileImage(member.photo);
                entityMember.setMobile(member.mobile);

                entityMember.setEmail(member.email);

                entityMember.setAboutMe(member.aboutMe);
                entityMember.setAltEmail(member.alternateEmail);
                entityMember.setAltMobile(member.alternateMobile);
                entityMember.setAltPhone(member.alternatePhone);
                entityMember.setCity(member.city);

                entityMember.setClubName(member.clubName);
                entityMember.setBusinessAddress(member.businessAddress);

                entityMember.setDistrict(member.district);
                entityMember.setDob(member.dateOfBirth);
                entityMember.setJoinDate(member.joinDate);
                entityMember.setMarriageDate(member.marriageDate);
                entityMember.setMIntNum(member_details.getMIntNum());


                entityMember.setPassword(member.userName + "~" + member.password);
                entityMember.setPhone(member.phone);
                entityMember.setProfession(member.profession);
                entityMember.setResidentialAddress(member.residentialAddress);

                entityMember.setSpouse(member.spouse);
                entityMember.setId(destId);*/
                MemberRepository.insertOrUpdate(Dashboard.this, memberDataToBeUpdated);


                Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                intent.putExtra("memberData", destId);

                intent.putExtra("cre", false);
                startActivity(intent);
            }else{
                System.out.println("VALUE null ");
            }
        }
    }

    public ProgressDialog dialog;

    public void doShowLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog = new ProgressDialog(Dashboard.this);
                dialog.setMessage("Please wait..........");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setIndeterminate(true);
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    public void doRemoveLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        });
    }

    public void onRowClick(long id){

        destId = id;
        Member member_details = MemberRepository.getMemberForId(Dashboard.this, destId);

        destMemid = member_details.getUserName();

        if(isOnline()){
            new UpdateMemberInfo().execute();
        }else{
            Intent intent=new Intent(Dashboard.this, MemberActivity.class);
            intent.putExtra("memberData",id);
            intent.putExtra("cre",false);

            startActivity(intent);
        }



       /* Intent intent=new Intent(Dashboard.this, MemberActivity.class);
        intent.putExtra("memberData",item.get(position).getId());
        intent.putExtra("cre",false);
        Log.e("else", "" + item.get(position).getId());
        activity.startActivity(intent);*/

    }

}
