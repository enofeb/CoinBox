package com.enofeb.dashboard.home.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.*
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.util.lerp
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.enofeb.dashboard.R
import com.enofeb.dashboard.home.HomeFragment.Companion.COIN_ID
import com.enofeb.dashboard.home.ShowProgress
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlin.math.min
import kotlin.math.max

private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val ImageOverlap = 115.dp
private val GradientScroll = 180.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val TitleHeight = 128.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment() {

    private val viewModel by viewModels<CoinDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            viewModel.apply {
                id = it.getString(COIN_ID)
                getCoinDetail()
            }
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ComposeMagic {
                CoinDetailScreen(viewModel)
            }
        }
    }
}

@Composable
fun CoinDetailScreen(viewModel: CoinDetailViewModel) {

    val state = viewModel.coinDetailState.collectAsState().value

    val loadingState = viewModel.loadingState.collectAsState().value

    if (loadingState.isLoading == true) {
        ShowProgress()
    }

    state.coinDetail?.let { coin ->
        Box(
            Modifier
                .fillMaxSize()
        ) {
            val scroll = rememberScrollState(0)
            Header()
            Body(scroll, coin.description.detail)
            Title(scroll.value, coin.name, coin.symbol)
            Image(
                imageUrl = coin.image.large,
                scroll = scroll.value
            )
        }
    }
}

//From Jetsnack google docs
@Composable
fun CollapsableImageLayout(
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, contraints ->
        check(measurables.size == 1)

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), contraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), contraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (contraints.maxWidth - imageWidth) / 2,
            contraints.maxWidth - imageWidth,
            collapseFraction
        )

        layout(
            width = contraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }

    }
}

@Composable
fun Image(
    imageUrl: String,
    scroll: Int
) {
    val collapseRange = with(LocalDensity.current) {
        (MaxTitleOffset - MinTitleOffset).toPx()
    }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsableImageLayout(
        collapseFraction = collapseFraction,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        Surface(
            color = Color.LightGray,
            elevation = 0.dp,
            shape = CircleShape
        ) {
            Image(
                painter = rememberImagePainter(data = imageUrl,
                    builder = {
                        crossfade(true)
                        placeholder(drawableResId = R.drawable.ic_z_cash)
                    }),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        }
    }
}

@Composable
fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(listOf(Color.Green, Color.DarkGray)))
    )
}

@Composable
fun Title(scroll: Int, name: String, symbol: String) {
    val maxOffSet = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffSet = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val offset = (maxOffSet - scroll).coerceAtLeast(minOffSet)

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .fillMaxWidth()
            .graphicsLayer { translationY = offset }
            .background(color = MaterialTheme.colors.background)
    ) {
        Spacer(Modifier.height(16.dp))

        Text(text = name, modifier = HzPadding, style = MaterialTheme.typography.h4)

        Text(text = symbol.uppercase(), modifier = HzPadding, style = MaterialTheme.typography.h4)

        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun Body(
    scroll: ScrollState,
    detail: String
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = detail,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}


