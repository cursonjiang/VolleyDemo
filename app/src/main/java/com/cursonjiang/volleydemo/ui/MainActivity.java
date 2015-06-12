package com.cursonjiang.volleydemo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cursonjiang.volleydemo.App;
import com.cursonjiang.volleydemo.R;
import com.cursonjiang.volleydemo.bean.Movie;
import com.cursonjiang.volleydemo.ui.adapter.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<Movie> mMovies;
    private MovieAdapter mAdapter;
    private ListView mListView;

    private static final String url = "http://www.jycoder.com/json/movies.json";

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.movie_list);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Loading...");
        mDialog.show();

        mMovies = new ArrayList<>();
        fetchMovies();
        mAdapter = new MovieAdapter(MainActivity.this, mMovies);
        mListView.setAdapter(mAdapter);
    }

    private void fetchMovies() {
        JsonArrayRequest request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        hideDialog();
                                    }
                                }, 1000
                        );
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setImage(jsonObject.getString("image"));
                                movie.setRating(jsonObject.getString("rating"));
                                movie.setName(jsonObject.getString("name"));
                                movie.setYear(jsonObject.getString("year"));
                                mMovies.add(movie);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "error:" + error.getMessage());
                    }
                }
        );
        App.getInstance().addToRequestQueue(request);
    }

    private void hideDialog() {
        if (mDialog != null)
            mDialog.dismiss();
        mDialog = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideDialog();
    }
}
