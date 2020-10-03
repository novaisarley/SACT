package com.br.arley.sact.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.br.arley.sact.R;
import com.br.arley.sact.model.Section;

import java.util.List;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.ViewHolder> {

    public List<Section> sectionList;
    Context context;

    public SectionRecyclerViewAdapter(List<Section> sectionList, Context context){
        this.sectionList = sectionList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvNumber;
        private RecyclerView rvCriterion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.item_section_number);
            tvTitle = itemView.findViewById(R.id.item_section_title);
            rvCriterion = itemView.findViewById(R.id.item_section_recycler_view);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CriterionRecyclerViewAdapter criterionRecyclerViewAdapter;

        holder.tvTitle.setText(sectionList.get(position).getTitle());
        holder.tvNumber.setText(String.format("%s.", Integer.toString(position + 1)));

        holder.rvCriterion.setLayoutManager(new LinearLayoutManager(context));
        criterionRecyclerViewAdapter = new CriterionRecyclerViewAdapter(sectionList.get(position).getCriterionList());

        holder.rvCriterion.setAdapter(criterionRecyclerViewAdapter);

    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


}
