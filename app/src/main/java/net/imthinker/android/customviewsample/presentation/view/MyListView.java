package net.imthinker.android.customviewsample.presentation.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyListView extends ListView {
    private static final String TAG = MyListView.class.getSimpleName();
    private List<String> mStrings;
    private MyListViewAdapter mMyListViewAdapter;

    public MyListView(Context context) {
        super(context);
        initialize(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        Log.d(TAG, "initialize");
        mStrings = new ArrayList<>();
        mMyListViewAdapter = new MyListViewAdapter(context, mStrings);
        setAdapter(mMyListViewAdapter);
    }

    public void updateAll(List<String> strings) {
        Log.d(TAG, "updateAll");
        mStrings = strings;
        mMyListViewAdapter.clear();
        mMyListViewAdapter.addAll(mStrings);
        mMyListViewAdapter.notifyDataSetChanged();
    }

    @NonNull
    public String getItem(int position) {
        String text = mMyListViewAdapter.getItem(position);
        if (TextUtils.isEmpty(text)) {
            text = "";
        }
        return text;
    }

    public static class MyListViewAdapter extends ArrayAdapter<String> {
        private LayoutInflater mInflater;

        public MyListViewAdapter(Context context, List<String> strings) {
            super(context, 0, strings);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            String text = getItem(position);
            MyListViewHolder holder;

            if (view == null) {
                view = mInflater.inflate(android.R.layout.simple_list_item_2, parent, false);
                holder = new MyListViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (MyListViewHolder) view.getTag();
            }

            holder.textView.setText(text);
            holder.textView2.setText(text);

            return view;
        }

        public static class MyListViewHolder {
            @Bind(android.R.id.text1)
            TextView textView;
            @Bind(android.R.id.text2)
            TextView textView2;

            public MyListViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
