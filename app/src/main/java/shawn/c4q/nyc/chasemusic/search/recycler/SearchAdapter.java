package shawn.c4q.nyc.chasemusic.search.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import shawn.c4q.nyc.chasemusic.R;
import shawn.c4q.nyc.chasemusic.model.itunesmodel.Result;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

    private ArrayList<Result> resultList;

    public SearchAdapter(ArrayList<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view_holder, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        final Result result = resultList.get(holder.getAdapterPosition());
        holder.bind(result);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
