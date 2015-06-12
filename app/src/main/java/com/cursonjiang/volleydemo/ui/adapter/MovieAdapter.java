package com.cursonjiang.volleydemo.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cursonjiang.volleydemo.App;
import com.cursonjiang.volleydemo.R;
import com.cursonjiang.volleydemo.bean.Movie;

import java.util.ArrayList;

/**
 * Created by root on 15/6/13.
 */
public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private Activity activity;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public MovieAdapter(Activity activity, ArrayList<Movie> movies) {
        this.activity = activity;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (imageLoader == null)
            imageLoader = App.getInstance().getImageLoader();
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            viewHolder.mImageView = (NetworkImageView) view.findViewById(R.id.movie_image);
            viewHolder.movieName = (TextView) view.findViewById(R.id.movie_name);
            viewHolder.movieRating = (TextView) view.findViewById(R.id.movie_rating);
            viewHolder.movieYear = (TextView) view.findViewById(R.id.movie_year);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mImageView.setImageUrl(movies.get(i).getImage(), imageLoader);
        viewHolder.movieName.setText(movies.get(i).getName());
        viewHolder.movieRating.setText("Rating:" + movies.get(i).getRating());
        viewHolder.movieYear.setText(movies.get(i).getYear());

        return view;
    }

    public class ViewHolder {
        public NetworkImageView mImageView;
        public TextView movieName;
        public TextView movieRating;
        public TextView movieYear;
    }
}
