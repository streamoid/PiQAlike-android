package piqalike.streamoid.com.piqalikesdk;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.streamoid.sdk.piqalike.PiqALike;

import piqalike.streamoid.com.piqalikesdk.colorpicker.ColorPickerDialog;

public class SettingsAct extends AppCompatActivity {

    private CheckBox colorize;
    private CheckBox bcolorize;
    private Toolbar toolbar;
    private ImageView themepickerbar;
    private ImageView bottombarpickerbar;
    private PiqALike piqALike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        piqALike = PiqALike.getInstance(SettingsAct.this);
//        toolbar = ((Toolbar) findViewById(R.id.toolbar));
//        toolbar.setTitle("Settings");
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              finish();
//
//            }
//        });
//        Button themepicker = (Button) findViewById(R.id.themepicker);
//        Button bottombarpicker = (Button) findViewById(R.id.bottombarpicker);
//        themepickerbar = (ImageView) findViewById(R.id.themepickerbar);
//        bottombarpickerbar = (ImageView) findViewById(R.id.bottombarpickerbar);
//        colorize = (CheckBox) findViewById(R.id.colorizetheme);
//        bcolorize = (CheckBox) findViewById(R.id.bottombarcolorize);
//        colorize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                piqALike.setThemeColorize(isChecked);
//                colorize.setChecked(piqALike.isThemeColorize());
//            }
//        });
//        bcolorize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                piqALike.setCameraBottomPadColorize(isChecked);
//                bcolorize.setChecked(piqALike.isCameraBottomPadColorize());
//            }
//        });
//        colorize.setChecked(piqALike.isThemeColorize());
//        bcolorize.setChecked(piqALike.isCameraBottomPadColorize());
//        themepickerbar.setBackgroundColor(piqALike.getThemeColor());
//        bottombarpickerbar.setBackgroundColor(piqALike.getCameraBottomPadColor());
//        bottombarpicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(SettingsAct.this, Color.RED, new ColorPickerDialog.OnColorSelectedListener() {
//
//                    @Override
//                    public void onColorSelected(int color) {
//                        piqALike.setCameraBottomPadColor(color);
//                        bottombarpickerbar.setBackgroundColor(color);
//                    }
//
//                });
//                colorPickerDialog.show();
//            }
//        });
//        themepicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(SettingsAct.this, Color.RED, new ColorPickerDialog.OnColorSelectedListener() {
//
//                    @Override
//                    public void onColorSelected(int color) {
//                        piqALike.setThemeColor(color);
//                        themepickerbar.setBackgroundColor(color);
//                    }
//
//                });
//                colorPickerDialog.show();
//            }
//        });
    }
}
