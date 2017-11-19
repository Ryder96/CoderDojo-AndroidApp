package modularity.andres.it.coderdojo


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import modularity.andres.it.coderdojo.ui.list.EventListActivity


class MainActivity : AppCompatActivity() {

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, EventListActivity::class.java)
        startActivity(intent)

    }


}
