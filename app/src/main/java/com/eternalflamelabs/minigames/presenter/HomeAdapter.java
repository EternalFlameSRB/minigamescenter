package com.eternalflamelabs.minigames.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternalflamelabs.minigames.R;

import java.util.ArrayList;
import java.util.List;

public final class HomeAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;

    public HomeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Single Player",       R.drawable.main_ai));
        mItems.add(new Item("Multiplayer",       R.drawable.main_quick));
        mItems.add(new Item("Favorite games",      R.drawable.main_bluetooth));
        mItems.add(new Item("Statistics",       R.drawable.main_stats));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.griditem, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    private static class Item {
        final String name;
        private final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}