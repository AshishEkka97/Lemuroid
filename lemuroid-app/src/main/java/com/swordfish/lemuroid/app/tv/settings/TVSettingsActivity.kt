package com.swordfish.lemuroid.app.tv.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.swordfish.lemuroid.app.shared.settings.GamePadSettingsPreferences
import com.swordfish.lemuroid.app.shared.settings.GamePadManager
import com.swordfish.lemuroid.app.shared.settings.SettingsInteractor
import com.swordfish.lemuroid.app.tv.shared.TVBaseSettingsActivity
import com.swordfish.lemuroid.lib.injection.PerActivity
import com.swordfish.lemuroid.lib.injection.PerFragment
import dagger.Provides
import dagger.android.ContributesAndroidInjector

class TVSettingsActivity : TVBaseSettingsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = TVSettingsFragmentWrapper()
            supportFragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit()
        }
    }

    class TVSettingsFragmentWrapper : BaseSettingsFragmentWrapper() {
        override fun createFragment(): Fragment {
            return TVSettingsFragment()
        }
    }

    @dagger.Module
    abstract class Module {

        @PerFragment
        @ContributesAndroidInjector(modules = [TVSettingsFragment.Module::class])
        abstract fun tvSettingsFragment(): TVSettingsFragment

        @dagger.Module
        companion object {

            @Provides
            @PerActivity
            @JvmStatic
            fun settingsInteractor(activity: TVSettingsActivity) = SettingsInteractor(activity)

            @Provides
            @PerActivity
            @JvmStatic
            fun gamepadSettingsPreferences(gamePadManager: GamePadManager) =
                GamePadSettingsPreferences(gamePadManager, true)
        }
    }
}
