# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/kaizou/Library/Android/sdk/tools/proguard/proguard-android.txt
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

# ----- Common { ------

-keepattributes Signature
-keepattributes Exceptions
-keepattributes EnclosingMethod
-keepattributes *Annotation*
-keepattributes SourceFile
-keepattributes LineNumberTable
-keepattributes InnerClasses

-keep class sun.misc.Unsafe { *; }

# блэт, почему он должен быть и keep, и dontwarn? бред какой-то
-dontwarn sun.misc.Unsafe

# ----- } Common ------

-keep public class com.google.gson.** { *; }

-keep public class com.orhanobut.** { *; }

# ----- Android Support { ------

#https://stackoverflow.com/questions/30645051/proguard-configuration-for-android-support-v4-22-2-0
-dontwarn android.support.v4.**

#https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-support-v7-appcompat.pro
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

#https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-support-v7-cardview.pro
# http://stackoverflow.com/questions/29679177/cardview-shadow-not-appearing-in-lollipop-after-obfuscate-with-proguard/29698051
-keep class android.support.v7.widget.RoundRectDrawable { *; }

#https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-support-design.pro
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

# ----- } Android Support ------

# ----- guava { ------

-keep class java.lang.ClassValue
-dontwarn java.lang.ClassValue

-keep class javax.lang.model.element.Modifier
-dontwarn javax.lang.model.element.Modifier

-keep public class com.google.guava.** { *; }

# ----- } guava ------

-keep public class android.arch.persistence.room.** { *; }

-keep public class com.github.smart-fun.** { *; }

-keep public class com.litl.leveldb.** { *; }

-keep public class android_serialport_api.** { *; }

# ----- protobuf/grpc { ------

-keep public class com.google.protobuf.** { *; }
-keep public class io.grpc.** { *; }

#-dontwarn com.google.protobuf.**
#-dontwarn io.grpc.**

-keep public class javax.naming.** { *; }
-dontwarn javax.naming.**

# ----- } protobuf/grpc ------

-keep public class net.jcip.** { *; }

-keep public class javax.annotation.** { *; }

-keep public class com.squareup.okio.** { *; }

# ----- Retrofit2/Okhttp1-3 { ------

#https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-square-retrofit2.pro
# Retrofit 2.X
## https://square.github.io/retrofit/ ##
-dontwarn retrofit2.**
-keep public class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

#https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-square-okhttp3.pro
# OkHttp
-keep public class okhttp3.** { *; }
-keep public interface okhttp3.** { *; }
-dontwarn okhttp3.**

#https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-square-okhttp.pro
# OkHttp
-keep public class com.squareup.okhttp.** { *; }
-keep public interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-keep class kotlin.Unit
-dontwarn kotlin.Unit
-keep class kotlin.Metadata
-dontwarn kotlin.Metadata
-keep class kotlin.jvm.JvmName
-dontwarn kotlin.jvm.JvmName
-keep class kotlin.jvm.internal.Intrinsics
-dontwarn kotlin.jvm.internal.Intrinsics

-keep public class org.conscrypt.** { *; }
-dontwarn org.conscrypt.**

-keep public class org.jetbrains.annotations.** { *; }
-dontwarn org.jetbrains.annotations.**

# ----- } Retrofit2/Okhttp1-3 ------

#---------------------------------------------
#----- end -----------------------------------
-printmapping mapping.txt
