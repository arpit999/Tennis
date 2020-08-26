package com.bicubic.tennis.fragment;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bicubic.tennis.R;

/**
 * Created by admin on 8/5/2016.
 */
public class PlayerDialogFragment extends DialogFragment implements View.OnClickListener {

    View rootView;
    Button  bt_close;

    public static PlayerDialogFragment newInstance() {
        return new PlayerDialogFragment();
    }

    public PlayerDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.player_dialog, container, false);

        bt_close = (Button) rootView.findViewById(R.id.bt_close);

        bt_close.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_close:
                dismiss();
                Toast.makeText(getActivity(), "Close ", Toast.LENGTH_SHORT).show();

                break;

            default:


                break;
        }
    }



}
