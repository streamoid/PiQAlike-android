package piqalike.streamoid.com.piqalikesdk;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.streamoid.sdk.piqalike.*;
import com.streamoid.sdk.piqalike.Util;
import com.streamoid.sdk.piqalike.models.FilterApplied;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Home extends AppCompatActivity {

    private static int REQUESTCODE = 833;
    private OkHttpClient okHttpClient;
    private ProgressDialog progressDialog;
    String VENDOR = "VENDER";
    String TOKEN = "TOKEN";
    private AppCompatButton findbyimage;
    private AppCompatButton findbyid;
    private Toolbar toolbar;
    private PiqALike piqALike;
    private EditText productIDedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        if (checkPermissions(this)) {
            initPiqALike();
        } else {
            REQUESTCODE = com.streamoid.sdk.piqalike.Util.requestPermission(this, Manifest.permission.CAMERA, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            com.streamoid.sdk.piqalike.Util.requestPermission(Home.this, Manifest.permission.CAMERA, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private boolean initPiqALike() {
        piqALike = PiqALike.getInstance(Home.this);
        if (!piqALike.IsInitialized()) {
            progressDialog = ProgressDialog.show(Home.this, "Initializing", "Please wait...", true);
            progressDialog.setCancelable(false);
            progressDialog.show();
            PiqALikeParams params=new PiqALikeParams();
            params.setThemeColor(Color.MAGENTA);
            params.setCropperType(PiqALikeParams.CROPPER.FREEMODE);
            params.setTextColor(Color.WHITE);
            piqALike.initialize(VENDOR, TOKEN,params, new com.streamoid.sdk.piqalike.Callback() {
                @Override
                public void onSuccess(String response) {
                    progressDialog.dismiss();
                    findbyimage.setEnabled(true);
                    findbyid.setEnabled(true);
                }

                @Override
                public void onFail(String error) {
                    progressDialog.dismiss();

                }
            });
            return false;
        } else {
            return true;
        }

    }

    private void initViews() {
        findbyimage = ((AppCompatButton) findViewById(R.id.opencamera));
        productIDedit = ((EditText) findViewById(R.id.editText2));
        findbyid = ((AppCompatButton) findViewById(R.id.settings));
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        toolbar.setTitle("PiqALike Demo");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Home.this).create();
                alertDialog.setTitle("Exit");
                alertDialog.setMessage("Do you want to exit the application ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        findbyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initPiqALike()) {
                    opencamera();
                }
            }
        });
        findbyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initPiqALike()) {
                    findbyid();
                }
            }
        });
    }

    public void opencamera() {
        piqALike.openCamera(new CameraCallback() {
            @Override
            public void onSuccess(String response, String originalBitmap, String croppedBitmap, String cropPoints, FilterApplied filterApplied) {
                Log.v("response",response);
                Intent intent=new Intent(Home.this,Results.class);
                intent.putExtra("result",response);
                intent.putExtra("orginalBitmap",originalBitmap);
                intent.putExtra("croppedBitmap",croppedBitmap);
                intent.putExtra("cropPoints",cropPoints);
                startActivity(intent);
            }

            @Override
            public void onFail(String error) {
                Log.v("Error",error);
            }
        });
    }

    public void findbyid() {
        progressDialog = ProgressDialog.show(Home.this, "Initializing", "Please wait...", true);
        progressDialog.setCancelable(false);//SHRUGWB-GREY-
        progressDialog.show();
        piqALike.getVisuallySimilarProducts(productIDedit.getText().toString(), new SimilarSearchResultsCallback() {
            @Override
            public void onSuccess(String response) {
                progressDialog.dismiss();
                Intent intent=new Intent(Home.this,Results.class);
                intent.putExtra("result",response);
                startActivity(intent);
            }

            @Override
            public void onFail(String error) {
                progressDialog.dismiss();
            }
        });

//        Intent intent = new Intent(Home.this, SettingsAct.class);
//        startActivity(intent);
    }

    private boolean checkPermissions(AppCompatActivity context) {
        boolean check = com.streamoid.sdk.piqalike.Util.checkPermissions(context, Manifest.permission.CAMERA, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_EXTERNAL_STORAGE);
        return check;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE) {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.v("permissions","granted");
                initPiqALike();
            }
        }
    }
}
