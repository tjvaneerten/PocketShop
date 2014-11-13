package com.osu.cse5236.pocketshop;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.osu.cse5236.framework.EditablePhoto;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PictureFrame.OnEditablePictureInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PictureFrame#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PictureFrame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private EditablePhoto editablePhoto;

    private OnEditablePictureInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param editablePhoto Parameter 1.
     * @return A new instance of fragment PictureFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static PictureFrame newInstance(EditablePhoto editablePhoto) {
        PictureFrame fragment = new PictureFrame();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, editablePhoto);
        fragment.setArguments(args);
        return fragment;
    }
    public PictureFrame() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            editablePhoto = (EditablePhoto)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_picture_frame, container, false);
        ImageView frame = (ImageView)v.findViewById(R.id.openedPicture);
        if (editablePhoto.getCurrentImage() != null) {
            frame.setImageBitmap(editablePhoto.getCurrentImage());
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onEditablePictureInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnEditablePictureInteractionListener) activity;
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
    public interface OnEditablePictureInteractionListener {
        // TODO: Update argument type and name
        public void onEditablePictureInteraction(Uri uri);
    }

}
