package com.example.lakshmikanta.sampleui;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.lakshmikanta.sampleui.model.*;

/**
 * Created by Lakshmi Kanta on 3/29/2016.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NOTES = 0, HEADER = 1, ITEMS = 2, GROUP_HEADER = 3;
    private LayoutInflater inflater = null;
    private LabvaluesModel labvaluesModel;
    private int itemListOffset = 0;

    public CustomRecyclerViewAdapter(FragmentActivity activity, LabvaluesModel labvaluesModel) {
        inflater = LayoutInflater.from(activity);
        this.labvaluesModel = labvaluesModel;
    }

    @Override
    public int getItemViewType(int position) {

        if (labvaluesModel.headers != null && labvaluesModel.headers.size() > 0 && position == 0) {
            return HEADER;
        } else {
            if (labvaluesModel.notes != null && labvaluesModel.notes.length() > 0 && position == 1) {
                return NOTES;
            } else {
                if (labvaluesModel.items.get(position - itemListOffset).groupHeader) {
                    return GROUP_HEADER;
                } else {
                    return ITEMS;
                }
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case HEADER: {
                View v1 = inflater.inflate(R.layout.labvalues_header, parent, false);
                viewHolder = new HeaderViewholder(v1);

                break;
            }
            case NOTES: {
                View v2 = inflater.inflate(R.layout.labvalues_note, parent, false);
                viewHolder = new NoteViewholder(v2);

                break;
            }
            case GROUP_HEADER: {
                View v3 = inflater.inflate(R.layout.groupheader, parent, false);
                viewHolder = new GroupheaderVH(v3);

                break;
            }
            case ITEMS: {
                View v4 = inflater.inflate(R.layout.labvalues_item, parent, false);
                viewHolder = new ItemViewholder(v4);

                break;
            }
            default: {
                View v = inflater.inflate(R.layout.labvalues_item, parent, false);
                viewHolder = new ItemViewholder(v);

                break;
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HEADER: {
                HeaderViewholder vh1 = (HeaderViewholder) holder;
                configureViewHolderHeader(vh1, position);

                break;
            }
            case NOTES: {
                NoteViewholder vh2 = (NoteViewholder) holder;
                configureNoteViewHolder(vh2);

                break;
            }
            case GROUP_HEADER: {
                GroupheaderVH vh3 = (GroupheaderVH) holder;
                configureGroupheaderViewHolder(vh3, position);

                break;
            }
            case ITEMS: {
                ItemViewholder vh4 = (ItemViewholder) holder;
                configureItemViewHolder(vh4, position);

                break;
            }
            default: {
                ItemViewholder vh = (ItemViewholder) holder;
                configureItemViewHolder(vh, position);

                break;
            }
        }
    }


    private void configureGroupheaderViewHolder(GroupheaderVH vh3, int position) {
        vh3.title.setText(labvaluesModel.items.get(position - itemListOffset).title);
    }

    private void configureNoteViewHolder(NoteViewholder vh2) {
        vh2.note.setText(labvaluesModel.notes);
    }

    private void configureViewHolderHeader(HeaderViewholder vh1, int position) {
        ArrayList<String> obj = labvaluesModel.headers;
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                vh1.firstheader.setText(obj.get(i));
            }
            if (i == 1) {
                vh1.secondheader.setText(obj.get(i));
            }
            if (i == 2) {
                vh1.thirdheader.setText(obj.get(i));
            }
        }
    }

    @Override
    public int getItemCount() {
        itemListOffset = 0;
        int itemCount = labvaluesModel.items.size();
        if (labvaluesModel.headers != null && labvaluesModel.headers.size() > 0) {
            itemCount++;
            itemListOffset++;
        }
        if (labvaluesModel.notes != null && labvaluesModel.notes.length() > 0) {
            itemCount++;
            itemListOffset++;
        }
        return itemCount;

    }

    private void configureItemViewHolder(ItemViewholder vh, int position) {
        vh.firstcolumn.setText(Html.fromHtml((labvaluesModel.items.get(position - itemListOffset).title) + ""));
        vh.secondcolumn.setText(Html.fromHtml((labvaluesModel.items.get(position - itemListOffset).range) + ""));
        vh.thirdcolumn.setText(Html.fromHtml((labvaluesModel.items.get(position - itemListOffset).siRange) + ""));
    }
}

class HeaderViewholder extends RecyclerView.ViewHolder {
    public TextView firstheader, secondheader, thirdheader;

    public HeaderViewholder(View itemView) {
        super(itemView);
        this.firstheader = (TextView) itemView.findViewById(R.id.firstheader);
        this.secondheader = (TextView) itemView.findViewById(R.id.secondheader);
        this.thirdheader = (TextView) itemView.findViewById(R.id.thirdheader);
    }
}

class ItemViewholder extends RecyclerView.ViewHolder {
    public TextView firstcolumn, secondcolumn, thirdcolumn;

    public ItemViewholder(View itemView) {
        super(itemView);
        this.firstcolumn = (TextView) itemView.findViewById(R.id.firstcolumn);
        this.secondcolumn = (TextView) itemView.findViewById(R.id.secondcolumn);
        this.thirdcolumn = (TextView) itemView.findViewById(R.id.thirdcolumn);
    }
}

class GroupheaderVH extends RecyclerView.ViewHolder {
    public TextView title;

    public GroupheaderVH(View itemView) {
        super(itemView);
        this.title = (TextView) itemView.findViewById(R.id.groupheaderTitle);
    }
}

class NoteViewholder extends RecyclerView.ViewHolder {
    public TextView note;

    public NoteViewholder(View itemView) {
        super(itemView);
        this.note = (TextView) itemView.findViewById(R.id.note);
    }
}
