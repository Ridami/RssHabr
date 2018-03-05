package ru.ridami.rsshabr.screen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import org.xml.sax.XMLReader;
import ru.ridami.rsshabr.R;
import ru.ridami.rsshabr.pojo.Hab;
import ru.ridami.rsshabr.pojo.Item;

/**
 * Created by Forest on 04.03.2018.
 */

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.ViewHolder> {
    List<Item> dataSet;
    private static final Drawable TRANSPARENT_DRAWABLE = new ColorDrawable(Color.TRANSPARENT);

    public RssAdapter(List<Item> mDataset) {
        this.dataSet = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_rss_list_item, parent, false);

        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataSet == null) return 0;
        return dataSet.size();
    }

    public void setDataSet(List<Item> dataSet) {
        this.dataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener,
                    Html.ImageGetter,
                    Html.TagHandler
    {
        private static final String TAG = "Holder";

        TextView tvTitle;
        TextView tvPabData;
        TextView tvDescription;
        TextView tvTagsContainer;
        String link;
        Context context;


        public ViewHolder(View v, Context context) {
            super(v);
            v.setOnClickListener(this);
            this.context = context;
            tvTitle = v.findViewById(R.id.title);
            tvDescription = v.findViewById(R.id.description);
            tvPabData = v.findViewById(R.id.pub_date);
            tvTagsContainer = v.findViewById(R.id.tags_container);
        }

        public void setData(Item data) {
            tvTitle.setText(data.getTitle());
            tvDescription.setText(Html.fromHtml(data.getDescription().replace("<br> <br>", "<br>"),this, this));
            tvPabData.setText(data.getPubDate());
            tvTagsContainer.setText(getDescription(data));

            link = data.getLink();
        }

        private StringBuilder getDescription(Item data){
            StringBuilder stringBuilder  = new StringBuilder();
            for (Hab hab :
                data.getHabs()) {
                stringBuilder.append("[\u00A0")
                    .append(hab.getContent().toLowerCase())
                    .append("\u00A0] ");
            }
            return stringBuilder;
        }

        @Override public void onClick(View view) {
            Intent openWeb = new Intent();
            openWeb.setAction(Intent.ACTION_VIEW);
            openWeb.setData(Uri.parse(link));
            context.startActivity(openWeb);
        }

        @Override public Drawable getDrawable(String s) {
            TRANSPARENT_DRAWABLE.setBounds(0 , 0 , 1, 1);
            return TRANSPARENT_DRAWABLE;
        }

        @Override
        public void handleTag(boolean b, String s, Editable editable, XMLReader xmlReader) { }
    }
}
