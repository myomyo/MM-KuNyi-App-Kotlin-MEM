package com.mem.mmkunyi.kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mem.mmkunyi.kotlin.R
import com.mem.mmkunyi.kotlin.adapters.JobAdapter
import com.mem.mmkunyi.kotlin.components.SmartScrollListener
import com.mem.mmkunyi.kotlin.data.models.JobModel
import com.mem.mmkunyi.kotlin.data.vos.JobVO
import com.mem.mmkunyi.kotlin.delegates.BeforeLoginDelegate
import com.mem.mmkunyi.kotlin.delegates.JobItemDelegate
import com.mem.mmkunyi.kotlin.events.DataEvent
import com.mem.mmkunyi.kotlin.events.ErrorEvent
import com.mem.mmkunyi.kotlin.utils.AppConstants
import com.mem.mmkunyi.kotlin.views.pods.BeforeLoginViewPod

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity(), JobItemDelegate, BeforeLoginDelegate {


    private var mJobAdapter: JobAdapter? = null
    private var mSmartScrollListener: SmartScrollListener? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)

        //Add menu icon in tool bar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)

        rvJobs.setEmptyView(vpEmptyJobs)
        rvJobs.layoutManager = LinearLayoutManager(applicationContext)
        mSmartScrollListener = SmartScrollListener(object : SmartScrollListener.OnSmartScrollListener{
            override fun onListEndReach() {
                swipeRefreshLayout.isRefreshing = true
                JobModel.getInstance().loadJobs()
            }
        })
        rvJobs.addOnScrollListener(mSmartScrollListener)

        mJobAdapter = JobAdapter(applicationContext,this)
        rvJobs.adapter = mJobAdapter
        swipeRefreshLayout.isRefreshing = true

        JobModel.getInstance().loadJobs()

        swipeRefreshLayout.setOnRefreshListener {
            JobModel.getInstance().forceLoadJobs()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_applied_jobs -> {
                    Snackbar.make(navigationView,"Tapped Applied Jobs", Snackbar.LENGTH_LONG).show()
                }
                R.id.menu_favorite_jobs -> {
                    Snackbar.make(navigationView,"Tapped Favorite Jobs", Snackbar.LENGTH_LONG).show()
                }
                R.id.menu_just_for_you ->{
                    Snackbar.make(navigationView,"Tapped Profile", Snackbar.LENGTH_LONG).show()
                }
            }
            for( menuItemIndex in 0 until navigationView.menu.size()){ // until => same with size()-1
                navigationView.menu.getItem(menuItemIndex).isChecked = false
            }
            it.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.END) // close navigation view
            return@setNavigationItemSelectedListener true
        }

        val vpBeforeLogin = navigationView.getHeaderView(0) as BeforeLoginViewPod
        vpBeforeLogin.setDelegate(this)
    }

    override fun onTabJobItem(job: JobVO?) {
        val intent = Intent(applicationContext, JobDetailsActivity::class.java)
        intent.putExtra("jobId", job!!.jobPostId)
        startActivity(intent)
    }

    override fun onTapLogin() {
        val intent = Intent(applicationContext, AccountControlActivity::class.java)
        intent.putExtra("action_type", AccountControlActivity.ACTION_TYPE_LOGIN)
        startActivity(intent)
    }

    override fun onTapRegister() {
        val intent = Intent(applicationContext, AccountControlActivity::class.java)
        intent.putExtra(AccountControlActivity.ACTION_TYPE, AccountControlActivity.ACTION_TYPE_REGISTER)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(item.itemId == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onJobsLoadedEvent(jobsLoadedEvent: DataEvent.JobsLoadedEvent){

        swipeRefreshLayout.isRefreshing = false
        mJobAdapter!!.setData(jobsLoadedEvent.loadedJobs as MutableList<JobVO>)
        vpEmptyJobs.visibility = View.GONE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorJobsLoadedEvent(apiErrorEvent: ErrorEvent.ApiErrorEvent) {
        swipeRefreshLayout.isRefreshing = false
        Snackbar.make(rvJobs, "ERROR : " + apiErrorEvent.getMsg(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        //vpEmptyJobs.visibility = View.VISIBLE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEmptyNewsLoadedEvent(emptyDataLoadedEvent: DataEvent.EmptyDataLoadedEvent) {
        swipeRefreshLayout.isRefreshing = false
        Snackbar.make(rvJobs, "ERROR : " + emptyDataLoadedEvent.errorMsg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        vpEmptyJobs.visibility = View.VISIBLE
    }
}
