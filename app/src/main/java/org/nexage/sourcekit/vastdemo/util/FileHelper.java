//
//  FileHelper.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package org.nexage.sourcekit.vastdemo.util;

import android.app.Activity;

import org.nexage.sourcekit.util.VASTLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHelper {

    private static final String TAG = "FileHelper";

    // This utility function retrieves a file from assets folder and return
    // the content as string
    public static String getFileContent(Activity activity, String fileName) {
        StringBuilder sb = new StringBuilder();
        VASTLog.v(TAG, "getFileContent\n");

        try {
            InputStream is = activity.getResources().getAssets().open(fileName);
            BufferedReader bufferReader = new BufferedReader(
                    new InputStreamReader(is));
            String line;
            while ((line = bufferReader.readLine()) != null) {
                sb.append(line);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileContent = sb.toString();
        VASTLog.v(TAG, fileContent);
        return fileContent;
    }

}
