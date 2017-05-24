package udacty.cyberavanza.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Arrays;

import udacty.cyberavanza.news.R;
import udacty.cyberavanza.news.dataholders.Article;

/**
 * Created by Ishaq Hassan on 12/3/2016.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    ArrayList<Article> trackArrayList = new ArrayList<>();
    Context context;

    public NewsListAdapter(Context ctx, ArrayList<Article> tracks){
        context = ctx;
        trackArrayList = tracks;
    }

    public NewsListAdapter(Context ctx, Article[] tracks){
        context = ctx;
        trackArrayList.addAll(Arrays.asList(tracks));
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        SimpleDraweeView placeImage;
        TextView placeTitle;
        TextView placeAddress;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.placeImage = (SimpleDraweeView) itemView.findViewById(R.id.placeImage);
            this.placeTitle = (TextView) itemView.findViewById(R.id.placeItemTitle);
            this.placeAddress = (TextView) itemView.findViewById(R.id.placeItemAddress);
        }
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder holder, final int position) {
        final Article article = trackArrayList.get(position);
        if(article.getUrlToImage() != null){
           // Glide.with(context).load(article.getUrlToImage()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.placeImage);
            Uri uri = Uri.parse(article.getUrlToImage());
            holder.placeImage.setImageURI(uri);
        }

        if(article.getTitle() != null){
            holder.placeTitle.setText(article.getTitle());
        }else{
            holder.placeTitle.setVisibility(View.GONE);
        }

        if(article.getDescription() != null){
            holder.placeAddress.setText(article.getDescription());
        }else{
            holder.placeAddress.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = article.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackArrayList.size();
    }
}
