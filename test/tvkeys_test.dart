import 'package:flutter_test/flutter_test.dart';
import 'package:tvkeys/tvkeys.dart';
import 'package:tvkeys/tvkeys_platform_interface.dart';
import 'package:tvkeys/tvkeys_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockTvkeysPlatform
    with MockPlatformInterfaceMixin
    implements TvkeysPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final TvkeysPlatform initialPlatform = TvkeysPlatform.instance;

  test('$MethodChannelTvkeys is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelTvkeys>());
  });

  test('getPlatformVersion', () async {
    Tvkeys tvkeysPlugin = Tvkeys();
    MockTvkeysPlatform fakePlatform = MockTvkeysPlatform();
    TvkeysPlatform.instance = fakePlatform;

    expect(await tvkeysPlugin.getPlatformVersion(), '42');
  });
}
