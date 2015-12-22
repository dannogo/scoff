package ua.com.scoff;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by oleh on 12/15/15.
 */
public class DialogAddProductToSpan extends DialogFragment {

    EditText quantity;
    TextInputLayout quantityValidation;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String denomination = getArguments().getString("denomination");
        builder.setTitle(new StringBuilder("Add \"").append(denomination).append("\" to span"));

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inflatedView = inflater.inflate(R.layout.dialog_add_product_to_span_edittext, null);
        quantity = (EditText) inflatedView.findViewById(R.id.scoff_quantity);
        quantityValidation = (TextInputLayout) inflatedView.findViewById(R.id.scoff_quantity_validation);
        builder.setView(inflatedView);
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

                    boolean isQuantityValid = false;

                    if (!quantity.getText().toString().matches("^$|\\s")) {
                        quantityValidation.setErrorEnabled(false);
                        ViewCompat.setBackgroundTintList(quantity, new ColorStateList(new int[][]{new int[]{0xFFde3309}}, new int[]{Color.parseColor("#00BFA5")}));
                        quantityValidation.setHint("Quantity");
                        isQuantityValid = true;
                    } else {
                        quantityValidation.setError("Empty field");
                        quantityValidation.setHint("This value is required");
                    }

                    if (isQuantityValid) {
                        int quantityValue = Integer.parseInt(quantity.getText().toString());
                        int spanId = getArguments().getInt("spanId");
                        int productId = getArguments().getInt("productId");
                        int id = ((AddScoffActivity)getActivity()).databaseAdapter.insertRecordToSpan(spanId, productId, quantityValue);
                        Log.w("DialogAddProductToSpan", String.valueOf(id));
                        if (id>0) {
                            ((AddScoffActivity) getActivity()).databaseAdapter.increaseProductFrequency(productId);

                            ((AddScoffActivity) getActivity()).fragmentAddScoff.scoffAdapter = new ScoffAdapter(getActivity(), Integer.parseInt(((AddScoffActivity) getActivity()).spanId));
                            ((AddScoffActivity) getActivity()).fragmentAddScoff.scoffList.setAdapter(((AddScoffActivity) getActivity()).fragmentAddScoff.scoffAdapter);
                            ((AddScoffActivity) getActivity()).fragmentAddScoff.displayTotals();
                        }
                        dialog.dismiss();
                    }


                }
            });
        }

    }
}
