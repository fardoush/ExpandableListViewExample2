package sample.android.com.expandablelistviewexample2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ExLvAdapter extends BaseExpandableListAdapter {

    private Context context;
    List<String> title;
    Map<String, List<String>> child;

    public ExLvAdapter(Context context, List<String> title, Map<String, List<String>> child) {
        this.context = context;
        this.title = title;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return title.size();  // how many title/group
    }

    @Override
    public int getChildrenCount(int i) {
        return child.get(title.get(i)).size();  // how many children per group
    }

    @Override
    public Object getGroup(int i) {
        return title.get(i);  // return the group
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(title.get(i)).get(i1);  // return the child
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent_layout, null);
        }

        TextView textView = view.findViewById(R.id.parent_title);
        ImageView imageView = view.findViewById(R.id.parent_indicator);
        textView.setText(title.get(i));

        // b is true when group is expanded,
        if (b) {  // if b is true
            imageView.setImageResource(R.drawable.arrow_right);
        } else {
            imageView.setImageResource(R.drawable.arrow_down);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_layout, null);
        }

        TextView textView = view.findViewById(R.id.child_title);
        textView.setText(child.get(title.get(i)).get(i1));


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
