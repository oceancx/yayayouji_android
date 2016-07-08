package com.yayayouji.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yayayouji.R;

/**
 * Created by oceancx on 16/7/8.
 */
public class SingleStubActivity extends BaseActivity {
    public static final String ARGS_CLASS_NAME = "Args_Class_Name";

    public static void start(Context context, String fgClassName) {
        Intent intent = new Intent(context, SingleStubActivity.class);
        Bundle data = new Bundle();
        data.putString(ARGS_CLASS_NAME, fgClassName);
        intent.putExtras(data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_stub_activity);
        String className = getIntent().getStringExtra(ARGS_CLASS_NAME);
        Fragment fragment = Fragment.instantiate(this, className, null);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_layout, fragment)
                .commit();
    }


}
