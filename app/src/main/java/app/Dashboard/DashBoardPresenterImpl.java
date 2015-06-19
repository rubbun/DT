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

import android.content.Context;

import java.util.List;

import lionsclub.com.directoryapp.Club;
import lionsclub.com.directoryapp.Member;


public class DashBoardPresenterImpl implements DashBoardPresenter, OnFinishedListener {

    Context mContext;
    private DashBoardView dashBoardView;
    private MemberDataInteractor memberDataInteractor;

    public DashBoardPresenterImpl(DashBoardView dashBoardView, Context context) {
        this.dashBoardView = dashBoardView;
        this.mContext = context;
        memberDataInteractor = new MemberDataInteractorImpl();
    }

    @Override
    public void onResume() {
        dashBoardView.showProgress();
        memberDataInteractor.getMemberData(this, mContext);
        memberDataInteractor.getClubList(this, mContext);
    }

    @Override
    public void onItemClicked(int position) {
        dashBoardView.showMessage(String.format("Position %d clicked", position + 1));


    }

    @Override
    public void onLoadClubs(List<Club> clubs) {
        dashBoardView.loadClubs(clubs);
    }

    @Override
    public void onFinished(List<Member> memberList) {
        dashBoardView.setItems(memberList);
        dashBoardView.hideProgress();
    }
}
