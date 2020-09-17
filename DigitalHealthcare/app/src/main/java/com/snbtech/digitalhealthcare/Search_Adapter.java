package com.snbtech.digitalhealthcare;

/**
 * Created by AK0554 on 12/22/2018.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Search_Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final String[] blood;
    private final String[] location;
    private final String[] mobile;

    public Search_Adapter(Activity context, String[] name,String[] blood,String[] location,String[] mobile) {
        super(context, R.layout.search, name);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name=name;
        this.blood=blood;
        this.location=location;
        this.mobile=mobile;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.search, null,true);

        TextView n = (TextView) rowView.findViewById(R.id.name);
        TextView b = (TextView) rowView.findViewById(R.id.blood);
        TextView l = (TextView) rowView.findViewById(R.id.location);
        TextView m = (TextView) rowView.findViewById(R.id.mobile);

        n.setText(name[position]);
        b.setText(blood[position]);
        l.setText(location[position]);
        m.setText(mobile[position]);

        return rowView;

    };
}
