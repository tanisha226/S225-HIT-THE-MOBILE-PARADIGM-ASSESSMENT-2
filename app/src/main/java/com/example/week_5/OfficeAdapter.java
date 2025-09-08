package com.example.week_5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OfficeAdapter extends RecyclerView.Adapter<OfficeAdapter.VH> {

    public interface OnAction {
        void onClick(Office office, String action);
    }

    private final List<Office> items;
    private final OnAction onAction;

    public OfficeAdapter(List<Office> items, OnAction onAction) {
        this.items = items;
        this.onAction = onAction;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvName;
        Button btnCall, btnMap;
        VH(@NonNull View v) {
            super(v);
            tvName = v.findViewById(R.id.tvName);
            btnCall = v.findViewById(R.id.btnCall);
            btnMap  = v.findViewById(R.id.btnMap);
        }
    }

    @NonNull
    @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_office, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        final Office o = items.get(pos);
        h.tvName.setText(o.name);
        h.btnCall.setOnClickListener(v -> onAction.onClick(o, "call"));
        h.btnMap.setOnClickListener(v -> onAction.onClick(o, "map"));
    }

    @Override public int getItemCount() { return items.size(); }
}
