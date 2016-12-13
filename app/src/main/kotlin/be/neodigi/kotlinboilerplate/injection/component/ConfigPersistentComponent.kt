package be.neodigi.kotlinboilerplate.injection.component

import be.neodigi.kotlinboilerplate.injection.ConfigPersistent
import be.neodigi.kotlinboilerplate.injection.module.ActivityModule
import dagger.Component

@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}