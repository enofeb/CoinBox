package com.enofeb.core.base

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import com.enofeb.core.ui.theme.CoinBoxTheme

abstract class BaseFragment: Fragment() {

    @Composable
    fun ComposeMagic(content: @Composable () -> Unit) {
        CoinBoxTheme(darkTheme = true) {
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

}
