package vladsaraykin.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private String display = "";
    private String currentOperator = "";
    @BindView(R.id.buttonEnter)
    TextView buttonEnter;


    @BindView(R.id.textResult)
    TextView textResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textResult.setText(display);


    }

    private void updateScreen() {
        textResult.setText(display);
    }

    public void onClickNumber(View view) {
        Button b = (Button) view;
        display += b.getText();
        updateScreen();
    }

    public void onClickOperator(View view) {
        Button b = (Button) view;
        display += ((Button) view).getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    protected void clear() {
        display = "";
        currentOperator = "";
    }

    public void onClickClear(View view) {
        clear();
        updateScreen();
    }

    private double operate(String a, String b) {
        double result = 0;
        switch (currentOperator) {
            case "+":
                result = Double.valueOf(a) + Double.valueOf(b);
                break;
            case "-":
                result = Double.valueOf(a) - Double.valueOf(b);
                break;
            case "*":
                result = Double.valueOf(a) * Double.valueOf(b);
                break;
            case "/":
                try {
                    result = Double.valueOf(a) / Double.valueOf(b);
                } catch (Exception e) {
                    System.out.println("Деление на 0");
                }
                break;
        }
        return result;
    }

    public void getResult(View view) {
        String[] split = display.split(Pattern.quote(currentOperator));
        double result = operate(split[0], split[1]);
        textResult.setText(display + "\n" + String.valueOf(result));
    }
}