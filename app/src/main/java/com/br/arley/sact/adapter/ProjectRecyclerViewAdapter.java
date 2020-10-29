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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.br.arley.sact.R;
import com.br.arley.sact.model.Avaliation;
import com.br.arley.sact.model.Constants;
import com.br.arley.sact.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    public List<Avaliation> avaliationList;
    private OnItemClickListener mListener;
    private Context context;


    public interface OnItemClickListener {
        void onProjectClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ProjectRecyclerViewAdapter(List<Avaliation> avaliationList, Context context) {
        this.avaliationList = avaliationList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvClassroom, tvCourse, tvMembers;
        private ConstraintLayout layoutProject;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvClassroom = itemView.findViewById(R.id.project_tv_class);
            tvName = itemView.findViewById(R.id.project_tv_name);
            tvCourse = itemView.findViewById(R.id.project_tv_course);
            tvMembers = itemView.findViewById(R.id.project_tv_members);
            layoutProject = itemView.findViewById(R.id.project_constraint_layout);


            layoutProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onProjectClick(position);
                        }
                    }
                }
            });

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mListener);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String members = avaliationList.get(position).getProject().getMembers();
        String[] list = members.split(",");

        holder.tvName.setText(avaliationList.get(position).getProject().getName());
        holder.tvClassroom.setText(avaliationList.get(position).getProject().getClassroom());
        holder.tvCourse.setText(avaliationList.get(position).getProject().getOccupationArea());

        if (avaliationList.get(position).getStatus().equals(Constants.RATED)){
            holder.tvName.setBackground(ContextCompat.getDrawable(context, R.drawable.caixa_subject_green));
        }
        else if(avaliationList.get(position).getStatus().equals(Constants.TO_EVALUATE)){
            holder.tvName.setBackground(ContextCompat.getDrawable(context, R.drawable.caixa_subject_blue));
        }

        for (int i = 0; i < list.length; i++) {
            if (i == 0) members = list[i];
            else members += "\n" + list[i].trim();
            holder.tvMembers.setText(members);
        }

    }

    @Override
    public int getItemCount() {
        return avaliationList.size();
    }


}
