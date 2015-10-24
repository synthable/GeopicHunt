package com.synthable.geopichunt.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.synthable.geopichunt.R;

public class StreamFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;

    private GridView mGridView;

    public StreamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stream, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView = (GridView) view.findViewById(R.id.gridview);
        mGridView.setAdapter(new ImageAdapter(getActivity()));
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }*/


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame,
                android.R.drawable.picture_frame
        };
    }
}
