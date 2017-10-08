package shawn.c4q.nyc.chasemusic.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import shawn.c4q.nyc.chasemusic.R;
import shawn.c4q.nyc.chasemusic.model.itunesmodel.Result;

import static shawn.c4q.nyc.chasemusic.R.layout.swipe_fragment;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class SwipeableLyricFragment extends SwipeAwayDialogFragment{

    @BindView(R.id.background_iv)
    ImageView backgroundImageView;
    @BindView(R.id.frag_track_name)
    TextView trackNameTextView;
    @BindView(R.id.frag_artist_name)
    TextView artistNameTextView;
    @BindView(R.id.frag_album_name)
    TextView albumNameTextView;
    @BindView(R.id.lyrics_tv)
    TextView lyricsTextView;
    private Bundle importeBundle;
    private Result currentSongResult;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        importeBundle = getArguments();
        if(importeBundle != null) {
            currentSongResult = (Result) importeBundle.getSerializable("RESULT_KEY");
        }else{
            Log.d("DEBUG TOOL", "import bundle is null");
        }
        AppCompatDialog dialog = new AppCompatDialog(getActivity(), R.style.AppTheme);
        View view = View.inflate(getActivity().getBaseContext(), swipe_fragment, null);
        ButterKnife.bind(this, view);
        dialog.setContentView(view);

        if(currentSongResult != null){
            initViews(currentSongResult, getActivity().getBaseContext());
        }else{
            Log.d("DEBUG TOOL", "currentSongResult is null");
        }
        // for the interest of time I went with a less favorable method to retrieve the php JSON. It would've been niice to find a way to implement this without the use of Asynch Task.
        new GetLyrics(new LyricsRecievedCallback() {
            @Override
            public void lyricsRecieved(String s) {
                retreivedJson(s);
            }
        }, currentSongResult.getArtistName(), currentSongResult.getTrackName()).execute();
        return dialog;
    }


    private void initViews(Result result, Context context){
        Glide.with(context).load(result.getImgUrl()).into(backgroundImageView);
        trackNameTextView.setText(result.getTrackName());
        artistNameTextView.setText(result.getArtistName());
        albumNameTextView.setText(result.getAlbumName());

    }


    private void retreivedJson(String s) {
        lyricsTextView.setText(s);
        Log.d("DEBUG TOOL", s);
    }

}
