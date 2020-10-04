package com.br.arley.sact.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.br.arley.sact.R;
import com.br.arley.sact.model.Criterion;

import java.util.List;

public class CriterionRecyclerViewAdapter extends RecyclerView.Adapter<CriterionRecyclerViewAdapter.ViewHolder> {

    public List<Criterion> criterionList;


    public CriterionRecyclerViewAdapter(List<Criterion> criterionList){
        this.criterionList = criterionList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        EditText tvGrade;
        private SeekBar sbGrade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_criterion_title);
            tvGrade = itemView.findViewById(R.id.item_criterion_grade);
            sbGrade = itemView.findViewById(R.id.item_criterion_seekbar);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_criterion, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.tvTitle.setText(criterionList.get(position).getTitle());

        holder.sbGrade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float gradeF = (float)progress/10;
                String gradeS = Float.toString(gradeF);
                holder.tvGrade.setText(gradeS);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return criterionList.size();
    }


}
