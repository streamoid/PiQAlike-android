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
    compile 'com.streamoid.sdk.piqalike:piqalikesdk:1.0.1'
    ...
}
```


### Verifying PiQAlike Configuration

Once you have finished adding PiQAlike framework to your project, you can test your configuration by importing the dependencies and connecting a client to the PiQAlike cloud. To do so, add following code to your Application class. (note that you must substitute the client name and client token placeholder text with your actual values, in order to get these values please contact us at support@streamoid.com):

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
Launch your application and verify that the connection is successful. You are now ready to begin visual search.

### Note

Since PiQAlike uses camera for image search, the application which uses PiQAlike should request for *camera & gallery permissions*

### Contact

You can reach the Streamoid team at any time by emailing support@streamoid.com.
