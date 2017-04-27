package mlt.fencepuzzle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by megam on 4/27/2017.
 */

public class WinDialogFragment extends DialogFragment {
    int lvl;

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.15f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }

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
                    .setNeutralButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            ((GameActivity) getActivity()).nextLevel(lvl);
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
                    .setNeutralButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            ((GameActivity) getActivity()).nextLevel(lvl);
                        }
                    })
                    .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            (getActivity()).finish();
                        }
                    });
        }
        builder.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    (getActivity()).finish();
                }
                return true;
            }
        });

        return builder.create();
    }
}
