package modularity.andres.it.coderdojo.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.TextView
import modularity.andres.it.coderdojo.R


class DescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        setupText(intent.getStringExtra("DESC"))
        setupCloseButton()
    }

    private fun setupCloseButton() {
        val closeButton = findViewById<ImageButton>(R.id.close_desc_button)
        closeButton.setOnClickListener { this.finish() }

    }

    private fun setupText(dataString: String?) {
        val description = findViewById<WebView>(R.id.full_description)
        var text = "<html><body><p align=\"justify\">"
        text += dataString
        text += "</p></body></html>"
        description.loadData(text, "text/html", "utf-8")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.stay, R.anim.slide_out_up)
    }
    
}
