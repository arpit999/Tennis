package com.bicubic.tennis.fragment;

import android.app.Dialog;

import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bicubic.tennis.R;

/**
 * Created by admin on 8/5/2016.
 */
public class FeedDialogFragment extends DialogFragment implements View.OnClickListener {

    View rootView;
    Button bt_facebook, bt_twitter, bt_share, bt_close;

    public static FeedDialogFragment newInstance() {
        return new FeedDialogFragment();
    }

    public FeedDialogFragment() {
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
        rootView = inflater.inflate(R.layout.feed_dialog, container, false);

        bt_facebook = (Button) rootView.findViewById(R.id.bt_facebook);
        bt_twitter = (Button) rootView.findViewById(R.id.bt_twitter);
        bt_share = (Button) rootView.findViewById(R.id.bt_share);
        bt_close = (Button) rootView.findViewById(R.id.bt_close);

        bt_facebook.setOnClickListener(this);
        bt_twitter.setOnClickListener(this);
        bt_share.setOnClickListener(this);
        bt_close.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_facebook:

                Toast.makeText(getActivity(), "Facebook ", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_twitter:

                Toast.makeText(getActivity(), "Twitter ", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_share:

                Toast.makeText(getActivity(), "Share ", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_close:
                dismiss();
                Toast.makeText(getActivity(), "Close ", Toast.LENGTH_SHORT).show();

                break;

            default:


                break;
        }
    }
}
