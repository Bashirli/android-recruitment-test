package com.bashirli.algoritmatask.presentation.ui.home

import androidx.fragment.app.viewModels
import com.bashirli.algoritmatask.R
import com.bashirli.algoritmatask.common.base.BaseFragment
import com.bashirli.algoritmatask.common.utils.gone
import com.bashirli.algoritmatask.common.utils.showMotionToast
import com.bashirli.algoritmatask.common.utils.visible
import com.bashirli.algoritmatask.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

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

                        is HomeUiState.InvestData -> {
                            progressBar.gone()
                        }

                        HomeUiState.Loading -> {
                            progressBar.visible()
                        }
                    }
                }
            }
        }
    }

    private fun setup() {

    }

}