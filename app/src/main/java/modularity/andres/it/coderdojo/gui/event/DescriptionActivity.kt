package modularity.andres.it.coderdojo.gui.event

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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
        closeButton.setOnClickListener(CloseDescriptionButtonListener(this))

    }

    private fun setupText(dataString: String?) {
        val description = findViewById<TextView>(R.id.full_description)
        description.text = dataString
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.stay, R.anim.slide_out_up)
    }

    private class CloseDescriptionButtonListener(var context: AppCompatActivity) : View.OnClickListener {

        override fun onClick(view: View?) {
            context.finish()
        }

    }
}
