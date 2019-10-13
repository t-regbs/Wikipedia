package com.example.wikipedia.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.wikipedia.R
import com.example.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.example.wikipedia.adapters.ArticleListItemRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_favourites.*

/**
 * A simple [Fragment] subclass.
 */
class FavouritesFragment : Fragment() {

    var favouritesRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_favourites, container, false)

        favouritesRecycler = view.findViewById(R.id.favourites_article_recycler)

        favouritesRecycler!!.layoutManager = LinearLayoutManager(context)
        favouritesRecycler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }


}
