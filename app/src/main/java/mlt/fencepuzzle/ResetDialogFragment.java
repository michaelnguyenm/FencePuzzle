package mlt.fencepuzzle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by megam on 4/29/2017.
 */

public class ResetDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to reset all your records?")
                .setPositiveButton("Yes", new resetOnClickListener())
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    public class resetOnClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int id) {
            ((SettingsActivity) getActivity()).resetAllScores();
            dismiss();
        }
    }
}
