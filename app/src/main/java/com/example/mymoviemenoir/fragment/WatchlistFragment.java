package com.example.mymoviemenoir.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviemenoir.R;
import com.example.mymoviemenoir.RoomEntity.MOVIE;
import com.example.mymoviemenoir.adapter.WatchlistRecyclerViewAdapter;
import com.example.mymoviemenoir.model.WatchlistResult;
import com.example.mymoviemenoir.viewmodel.WatchlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class WatchlistFragment extends Fragment {
    private View view = null;
    private WatchlistRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<WatchlistResult> movies;
    private WatchlistViewModel watchlistViewModel;

    public WatchlistFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        this.view = inflater.inflate(R.layout.fragment_watchlist, container, false);

        //Ready for the the recycler view
        recyclerView = view.findViewById(R.id.watchlistRecyclerView);
        movies = new ArrayList<WatchlistResult>();

        //Query Room to get all movies
        watchlistViewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        watchlistViewModel.initialize(getActivity().getApplication());
        watchlistViewModel.getAllMovies().observe(getViewLifecycleOwner(), new Observer<List<MOVIE>>() {
            @Override
            public void onChanged(List<MOVIE> moviesResult) {
                for(MOVIE thisMOVIE : moviesResult){
                    String movieName = thisMOVIE.getMovieName();
                    String releaseDate = thisMOVIE.getReleaseDate();
                    String timeAdded = thisMOVIE.getTimeAdded();

                    //Add all the movies from room to the rv model
                    WatchlistResult thisMovieResult = new WatchlistResult(movieName, releaseDate, timeAdded);
                    movies.add(thisMovieResult);
                }

                //setup the recycler view
                adapter = new WatchlistRecyclerViewAdapter(movies);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

            }
        });



        return view;
    }
}
