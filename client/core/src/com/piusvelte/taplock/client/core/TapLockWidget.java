/*
 * TapLock
 * Copyright (C) 2012 Bryan Emmanuel
 * 
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Bryan Emmanuel piusvelte@gmail.com
 */
package com.piusvelte.taplock.client.core;

import static com.piusvelte.taplock.client.core.TapLock.ACTION_TOGGLE;
import static com.piusvelte.taplock.client.core.TapLock.EXTRA_DEVICE_ADDRESS;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TapLockWidget extends AppWidgetProvider {
	private static final String TAG = "TapLockWidget";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.d(TAG, "action: " + action);
		if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE))
			context.startService(intent.setClass(context, TapLock.getPackageClass(context, TapLockService.class)));
		else if (action.equals(ACTION_TOGGLE)) {
			if (intent.hasExtra(EXTRA_DEVICE_ADDRESS))
				context.startService(intent.setClass(context, TapLock.getPackageClass(context, TapLockService.class)));
		} else if (action.equals(AppWidgetManager.ACTION_APPWIDGET_DELETED))
			context.startService(intent.setClass(context, TapLock.getPackageClass(context, TapLockService.class)));
		else
			super.onReceive(context, intent);
	}

}
