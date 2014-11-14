package com.osu.cse5236.pocketshop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.osu.cse5236.framework.EditablePhoto;
import com.osu.cse5236.framework.RandomCollage;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.osu.cse5236.pocketshop.CanvasFrame.OnCollageInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CanvasFrame#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CanvasFrame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private EditablePhoto editablePhoto;
    private RandomCollage randomCollage;

    private OnCollageInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CanvasFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static CanvasFrame newInstance(EditablePhoto param1, RandomCollage param2) {
        CanvasFrame fragment = new CanvasFrame();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public CanvasFrame() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            editablePhoto = (EditablePhoto)getArguments().getSerializable(ARG_PARAM1);
            randomCollage = (RandomCollage)getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canvas_frame, container, false);
        final ImageView collageFrame = (ImageView)view.findViewById(R.id.collage_placeholder);
        collageFrame.setImageBitmap(randomCollage.getCollage());
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                randomCollage.addImage(editablePhoto, x, y);
                collageFrame.setImageBitmap(randomCollage.getCollage());
                return true;
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCollageInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCollageInteractionListener {
        // TODO: Update argument type and name
        public void onCollageInteraction(float x, float y);
    }

}
