package ua.com.scoff;

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
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by oleh on 12/5/15.
 */
public class DialogAddProduct extends DialogFragment {

    EditText denomination, proteins, fats, carbohydrates, caloricCapacity;
    TextInputLayout denominationValidation, proteinsValidation, fatsValidation,
            carbohydratesValidation, caloricCapacityValidation;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new product");
//        builder.setMessage("Fill all fields, please");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inputFields = inflater.inflate(R.layout.fragment_add_input_fields, null);
        denomination = (EditText) inputFields.findViewById(R.id.denomination_field);
        denominationValidation = (TextInputLayout) inputFields.findViewById(R.id.denomination_validation);
        denomination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (!validateDenomination()){
                        return true;
                    }
                }
                return false;
            }
        });
        proteins = (EditText) inputFields.findViewById(R.id.proteins_field);
        proteinsValidation = (TextInputLayout) inputFields.findViewById(R.id.proteins_validation);
        proteins.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (!validateNumericField(proteins, proteinsValidation, "Proteins")){
                        return true;
                    }
                }
                return false;
            }
        });
        fats = (EditText) inputFields.findViewById(R.id.fats_field);
        fatsValidation = (TextInputLayout) inputFields.findViewById(R.id.fats_validation);
        fats.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (!validateNumericField(fats, fatsValidation, "Fats")){
                        return true;
                    }
                }
                return false;
            }
        });
        carbohydrates = (EditText) inputFields.findViewById(R.id.carbohydrates_field);
        carbohydratesValidation = (TextInputLayout) inputFields.findViewById(R.id.carbohydrates_validation);
        carbohydrates.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    if (!validateNumericField(carbohydrates, carbohydratesValidation, "Carbs")){
                        return true;
                    }
                }
                return false;
            }
        });
        caloricCapacity = (EditText) inputFields.findViewById(R.id.caloric_capacity_field);
        caloricCapacityValidation = (TextInputLayout) inputFields.findViewById(R.id.caloric_capacity_validation);
        caloricCapacity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!validateNumericField(caloricCapacity, caloricCapacityValidation, "Caloric capacity")) {
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

            }
        });
        Dialog dialog = builder.create();

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


//        Log.w("LOG", ""+((AddScoffActivity) getActivity()).fragmentAddProducts.);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null){
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isDenominationValid = validateDenomination();
                    boolean isProteinsValid = validateNumericField(proteins, proteinsValidation, "Proteins");
                    boolean isFatsValid = validateNumericField(fats, fatsValidation, "Fats");
                    boolean isCarbohydratesValid = validateNumericField(carbohydrates, carbohydratesValidation, "Carbs");
                    boolean isCaloricCapacityValid = validateNumericField(caloricCapacity, caloricCapacityValidation, "Caloric capacity");

                    if (isDenominationValid && isProteinsValid && isFatsValid && isCarbohydratesValid && isCaloricCapacityValid){

                        String denominationValue = denomination.getText().toString();
                        int proteinsValue = Integer.parseInt(proteins.getText().toString());
                        int fatsValue = Integer.parseInt(fats.getText().toString());
                        int carbohydratesValue = Integer.parseInt(carbohydrates.getText().toString());
                        int caloricCapacityValue = Integer.parseInt(caloricCapacity.getText().toString());

//                        Log.w("LOG", denominationValue+"\n"+proteinsValue+"\n"+fatsValue+"\n"+carbohydratesValue+"\n"+caloricCapacityValue);


                        int id = ((AddScoffActivity) getActivity()).databaseAdapter.insertProduct(
                                denominationValue, proteinsValue, fatsValue, carbohydratesValue, caloricCapacityValue);

                        if (id>0){

                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.productIDsList.add(String.valueOf(id));
                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.denominationsList.add(denominationValue);
                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.proteinsList.add(String.valueOf(proteinsValue));
                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.fatsList.add(String.valueOf(fatsValue));
                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.carbohydratesList.add(String.valueOf(carbohydratesValue));
                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.caloriesList.add(String.valueOf(caloricCapacityValue));
                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.frequenciesList.add(0);

                            ((AddScoffActivity) getActivity()).fragmentAddProducts.productsAdapter.notifyItemRangeInserted(
                                    ((AddScoffActivity) getActivity()).fragmentAddProducts.productsAdapter.getItemCount() - 1, ((AddScoffActivity) getActivity()).fragmentAddProducts.productsAdapter.getItemCount());
                            ((AddScoffActivity)getActivity()).fragmentAddProducts.productsAdapter.notifyDataSetChanged();

                        }

                        dialog.dismiss();
                    }

                }
            });
        }
    }

    private boolean validateNumericField(EditText editText, TextInputLayout validation, String hint){
        if (!editText.getText().toString().matches("^$|\\s")){
            validation.setErrorEnabled(false);
            ViewCompat.setBackgroundTintList(editText, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
            validation.setHint(hint);
            return true;
        }else{
            validation.setError("Empty field");
            validation.setHint("Fill it");
            return false;
        }
    }

    private boolean validateDenomination(){
        if (denomination.getText().toString().matches("^[a-zA-Zа-яА-Я0-9 ]*$") && !(denomination.getText().toString().matches("^$|\\s"))){
            denominationValidation.setErrorEnabled(false);
            ViewCompat.setBackgroundTintList(denomination, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
            denominationValidation.setHint("Denomination");
            return true;
        }else{
            denominationValidation.setError("Invalide Denomination");
            denominationValidation.setHint("Try again");
            return false;
        }
    }

}
