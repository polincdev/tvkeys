package com.polinc.tvkeys;
import android.view.KeyEvent;
//import com.rouninlabs.another_tv_remote.TvRemoteEventProcessor;
import com.polinc.tvkeys.TVKeysEventProcessor;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.EventChannel;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.embedding.android.FlutterActivity;
import android.app.UiModeManager;
import android.content.ComponentName;

import android.content.Context;
import android.content.res.Configuration;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import android.content.ContextWrapper;
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** TvkeysPlugin */
public class TvkeysPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private EventChannel mRemoteBtnEventChannel;
  TVKeysEventProcessor tvKeysEventProcessor;
  
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "tv_keys");
    channel.setMethodCallHandler(this);
	
	   mRemoteBtnEventChannel = new EventChannel(flutterEngine.getDartExecutor(), "tv_keys_input");
       tvKeysEventProcessor= new TVKeysEventProcessor();
        mRemoteBtnEventChannel.setStreamHandler(tvKeysEventProcessor);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
