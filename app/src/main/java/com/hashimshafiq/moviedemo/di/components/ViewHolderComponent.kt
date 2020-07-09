package com.hashimshafiq.moviedemo.di.components

import com.hashimshafiq.moviedemo.di.ViewModelScope
import com.hashimshafiq.moviedemo.di.modules.ViewHolderModule
import com.hashimshafiq.moviedemo.ui.home.adapter.MovieItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

        fun inject(viewHolder : MovieItemViewHolder)
}