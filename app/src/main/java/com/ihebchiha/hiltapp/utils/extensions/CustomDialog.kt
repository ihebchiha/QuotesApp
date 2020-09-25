package com.ihebchiha.hiltapp.utils.extensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ihebchiha.hiltapp.R
import kotlinx.android.synthetic.main.custom_dialog_with_field.*

class CustomDialog(private val titleToPut: String, val action: (email: String) -> Unit) :
    DialogFragment(), View.OnClickListener {

    private lateinit var emailET:EditText
    private lateinit var send: Button
    private lateinit var titleTv: TextView
    private lateinit var errorMessageTv: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog_with_field, container);

    }

    companion object {
        @JvmStatic
        fun newInstance(titleToPut: String, action: (email: String) -> Unit) =
            CustomDialog(titleToPut = titleToPut, action = action).apply {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTv = view.findViewById(R.id.titleTV)
        errorMessageTv = view.findViewById(R.id.errorMessageLabel)
        titleTv.text = titleToPut

        send = view.findViewById(R.id.sendButton)
        send.setOnClickListener(this)

        emailET = view.findViewById(R.id.emailET)

    }

    override fun onClick(p0: View?) {
        if (p0!!.id == R.id.sendButton) {
            action(emailET.text.toString())
        }
    }


//    fun showDialogWithField(
//        activity: AppCompatActivity,
//        titleToPut: String,
//        action: (email: String) -> Unit
//    ) {
//        val dialog = Dialog(activity)
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.custom_dialog_with_field)
//        val title = dialog.findViewById(R.id.titleTV) as TextView
//        val errorMessage = dialog.findViewById(R.id.errorMessageLabel) as TextView
//        title.text = titleToPut
//        val sendButton = dialog.findViewById(R.id.sendButton) as Button
//        val emailET = dialog.findViewById<EditText>(R.id.emailET)
//        sendButton.setOnClickListener {
//            action(emailET.text.toString())
//        }
//        dialog.show()
//    }


}