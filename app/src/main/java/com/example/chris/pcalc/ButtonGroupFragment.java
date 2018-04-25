package com.example.chris.pcalc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chris.pcalc.input.Message;
import com.example.chris.pcalc.input.MessageType;
import com.example.chris.pcalc.numeric.Mode;


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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
                case R.id.button_equals:
                    type = MessageType.EQUALS;
                    break;
                case R.id.button_mode:
                    MultiButtonView mode = (MultiButtonView)view;
                    type = MessageType.MODE;
                    messageText = mode.getText().toString();
                    break;
                default:
                    MultiButtonView button = (MultiButtonView)view;
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

    public void switchMode(Mode mode) {
        Activity activity = getActivity();
        if (activity != null) {
            MultiButtonView mode_btn = activity.findViewById(R.id.button_mode);
            MultiButtonView mod = activity.findViewById(R.id.button_mod);
            MultiButtonView div = activity.findViewById(R.id.button_div);
            MultiButtonView mul = activity.findViewById(R.id.button_times);
            MultiButtonView add = activity.findViewById(R.id.button_plus);
            MultiButtonView sub = activity.findViewById(R.id.button_minus);
            switch (mode) {
                case INT:
                    mode_btn.setCenterButtonText(R.string.button_mode_int);
                    mod.setLeftButtonText(R.string.button_shl);
                    mod.setRightButtonText(R.string.button_shr);
                    mul.setBottomButtonText(R.string.button_xor);
                    div.setBottomButtonText(R.string.button_or);
                    add.setBottomButtonText(R.string.button_and);
                    sub.setBottomButtonText(R.string.button_not);
                    break;
                case REAL:
                    mode_btn.setCenterButtonText(R.string.button_mode_real);
                    mod.setLeftButtonText("");
                    mod.setRightButtonText("");
                    mul.setBottomButtonText("");
                    div.setBottomButtonText("");
                    add.setBottomButtonText("");
                    sub.setBottomButtonText("");
                    break;
                default:
                    Log.w("pCalc", "Unimplemented mode " + mode);
            }
        } else {
            Log.wtf("pCalc", "Can't get activity");
        }
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
