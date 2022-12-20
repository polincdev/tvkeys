import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'tvkeys_platform_interface.dart';

/// An implementation of [TvkeysPlatform] that uses method channels.
class MethodChannelTvkeys extends TvkeysPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('tvkeys');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
