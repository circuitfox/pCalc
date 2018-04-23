package com.example.chris.pcalc;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.chris.pcalc.input.Message;
import com.example.chris.pcalc.input.MessageType;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ButtonGroupFragment.OnButtonPressListener} interface
 * to handle interaction events.
 * Use the {@link ButtonGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonGroupFragment extends Fragment {
    private OnButtonPressListener mListener;

    public ButtonGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ButtonGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ButtonGroupFragment newInstance() {
        return new ButtonGroupFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_button_group, container, false);
    }

    public void pressButton(View view) {
        if (mListener != null) {
            MessageType type;
            String messageText = "";
            switch(view.getId()) {
                case R.id.button_clear:
                    type = MessageType.CLEAR;
                    break;
                case R.id.button_delete:
                    type = MessageType.DELETE;
                    break;
                default:
                    MultiButtonView button = view.findViewById(view.getId());
                    type = MessageType.SYMBOL;
                    messageText = button.getText().toString();
                    break;
            }
            Message message = new Message(type, messageText);
            mListener.onButtonPress(message);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonPressListener) {
            mListener = (OnButtonPressListener)context;
        } else {
            throw new RuntimeException(context.toString()
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
    public interface OnButtonPressListener {
        void onButtonPress(Message message);
    }
}
