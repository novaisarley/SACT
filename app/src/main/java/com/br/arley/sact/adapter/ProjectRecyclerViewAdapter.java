package com.br.arley.sact.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.br.arley.sact.R;
import com.br.arley.sact.model.Project;

import java.util.List;

public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    public List<Project> projectList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onProjectClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ProjectRecyclerViewAdapter(List<Project> projectList){
        this.projectList = projectList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
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
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
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
        String members = "";
        String[] list = projectList.get(position).getMembers();

        holder.tvName.setText(projectList.get(position).getName());
        holder.tvClassroom.setText(projectList.get(position).getClassroom());
        holder.tvCourse.setText(projectList.get(position).getCourse());
        for (int i = 0; i < projectList.get(position).getMembers().length; i++) {

            if (i==0) members = list[i];
            else members += "\n" + list[i];
            holder.tvMembers.setText(members);
        }

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }


}
