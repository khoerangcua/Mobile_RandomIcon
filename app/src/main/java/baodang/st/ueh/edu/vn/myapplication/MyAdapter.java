package baodang.st.ueh.edu.vn.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

public class MyAdapter extends ArrayAdapter {
    List<Integer> ds;
    public MyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.ds = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        Integer pathname = ds.get(position);
        view.setBackgroundResource(pathname);
        view.setText("");

        return view;
    }
}
