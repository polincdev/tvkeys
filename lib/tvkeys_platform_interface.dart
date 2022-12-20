import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'tvkeys_method_channel.dart';

abstract class TvkeysPlatform extends PlatformInterface {
  /// Constructs a TvkeysPlatform.
  TvkeysPlatform() : super(token: _token);

  static final Object _token = Object();

  static TvkeysPlatform _instance = MethodChannelTvkeys();

  /// The default instance of [TvkeysPlatform] to use.
  ///
  /// Defaults to [MethodChannelTvkeys].
  static TvkeysPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [TvkeysPlatform] when
  /// they register themselves.
  static set instance(TvkeysPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
