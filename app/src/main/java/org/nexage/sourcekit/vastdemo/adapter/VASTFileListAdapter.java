//
//  VASTFileListAdapter.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package org.nexage.sourcekit.vastdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.nexage.sourcekit.vastdemo.R;

import java.util.ArrayList;

public class VASTFileListAdapter extends ArrayAdapter<VASTListItem> {

    private final Context mContext;
    private final ArrayList<VASTListItem> mVASTListItems;

    public VASTFileListAdapter(Context context, ArrayList<VASTListItem> VASTListItems) {
        super(context, R.layout.row, VASTListItems);
        mContext = context;
        mVASTListItems = VASTListItems;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);

        // 4. Set the text for textView
        labelView.setText(mVASTListItems.get(position).getTitle());
        valueView.setText(mVASTListItems.get(position).getDescription());

        // 5. return rowView
        return rowView;
    }
}