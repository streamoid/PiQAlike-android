# PiQAlike Android

[![N|Solid](http://www.streamoid.com/images/logo-white.png)](http://www.streamoid.com/)

This repository contains binary distributions of PiQAlike Android framework.

If you have any questions, comments, or issues related to PiQAlike, Please contact the team by emailing support@streamoid.com.


### PiQAlike

PiQAlike is an Android framework for visual search recommendations in fashion. PiQAlike framework provided by Streamoid Technologies lets you seemlessly integrate visual search feature into your native android applications

- what is visual search in fashion ?
- That perfect summer dress you saw in a magazine or those shoes that the woman was wearing at the coffee shop this morning. Just take a photo and find similar products in your app using PiQAlike SDK.


### Installation

### Binary

You may [download AAR releases here.](https://github.com/streamoid/PiQAlike-android/releases)

### JCenter

Add JCenter to your build file's list of repositories.

```groovy
repositories {
    jcenter()
}
```

to use the JCenter Repository

```groovy
dependencies {
    ...
    compile 'com.streamoid.sdk.piqalike:piqalikesdk:1.0.9'
    ...
}
```

Add following to manifest application


```
 <application
  ...
  tools:replace="android:theme"
  ...
  >
```

### Verifying PiQAlike Configuration

Once you have finished adding PiQAlike framework to your project, you can test your configuration by importing the dependencies and connecting a client to the PiQAlike cloud. To do so, add following code to your Application class. (note that you must substitute the client name and client token placeholder text with your actual values, in order to get these values please contact us at support@streamoid.com):

### Simple Intialization
```sh
 piqALike.initialize(VENDOR, TOKEN, new com.streamoid.sdk.piqalike.Callback() {
                @Override
                public void onSuccess(String response) {
                    //Connection Success
                }

                @Override
                public void onFail(String error) {
                    //Connection Failed

                }
            });

```

### Custom Intialization

```sh

                PiqALikeParams params=new PiqALikeParams();
                params.setThemeColor(Color.MAGENTA);
                params.setCropperType(PiqALikeParams.CROPPER.FREEMODE);
                params.setBottomBarColor(Color.BLUE);
                params.setDrawableCameraRotate(R.mipmap.ic_camera_rotate);
                params.setDrawableBackButton(R.mipmap.demo_ic_back);
                params.setDrawableFlashAuto(R.mipmap.ic_flash_auto);
                params.setDrawableFlashOff(R.mipmap.ic_flashoff);
                params.setDrawableFlashOn(R.mipmap.ic_flash_on);
                params.setTextColor(Color.WHITE);
                params.setDrawableCameraSnapButton(R.drawable.demo_camera_button);
                PiqALike.getInstance(Demo.this).initialize(VENDOR, TOKEN,params, new Callback() {
                    @Override
                    public void onSuccess(String response) {
                        PiqALike.getInstance(Demo.this).openCamera(new CameraCallback() {
                            @Override
                            public void onSuccess(String response, String originalBitmap, String croppedBitmap, String cropPoints) {
                                Log.v("callback","success");
                            }

                            @Override
                            public void onFail(String error) {
                                Log.v("callback","fail");
                            }
                        });
                    }

                    @Override
                    public void onFail(String error) {

                    }
                });




```

Launch your application and verify that the connection is successful. You are now ready to begin visual search.


### To Start Visual Search

```
 PiqALike.getInstance(Context).openCamera(new CameraCallback() {
                    @Override
                    public void onSuccess(final String response, String originalBitmap, String croppedBitmap, String cropPoints){
                        // Handle Response

                    }

                    @Override
                    public void onFail(String error) {
                    // Handle Fail Case
                    }
                });

```


### To find Visually similar products by productId

```
PiqALike.getInstance(Context).getVisuallySimilarProducts(PRODUCTID, new SimilarSearchResultsCallback() {
            @Override
            public void onSuccess(String response) {
                // Handle Response
            }

            @Override
            public void onFail(String error) {
              // Handle Fail Case
            }
        });
```
### Note

Following Permissions are mandatory:

```sh
Manifest.permission.CAMERA // To use Device Camera
Manifest.permission.INTERNET // To connect Webservice and fetch matches
Manifest.permission.ACCESS_NETWORK_STATE // To check Network Availibility
Manifest.permission.READ_EXTERNAL_STORAGE or Manifest.permission.WRITE_EXTERNAL_STORAGE// To access Gallery to pick image for search matches
```
### Contact

You can reach the Streamoid team at any time by emailing support@streamoid.com.

