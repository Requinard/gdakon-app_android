package org.eurofurence.connavigator.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import io.swagger.client.model.EventEntry
import io.swagger.client.model.Image
import org.eurofurence.connavigator.R
import org.eurofurence.connavigator.driver.DriverCallback
import org.eurofurence.connavigator.net.imageService
import org.eurofurence.connavigator.util.logd
import org.eurofurence.connavigator.util.viewInHolder
import org.joda.time.DateTime
import org.joda.time.Days

class LaunchScreenActivity : BaseActivity() {
    /**
     * The database access, relative to the launch screen activity to support
     * feedback events.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logd { "Launch screen creating" }

        // Assign the main layout
        setContentView(R.layout.activity_launch_screen_base)

        // Inject menu navigation
        injectNavigation(savedInstanceState)

        // Turn this off if there are views of different sizes in the recycler
        eventRecycler.setHasFixedSize(true)

        // Default setup for recycler layout and animation
        eventRecycler.layoutManager = LinearLayoutManager(this)
        eventRecycler.itemAnimator = DefaultItemAnimator()

        // Event view holder finds and memorizes the views in an event card
        class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            val eventImage by viewInHolder(ImageView::class.java)
            val eventTitle by viewInHolder(TextView::class.java)
            val eventDate by viewInHolder(TextView::class.java)
            val eventHosts by viewInHolder(TextView::class.java)
            val eventDescription by viewInHolder(TextView::class.java)
            lateinit var eventEntry: EventEntry

            init {
                eventTitle.setOnClickListener { onClick(it) }
            }

            override fun onClick(v: View?) {
                onEventViewPress(eventEntry)
            }
        }


        // Assign a new adapter mapping to the previously defined view event holders
        eventRecycler.adapter = object : RecyclerView.Adapter<EventViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, p1: Int): EventViewHolder {
                // To create the view holder, inflate the main event. This can be replaced, but differently
                // sized fragments will require the fixed size property of the recycler to be lifted
                return EventViewHolder(LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.fragment_main_event, parent, false))
            }

            override fun getItemCount(): Int {
                // Fixed size, map to the events
                return driver.eventEntryDb.elements.size
            }

            override fun onBindViewHolder(holder: EventViewHolder, pos: Int) {
                // Get the event for the position
                val event = driver.eventEntryDb.elements[pos]

                // Assign the properties of the view
                holder.eventTitle.text = event.title
                holder.eventDate.text = event.startTime
                holder.eventHosts.text = event.panelHosts
                holder.eventDescription.text = event.description
                holder.eventEntry = event

                // Assign an image if present
                if (event.imageId != null) {
                    val img = driver.imageDb.elements.firstOrNull { it.id == event.imageId }
                    if (img != null)
                        imageService.load(img, holder.eventImage)

                    holder.eventImage.visibility = View.VISIBLE
                } else {
                    holder.eventImage.visibility = View.GONE
                }
            }
        }

        // Manually set the first date, since the database is not updated with EF 22
        val firstDay = DateTime(2016, 8, 17, 0, 0)

        // Calculate the days between, using the current time. Todo: timezones
        val days = Days.daysBetween(DateTime.now(), DateTime(firstDay)).days

        // On con vs. before con. This should be updated on day changes
        if (days <= 0)
            navDays.text = "Day ${1 - days}"
        else
            navDays.text = "Only $days days left"


        // Find and setup the floating button
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Reloading database", Snackbar.LENGTH_SHORT).show()

            // Update the database
            driver.update (object : DriverCallback {
                override fun gotImages(delta: List<Image>) {
                    // Notify the recycler that its content has changed
                    eventRecycler.adapter.notifyDataSetChanged()
                }

                override fun gotEvents(delta: List<EventEntry>) {
                    // Notify the recycler that its content has changed
                    eventRecycler.adapter.notifyDataSetChanged()
                }

                override fun done(success: Boolean) {
                    val cts = driver.dateDb.elements.firstOrNull()
                    Snackbar.make(findViewById(R.id.fab), "Database reload ${if (success) "successful" else "failed"}, version $cts", Snackbar.LENGTH_SHORT).show()
                }
            } + DriverCallback.OUTPUT)
        }

        logd { "Launch screen created" }
    }

    fun onEventViewPress(eventEntry: EventEntry) {
        val intent = Intent(this, EventActivity::class.java)
        intent.putExtra("uid", eventEntry.id.toString())

        startActivity(intent)
    }


    override fun onBackPressed() {
        // Sample method, maps a press on the back button to either 'close the drawer' or to the default behavior
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.launch_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
