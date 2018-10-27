package com.veronikabogdanovich.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class FinishActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.finish);
    }
}
