
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
 * [RegistrationFragmentD.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegistrationFragmentD.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RegistrationFragmentD : Fragment() {
    // TODO: Rename and change types of parameters
    private var address:Address? = null
    private var listener: OnFragmentInteractionListener? = null

    private var tAddressLine1:TextInputEditText? = null
    private var tAddressLine2:TextInputEditText? = null
    private var tAddressCity:TextInputEditText? = null
    private var tAddressState:TextInputEditText? = null
    private var tAddressCountry:TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            address = it.getSerializable(ARG_PARAM1) as Address?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.reg_fragment_d, container, false)
        val btn: Button = view.findViewById(R.id.next)

        tAddressLine1 = view.findViewById(R.id.addressLine1)
        tAddressLine2 = view.findViewById(R.id.addressLine2)
        tAddressCity = view.findViewById(R.id.addressCity)
        tAddressState = view.findViewById(R.id.addressState)
        tAddressCountry = view.findViewById(R.id.addressCountry)

        if(address != null){
            tAddressCity?.setText(address?.city)
            tAddressCountry?.setText(address?.country)
            tAddressState?.setText(address?.state)
            tAddressLine1?.setText(address?.addressLine1)
            tAddressLine2?.setText(address?.addressLine2)

        }else{
            address = Address("","","","","")
        }

        btn.setOnClickListener{
            collectDataFromInputs()
            onButtonPressed(4, address!!)

        }
        return view
    }

    fun collectDataFromInputs(){
        address?.let {
            it.addressLine2 = tAddressLine2?.text.toString()
            it.addressLine1 = tAddressLine1?.text.toString()
            it.city = tAddressCity?.text.toString()
            it.state = tAddressState?.text.toString()
            it.country = tAddressCountry?.text.toString()

            println("Param Object updated")
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Int, address: Address) {
        listener?.onRegistrationFragmentDInteraction(uri, address)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onRegistrationFragmentDInteraction(uri: Int, address: Address)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationFragmentD.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(address: Address?) =
                RegistrationFragmentD().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, address)
                    }
                }
    }
}
