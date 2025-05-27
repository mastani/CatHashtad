package app.mastani.cathashtad.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.appConfigDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_config_preferences")

val IS_DARK_THEME_KEY = booleanPreferencesKey("is_dark_theme")