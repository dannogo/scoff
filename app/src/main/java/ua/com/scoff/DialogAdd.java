package ua.com.scoff;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by oleh on 12/5/15.
 */
public class DialogAdd extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new product");
        builder.setMessage("Fill all fields, please");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inputFields = inflater.inflate(R.layout.fragment_add_input_fields, null);
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

        return dialog;
    }


}
