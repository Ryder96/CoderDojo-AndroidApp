package modularity.andres.it.coderdojo


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import modularity.andres.it.coderdojo.gui.adpaters.EventsListAdapter
import modularity.andres.it.coderdojo.gui.list.EventListActivity


class MainActivity : AppCompatActivity() {

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()

        val intent = Intent(this, EventListActivity::class.java)
        startActivity(intent)

    }


    private fun setup() {
        searchViewSetup()
        recyclerViewSetup()
    }

    private fun recyclerViewSetup() {
        var recyclerView = findViewById<RecyclerView>(R.id.event_list)
        recyclerView.setHasFixedSize(true)
        var mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = EventsListAdapter(this, fooList())

    }

    private fun fooList(): List<String> {
        var fooList = ArrayList<String>()
        (0..99).mapTo(fooList) { "Foo" + it }
        Log.d("LIST SIZE", "SIZE: " + fooList.size)
        return fooList
    }

    private fun searchViewSetup() {
        searchView = findViewById(R.id.search_cities)
        searchView?.queryHint = resources.getString(R.string.search)
        searchView?.setOnClickListener { searchView?.setIconifiedByDefault(false) }
    }


}
