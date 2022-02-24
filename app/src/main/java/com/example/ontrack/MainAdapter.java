package com.example.ontrack;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<Event> data;
    private static ClickListener listener;

    public interface ClickListener
    {
        void click(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView;
        private final TextView dateText;
        View view;

        public ViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            dateText = (TextView) itemView.findViewById(R.id.tvDate);
            view = itemView;
        }

        public TextView getTextView()
        {
            return textView;
        }
        public TextView getDateText() { return dateText; }
    }

    public MainAdapter(ArrayList data, ClickListener listener)
    {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = data.get(position);
        holder.getTextView().setText(event.name);
        holder.getDateText().setText(event.makeDate());

        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                listener.click(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
