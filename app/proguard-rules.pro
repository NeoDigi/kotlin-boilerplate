# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Neocypher/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


# Retrofit rules
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# OkHttp rules
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# Gson rules
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
# TODO change to match your package model
# Keep non static or private fields of models so Gson can find their names
-keepclassmembers class be.neodigi.kotlinboilerplate.data.model.** {
    !static !private <fields>;
}

# TODO change to match your Retrofit services (only if using inner models withing the service)
# Some models used by gson are inner classes inside the retrofit service
-keepclassmembers class be.neodigi.kotlinboilerplate.data.remote.RestService$** {
    !static !private <fields>;
}

# Produces useful obfuscated stack traces
# http://proguard.sourceforge.net/manual/examples.html#stacktrace
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable