package com.boardgamegeek.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import timber.log.Timber;

public class AuthenticationService extends Service {
	private Authenticator mAuthenticator;

	@Override
	public void onCreate() {
		Timber.v("BoardGameGeek Authentication Service started.");
		mAuthenticator = new Authenticator(this);
	}

	@Override
	public void onDestroy() {
		Timber.v("BoardGameGeek Authentication Service stopped.");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Timber.v("getBinder: returning the AccountAuthenticator binder for intent " + intent);
		return mAuthenticator.getIBinder();
	}
}