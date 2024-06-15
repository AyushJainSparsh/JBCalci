package com.example.jbcalci;
// Main Module of whole Project
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    /*
    1 - Dal Fry
    2 - Sabzi
    3 - Roti without Butter
    4- Roti with Butter
    5 - Water Bottle
    6 - Thali Packed
    7 - Thali
    8 - Rice Quarter
    9- Rice Fry
    10- Rice with Dal
    11- Sada Paratha
    12 - Other
     */

    EditText[] editText; // Array of EditList widget to take values for execution


    // Items array for Preparing Bill
    String[] item = {"Dal Fry","Sabzi","Roti(Without Butter)","Roti(With Butter)","Water Bottle",
    "Thali(Packed)","Thali","Rice","Rice Fry","Rice With Dal","Sada Paratha"};


    // Amount array for Preparing Bill
    float[] amount = {60.0f , 50.0f , 6.0f , 7.0f , 20.0f , 100.0f , 80.0f , 10.0f , 50.0f , 50.0f , 20.0f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Intialising our array by ID
        editText = new EditText[]{findViewById(R.id.editText1),
                findViewById(R.id.editText2),
                findViewById(R.id.editText3),
                findViewById(R.id.editText4),
                findViewById(R.id.editText5),
                findViewById(R.id.editText6),
                findViewById(R.id.editText7),
                findViewById(R.id.editText8),
                findViewById(R.id.editText9),
                findViewById(R.id.editText10),
                findViewById(R.id.editText11),
                findViewById(R.id.editText12)
        };
    }

    // When Plus button is clicked this will be called and increase the quantity by 1
    public void plus(View view)
    {
        // Intialising the button whom which we tapped
        ImageButton imageButton = (ImageButton) view;
        // Taking tag of button so that we call use that editlist value
        int tag = Integer.parseInt(imageButton.getTag().toString());
        tag--;
        // Taking Value from that widget and then we increase it and again update it
        int value = Integer.parseInt(editText[tag].getText().toString());
        value++;
        editText[tag].setText(""+value);
    }


    // When Minus button is clicked this will be called and decrease the quantity by 1
    public void minus(View view)
    {
        // Intialising the button whom which we tapped
        ImageButton imageButton = (ImageButton) view;
        // Taking tag of button so that we can use its adjacent editlist value
        int tag = Integer.parseInt(imageButton.getTag().toString());
        tag--;
        // Taking Value from the widget and then we decrease it by one and update it unless its
        // not less than or equal to 0
        int value = Integer.parseInt(editText[tag].getText().toString());
        if(value<=0)
            Toast.makeText(this, "Invalid Option", Toast.LENGTH_SHORT).show();
        else
        {
            value--;
            editText[tag].setText(""+value);
        }
    }

    // In this function we prepare bill and send it to other activity to represent it
    // This will be called when bill named button will be called
    public void bill(View view)
    {
        String bill = encodeBill();
        Intent intent = new Intent(this , loadingSplashScreen.class);
        intent.putExtra(BILL , bill);
        startActivity(intent);
    }

    // Universal Variable be used so that other activities use our bill
    public static final String BILL = "com.example.jbcalci.BILL";

    // Calculate bill and return to bill function
    // Called when we click bill button
    String encodeBill()
    {
        String bill = ""; // String store bill
        float total_amount = 0.0f; // Store total amount
        for(int i = 0 ; i < 11 ; i++)
        {
            // Take quantity from different different editlist to calculate bill
            float quantity = Float.parseFloat(editText[i].getText().toString());
            if(quantity>0)
            {
                // calculate bill and store it in bill format from starting so that there is no other
                // Work left
                float t_amount = quantity * amount[i];
                bill = bill + item[i] + "\n";
                bill = bill + "quantity : " + quantity +"\t" + "Price : " + t_amount + " Rs" + "\n";
                total_amount += t_amount;
            }
        }
        // calculate bill
        bill = bill + "Other : " + editText[11].getText().toString() + " Rs" + "\n";
        total_amount += Float.parseFloat(editText[11].getText().toString());
        bill = bill + "\nTotal Amount Payable : " + total_amount + " Rs";
        // returning bill to bill function for further sharing
        return bill;
    }
}