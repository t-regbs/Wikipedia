package com.example.wikipedia.fragments


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.wikipedia.R
import com.example.wikipedia.WikiApplication
import com.example.wikipedia.activities.SearchActivity
import com.example.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.example.wikipedia.managers.WikiManager
import com.example.wikipedia.models.WikiResult
import com.example.wikipedia.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.fragment_explore.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class ExploreFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    var searchCardView: CardView? = null
    var refresher: SwipeRefreshLayout? = null
    var exploreRecycler: RecyclerView? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById(R.id.search_cardview)
        refresher = view.findViewById(R.id.refresher)
        exploreRecycler = view.findViewById(R.id.explore_article_recycler)

        searchCardView!!.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java)
            context!!.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomArticles()
        }

        getRandomArticles()

        return view
    }

    private fun getRandomArticles() {
        refresher?.isRefreshing = true

        try {
            wikiManager?.getRandom(15) { wikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)
                activity?.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false
                }
            }
        } catch (ex: Exception) {
            //show dialog
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(ex.message).setTitle("oops!!")
            val dialog = builder.create()
            dialog.show()
        }

    }

}
