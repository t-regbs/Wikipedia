package com.example.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.wikipedia.R
import com.example.wikipedia.WikiApplication
import com.example.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.example.wikipedia.adapters.ArticleListItemRecyclerAdapter
import com.example.wikipedia.managers.WikiManager
import com.example.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.fragment_favourites.*
import org.jetbrains.anko.doAsync

/**
 * A simple [Fragment] subclass.
 */
class FavouritesFragment : Fragment() {
    private var wikiManager: WikiManager? = null
    var favouritesRecycler: RecyclerView? = null
    private val adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_favourites, container, false)

        favouritesRecycler = view.findViewById(R.id.favourites_article_recycler)

        favouritesRecycler!!.layoutManager = LinearLayoutManager(context)
        favouritesRecycler!!.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()

        doAsync {
            val favouriteArticles = wikiManager!!.getFavourites()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favouriteArticles as ArrayList<WikiPage>)
            activity?.runOnUiThread{ adapter.notifyDataSetChanged() }
        }
    }

}
