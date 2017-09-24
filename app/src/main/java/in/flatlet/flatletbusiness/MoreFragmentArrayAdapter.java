package in.flatlet.flatletbusiness;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


class MoreFragmentArrayAdapter extends ArrayAdapter<String> {
    private final String[] moreFragmentItems;
    private final Context context;
    private final int[] moreFragmentVectors;

    MoreFragmentArrayAdapter(Context context, String[] moreFragmentItems, int[] moreFragmentVectors) {
        super(context, android.R.layout.simple_list_item_1, moreFragmentItems);
        this.context = context;
        this.moreFragmentItems = moreFragmentItems;
        this.moreFragmentVectors = moreFragmentVectors;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitems, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.list_text);
        textView.setText(moreFragmentItems[position]);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_vector);
        imageView.setImageResource(moreFragmentVectors[position]);
        return convertView;
    }
}
