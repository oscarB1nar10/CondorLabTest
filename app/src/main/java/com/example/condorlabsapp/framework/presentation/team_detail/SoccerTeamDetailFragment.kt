package com.example.condorlabsapp.framework.presentation.team_detail

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.condorlabsapp.R
import com.example.condorlabsapp.business.domain.model.SoccerTeam
import com.example.condorlabsapp.util.NavigationArguments.SOCCER_TEAM
import com.example.condorlabsapp.util.NavigationArguments.WEB_SIDE
import com.example.condorlabsapp.util.UIHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_team_detail.*
import kotlinx.android.synthetic.main.layout_toolbar_component.*
import javax.inject.Inject

@AndroidEntryPoint
class SoccerTeamDetailFragment : Fragment(R.layout.fragment_team_detail) {

    @Inject
    lateinit var requestManager: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureUi()
        setupToolbar()
        populateView()
    }

    private fun configureUi() {
        activity?.progress_circular?.visibility = GONE
    }


    private fun setupToolbar() {
        activity?.let { context ->
            context.toolbar_title.text = getString(R.string.soccer_teams_fragment_toolbar_title)
            context.icon_toolbar_left.visibility = VISIBLE
            context.icon_toolbar_left.setOnClickListener {
                context.icon_toolbar_left.visibility = GONE
                activity?.onBackPressed()
            }
        }
    }

    private fun populateView() {
        val soccerTeam: SoccerTeam? = arguments?.getParcelable(SOCCER_TEAM)
        soccerTeam?.let {
            requestManager
                .load(it.temBadge)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(team_badge)

            handleJerseyVisibility(it)

            team_name.text = it.teamName
            team_formed_year.text = it.temFoundationYear.toString()
            team_description.text = it.teamDescription

            setupClickListenerForWebSide(it)
        }

    }

    private fun handleJerseyVisibility(soccerTeam: SoccerTeam) {
        if (soccerTeam.teamWebSide.isNullOrEmpty())
            team_jersey.visibility = GONE
        else
            requestManager
                .load(soccerTeam.teamJersey)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(team_jersey)
    }

    private fun setupClickListenerForWebSide(soccerTeam: SoccerTeam) {
        if (soccerTeam.teamWebSide.isNullOrEmpty())
            team_web_side.visibility = GONE
        else {

            activity?.let { context ->

                UIHelper.colorText(
                    team_web_side,
                    soccerTeam.teamWebSide,
                    soccerTeam.teamWebSide,
                    ContextCompat.getColor(context, R.color.colorPrimaryDark), true
                )

                team_web_side.setOnClickListener {
                    val bundle = bundleOf(WEB_SIDE to team_web_side.text.toString())
                    findNavController().navigate(
                        R.id.action_soccerTeamDetail_to_webViewFragment,
                        bundle
                    )
                }
            }
        }
    }

}
