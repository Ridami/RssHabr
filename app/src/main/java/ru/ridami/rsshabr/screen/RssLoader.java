package ru.ridami.rsshabr.screen;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import ru.ridami.rsshabr.network.Api;
import ru.ridami.rsshabr.pojo.Rss;

/**
 * Created by Forest on 04.03.2018.
 */

public class RssLoader extends AsyncTaskLoader<Rss> {
    public RssLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Rss loadInBackground() {
        return Api.loadRSS();
    }
}
