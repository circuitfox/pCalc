package com.example.chris.pcalc;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    public void onButtonPressed(View view) {
        if (mListener != null) {
            MessageType type;
            String messageText = "";
            switch(view.getId()) {
                case R.id.button0:
                case R.id.button1:
                case R.id.button2:
                case R.id.button3:
                case R.id.button4:
                case R.id.button5:
                case R.id.button6:
                case R.id.button7:
                case R.id.button8:
                case R.id.button9:
                case R.id.button_plus:
                case R.id.button_minus:
                case R.id.button_times:
                case R.id.button_div:
                case R.id.button_pleft:
                case R.id.button_pright:
                    Button button = view.findViewById(view.getId());
                    type = MessageType.SYMBOL;
                    messageText = button.getText().toString();
                    break;
                case R.id.button_clear:
                    type = MessageType.CLEAR;
                    break;
                default:
                    type = MessageType.SYMBOL;
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
        // TODO: Update argument type and name
        void onButtonPress(Message message);
    }
}
