package com.outsource.gotopartjob;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    private ArrayList<TMapPOIItem> list = new ArrayList<>();
    private List<String> name= new ArrayList<>();
    private List<String> address= new ArrayList<>();

    Activity activity;

    public Adapter(Activity activity) {
        this.activity=activity;
        list = GetTMapDataActivity.list;
        name = GetTMapDataActivity.name;
        address = GetTMapDataActivity.address;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(name.get(position));
        holder.content.setText(address.get(position).replace("null",""));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setString(activity, PreferenceManager.KEY_LAT, String.valueOf(list.get(position).getPOIPoint().getLatitude()));
                PreferenceManager.setString(activity, PreferenceManager.KEY_LON, String.valueOf(list.get(position).getPOIPoint().getLongitude()));
                activity.setResult(Activity.RESULT_OK);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (name != null ? name.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, content;
        ImageButton button;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title);
            content = (TextView)itemView.findViewById(R.id.content);
            button = (ImageButton)itemView.findViewById(R.id.button_);
        }
    }
}
