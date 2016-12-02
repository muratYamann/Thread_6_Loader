package com.yamankod.thread_6_loader;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesLoader extends AsyncTaskLoader<SharedPreferences>
    implements SharedPreferences.OnSharedPreferenceChangeListener {
  private SharedPreferences prefs = null;

  public static void persist(final SharedPreferences.Editor editor) {
    editor.apply();
  }

  public SharedPreferencesLoader(Context context) {
    super(context);
  }
  @Override
  public SharedPreferences loadInBackground() {
    prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
    prefs.registerOnSharedPreferenceChangeListener(this);
    return (prefs);
  }
  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
      String key) {
    onContentChanged();
  }
   @Override
  protected void onStartLoading() {
    if (prefs != null) {
      deliverResult(prefs);
    }

    if (takeContentChanged() || prefs == null) {
      forceLoad();
    }
  }
} 