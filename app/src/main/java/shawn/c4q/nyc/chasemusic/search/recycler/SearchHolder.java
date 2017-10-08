package shawn.c4q.nyc.chasemusic.search.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import shawn.c4q.nyc.chasemusic.R;
import shawn.c4q.nyc.chasemusic.model.itunesmodel.Result;
import shawn.c4q.nyc.chasemusic.search.SearchItemCallback;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class SearchHolder extends RecyclerView.ViewHolder {

    private Context context;
    private SearchItemCallback callBack;
    @BindView(R.id.album_artwork_iv)
    ImageView artworkIv;
    @BindView(R.id.track_name_tv)
    TextView trackNameTv;
    @BindView(R.id.artist_name_tv)
    TextView artistNameTv;
    @BindView(R.id.artist_album_tv)
    TextView artistAlbumNameTv;

    public SearchHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        callBack = (SearchItemCallback) context;


    }

    public void bind(final Result result) {
        Glide.with(context).load(result.getImgUrl()).into(artworkIv);
        trackNameTv.setText(result.getTrackName());
        artistNameTv.setText(result.getArtistName());
        artistAlbumNameTv.setText(result.getAlbumName());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.handleResultClick(view, result);
            }
        });
    }
}
