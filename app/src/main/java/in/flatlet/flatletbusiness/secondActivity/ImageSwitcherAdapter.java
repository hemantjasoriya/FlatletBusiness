package in.flatlet.flatletbusiness.secondActivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import in.flatlet.flatletbusiness.R;

/**
 * Created by Dragon on 03-10-2017.
 */

class ImageSwitcherAdapter extends PagerAdapter {
    private Context context;

    ImageSwitcherAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Picasso.with(context).load("http://images.flatlet.in/tutorial/1.webp").into(imageView);
        container.addView(imageView,0);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
