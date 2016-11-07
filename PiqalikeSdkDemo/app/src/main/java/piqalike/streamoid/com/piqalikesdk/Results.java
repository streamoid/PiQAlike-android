package piqalike.streamoid.com.piqalikesdk;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.streamoid.sdk.piqalike.CameraCallback;
import com.streamoid.sdk.piqalike.Filters;
import com.streamoid.sdk.piqalike.PiqALike;
import com.streamoid.sdk.piqalike.ResultsCallback;
import com.streamoid.sdk.piqalike.models.CategoyModel;
import com.streamoid.sdk.piqalike.models.ResultPojo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class Results extends AppCompatActivity {
    private static final int RESULT = 112;
    private static final int FILTERREQUEST = 233;
    private OkHttpClient okHttpClient;
    public Handler handler;
    private RecyclerView recyclerView;
    private String result;
    private Toolbar toolbar;
    private String orginalBitmap;
    private String croppedBitmap;
    private boolean isFilter=false;
    private String cropPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        toolbar.setTitle("Results");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
        result = getIntent().getStringExtra("result");
        orginalBitmap = getIntent().getStringExtra("orginalBitmap");
        croppedBitmap = getIntent().getStringExtra("croppedBitmap");
        cropPoints = getIntent().getStringExtra("cropPoints");
        isFilter = getIntent().getBooleanExtra("isFilter",false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new ItemDecorationAlbumColumns(
                getResources().getDimensionPixelSize(R.dimen.list_spacing),
              2));
        recyclerView.setLayoutManager(new GridLayoutManager(Results.this,2));
        ResultPojo resultPojo=new Gson().fromJson(result,ResultPojo.class);
        ResultsAdapter resultsAdapter=new ResultsAdapter(Results.this,resultPojo.getData());
        recyclerView.setAdapter(resultsAdapter);
      //  if(!isFilter){
//        if(resultPojo.getData().length<1){
//            Intent intent=new Intent(Results.this,Filters.class);
//            intent.putExtra("orginalBitmap",getIntent().getStringExtra("orginalBitmap"));
//            intent.putExtra("croppedBitmap",getIntent().getStringExtra("croppedBitmap"));
//            startActivityForResult(intent,FILTERREQUEST);
//        }}

    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==FILTERREQUEST){
//            if(resultCode==RESULT_OK){
//                ArrayList<String> gender=new ArrayList<>();
//            ArrayList<String> categories=new ArrayList<>();
//
//            for (CategoyModel categoyModel : Filters.filtersSelected) {
//                boolean genderexists=false;
//                for (String gen : gender) {
//                    if(categoyModel.getId().equals(gen)){
//                        genderexists=true;
//                    }
//                }
//                if(!genderexists){
//                    gender.add(categoyModel.getId());
//                }
//                categories.add(categoyModel.getCategory());
//            }
//
//                String genderList=getJoinedList(gender);
//                String categoryList=getJoinedList(categories);
//                PiqALike.getInstance(Results.this).getFilteredResults(orginalBitmap, genderList, categoryList, new ResultsCallback() {
//                    @Override
//                    public void onSuccess(String response, String bitmap) {
//                        Intent intent=new Intent(Results.this,Results.class);
//                        intent.putExtra("result",response);
//                        intent.putExtra("orginalBitmap",orginalBitmap);
//                        intent.putExtra("croppedBitmap",croppedBitmap);
//                        intent.putExtra("cropPoints",cropPoints);
//                        intent.putExtra("isFilter",true);
//                        startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onFail(String error) {
//
//                    }
//                });
//            }
//        }
//    }
//
//    public Bitmap cropBitmapToBoundingBox(Bitmap picToCrop, int unusedSpaceColor) {
//        int[] pixels = new int[picToCrop.getHeight() * picToCrop.getWidth()];
//        int marginTop = 0, marginBottom = 0, marginLeft = 0, marginRight = 0, i;
//        picToCrop.getPixels(pixels, 0, picToCrop.getWidth(), 0, 0,
//                picToCrop.getWidth(), picToCrop.getHeight());
//
//        for (i = 0; i < pixels.length; i++) {
//            if (pixels[i] != unusedSpaceColor) {
//                marginTop = i / picToCrop.getWidth();
//                break;
//            }
//        }
//
//        outerLoop1:
//        for (i = 0; i < picToCrop.getWidth(); i++) {
//            for (int j = i; j < pixels.length; j += picToCrop.getWidth()) {
//                if (pixels[j] != unusedSpaceColor) {
//                    marginLeft = j % picToCrop.getWidth();
//                    break outerLoop1;
//                }
//            }
//        }
//
//        for (i = pixels.length - 1; i >= 0; i--) {
//            if (pixels[i] != unusedSpaceColor) {
//                marginBottom = (pixels.length - i) / picToCrop.getWidth();
//                break;
//            }
//        }
//
//        outerLoop2:
//        for (i = pixels.length - 1; i >= 0; i--) {
//            for (int j = i; j >= 0; j -= picToCrop.getWidth()) {
//                if (pixels[j] != unusedSpaceColor) {
//                    marginRight = picToCrop.getWidth()
//                            - (j % picToCrop.getWidth());
//                    break outerLoop2;
//                }
//            }
//        }
//
//        return Bitmap.createBitmap(picToCrop, marginLeft, marginTop,
//                picToCrop.getWidth() - marginLeft - marginRight,
//                picToCrop.getHeight() - marginTop - marginBottom);
//    }
//
////    public void upload(String url, File file,boolean filtererd) throws IOException {
////        Request request;
////        if(!filtererd) {
////            RequestBody formBody = new MultipartBody.Builder()
////                    .setType(MultipartBody.FORM)
////                    .addFormDataPart("image", file.getName(),
////                            RequestBody.create(MediaType.parse("image/png"), file))
////                    .build();
////             request = new Request.Builder().url(url).post(formBody).build();
////        }else {
////            ArrayList<String> gender=new ArrayList<>();
////            ArrayList<String> categories=new ArrayList<>();
////
////            for (CategoyModel categoyModel : Filters.filtersSelected) {
////                boolean genderexists=false;
////                for (String gen : gender) {
////                    if(categoyModel.getId().equals(gen)){
////                        genderexists=true;
////                    }
////                }
////                if(!genderexists){
////                    gender.add(categoyModel.getId());
////                }
////                categories.add(categoyModel.getCategory());
////            }
////
////
////
////            RequestBody formBody = new MultipartBody.Builder()
////                    .setType(MultipartBody.FORM)
////                    .addFormDataPart("image", file.getName(),  RequestBody.create(MediaType.parse("image/png"), file))
////                    .addFormDataPart("gender",getJoinedList(gender))
////                    .addFormDataPart("category",getJoinedList(categories))
////                    .build();
////             request = new Request.Builder().url(url).post(formBody).build();
////        }
////        this.okHttpClient.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////
////            }
////
////            @Override
////            public void onResponse(Call call, final Response response) throws IOException {
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        try {
////                            Message message = new Message();
////                            message.what = RESULT;
////                            Bundle bundle = new Bundle();
////                            bundle.putString("result", response.body().string());
////                            message.setData(bundle);
////                            handler.sendMessage(message);
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////
////                    }
////                });
////
////            }
////        });
////
////
////
////    }
//
////
//    public String getJoinedList(ArrayList<String> list){
//        StringBuilder strb=new StringBuilder();
//        for (String s : list) {
//            strb.append(s);
//            strb.append("|");
//        }
//        strb.deleteCharAt(strb.length()-1);
//        return strb.toString();
//    }
}
