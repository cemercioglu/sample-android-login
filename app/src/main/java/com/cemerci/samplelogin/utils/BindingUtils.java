package com.cemerci.samplelogin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Base64;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.BindingAdapter;

import com.cemerci.samplelogin.R;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-31.
 */
public class BindingUtils {
    private BindingUtils() {
    }

    @BindingAdapter("imageBase64")
    public static void setImage(ImageView imageView, String imageBase64) {
        Context context = imageView.getContext();

        Bitmap bitmap = null;
        if (imageBase64 != null && !imageBase64.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(imageBase64, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            bitmap = getBitmapFromVectorDrawable(context, R.drawable.ic_account_circle_black_24dp);

        }
        imageView.setImageBitmap(bitmap);
    }

    private static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
