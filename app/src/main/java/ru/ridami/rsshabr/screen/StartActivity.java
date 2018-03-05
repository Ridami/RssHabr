package ru.ridami.rsshabr.screen;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import ru.ridami.rsshabr.R;
import ru.ridami.rsshabr.base.AbstractSingleFragActivity;

public class StartActivity extends AbstractSingleFragActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment getFragment() {
        return RecyclerViewFragment.newInstance();
    }
}
