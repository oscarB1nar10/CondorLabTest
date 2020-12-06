package com.example.condorlabsapp.framework.presentation.dashboard

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.condorlabsapp.R
import com.example.condorlabsapp.business.domain.model.SoccerTeam
import com.example.condorlabsapp.framework.presentation.adapters.SoccerTeamsListAdapter
import com.example.condorlabsapp.util.NavigationArguments.SOCCER_TEAM
import com.example.condorlabsapp.util.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.layout_toolbar_component.*
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var searchView: SearchView
    private val dashboardFragmentViewModel: DashboardFragmentViewModel by viewModels()
    lateinit var soccerTeamsListAdapter: SoccerTeamsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        configureUI()
        setupToolbar()
        subscribeObservers()
        populateView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        initSearchView(menu)
    }

    private fun configureUI() {
        setupRecyclerAdapter()
    }

    private fun setupToolbar() {
        activity?.let { context ->
            context.toolbar_title.text = getString(R.string.dashboard_fragment_toolbar_title)
        }
    }

    private fun subscribeObservers() {
        observe(dashboardFragmentViewModel.soccerTeams, ::handleSoccerTeams)
    }

    private fun populateView() {
        dashboardFragmentViewModel.getSoccerTeams()
    }

    private fun setupRecyclerAdapter() {
        rcv_soccer_teams.apply {
            layoutManager = LinearLayoutManager(activity)
            soccerTeamsListAdapter = SoccerTeamsListAdapter(
                requestManager,
                ::selectTeam
            )
            adapter = soccerTeamsListAdapter
            soccerTeamsListAdapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun handleSoccerTeams(soccerTeams: List<SoccerTeam>) {
        soccerTeamsListAdapter.preloadGlideImages(
            requestManager,
            soccerTeams
        )
        soccerTeamsListAdapter.submitList(soccerTeams)
    }

    private fun selectTeam(soccerTeam: SoccerTeam) {
        val bundle = bundleOf(SOCCER_TEAM to soccerTeam)
        findNavController().navigate(R.id.action_dashboardFragment_to_soccerTeamDetail, bundle)
    }

    private fun initSearchView(menu: Menu) {

        activity?.apply {
            val searchManager: SearchManager =
                getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView = menu.findItem(R.id.action_search).actionView as SearchView
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            searchView.maxWidth = Integer.MAX_VALUE
            searchView.setIconifiedByDefault(true)
            searchView.isSubmitButtonEnabled = true
        }
    }
}
