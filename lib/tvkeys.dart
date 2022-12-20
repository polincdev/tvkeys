
import 'tvkeys_platform_interface.dart';
import 'package:flutter/services.dart';

class Tvkeys {
  Future<String?> getPlatformVersion() {
    return TvkeysPlatform.instance.getPlatformVersion();
  }


  static const MethodChannel _channel = MethodChannel('tv_keys');

  static const EventChannel _tvRemoteEventChannel = EventChannel("tv_keys_input");

  /// Register for TV Remote button events.
  static Stream<TvRemoteEvent> getTvRemoteEvents() async* {
    yield* _tvRemoteEventChannel.receiveBroadcastStream().map((buffer) =>
        TvRemoteEvent(type: KeyType.fromName(name: buffer["type"]), action: KeyAction.fromName(name: buffer["action"])));
  }
}


enum KeyType {
  unknown,
  ok,
  //play,
  back,
  fastForward,
  rewind,
  skipForward,
  skipBackward,
  dPadUp,
  dPadDown,
  dPadLeft,
  dPadRight;

  static KeyType fromName({required String name}) {
    for (var value in values) {
      if (value.toShortString() == name) {
        return value;
      }
    }
    return KeyType.unknown;
  }

  String toShortString() {
    return toString().split('.').last;
  }
}

enum KeyAction {
  unknown, down, up;

  static KeyAction fromName({required String name}) {
    for (var value in values) {
      if (value.toShortString() == name) {
        return value;
      }
    }
    return KeyAction.unknown;
  }

  String toShortString() {
    return toString().split('.').last;
  }
}

class TvRemoteEvent {
  const TvRemoteEvent({required this.type, required this.action});

  final KeyType type;
  final KeyAction action;

  String toString() {
    return "Key: $type - Action: $action";
  }
}
