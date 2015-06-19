package app.admin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import lionsclub.com.directoryapp.CredentialRequestQueue;
import lionsclub.com.directoryapp.R;

public class CredentialRequest extends ActionBarActivity implements CredentialRequestView {

    RecyclerView recyclerView;
    CredentialRequestPresenter credentialRequestPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_request);
        recyclerView = (RecyclerView) findViewById(R.id.memberListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress);
        credentialRequestPresenter = new CredentialRequestPresenterImplementation(this, CredentialRequest.this);
        credentialRequestPresenter.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_credential_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void searchMember() {

    }

    @Override
    public void onLoadMemberData(List<CredentialRequestQueue> credentialRequestQueues) {

        System.out.println("credentialRequestQueues "+credentialRequestQueues.size());
        List<CredentialRequestQueue> credentialRequestQueuestemp = new ArrayList<CredentialRequestQueue>();

        for(int i=0; i<credentialRequestQueues.size(); i++){
            if(i==0){
                credentialRequestQueuestemp.add(credentialRequestQueues.get(i));
            }else{
             boolean  flag = false;

                for(int j=0;j<credentialRequestQueuestemp.size(); j++){
                    if(credentialRequestQueuestemp.get(j).getMemInt() == credentialRequestQueues.get(i).getMemInt()){
                        flag = true;
                        break;
                    }
                }

                if(!flag){
                    credentialRequestQueuestemp.add(credentialRequestQueues.get(i));
                }
            }
        }
        recyclerView.setAdapter(new CredentialRequestRecyclerAdapter(CredentialRequest.this, credentialRequestQueuestemp));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void navigateToMemberDetail() {

    }
}
