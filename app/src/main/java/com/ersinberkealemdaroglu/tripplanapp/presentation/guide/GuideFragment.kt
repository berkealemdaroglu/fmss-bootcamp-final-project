package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentGuideBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.guide.guidecategoryadapter.GuideCategoryAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.guide.needblogadapter.MightNeedTheseAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.guide.toparticlesadapter.BlogDataTopArticlesAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter.BookmarkAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkItemOnClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.SharedPreference
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : Fragment() {

    private lateinit var guideBinding: FragmentGuideBinding
    private val guideFragmentViewModel: GuideFragmentViewModel by activityViewModels()
    private lateinit var mightNeedTheseAdapter: MightNeedTheseAdapter
    private lateinit var blogDataTopArticlesAdapter: BlogDataTopArticlesAdapter
    private val bookmarkAdapter: BookmarkAdapter = BookmarkAdapter()
    private val guideCategoryAdapter = GuideCategoryAdapter()
    private val travelModel = TravelModel()
    private lateinit var sharedPreference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        guideBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_guide, container, false)
        return guideBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        getBlogDataApi()
        getTopArticlesDataApi()
        seeAllNavigate()
        pushMightNeedThisOnClickController()
        refreshData()
        loadingData()
        bookmarkAddLocalDB()
        getAllGuideCategoryData()
        searchBottomSheet()
        bottomNavigationVisible()
        fmssIkUsers()
    }

    private fun init() {
        mightNeedTheseAdapter = MightNeedTheseAdapter()
        guideBinding.mightNeedRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        guideBinding.mightNeedRecyclerview.adapter = mightNeedTheseAdapter

        blogDataTopArticlesAdapter = BlogDataTopArticlesAdapter()
        guideBinding.topArticlesRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        guideBinding.topArticlesRecyclerview.adapter = blogDataTopArticlesAdapter

        guideBinding.categoryRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        guideBinding.categoryRecyclerview.adapter = guideCategoryAdapter

        guideBinding.mightNeedRecyclerview.isNestedScrollingEnabled.not()

        context?.let {
            sharedPreference = SharedPreference(it)
        }


    }

    private fun getBlogDataApi() {
        guideFragmentViewModel.blogData.observe(viewLifecycleOwner) { getBlogData ->
            mightNeedTheseAdapter.setBlogDataModel(getBlogData)
        }
    }

    private fun getTopArticlesDataApi() {
        guideFragmentViewModel.blogData.observe(viewLifecycleOwner) { topArticles ->
            blogDataTopArticlesAdapter.setBlogDataModel(topArticles)
        }
    }

    private fun getAllGuideCategoryData() {
        guideFragmentViewModel.guideCategory.observe(viewLifecycleOwner) { guideCategory ->
            guideCategoryAdapter.setAllGuideCategory(guideCategory)
        }
    }

    private fun seeAllNavigate() {
        guideBinding.seeAllButton.setOnClickListener {
            findNavController().navigate(GuideFragmentDirections.actionGuideFragmentToGuideSeeAllFragment())
        }
    }

    private fun pushMightNeedThisOnClickController() {
        mightNeedTheseAdapter.setMightNeedThisOnClickListener(object :
            MightNeedTheseOnClickListener {
            override fun onClick(travelItem: TravelModelItem) {
                val action = GuideFragmentDirections.actionGuideFragmentToDetailFragment(travelItem)
                findNavController().navigate(action)
            }
        })
    }

    private fun refreshData() {
        guideBinding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                mightNeedRecyclerview.visibility = View.INVISIBLE
                topArticlesRecyclerview.visibility = View.INVISIBLE
                guideLoading.visibility = View.INVISIBLE
                guideFragmentViewModel.getAllBlogData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun loadingData() {
        guideFragmentViewModel.guideLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                guideBinding.apply {
                    if (it) {
                        guideLoading.visibility = View.VISIBLE
                        mightNeedRecyclerview.visibility = View.INVISIBLE
                        topArticlesRecyclerview.visibility = View.INVISIBLE
                    } else {
                        mightNeedRecyclerview.visibility = View.VISIBLE
                        topArticlesRecyclerview.visibility = View.VISIBLE
                        guideLoading.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun fmssIkUsers(){
        guideFragmentViewModel.fmssUsers.observe(viewLifecycleOwner){ users ->
            guideBinding.guideTitle.text = users[1].email.toString()
            println("osman" + users.get(1).email.toString())
        }
    }

    private fun bookmarkAddLocalDB() {
        blogDataTopArticlesAdapter.setBookmarkOnClickListener(object : BookmarkItemOnClickListener {
            override fun onClick(travelModelItem: TravelModelItem) {
                guideFragmentViewModel.addBookmarkLocalDB(travelModelItem)
                bookmarkAdapter.setBookmarkData(travelModel)
            }
        })
    }

    private fun searchBottomSheet() {

        guideBinding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                guideBinding.searchBar.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId != EditorInfo.IME_ACTION_DONE) {
                        val searchText = s.toString()
                        val action =
                            GuideFragmentDirections.actionGuideFragmentToSearchListFragment(
                                searchText
                            )
                        findNavController().navigate(action)
                        true
                    } else {
                        println("false")
                        false
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun bottomNavigationVisible() {
        //Bottom Navigation VISIBLE
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navbarBlur =
            requireActivity().findViewById<ImageView>(R.id.background_blur_bottom_navigation)
        navBar.visibility = View.VISIBLE
        navbarBlur.visibility = View.VISIBLE
    }
}