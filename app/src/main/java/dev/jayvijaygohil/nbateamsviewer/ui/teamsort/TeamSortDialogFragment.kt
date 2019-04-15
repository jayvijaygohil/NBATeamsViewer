package dev.jayvijaygohil.nbateamsviewer.ui.teamsort

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.jayvijaygohil.nbateamsviewer.R
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase.SortType
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase.SortType.*
import kotlinx.android.synthetic.main.fragment_team_sort_dialog.*

class TeamSortDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_sort_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSelectedSortLogic()
    }

    private fun setupSelectedSortLogic() {
        btn_sort_asc.setOnClickListener {
            sendSelectedSortTypeToTargetFragment(NAME_ASC)
            this.dismiss()
        }
        btn_sort_des.setOnClickListener {
            sendSelectedSortTypeToTargetFragment(NAME_DESC)
            this.dismiss()
        }
        btn_sort_hwins.setOnClickListener {
            sendSelectedSortTypeToTargetFragment(HIGHEST_WINS)
            this.dismiss()
        }
        btn_sort_lwins.setOnClickListener {
            sendSelectedSortTypeToTargetFragment(LOWEST_WINS)
            this.dismiss()
        }
        btn_sort_hlosses.setOnClickListener {
            sendSelectedSortTypeToTargetFragment(HIGHEST_LOSSES)
            this.dismiss()
        }
        btn_sort_llosses.setOnClickListener {
            sendSelectedSortTypeToTargetFragment(LOWEST_LOSSES)
            this.dismiss()
        }
    }

    private fun sendSelectedSortTypeToTargetFragment(selectedSortType: SortType) {
        val intent = Intent().also {
            it.putExtra(SORT_RESULT_INTENT_KEY, selectedSortType.ordinal)
        }

        targetFragment?.onActivityResult(
            targetRequestCode,
            SORT_RESULT_CODE,
            intent
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = TeamSortDialogFragment()

        const val SORT_RESULT_INTENT_KEY = "sort_result_intent_key"
        const val SORT_RESULT_CODE = 5000
        const val TAG = "[TeamSortDialogFragment]"
    }
}