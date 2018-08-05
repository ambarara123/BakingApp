package com.akumar.bakingapp.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetFactory(getApplicationContext());
    }
}
