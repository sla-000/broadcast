package cc.mobylabs.broadcast;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cc.mobylabs.app.AppContext;

/**
 * sla 2018-10-09
 *
 * Broadcast send
 */
public class BroadcastSend {
	private final static String TAG = "BroadcastSend";

	public static synchronized BroadcastSend getInstance() {
		if( mBroadcastSend == null ) {
			mBroadcastSend = new BroadcastSend();
		}

		return mBroadcastSend;
	}

	/**
	 * Send broadcast intent
	 */
	public void send( @NonNull final Intent intent ) {
		AppContext.getInstance().getOrThrow().sendBroadcast(intent);
	}

	/**
	 * Ctor
	 */
	private BroadcastSend() {
	}

	@Nullable
	private static BroadcastSend mBroadcastSend;
}
