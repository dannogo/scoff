package ua.com.scoff;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by oleh on 12/5/15.
 */
public class DialogAddProduct extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new product");
//        builder.setMessage("Fill all fields, please");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inputFields = inflater.inflate(R.layout.fragment_add_input_fields, null);
        final EditText denomination = (EditText) inputFields.findViewById(R.id.denomination_field);
        final TextInputLayout denominationValidation = (TextInputLayout) inputFields.findViewById(R.id.denomination_validation);
        denomination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (denomination.getText().toString().matches("^[a-zA-Zа-яА-Я0-9 ]*$") && !(denomination.getText().toString().matches("^$|\\s"))){
                        denominationValidation.setErrorEnabled(false);
                        ViewCompat.setBackgroundTintList(denomination, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
                        denominationValidation.setHint("Denomination");
                    }else{
                        denominationValidation.setError("Invalide Denomination");
                        denominationValidation.setHint("Try again");
                        return true;
                    }
                }
                return false;
            }
        });
        final EditText proteins = (EditText) inputFields.findViewById(R.id.proteins_field);
        final TextInputLayout proteinsValidation = (TextInputLayout) inputFields.findViewById(R.id.proteins_validation);
        proteins.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (!proteins.getText().toString().matches("^$|\\s")){
                        proteinsValidation.setErrorEnabled(false);
                        ViewCompat.setBackgroundTintList(proteins, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
                        proteinsValidation.setHint("Proteins");
                    }else{
                        proteinsValidation.setError("Invalide value");
                        proteinsValidation.setHint("Try again");
                        return true;
                    }
                }
                return false;
            }
        });
        final EditText fats = (EditText) inputFields.findViewById(R.id.fats_field);
        final TextInputLayout fatsValidation = (TextInputLayout) inputFields.findViewById(R.id.fats_validation);
        fats.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (!fats.getText().toString().matches("^$|\\s")){
                        fatsValidation.setErrorEnabled(false);
                        ViewCompat.setBackgroundTintList(fats, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
                        fatsValidation.setHint("Fats");
                    }else{
                        fatsValidation.setError("Invalide value");
                        fatsValidation.setHint("Try again");
                        return true;
                    }
                }
                return false;
            }
        });
        final EditText carbohydrates = (EditText) inputFields.findViewById(R.id.carbohydrates_field);
        final TextInputLayout carbohydratesValidation = (TextInputLayout) inputFields.findViewById(R.id.carbohydrates_validation);
        carbohydrates.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (!carbohydrates.getText().toString().matches("^$|\\s")){
                        carbohydratesValidation.setErrorEnabled(false);
                        ViewCompat.setBackgroundTintList(carbohydrates, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
                        carbohydratesValidation.setHint("Carbohydrates");
                    }else{
                        carbohydratesValidation.setError("Invalide value");
                        carbohydratesValidation.setHint("Try again");
                        return true;
                    }
                }
                return false;
            }
        });
        final EditText caloricCapacity = (EditText) inputFields.findViewById(R.id.caloric_capacity_field);
        final TextInputLayout caloricCapacityValidation = (TextInputLayout) inputFields.findViewById(R.id.caloric_capacity_validation);
        caloricCapacity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    if (!caloricCapacity.getText().toString().matches("^$|\\s")){
                        caloricCapacityValidation.setErrorEnabled(false);
                        ViewCompat.setBackgroundTintList(caloricCapacity, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
                        caloricCapacityValidation.setHint("Caloric capacity");
                    }else{
                        caloricCapacityValidation.setError("Invalide value");
                        caloricCapacityValidation.setHint("Try again");
                        return true;
                    }
                }
                return false;
            }
        });
        builder.setView(inputFields);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String denominationValue = denomination.getText().toString();
                int proteinsValue = Integer.parseInt(proteins.getText().toString());
                int fatsValue = Integer.parseInt(fats.getText().toString());
                int carbohydratesValue = Integer.parseInt(carbohydrates.getText().toString());
                int caloricCapacityValue = Integer.parseInt(caloricCapacity.getText().toString());
                ((AddScoffActivity) getActivity()).databaseAdapter.insertProduct();
            }
        });
        Dialog dialog = builder.create();

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return dialog;
    }

}
