package com.example.chris.pcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.chris.pcalc.ast.Ast;
import com.example.chris.pcalc.input.Message;
import com.example.chris.pcalc.parse.Tokenizer;
import com.example.chris.pcalc.parse.Tokens;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
    implements ButtonGroupFragment.OnButtonPressListener {

    private ButtonGroupFragment buttonGroupFragment;
    private EditText input;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGroupFragment = (ButtonGroupFragment)getSupportFragmentManager().findFragmentById(R.id.buttonGroup);
        input = findViewById(R.id.input);
        result = findViewById(R.id.result);

        // we want users clicking the buttons, not typing stuff in
        input.setKeyListener(null);
    }

    @Override
    public void onButtonPress(Message message) {
        Log.d("pCalc", message.toString());
        switch (message.getType()) {
            case CLEAR:
                input.setText("");
                result.setText("");
                break;
            case SYMBOL:
                input.append(message.getValue());
                break;
            case DELETE:
                String text = input.getText().toString();
                if (text.length() > 0) {
                    input.setText(text.substring(0, text.length() - 1));
                    input.setSelection(input.getText().length());
                }
                break;
            default:
                Log.w("pCalc", "Unhandled message " + message);
                break;
        }
    }

    public void pressEquals(View view) {
        String resultStr = input.getText().toString();
        Log.d("pCalc", resultStr);
        Tokens tokens = new Tokenizer(resultStr).tokenize();
        Log.d("pCalc/parse", tokens.toString());
        Ast ast = new Ast(tokens);
        Log.d("pCalc/ast", ast.toString());
        int value = ast.evaluate();
        result.setText(String.format(Locale.getDefault(), "%d", value));
    }

    public void pressButton(View view) {
        buttonGroupFragment.pressButton(view);
    }
}
