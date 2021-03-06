package com.example.unclep.testpages

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RegistrationFragmentA.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegistrationFragmentA.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RegistrationFragmentA : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Credentials? = null
    private var param2: String? = null
    private var listener: OnRegistrationFragmentAInteractionListener? = null

    var email:TextInputEditText? = null
    var password:TextInputEditText? = null
    var phoneNumber:TextInputEditText? = null
    var username:TextInputEditText?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Credentials?
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.reg_fragment_a, container, false)
        val btn: Button = view.findViewById(R.id.next)

        //Initialize view components
        email = view.findViewById(R.id.credentialsEmail)
        password = view.findViewById(R.id.credentialsPassword)
        phoneNumber = view.findViewById(R.id.credentialsPhoneNumber)
        username = view.findViewById(R.id.businessUsername)

        /**
         * Check if credential value is null, if so initialize else set the components with the credentials value
         */
        if (param1 != null) {
            email?.setText(param1?.email)
            phoneNumber?.setText(param1?.phone_number)
            password?.setText(param1?.password)
            username?.setText(param1?.username)
        }else{
            param1 = Credentials("", "","", "")
        }

        /**
         * When the next button is clicked, send data back to the activity and open the next fragment
         */
        btn.setOnClickListener{
            collectDataFromInputs()
            onButtonPressed(1, param1!!)
        }

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Int, credentials: Credentials) {
        listener?.onRegistrationFragmentAInteraction(1, credentials)
    }

    fun collectDataFromInputs(){
        param1?.let {
            it.phone_number = phoneNumber?.text.toString()
            it.password = password?.text.toString()
            it.email = email?.text.toString()
            it.username = username?.text.toString()

            println("Param Object updated")
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegistrationFragmentAInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnRegistrationFragmentAInteractionListener {
        // TODO: Update argument type and name
        fun onRegistrationFragmentAInteraction(uri: Int, credentials: Credentials)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationFragmentA.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Credentials?, param2: String) =
                RegistrationFragmentA().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
