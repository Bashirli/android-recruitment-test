package com.bashirli.algoritmatask.presentation.ui.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bashirli.algoritmatask.R
import com.bashirli.algoritmatask.common.base.BaseFragment
import com.bashirli.algoritmatask.common.utils.gone
import com.bashirli.algoritmatask.common.utils.showMotionToast
import com.bashirli.algoritmatask.common.utils.visible
import com.bashirli.algoritmatask.databinding.FragmentHomeBinding
import com.bashirli.algoritmatask.presentation.sharedViewModel.NetworkSharedUiModel
import com.bashirli.algoritmatask.presentation.sharedViewModel.NetworkSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val sharedViewModel by activityViewModels<NetworkSharedViewModel>()
    private val adapter = InvestAdapter()

    override fun onViewCreateFinish() {
        setup()
    }

    override fun observeEvents() {
        with(binding) {
            with(viewModel) {
                state.observe(viewLifecycleOwner) {
                    when (it) {
                        is HomeUiState.Error -> {
                            progressBar.gone()
                            requireActivity().showMotionToast(
                                resources.getString(R.string.error),
                                it.message,
                                MotionToastStyle.ERROR
                            )
                        }

                        is HomeUiState.IsOnline -> {
                            sharedViewModel.setNetworkState(it.isOnline)
                        }

                        is HomeUiState.InvestData -> {
                            progressBar.gone()
                            adapter.submitData(it.data.result)
                            viewModel.updateDatabase(it.data.result)
                        }

                        HomeUiState.Loading -> {
                            progressBar.visible()
                        }

                        is HomeUiState.OfflineData -> {
                            progressBar.gone()
                            adapter.submitData(it.data)
                        }

                        HomeUiState.DatabaseEdit -> progressBar.gone()
                    }
                }
            }
            sharedViewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkSharedUiModel.IsOnline -> {
                        if (!it.isOnline) {
                            viewModel.getOfflineData()
                        }
                    }
                }
            }
        }
    }

    private fun setup() {
        setRV()
    }

    private fun setRV() {
        with(binding) {
            rvData.adapter = adapter

            rvData.itemAnimator = null
        }
    }

}