/*
 * Copyright (C) 2014 Saravan Pantham
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aniruddhc.acemusic.player.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.aniruddhc.acemusic.player.Services.AudioPlaybackService;
import com.aniruddhc.acemusic.player.Utils.Common;

/**
 * BroadcastReceiver that handles and processes all headset 
 * button clicks/events.
 * 
 * @author Saravan Pantham
 */
public class HeadsetButtonsReceiver extends BroadcastReceiver {
	
	private Common mApp;
	
    public HeadsetButtonsReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    			
    	mApp = (Common) context.getApplicationContext();

		// Required: Service has to be running for the check to run.    	
    	if (mApp.isServiceRunning()) {
    			
			// Return if the intent doesn't contain info about headset control inputs.
			if (!Intent.ACTION_MEDIA_BUTTON.equals( intent.getAction )) {
				return;
			}
			
			KeyEvent event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

        	if( event.getAction() == KeyEvent.ACTION_DOWN ) {
            	Intent intent = new Intent();

        		switch ( event.getKeyCode() ) {
            		case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
            		case KeyEvent.KEYCODE_HEADSETHOOK:
                		intent.setAction(AudioPlaybackService.PLAY_PAUSE_ACTION);
                		break;

            		case KeyEvent.KEYCODE_MEDIA_NEXT:
                		intent.setAction("com.aniruddhc.acemusic.player.NEXT_ACTION");
                		break;

            		case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                		intent.setAction("com.aniruddhc.acemusic.player.PREVIOUS_ACTION");
                		break;
        		}

            	context.sendBroadcast(intent);
        	}
    	}
    }
}
