package com.android.youhu.ui.mine;


import android.os.Bundle;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.ui.base.FragmentBaseActivity;
import com.umeng.fb.fragment.FeedbackFragment;

public class GiveBackActivity extends FragmentBaseActivity {
    private static final String TAG = GiveBackActivity.class.getName();

    private FeedbackFragment mFeedbackFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_back);
        ((TextView) findViewById(R.id.title)).setText("留言反馈");
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            String conversation_id = getIntent().getStringExtra(FeedbackFragment
                    .BUNDLE_KEY_CONVERSATION_ID);
            mFeedbackFragment = FeedbackFragment.newInstance(conversation_id);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mFeedbackFragment)
                    .commit();
        }
    }

    @Override
    protected void onNewIntent(android.content.Intent intent) {
        mFeedbackFragment.refresh();
    }
}

