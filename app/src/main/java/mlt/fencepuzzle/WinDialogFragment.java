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
    int prevScore;
    int currScore;

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        Dialog dialog = getDialog();
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.15f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        lvl = ((GameActivity) getActivity()).getLevel();
        prevScore = ((GameActivity) getActivity()).getmPrevCount();
        currScore = ((GameActivity) getActivity()).getmTapCount();

        StringBuilder sb = new StringBuilder();
        if (lvl != 12)
            sb.append("The current level is: " + lvl + "\n");
        else
            sb.append("You have finished the last level! Congratulations!\n");
        if (prevScore == -1) {
            sb.append("Your new record is: " + currScore + "\n");
        } else if (currScore < prevScore) {
            sb.append("Your new record is: " + currScore + "\n");
            sb.append("Your previous record was: " + prevScore + "\n");
        } else {
            sb.append("Your taps to complete is: " + currScore + "\n");
            sb.append("Your current record is: " + prevScore + "\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (lvl != 12) {
            builder.setTitle("You did it!")
                    .setMessage(sb.toString())
                    .setPositiveButton("Next Level", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                            ((GameActivity) getActivity()).loadLevel(lvl + 1);
                        }
                    })
                    .setNeutralButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            ((GameActivity) getActivity()).loadLevel(lvl);
                        }
                    })
                    .setNegativeButton("Exit Level", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            (getActivity()).finish();
                        }
                    });
        } else {
            builder.setTitle("You did it!")
                    .setMessage(sb.toString())
                    .setNeutralButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            ((GameActivity) getActivity()).loadLevel(lvl);
                        }
                    })
                    .setNegativeButton("Exit Level", new DialogInterface.OnClickListener() {
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
