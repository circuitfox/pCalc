package com.example.chris.pcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.chris.pcalc.input.Message;
import com.example.chris.pcalc.input.MessageType;

public class MainActivity extends AppCompatActivity
    implements ButtonGroupFragment.OnButtonPressListener {

    private ButtonGroupFragment buttonGroupFragment;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGroupFragment = (ButtonGroupFragment)getSupportFragmentManager().findFragmentById(R.id.buttonGroup);
        input = findViewById(R.id.input);

        // we want users clicking the buttons, not typing stuff in
        input.setKeyListener(null);
    }

    public void pressButton(View view) {
        buttonGroupFragment.onButtonPressed(view);
    }

    @Override
    public void onButtonPress(Message message) {
        Log.d("pCalc", message.toString());
        switch (message.getType()) {
            case CLEAR:
                input.setText("");
                break;
            case SYMBOL:
                input.append(message.getValue());
                break;
            default:
                Log.w("pCalc", "Unhandled message " + message);
                break;
        }
    }
}
