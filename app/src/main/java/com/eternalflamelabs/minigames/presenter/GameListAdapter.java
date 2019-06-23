package com.eternalflamelabs.minigames.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public final class GameListAdapter extends ArrayAdapter<ListItem> {
    private Context mContext;
    private List<ListItem> list_items;

    public GameListAdapter(@NonNull Context context, ArrayList<ListItem> list) {
        super(context, 0 , list);
        mContext = context;
        list_items = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listitem,parent,false);

        ListItem list_item = list_items.get(position);

        ImageView image = listItem.findViewById(R.id.card_view_image);
        image.setImageResource(list_item.getImage());

        TextView title = listItem.findViewById(R.id.card_view_title);
        title.setText(list_item.getTitle());

        TextView description = listItem.findViewById(R.id.card_view_text);
        description.setText(list_item.getText());

        return listItem;
    }
}