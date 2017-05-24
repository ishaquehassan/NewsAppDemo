package udacty.cyberavanza.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import udacty.cyberavanza.news.R;
import udacty.cyberavanza.news.dataholders.Source;

/**
 * Created by Ishaq Hassan on 12/3/2016.
 */

public class SourcesListAdapter extends RecyclerView.Adapter<SourcesListAdapter.ViewHolder> {
    ArrayList<Source> trackArrayList = new ArrayList<>();
    Context context;

    public SourcesListAdapter(Context ctx, ArrayList<Source> tracks){
        context = ctx;
        trackArrayList = tracks;
    }

    public SourcesListAdapter(Context ctx, Source[] tracks){
        context = ctx;
        trackArrayList.addAll(Arrays.asList(tracks));
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView placeImage;
        TextView placeTitle;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
            this.placeTitle = (TextView) itemView.findViewById(R.id.placeItemTitle);
        }
    }

    @Override
    public SourcesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.source_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SourcesListAdapter.ViewHolder holder, final int position) {
        final Source article = trackArrayList.get(position);
        if(article.getUrlsToLogos().getMedium() != null){
            Glide.with(context).load(article.getUrlsToLogos().getMedium()).into(holder.placeImage);
        }

        if(article.getName() != null){
            holder.placeTitle.setText(article.getName());
        }else{
            holder.placeTitle.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(article.getClickListener());
    }

    @Override
    public int getItemCount() {
        return trackArrayList.size();
    }
}
