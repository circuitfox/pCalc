package com.example.chris.pcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.chris.pcalc.ast.BigIntAst;
import com.example.chris.pcalc.ast.BigDecimalAst;
import com.example.chris.pcalc.input.Message;
import com.example.chris.pcalc.numeric.BigDecimal;
import com.example.chris.pcalc.numeric.BigInt;
import com.example.chris.pcalc.numeric.Mode;
import com.example.chris.pcalc.numeric.Numeric;
import com.example.chris.pcalc.parse.Tokenizer;
import com.example.chris.pcalc.parse.Tokens;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity
    implements ButtonGroupFragment.OnButtonPressListener {

    private ButtonGroupFragment buttonGroupFragment;
    private Mode mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGroupFragment = (ButtonGroupFragment)getSupportFragmentManager().findFragmentById(R.id.buttonGroup);
        mode = Mode.INT;

        // we want users clicking the buttons, not typing stuff in
        EditText input = findViewById(R.id.input);
        input.setKeyListener(null);
    }

    @Override
    public void onButtonPress(Message message) {
        Log.d("pCalc", message.toString());
        EditText input = findViewById(R.id.input);
        TextView result = findViewById(R.id.result);
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
            case EQUALS:
                text = input.getText().toString();
                evaluate(text);
                break;
            case MODE:
                mode = Mode.fromString(message.getValue());
                switch (mode) {
                    case INT:
                        buttonGroupFragment.switchMode(Mode.REAL);
                        mode = Mode.REAL;
                        break;
                    case REAL:
                        buttonGroupFragment.switchMode(Mode.INT);
                        mode = Mode.INT;
                        break;
                }
            default:
                Log.w("pCalc", "Unhandled message " + message);
                break;
        }
    }

    private void evaluate(String input) {
        TextView result = findViewById(R.id.result);
        Log.d("pCalc", input);
        Tokens tokens = new Tokenizer(input).tokenize();
        Log.d("pCalc/parse", tokens.toString());
        switch (mode) {
            case INT:
                BigIntAst intAst = new BigIntAst(tokens);
                Log.d("pCalc/ast", intAst.toString());
                BigInt intValue = intAst.evaluate();
                result.setText(intValue.toString());
                break;
            case REAL:
                BigDecimalAst decimalAst = new BigDecimalAst(tokens);
                Log.d("pCalc/ast", decimalAst.toString());
                BigDecimal realValue = decimalAst.evaluate();
                result.setText(realValue.toString());
                break;
        }
    }

    public void pressButton(View view) {
        buttonGroupFragment.pressButton(view);
    }
}
