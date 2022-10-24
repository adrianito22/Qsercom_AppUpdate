package com.tiburela.qsercom.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.tiburela.qsercom.R;

public class CanvasActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canvas_layout);

        // initializing our view.
        relativeLayout = findViewById(R.id.idRLView);

        // calling our  paint view class and adding
        // its view to our relative layout.
        PaintView paintView = new PaintView(this);
        relativeLayout.addView(paintView);

    }
}