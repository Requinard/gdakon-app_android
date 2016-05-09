package org.eurofurence.connavigator.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.eurofurence.connavigator.R
import org.eurofurence.connavigator.database.Database
import org.eurofurence.connavigator.database.UpdateIntentService
import org.eurofurence.connavigator.util.delegators.view
import org.eurofurence.connavigator.util.extensions.*
import java.util.*

/**
 * Created by David on 28-4-2016.
 */
class ActivityStart : AppCompatActivity() {
    val buttonStart by view(Button::class.java)
    val textHelp by view(TextView::class.java)

    val updateReceiver = localReceiver(UpdateIntentService.UPDATE_COMPLETE) {
        textHelp.text = "There, all done!"
        buttonStart.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)

        updateReceiver.register()

        val db = Database(this)

        if ( db.eventConferenceDayDb.items.isNotEmpty())
            startRootActivity()

        buttonStart.setOnClickListener {
            startRootActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        updateReceiver.register()
    }

    override fun onPause() {
        super.onPause()
        updateReceiver.unregister()
    }

    private fun startRootActivity() {
        val intent = Intent(this, ActivityRoot::class.java)
        startActivity(intent)
        finish()
    }
}