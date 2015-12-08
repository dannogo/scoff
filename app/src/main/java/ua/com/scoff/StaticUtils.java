package ua.com.scoff;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by oleh on 12/3/15.
 */
public class StaticUtils  {

    protected static void showAddProductDialog(Context context){
        DialogAddProduct dialogAddProduct = new DialogAddProduct();
        Bundle data = new Bundle();
//        data.putString();
        dialogAddProduct.setArguments(data);
        dialogAddProduct.show(((AppCompatActivity) context).getFragmentManager(), "FragmentAddProduct");

    }

    protected  static void  showPopupContextWindow(Context context, View view){
        int[] location = new int[2];

        // Get the x, y location and store it in the location[] array
        location[0] = (int) view.getX();
        location[1] = (int) view.getY();
        view.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        Point point = new Point();
        point.x = location[0];
        point.y = location[1];

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_context_menu, null);

        final PopupWindow popupWindow = new PopupWindow(context);

        View addToSpanItem = layout.findViewById(R.id.addToSpanItem);
        View deleteProductItem = layout.findViewById(R.id.deleteProductItem);

        addToSpanItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });

        deleteProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setContentView(layout);
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

//        context.getResources()
        int OFFSET_X = + 170 ;
        int OFFSET_Y = 50;

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.showAtLocation(layout, Gravity.NO_GRAVITY, point.x + OFFSET_X, point.y + OFFSET_Y);
    }


    static class BottomOffsetDecoration extends RecyclerView.ItemDecoration {
        private int mBottomOffset;

        public BottomOffsetDecoration(int bottomOffset) {
            mBottomOffset = bottomOffset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int dataSize = state.getItemCount();
            int position = parent.getChildAdapterPosition(view);
            if (dataSize > 0 && position == dataSize - 1) {
                outRect.set(0, 0, 0, mBottomOffset);
            } else {
                outRect.set(0, 0, 0, 0);
            }

        }
    }
}
