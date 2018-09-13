package net.husnilkamil.kamusindo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.husnilkamil.kamusindo.R;
import net.husnilkamil.kamusindo.db.Kamus;

import java.util.ArrayList;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusHolder> {

    ArrayList<Kamus> dataKamus;

    public void setData(ArrayList<Kamus> data){
        dataKamus = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KamusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View kamusView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        KamusHolder kamusHolder = new KamusHolder(kamusView);
        return kamusHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KamusHolder kamusHolder, int i) {
        Kamus k = dataKamus.get(i);
        kamusHolder.textWord.setText(k.getWord());
        kamusHolder.textDefinition.setText(k.getDefinition());
    }

    @Override
    public int getItemCount() {
        if(dataKamus == null){
            return 0;
        }
        return dataKamus.size();
    }

    public class KamusHolder extends RecyclerView.ViewHolder{

        public TextView textWord;
        public TextView textDefinition;

        public KamusHolder(@NonNull View itemView) {
            super(itemView);
            textWord = itemView.findViewById(R.id.word);
            textDefinition = itemView.findViewById(R.id.definition);
        }
    }
}
