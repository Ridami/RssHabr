package ru.ridami.rsshabr.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import ru.ridami.rsshabr.R;
import ru.ridami.rsshabr.pojo.Item;
import ru.ridami.rsshabr.pojo.Rss;

/**
 * Created by Forest on 03.03.2018.
 */

public class RecyclerViewFragment extends Fragment {
    private static final String TAG = "FragmentRecyclerList";
    private static final int CURRENT_LOADER_ID = 13;
    private RecyclerView mRecycler;
    private RssAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Item> mDataset;
    private SwipeRefreshLayout mSwipe;
    LoaderManager.LoaderCallbacks<Rss> callbacks;


    public static RecyclerViewFragment newInstance() {
        Bundle args = new Bundle();
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbacks = new DataLoadCallback();
        getLoaderManager().initLoader(CURRENT_LOADER_ID, Bundle.EMPTY, callbacks);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_recyclerview, container, false);

        swipeInit(view);
        recyclerInit(view);

        return view;
    }

    private void recyclerInit(View view) {
        mRecycler = view.findViewById(R.id.recycler_rss_list);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new RssAdapter(mDataset);
        mRecycler.setAdapter(mAdapter);
    }

    private void swipeInit(View view) {
        mSwipe = view.findViewById(R.id.swipe_refresh_layout);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLoaderManager().restartLoader(CURRENT_LOADER_ID, Bundle.EMPTY, callbacks);
            }
        });
    }

    private class DataLoadCallback implements LoaderManager.LoaderCallbacks<Rss>
    {
        @Override
        public Loader<Rss> onCreateLoader(int id, Bundle args) {
            if (id == CURRENT_LOADER_ID)
                return new RssLoader(getContext());
            else
                return null;
        }

        @Override
        public void onLoadFinished(Loader<Rss> loader, Rss data) {
            if (loader.getId() == CURRENT_LOADER_ID) {
                if (data == null) {
                    Toast.makeText(getActivity(), "Something going wrong. Try again later." , Toast.LENGTH_SHORT).show();
                    mSwipe.setRefreshing(false);
                    return;
                }
                mDataset = data.getChannel().getItemsList();
                mAdapter.setDataSet(mDataset);
                mAdapter.notifyDataSetChanged();
                mSwipe.setRefreshing(false);
            }
        }

        @Override
        public void onLoaderReset(Loader<Rss> loader) {

        }

    }
}
