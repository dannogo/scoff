package ua.com.scoff;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by oleh on 12/10/15.
 */
public class DialogAddSpan extends DialogFragment {

    EditText editText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new span");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inflatedView = inflater.inflate(R.layout.dialog_add_span_edittext, null);
        editText = (EditText) inflatedView.findViewById(R.id.span_title_field);
        builder.setView(inflatedView);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = editText.getText().toString();
                if (title.isEmpty()) {
                    title = null;
                }
                int id = ((MainActivity)getActivity()).databaseAdapter.insertSpan(title);
                Log.w("Id of inserted span", String.valueOf(id));
                if (id>0) {
                    ((MainActivity) getActivity()).spansAdapter.spanIdsList.add(0, String.valueOf(id));
                    if (title == null) {
                        ((MainActivity) getActivity()).spansAdapter.namesList.add(0, StaticUtils.getCurrentDateTime(true));
                    } else {
                        ((MainActivity) getActivity()).spansAdapter.namesList.add(0, title);
                    }
                    ((MainActivity) getActivity()).spansAdapter.datetimesList.add(0, StaticUtils.getCurrentDateTime(false));
                    ((MainActivity) getActivity()).spansAdapter.isClosedList.add(0, false);
                    ((MainActivity) getActivity()).spansAdapter.notifyItemInserted(0);
                    ((MainActivity) getActivity()).spansList.scrollToPosition(0);
                }else{
                    Toast.makeText(getActivity(), "The Span was NOT save", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Dialog dialog = builder.create();
//        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return dialog;
    }
}
