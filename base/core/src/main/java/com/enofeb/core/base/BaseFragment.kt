package com.enofeb.core.base

import androidx.fragment.app.Fragment
import com.enofeb.core.state.UiState
import com.enofeb.core.state.UiStateRender

abstract class BaseFragment<US : UiState> : Fragment(), UiStateRender<US> {

}
