package com.example.bai2;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Item> itemList;
    boolean isShowAll;
    int positionToBeDeleted;
    SimpleDateFormat sdfDate;
    SimpleDateFormat sdfTime;

    public MyAdapter(List<Item> itemList) {
        this.itemList = itemList;
        isShowAll = false;
        sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        sdfTime = new SimpleDateFormat("HH:mm");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPlace.setText(item.getPlace());
        holder.tvDate.setText(sdfDate.format(item.calendar.getTime()));
//        holder.tvTime.setText(item.calendar.get(Calendar.HOUR_OF_DAY)+":"+item.calendar.get(Calendar.MINUTE));
        holder.tvTime.setText(sdfTime.format(item.calendar.getTime()));


        //switch on/off when clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.checked = !item.checked;
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        //set position to be deleted when long clicked
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MyAdapter.this.positionToBeDeleted = holder.getAdapterPosition();
                return false;
            }
        });

        //switch state logic (similar to checkbox)
        holder.swSwitch.setOnCheckedChangeListener(null);
        holder.swSwitch.setChecked(item.checked);
        holder.swSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.checked = isChecked;
            }
        });

        if (item.checked) {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        else {
            if (isShowAll) {
                holder.itemView.setVisibility(View.VISIBLE);
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else {
                holder.itemView.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
            }
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tvName;
        TextView tvPlace;
        TextView tvDate;
        TextView tvTime;
        Switch swSwitch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            swSwitch = itemView.findViewById(R.id.swSwitch);
        }
    }


}

