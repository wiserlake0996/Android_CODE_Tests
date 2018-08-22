package com.example.unclep.testpages

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_tabbed.*
import kotlinx.android.synthetic.main.fragment_tabbed.view.*

class TabbedActivity : AppCompatActivity(), FragmentA.OnFragmentAInteractionListener, FragmentB.OnFragmentInteractionListener, FragmentC.OnFragmentInteractionListener, FragmentD.OnFragmentInteractionListener, FragmentE.OnFragmentInteractionListener {

    lateinit var newUser:User
    var businessProfile: BusinessProfile? = null
    var ownerProfile: OwnerProfile? = null
    var address: Address? = null
    var contactInformation: ContactInformation? = null
    var credentials:Credentials? = null

    override fun onFragmentEInteraction(uri: Int, address: Address) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDInteraction(uri: Int, ownerProfile: OwnerProfile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentCInteraction(uri: Int, contactInformation: ContactInformation) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentBInteraction(uri: Int, businessProfile: BusinessProfile) {

    }

    override fun onFragmentAInteraction(uri: Int, cred: Credentials) {
        container.setCurrentItem(uri)

        println(cred?.email)
    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_tabbed)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)


        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_tabbed, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            println("POSITION")
            println(position)

            println("CONTACT DATA FROM ACTIVITY")
            println(credentials?.email)
            if(position == 0){
                return FragmentA.newInstance(credentials,(position + 1).toString())

            }else if(position == 1){
                return FragmentB.newInstance((position + 1).toString(),(position + 1).toString())

            }else if(position == 2){
                return FragmentC.newInstance((position + 1).toString(),(position + 1).toString())

            }else if (position == 3){
                return FragmentE.newInstance((position + 1).toString(),(position + 1).toString())

            }else if (position == 4){
                return FragmentD.newInstance((position + 1).toString(),(position + 1).toString())

            }
            return FragmentD.newInstance((position + 1).toString(),(position + 1).toString())


        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 5
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_tabbed, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
