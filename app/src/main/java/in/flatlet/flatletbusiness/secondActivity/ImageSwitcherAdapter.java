package in.flatlet.flatletbusiness.secondActivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.flatlet.flatletbusiness.R;


class ImageSwitcherAdapter extends PagerAdapter {
    private Context context;
    private String first_hostel;
    private int arraySize;
    private ArrayList<String> GalleryURL = new ArrayList<>();


    ImageSwitcherAdapter(Context context, int arraySize, String first_hostel) {
        this.context = context;
        this.first_hostel = first_hostel;
        this.arraySize = arraySize;
        arrayFormation();

    }

    private ArrayList arrayFormation() {
        for (int i = 1; i <= arraySize; i++) {

            GalleryURL.add(i - 1, "http://images.flatlet.in/images/" + first_hostel + "/" + i + ".webp");

        }
        return GalleryURL;

    }

    @Override
    public int getCount() {
        return arraySize;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        if (GalleryURL.size() != 0) {
            Picasso.with(context).load(GalleryURL.get(position)).error(R.drawable.ic_blank_image).into(imageView);
        } else {
            Picasso.with(context).load("http://www.kalahandi.info/wp-content/uploads/2016/05/sorry-image-not-available.png").error(R.drawable.ic_blank_image).into(imageView);
        }

        container.addView(imageView, 0);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
