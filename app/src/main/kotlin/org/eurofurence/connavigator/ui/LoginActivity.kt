package org.eurofurence.connavigator.ui

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.eurofurence.connavigator.R
import org.eurofurence.connavigator.broadcast.LoginReceiver
import org.eurofurence.connavigator.pref.AuthPreferences
import org.eurofurence.connavigator.tracking.Analytics
import org.eurofurence.connavigator.util.extensions.booleans
import org.eurofurence.connavigator.util.extensions.localReceiver
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.joda.time.DateTime

/**
 * Created by requinard on 6/26/17.
 */
class LoginActivity : AppCompatActivity(), AnkoLogger {
    val ui by lazy { LoginUi() }

    val loginReceiver = localReceiver(LoginReceiver.LOGIN_RESULT) {
        val success = it.booleans["success"]

        info { "Received broadast from LoginReceiver" }

        if (success) {
            info { "Login was success! Closing activity" }
            toast("Logged in as ${AuthPreferences.username}")
        } else {
            info { "Login failed! Showing error message" }
            ui.errorText.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Analytics.screen(this, "Login")
        info { "Starting login attempt" }
        ui.setContentView(this)

        ui.submit.setOnClickListener { attemptSubmit() }

        loginReceiver.register()
    }

    /**
     * Validates the fields from the UI
     */
    private fun attemptSubmit() {
        val emptyText = "This field is not supposed to be empty!"

        if (ui.regNumber.text.isEmpty()) {
            ui.regNumber.error = emptyText
            return
        }

        if (ui.username.text.isEmpty()) {
            ui.username.error = emptyText
            return
        }

        if (ui.password.text.isEmpty()) {
            ui.password.error = emptyText
            return
        }

        sendBroadcast(intentFor<LoginReceiver>(
                LoginReceiver.REGNUMBER to ui.regNumber.text.toString(),
                LoginReceiver.USERNAME to ui.username.text.toString(),
                LoginReceiver.PASSWORD to ui.password.text.toString()
        ))
    }
}

class LoginUi : AnkoComponent<LoginActivity> {
    lateinit var username: EditText
    lateinit var regNumber: EditText
    lateinit var password: EditText
    lateinit var errorText: TextView
    lateinit var submit: Button

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        linearLayout {
            lparams(matchParent, matchParent)
            // If not logged in
            verticalLayout {
                visibility = if (AuthPreferences.isLoggedIn()) View.GONE else View.VISIBLE

                toolbar {
                    title = "Login"
                    lparams(matchParent, wrapContent)
                    backgroundResource = R.color.primary
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setTitleTextColor(ctx.getColor(R.color.textWhite))
                    }
                }

                lparams(matchParent, matchParent)

                regNumber = editText {
                    hint = "Registration number"
                    inputType = InputType.TYPE_CLASS_NUMBER
                    lparams(matchParent, wrapContent)
                }

                username = editText {
                    hint = "Username"
                    lparams(matchParent, wrapContent)
                }

                password = editText {
                    hint = "Password"
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    lparams(matchParent, wrapContent)
                }

                errorText = textView("Your login was unsuccesfull, are you sure you entered the correct data?") {
                    visibility = View.GONE
                }

                submit = button {
                    text = "Submit"
                    lparams(matchParent, wrapContent)
                }
            }

            // If logged in
            verticalLayout {
                visibility = if (AuthPreferences.isLoggedIn()) View.VISIBLE else View.GONE

                textView("Username: ${AuthPreferences.username}")
                textView("Login is valid until: ${DateTime(AuthPreferences.tokenValidUntil).toLocalDateTime()}")
            }
        }
    }

}