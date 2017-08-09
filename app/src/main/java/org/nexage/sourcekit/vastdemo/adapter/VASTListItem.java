//
//  VASTListItem.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package org.nexage.sourcekit.vastdemo.adapter;

public class VASTListItem {

    private String mTitle;
    private String mDescription;

    public VASTListItem(String title, String description) {
        super();
        setTitle(title);
        setDescription(description);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
