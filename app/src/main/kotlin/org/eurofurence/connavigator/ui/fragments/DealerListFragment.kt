@file:Suppress("MemberVisibilityCanBePrivate")

package org.eurofurence.connavigator.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.perf.metrics.AddTrace
import com.pawegio.kandroid.runDelayed
import com.pawegio.kandroid.textWatcher
import io.swagger.client.model.DealerRecord
import org.eurofurence.connavigator.R
import org.eurofurence.connavigator.database.HasDb
import org.eurofurence.connavigator.database.lazyLocateDb
import org.eurofurence.connavigator.ui.adapter.DealerRecyclerAdapter
import org.eurofurence.connavigator.util.extensions.recycler
import org.eurofurence.connavigator.util.extensions.setFAIcon
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.act

/**
 * Created by David on 15-5-2016.
 */
class DealerListFragment : Fragment(), HasDb, AnkoLogger {
    override val db by lazyLocateDb()

    val ui by lazy { DealersUi() }
    val layoutManager get() = ui.dealerList?.layoutManager
    private var effectiveDealers = emptyList<DealerRecord>()

    var searchText = ""
    var searchCategory = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            UI { ui.createView(this) }.view

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        effectiveDealers = sortDealers(dealers.items)

        info { "Rendering ${effectiveDealers.size} dealers out of ${db.dealers.items.size}" }

        ui.dealerList?.adapter = DealerRecyclerAdapter(effectiveDealers, db, this)
        ui.dealerList?.layoutManager = LinearLayoutManager(activity)
        ui.dealerList?.itemAnimator = DefaultItemAnimator()

        val distinctCategories = dealers.items
                .map { it.categories ?: emptyList() }
                .fold(emptyList<String>()) { a, b -> a.plus(b).distinct() }
                .sorted()

        ui.categorySpinner.adapter =
                ArrayAdapter<String>(this.act, android.R.layout.simple_spinner_dropdown_item,
                        listOf("All Categories").plus(distinctCategories))

        ui.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                searchCategory = if (position == 0) {
                    ""
                } else {
                    parent.getItemAtPosition(position) as String
                }
                updateFilter()
            }
        }

        ui.search.textWatcher {
            afterTextChanged { text -> searchText = text.toString(); updateFilter() }
        }

    }

    override fun onResume() {
        super.onResume()

        activity?.apply {
            this.findViewById<Toolbar>(R.id.toolbar).apply {
                this.menu.clear()
                this.inflateMenu(R.menu.dealer_list_menu)
                this.context?.let {
                    this.menu.setFAIcon(it, R.id.action_search, R.string.fa_search_solid, white = true)
                }
                this.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_search -> onSearchButtonClick()
                    }
                    true
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        layoutManager?.also { lm ->
            outState.putParcelable("lm_key", lm.onSaveInstanceState())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layoutManager?.also { lm ->
            runDelayed(200) {
                savedInstanceState
                        ?.getParcelable<Parcelable>("lm_key")
                        ?.let(lm::onRestoreInstanceState)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        activity?.apply {
            this.findViewById<Toolbar>(R.id.toolbar).menu.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.apply {
            this.findViewById<Toolbar>(R.id.toolbar).menu.clear()
        }
    }

    @AddTrace(name = "DealerListFragment:search", enabled = true)
    fun updateFilter() {
        info { "Filtering dealers for text=$searchText, category=$searchCategory" }

        effectiveDealers = db.dealers.items.toList()

        if (!searchText.isEmpty())
            effectiveDealers = effectiveDealers.filter { it.displayName.contains(searchText, true) or it.attendeeNickname.contains(searchText, true) }

        if (!searchCategory.isEmpty())
            effectiveDealers = effectiveDealers.filter {
                it.categories?.contains(searchCategory) ?: false
            }

        ui.dealerList?.adapter = DealerRecyclerAdapter(sortDealers(effectiveDealers), db, this).also {
            it.notifyDataSetChanged()
        }
    }

    private fun sortDealers(dealers: Iterable<DealerRecord>): List<DealerRecord> =
            dealers.sortedBy { (if (it.displayName != "") it.displayName else it.attendeeNickname).toLowerCase() }

    fun onSearchButtonClick() {
        if (ui.searchLayout.visibility == View.GONE) {
            info { "Showing search bar" }
            ui.searchLayout.visibility = View.VISIBLE
            ui.search.requestFocus()
        } else {
            info { "Hiding search bar" }
            ui.searchLayout.visibility = View.GONE
            searchText = ""
            updateFilter()
        }
    }
}

class DealersUi : AnkoComponent<Fragment> {
    var dealerList: RecyclerView? = null
    lateinit var search: EditText
    lateinit var searchLayout: LinearLayout
    lateinit var categorySpinner: Spinner

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        verticalLayout {
            lparams(matchParent, matchParent)
            backgroundResource = R.color.backgroundGrey

            // Needed to properly inflate the android layouts. In case of big screens, increase facerolling
            textView("fabduawbdabdw dgavgwd hawdjw adg awjhd jwha dawdkawdkawda HAHA TURNS OUT THSI TEXT WAS NOT LONG ENOUGH FOR OTHER DEVICES CAN YOU IMAGINE THAT?").lparams(matchParent, dip(1))

            verticalLayout {
                // Search widgets
                padding = dip(10)
                linearLayout {
                    // Filter types
                    weightSum = 100F
                    visibility = View.GONE

                    textView {
                        textResource = R.string.misc_show
                        leftPadding = dip(5)
                    }.lparams(dip(0), wrapContent, 20F)

                    categorySpinner = spinner {
                        prompt = resources.getString(R.string.misc_filter)
                    }.lparams(dip(0), wrapContent, 80F)
                }

                searchLayout = linearLayout {
                    weightSum = 100F
                    visibility = View.GONE
                    textView {
                        textResource = R.string.misc_find
                        leftPadding = dip(5)
                    }.lparams(dip(0), wrapContent, 20F)

                    search = editText { singleLine = true }.lparams(dip(0), wrapContent, 80F)
                }
            }

            dealerList = recycler {
                lparams(matchParent, matchParent)
                verticalPadding = dip(10)
                clipToPadding = false
                backgroundResource = R.color.lightBackground
            }
        }
    }
}