package cc.mobylabs.broadcast;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cc.mobylabs.app.AppContext;
import cc.mobylabs.log.Log;

import static android.os.SystemClock.sleep;

public class BroadcastSendTest {
	private final static String TAG = "BroadcastSendTest";

	private final static String ACTION_TEST_1 = "cc.mobylabs.broadcast.ACTION_TEST_1";
	private final static String EXTRA_TEST_1 = "EXTRA_TEST_1";

	private final static String TEST_TEXT_1 = "test!!!!1111!1!1";
	private final static String TEST_TEXT_2 = "test2!!!!1111!1!1";
	private final static String TEST_TEXT_3 = "test3!!!!1111!1!1";

	private Context mMockContext;

	@Before
	public void setUp() {
		mMockContext = InstrumentationRegistry.getContext();
//		mMockContext = new RenamingDelegatingContext( InstrumentationRegistry.getTargetContext(), "test_");
	}

	@Test
	public void send() {
		AppContext.getInstance().set( mMockContext );

		BroadcastListen.getInstance().add( ACTION_TEST_1, (action, intent) -> {
			Log.e(TAG, "onReceive: action=" + action + ", extra=" + intent.getStringExtra( EXTRA_TEST_1 ));

			Assert.assertEquals( ACTION_TEST_1, action );
//			Assert.assertEquals( TEST_TEXT_1, intent.getStringExtra( EXTRA_TEST_1 ) );
		} );

		{
			final Intent intent = new Intent( ACTION_TEST_1 );
			intent.putExtra( EXTRA_TEST_1, TEST_TEXT_1 );

			BroadcastSend.getInstance().send( intent );
		}

		{
			final Intent intent = new Intent( ACTION_TEST_1 );
			intent.putExtra( EXTRA_TEST_1, TEST_TEXT_2 );

			BroadcastSend.getInstance().send( intent );
		}

		{
			final Intent intent = new Intent( ACTION_TEST_1 );
			intent.putExtra( EXTRA_TEST_1, TEST_TEXT_3 );

			BroadcastSend.getInstance().send( intent );
		}

		sleep(200);

		BroadcastListen.getInstance().removeAll();
	}
}