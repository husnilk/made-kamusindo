package net.husnilkamil.kamusindo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import net.husnilkamil.kamusindo.R;
import net.husnilkamil.kamusindo.adapter.KamusAdapter;
import net.husnilkamil.kamusindo.db.Kamus;
import net.husnilkamil.kamusindo.db.KamusHelper;

import java.util.ArrayList;

public class EnglishFragment extends Fragment {


    SearchView searchView;
    RecyclerView recyclerViewEng;
    KamusAdapter engAdapter;
    KamusHelper kamusHelper;

    public EnglishFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_english, container, false);

        kamusHelper = new KamusHelper(getContext());

        //Init recycler View
        engAdapter = new KamusAdapter();

        recyclerViewEng = v.findViewById(R.id.rvEngIndo);
        recyclerViewEng.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewEng.setAdapter(engAdapter);

        searchView = v.findViewById(R.id.svEngIndo);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                kamusHelper.open();
                ArrayList<Kamus> wordList = kamusHelper.search(KamusHelper.ENG, query);
                engAdapter.setData(wordList);
                kamusHelper.close();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return v;
    }



}
