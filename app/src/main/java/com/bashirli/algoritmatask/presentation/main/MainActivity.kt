package com.bashirli.algoritmatask.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bashirli.algoritmatask.R
import com.bashirli.algoritmatask.common.network.ConnectivityObserver
import com.bashirli.algoritmatask.common.utils.gone
import com.bashirli.algoritmatask.common.utils.visible
import com.bashirli.algoritmatask.databinding.ActivityMainBinding
import com.bashirli.algoritmatask.presentation.sharedViewModel.NetworkSharedUiModel
import com.bashirli.algoritmatask.presentation.sharedViewModel.NetworkSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()
    private val sharedViewModel by viewModels<NetworkSharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeData()
        setup()
    }

    private fun observeData() {
        viewModel.state.observe(this) {
            when (it) {
                is MainUiState.NetworkStatus -> {
                    when (it.status) {
                        ConnectivityObserver.ConnectionStatus.Available -> {
                            customMessageBar(
                                R.string.online,
                                R.color.green,
                                R.color.green
                            )
                            sharedViewModel.setNetworkState(true)
                        }

                        ConnectivityObserver.ConnectionStatus.Unavailable -> {
                            customMessageBar(
                                R.string.offline,
                                R.color.red,
                                R.color.red
                            )
                        }

                        ConnectivityObserver.ConnectionStatus.Losing -> {
                            customMessageBar(
                                R.string.offline,
                                R.color.red,
                                R.color.red
                            )
                        }

                        ConnectivityObserver.ConnectionStatus.Lost -> {
                            customMessageBar(
                                R.string.offline,
                                R.color.red,
                                R.color.red
                            )
                            sharedViewModel.setNetworkState(false)
                        }
                    }
                }
            }
        }

        sharedViewModel.state.observe(this) {
            when (it) {
                is NetworkSharedUiModel.IsOnline -> {
                    if (it.isOnline) {
                        customMessageBar(
                            R.string.online,
                            R.color.green,
                            R.color.green
                        )
                    } else {
                        customMessageBar(
                            R.string.offline,
                            R.color.red,
                            R.color.red
                        )
                    }
                }
            }
        }

    }

    private fun setup() {
        with(binding) {
            buttonClose.setOnClickListener {
                constraintLayout.gone()
            }
        }
    }

    private fun customMessageBar(
        @StringRes text: Int,
        @ColorRes color: Int,
        @ColorRes statusBarColor: Int
    ) {
        with(binding) {
            window.statusBarColor = resources.getColor(statusBarColor, null)
            textView3.text = resources.getString(text)
            constraintLayout.setBackgroundColor(resources.getColor(color, null))
            if (!constraintLayout.isVisible) {
                constraintLayout.visible()
            }
        }
    }


}