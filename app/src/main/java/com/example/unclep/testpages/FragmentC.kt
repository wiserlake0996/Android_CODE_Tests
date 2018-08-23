package com.example.unclep.testpages

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentC.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentC.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentC : Fragment() {
    // TODO: Rename and change types of parameters
    private var contactInformation: ContactInformation? = null
    private var listener: OnFragmentInteractionListener? = null

    private var tContactEmail:TextInputEditText? = null
    private var tWebsite:TextInputEditText? = null
    private var tFacebook:TextInputEditText? = null
    private var tInstagram:TextInputEditText? = null
    private var tTwitter:TextInputEditText? = null

    private var tWhatsappNumber:TextInputEditText? = null

    private var cContactEmail:CheckBox? = null
    private var cWhatsappNumber:CheckBox?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactInformation = it.getSerializable(ARG_PARAM1) as ContactInformation?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_c, container, false)
        val btn: Button = view.findViewById(R.id.next)

        tContactEmail = view.findViewById(R.id.businessEmail)
        tWebsite = view.findViewById(R.id.businessWebsite)
        tFacebook = view.findViewById(R.id.businessFacebook)
        tInstagram = view.findViewById(R.id.businessInstagram)
        tTwitter = view.findViewById(R.id.businessTwitter)

        tWhatsappNumber = view.findViewById(R.id.businessWhatsapp)

        cContactEmail = view.findViewById(R.id.contactEmailCheckbox)
        cWhatsappNumber = view.findViewById(R.id.whatsappNumberCheckbox)

        if(contactInformation != null){
            tContactEmail?.setText(contactInformation?.email)
            tWebsite?.setText(contactInformation?.website)
            tFacebook?.setText(contactInformation?.social_accounts?.facebook)
            tTwitter?.setText(contactInformation?.social_accounts?.twitter)
            tInstagram?.setText(contactInformation?.social_accounts?.instagram)
            tWhatsappNumber?.setText(contactInformation?.social_accounts?.whatsapp)
        }else{
            contactInformation = ContactInformation("",null,"",null,null,null,null)
        }

        btn.setOnClickListener{
            collectDataFromInputs()

            onButtonPressed(3, contactInformation!!)

        }
        return view
    }

    fun collectDataFromInputs(){
        contactInformation?.let {
            it.email = tContactEmail?.text.toString()
            it.website = tWebsite?.text.toString()
            it.address = null
            it.phone_number = null

            it.social_accounts = SocialAccounts(tInstagram?.text.toString(), tTwitter?.text.toString()
            , tFacebook?.text.toString(), tWhatsappNumber?.text.toString(), null
            )




        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Int, contactInformation: ContactInformation) {
        listener?.onFragmentCInteraction(3, contactInformation)
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
        fun onFragmentCInteraction(uri: Int, contactInformation: ContactInformation)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ContactInformation?) =
                FragmentC().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                    }
                }
    }
}
