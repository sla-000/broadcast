package cc.mobylabs.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import cc.mobylabs.app.AppContext;

/**
 * sla 2018-10-09
 *
 * Listening on broadcast intents
 */
public class BroadcastListen {
	private final static String TAG = "BroadcastListen";

	/**
	 * Get instance
	 */
	@NonNull
	public static BroadcastListen getInstance() {
		if( mBroadcastListen == null ) {
			mBroadcastListen = new BroadcastListen();
		}
		return mBroadcastListen;
	}

	/**
	 * Interface for action listner
	 */
	public interface Listner {
		/**
		 * Receive callback
		 *
		 * @param action Action string
		 * @param intent Intent
		 */
		void onReceive( @NonNull final String action, @NonNull final Intent intent );
	}

	/**
	 * Add action and listner interface
	 * @param action Action string to listen on broadcast
	 * @param listner Listner listner
	 */
	public boolean add( @NonNull final String action, @NonNull final Listner listner ) {
		final BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				listner.onReceive( action, intent );
			}
		};

		AppContext.getInstance().getOrThrow().registerReceiver(receiver, new IntentFilter(action));

		map.put( action, receiver );
		return true;
	}

	/**
	 * Remove receiver and listner for action
	 *
	 * @param action Action
	 * @return true - action was in list, false - already removed
	 */
	public boolean remove( @NonNull final String action ) {
		final BroadcastReceiver receiver = map.get( action );

		if( receiver != null ) {
			AppContext.getInstance().getOrThrow().unregisterReceiver( receiver );

			map.remove( action );
			return true;
		}

		return false;
	}

	/**
	 * Remove all listners
	 */
	public void removeAll() {
		for( final String action : map.keySet() ) {
			remove( action );
		}
	}

	@NonNull
	private final static Map<String, BroadcastReceiver> map = new HashMap<>();

	@Nullable
	private static BroadcastListen mBroadcastListen;
}
