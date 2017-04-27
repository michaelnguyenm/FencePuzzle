package mlt.fencepuzzle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by megam on 4/27/2017.
 */

public class WinDialogFragment extends DialogFragment {
    int lvl;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        lvl = ((GameActivity) getActivity()).getLevel();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (lvl != 12) {
            builder.setMessage("You did it!\n" + "The current level is: " + lvl)
                    .setPositiveButton("Next Level", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                            ((GameActivity) getActivity()).nextLevel(lvl + 1);
                        }
                    })
                    .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            (getActivity()).finish();
                        }
                    });
        } else {
            builder.setMessage("You have finished the last level! Congratulations!")
                    .setNeutralButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            (getActivity()).finish();
                        }
                    });
        }
        return builder.create();
    }
}
