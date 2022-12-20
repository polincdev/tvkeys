import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:tvkeys/tvkeys_method_channel.dart';

void main() {
  MethodChannelTvkeys platform = MethodChannelTvkeys();
  const MethodChannel channel = MethodChannel('tvkeys');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
