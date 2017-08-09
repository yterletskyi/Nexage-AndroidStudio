//
//  VASTSamplesFragment.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//
package org.nexage.sourcekit.vastdemo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.VASTPlayer;
import org.nexage.sourcekit.vastdemo.adapter.VASTFileListAdapter;
import org.nexage.sourcekit.vastdemo.adapter.VASTListItem;
import org.nexage.sourcekit.vastdemo.util.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VASTSamplesFragment extends ListFragment {

    private final static String TAG = "VASTSamplesFragment";
    VASTPlayer newPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VASTLog.d(TAG, "onCreateView");

        VASTFileListAdapter adapter = new VASTFileListAdapter(inflater.getContext(), getTestFiles());
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);

        // Find the selected row
        VASTListItem selectedListItem = (VASTListItem) getListAdapter().getItem(position);
        VASTLog.d(TAG, "Selected List item: " + selectedListItem.getTitle());

        this.getListView().setClickable(false); // Disable multiple taps

        // Get file content
        String vastXMLContent = FileHelper.getFileContent(getActivity(),
                selectedListItem.getDescription());
        if (!TextUtils.isEmpty(vastXMLContent)) {
            // We can create VAST Player and pass the data
            newPlayer = new VASTPlayer(getActivity(),
                    new VASTPlayer.VASTPlayerListener() {

                        @Override
                        public void vastReady() {
                            VASTLog.i(TAG,
                                    "VAST Document is ready and we can play it now");
                            newPlayer.play();
                        }

                        @Override
                        public void vastError(int error) {
                            String message = "Unable to play VAST Document: Error: " + error;
                            VASTLog.e(TAG, message);
                            getListView().setClickable(true);
                        }

                        @Override
                        public void vastClick() {
                            VASTLog.e(TAG, "VAST click event fired");
                        }

                        @Override
                        public void vastComplete() {
                            VASTLog.e(TAG, "VAST complete event fired");
                        }

                        @Override
                        public void vastDismiss() {
                            VASTLog.e(TAG, "VAST dismiss event fired");
                        }
                    });

            newPlayer.loadVideoWithData(vastXMLContent);
        }

    }

    private ArrayList<VASTListItem> getTestFiles() {
        VASTLog.d(TAG, "getTestFiles");

        String[] files = null;

        Map<String, String> map = new HashMap<>();
        map.put("WrapperSimple", "Wrapper");
        map.put("SimpleTracking", "Impression Tracking");
        map.put("vast_doubleclick_inline_comp", "Inline");
        map.put("vast_liverail_linear_comp", "Linear");
        map.put("vast_missing_mediafile", "Missing MediaFile");
        map.put("vast_wrapper_linear_1", "Wrapper Linear");

        ArrayList<VASTListItem> testFiles = new ArrayList<>();
        try {
            files = getResources().getAssets().list("");
        } catch (IOException e) {
            VASTLog.e(TAG, e.getMessage(), e);
        }
        if (files != null) {
            for (String file : files) {
                String fileName = file.replace(".xml", "");

                // Ignore some files which were created by Android
                if (fileName.equals("images") || fileName.equals("sounds") || fileName.equals("webkit")) {
                    VASTLog.d(TAG, "We will ignore " + fileName);
                } else {
                    VASTLog.d(TAG, file);
                    String displayName = (map.get(fileName) == null ? fileName : map.get(fileName));
                    testFiles.add(new VASTListItem(displayName, fileName + ".xml"));
                }
            }
        }
        return testFiles;
    }

}
