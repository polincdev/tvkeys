package com.polinc.tvkeys;

import android.view.KeyEvent;
import io.flutter.plugin.common.EventChannel;
import java.util.HashMap;
import java.util.*;

public class TVKeysEventProcessor implements  EventChannel.StreamHandler{

    private static TVKeysEventProcessor INSTANCE;
    public static TVKeysEventProcessor getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TVKeysEventProcessor();
        }
        return INSTANCE;
    }


    EventChannel.EventSink mEventSink = null;

    public void setEventNotifier(EventChannel.EventSink sink) {
        mEventSink = sink;
    }

    public  void notifyEvent(KeyEvent event) {

        String buttonKey="unknown";
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_B || event.getKeyCode()==KeyEvent.KEYCODE_BACK)
            buttonKey="back";
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_SELECT ||
                event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_A ||
                event.getKeyCode()==KeyEvent.KEYCODE_ENTER ||
                event.getKeyCode()==KeyEvent.KEYCODE_DPAD_CENTER ||
                event.getKeyCode()==KeyEvent.KEYCODE_NUMPAD_ENTER  )
            buttonKey="ok";
        if(event.getKeyCode()==KeyEvent.KEYCODE_DPAD_UP  )
            buttonKey="dPadUp";
        if(event.getKeyCode()==KeyEvent.KEYCODE_DPAD_DOWN  )
            buttonKey="dPadDown";
        if(event.getKeyCode()==KeyEvent.KEYCODE_DPAD_LEFT  )
            buttonKey="dPadLeft";
        if(event.getKeyCode()==KeyEvent.KEYCODE_DPAD_RIGHT  )
            buttonKey="dPadRight";
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_R1  )
            buttonKey="skipForward";
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_L1  )
            buttonKey="skipBackward";
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_R2  )
            buttonKey="fastForward";
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_L2  )
            buttonKey="rewind";


        String actionKey="unknown";
        if(event.getAction()==KeyEvent.ACTION_UP)
            actionKey="up";
        if(event.getAction()==KeyEvent.ACTION_DOWN)
            actionKey="down";

        Map<String, String> mapAction=new HashMap();
        mapAction.put( "type",buttonKey);
        mapAction.put( "action",actionKey);
        mEventSink.success(mapAction );

    }

public void	onCancel(Object arguments)	{
    setEventNotifier( null);
}


public void	onListen(Object arguments, EventChannel.EventSink events){
    setEventNotifier(  events);
}



}
