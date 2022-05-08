package sg.edu.rp.c346.id21020263.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //variables
    EditText amount;
    EditText pax;
    EditText discount;
    ToggleButton svc;
    ToggleButton gst;
    RadioGroup payment;
    Button split;
    Button reset;
    TextView bill;
    TextView pays;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking variables
        amount = findViewById(R.id.editTextAmount);
        pax = findViewById(R.id.editTextPax);
        discount = findViewById(R.id.editTextDiscount);
        svc = findViewById(R.id.tbsvc);
        gst = findViewById(R.id.tbgst);
        payment = findViewById(R.id.rgPayment);
        split = findViewById(R.id.bsplit);
        reset = findViewById(R.id.breset);
        bill = findViewById(R.id.tvTotal);
        pays = findViewById(R.id.tvSplit);


        double amt = Double.parseDouble(amount.getText().toString());
        int num = Integer.parseInt(pax.getText().toString());
        double disc = Double.parseDouble(discount.getText().toString()) / 100;
        final double[] total = {0};


        svc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //making total amount + 10%
                total[0] = total[0] * 1.1;

            }
        });

        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //making total amount + 7%
                total[0] = total[0] + (amt * 1.07);

            }
        });

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //making text for total bill and each pays
                String txtTotal = "Total Bill: $" + (total[0] - (total[0] * disc));
                String txtSplit = "Each pays: $" + total[0] / num ;
                //total bill
                bill.setText(txtTotal);

                //
                int checkedRadioId = payment.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.rbcash){
                    txtSplit = txtSplit + " in cash";
                }
                else {
                    txtSplit = txtSplit + " via PayNow to 912345678";
                }


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.getText().clear();
                pax.getText().clear();
                discount.getText().clear();
                svc.setChecked(false);
                gst.setChecked(false);
            }
        });

        String sAmt = amount.getText().toString();
        String sNum = pax.getText().toString();
        String sDisc = discount.getText().toString();

        if (sAmt.isEmpty() || sNum.isEmpty() || sDisc.isEmpty()) {
            Toast toast = Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT);
            View view = toast.getView();
            TextView text = view.findViewById(android.R.id.message);
            text.setTextColor((Color.parseColor("FF0000")));
        }




    }
}