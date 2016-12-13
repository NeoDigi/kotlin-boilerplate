package be.neodigi.kotlinboilerplate.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.util.LongSparseArray
import android.support.v7.app.AppCompatActivity
import be.neodigi.kotlinboilerplate.BoilerplateApplication
import be.neodigi.kotlinboilerplate.injection.component.ActivityComponent
import be.neodigi.kotlinboilerplate.injection.component.ConfigPersistentComponent
import be.neodigi.kotlinboilerplate.injection.component.DaggerConfigPersistentComponent
import be.neodigi.kotlinboilerplate.injection.module.ActivityModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

abstract class BaseActivity : AppCompatActivity() {

    private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
    private val NEXT_ID = AtomicLong(0)
    private val sComponentsMap = LongSparseArray<ConfigPersistentComponent>()

    private lateinit var activityComponent: ActivityComponent
    private var activityId: Long = 0

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.andIncrement
        val configPersistentComponent: ConfigPersistentComponent
        if (null == sComponentsMap.get(activityId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", activityId)

            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build()
            sComponentsMap.put(activityId, configPersistentComponent)
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", activityId)
            configPersistentComponent = sComponentsMap.get(activityId)
        }
        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId)
            sComponentsMap.remove(activityId)
        }
        super.onDestroy()
    }

    fun activityComponent(): ActivityComponent? {
        return activityComponent
    }
}