package com.vivek.imageloadersample.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("vvk", "oncreate " + this.getTag() + this.getId());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("vvk", "oncreateview " + this.getTag() + this.getId());
        TextView textView = new TextView(getActivity());
        textView.setText("hello");
        return textView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("vvk", "onViewCreated " + this.getTag() + this.getId());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("vvk", "onActivityCreated " + this.getTag() + this.getId());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d("vvk", "onresume " + this.getTag() + this.getId());
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("vvk", "onpause " + this.getTag() + this.getId());
        super.onPause();
    }

    @Override
    public void onDetach() {
        Log.d("vvk", "onDetach " + this.getTag() + this.getId());
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("vvk", "onAttach " + this.getTag() + this.getId());
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        Log.d("vvk", "onstop " + this.getTag() + this.getId());
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("vvk", "ondestroy " + this.getTag() + this.getId());
        super.onDestroy();
    }

    @Override
    public void onStart() {
        Log.d("vvk", "onStart " + this.getTag() + this.getId());
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        Log.d("vvk", "ondestroyView " + this.getTag() + this.getId());
        super.onDestroyView();
    }

}

