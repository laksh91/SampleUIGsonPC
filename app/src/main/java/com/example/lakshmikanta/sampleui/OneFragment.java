package com.example.lakshmikanta.sampleui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lakshmikanta.sampleui.model.*;
import java.util.ArrayList;


public class OneFragment extends Fragment {
    private LabvaluesModel labvaluesModel;
    public RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        labvaluesModel = (LabvaluesModel) getArguments().getParcelable("LAB_VALUE");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one,container,false);
        Log.e("IN_FRAGMENT", String.valueOf(labvaluesModel.items.get(0).title));
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(getActivity(),labvaluesModel);
        mRecyclerView.setAdapter(adapter);
        return v;
    }
}
