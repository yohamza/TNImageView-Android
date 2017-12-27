package us.technerd.tnimageview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza on 12/27/2017.
 */

public class TNImageView {

    float scalediff;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private List<ImageView> imageList = new ArrayList<>();


    public ImageView makeRotatableScalable(ImageView imageView){

        imageView.setOnTouchListener(new View.OnTouchListener() {

            RelativeLayout.LayoutParams parms;
            int startwidth;
            int startheight;
            float dx = 0, dy = 0, x = 0, y = 0;
            float angle = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final ImageView view = (ImageView) v;

                if(!imageList.isEmpty()) {
                    for (ImageView i : imageList) {

                        if (i == view) {
                            view.bringToFront();
                            view.getRootView().invalidate();
                        }
                    }
                }

                ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        startwidth = parms.width;
                        startheight = parms.height;
                        dx = event.getRawX() - parms.leftMargin;
                        dy = event.getRawY() - parms.topMargin;
                        mode = DRAG;
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 10f) {
                            mode = ZOOM;
                        }

                        d = rotation(event);

                        break;
                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {

                            x = event.getRawX();
                            y = event.getRawY();

                            parms.leftMargin = (int) (x - dx);
                            parms.topMargin = (int) (y - dy);

                            parms.rightMargin = 0;
                            parms.bottomMargin = 0;
                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                            view.setLayoutParams(parms);

                        } else if (mode == ZOOM) {

                            if (event.getPointerCount() == 2) {

                                newRot = rotation(event);
                                float r = newRot - d;
                                angle = r;

                                x = event.getRawX();
                                y = event.getRawY();

                                float newDist = spacing(event);
                                if (newDist > 10f) {
                                    float scale = newDist / oldDist * view.getScaleX();
                                    if (scale > 0.6) {
                                        scalediff = scale;
                                        view.setScaleX(scale);
                                        view.setScaleY(scale);

                                    }
                                }

                                view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                x = event.getRawX();
                                y = event.getRawY();

                                parms.leftMargin = (int) ((x - dx) + scalediff);
                                parms.topMargin = (int) ((y - dy) + scalediff);

                                parms.rightMargin = 0;
                                parms.bottomMargin = 0;
                                parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                view.setLayoutParams(parms);


                            }
                        }
                        break;
                }

                return true;

            }
        });

        return imageView;
    }




    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    public void addTNImageViewsWithUri(Context context, List<Uri> uriList, RelativeLayout relativeLayout) {

        for (int i = 0; i < uriList.size(); i++) {

            final Uri imageUri = uriList.get(i);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView image = new ImageView(context);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
            layoutParams.leftMargin = 150 + 200 * i;
            layoutParams.topMargin = 200;
            layoutParams.bottomMargin = 250 - 500;
            layoutParams.rightMargin = 250 - 500;
            image.setLayoutParams(layoutParams);
            imageList.add(image);
            makeRotatableScalable(image);
            relativeLayout.addView(image, layoutParams);
        }
    }

    public void addTNImageViewsWithBitmap(Context context, List<Bitmap> bitmapList, RelativeLayout relativeLayout){

        for(int i=0;i<bitmapList.size();i++){

            ImageView image = new ImageView(context);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(bitmapList.get(i).getWidth(), bitmapList.get(i).getHeight());
            layoutParams.leftMargin = 150 + 200 * i;
            layoutParams.topMargin = 200;
            layoutParams.bottomMargin = 250 - 500;
            layoutParams.rightMargin = 250 - 500;
            image.setLayoutParams(layoutParams);
            imageList.add(image);
            makeRotatableScalable(image);
            relativeLayout.addView(image, layoutParams);
        }

    }


}
