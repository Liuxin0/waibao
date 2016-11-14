package com.example.rental.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rental.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by caolu on 2016/10/4.
 */
public class DiscoveryMain extends Fragment{

    private PullToRefreshListView mListView;
    private ImageView upImage;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discoverymain_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        upImage = (ImageView) getActivity().findViewById(R.id.dis_upimg);
        upImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpDisActivity.class));
            }
        });
    }
}
