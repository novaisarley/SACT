package com.br.arley.sact.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.br.arley.sact.R;
import com.br.arley.sact.model.Criterion;

import java.util.List;

public class CriterionRecyclerViewAdapter extends RecyclerView.Adapter<CriterionRecyclerViewAdapter.ViewHolder> {

    public List<Criterion> criterionList;
    public Context context;

    public CriterionRecyclerViewAdapter(List<Criterion> criterionList, Context context){
        this.criterionList = criterionList;
        this.context = context;
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

        Float maxGrade = criterionList.get(position).getMaxGrade();
        Float minGrade = criterionList.get(position).getMinGrade();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.sbGrade.setMin((int)criterionList.get(position).getMinGrade()*10);
        }
        holder.sbGrade.setMax((int)criterionList.get(position).getMaxGrade()*10);

        holder.sbGrade.setProgress((int)criterionList.get(position).getMinGrade()*10);

        float gradeF = (float)holder.sbGrade.getProgress()/10;
        String gradeS = Float.toString(gradeF);
        holder.tvGrade.setText(gradeS);

        /*Log.d("FICHA", (int)criterionList.get(position).getMaxGrade() + "");
        Log.d("FICHA", (int)criterionList.get(position).getMinGrade() + "");
        Log.d("FICHA", (int)holder.sbGrade.getProgress() + "");*/

        holder.sbGrade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float gradeF = (float)progress/10;
                String gradeS = Float.toString(gradeF);
                holder.tvGrade.setText(gradeS);
                criterionList.get(position).setGrade(gradeS);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        holder.tvGrade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    EditText edtGrade = (EditText)v;

                    String grade = ((EditText)v).getText().toString().trim();

                    if (!grade.isEmpty()){
                        if (Float.parseFloat(grade) > maxGrade){
                            edtGrade.setText(Float.toString(maxGrade));
                            Toast.makeText(context, "A nota máxima para esse critério é " + Float.toString(maxGrade), Toast.LENGTH_SHORT).show();
                        }
                        else if (Float.parseFloat(grade) < minGrade){
                            edtGrade.setText(Float.toString(minGrade));
                            Toast.makeText(context, "A nota mínima para esse critério é " + Float.toString(minGrade), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            edtGrade.setText(Float.toString(Float.parseFloat(edtGrade.getText().toString().trim())));
                        }
                    }
                }
            }
        });

        holder.tvGrade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return criterionList.size();
    }


}
