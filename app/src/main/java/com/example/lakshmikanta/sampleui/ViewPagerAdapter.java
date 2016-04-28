package com.example.lakshmikanta.sampleui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.lakshmikanta.sampleui.model.*;

import java.util.ArrayList;

/**
 * Created by Lakshmi Kanta on 3/29/2016.
 */

class ViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<LabvaluesModel> labvaluesModels;
    public ViewPagerAdapter(FragmentManager manager, ArrayList<LabvaluesModel> labvaluesModels) {
        super(manager);
        this.labvaluesModels = labvaluesModels;
    }

    @Override
    public Fragment getItem(int position){
        LabvaluesModel labvaluesModel = labvaluesModels.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("LAB_VALUE", labvaluesModel);
        Fragment fragment = new OneFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return labvaluesModels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return labvaluesModels.get(position).title;
    }
}
