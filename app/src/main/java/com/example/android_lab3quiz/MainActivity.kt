package com.example.android_lab3quiz
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

const val LOG_I_MAIN = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout);
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.home -> replaceFragment(R.id.fragment_container_view,HomeFragment())
                R.id.quiz -> replaceFragment(R.id.fragment_container_view,QuizStartFragment())
                R.id.profile -> replaceFragment(R.id.fragment_container_view,ProfileFragment())
                R.id.listOfQuestions -> replaceFragment(R.id.fragment_container_view,QuestionListFragment())
                R.id.newQuestion -> replaceFragment(R.id.fragment_container_view,QuestionNewFragment())
                else -> replaceFragment(R.id.fragment_container_view,HomeFragment())
            }
            drawerLayout.closeDrawer(Gravity.LEFT);
            true
        }
    }



}

inline fun FragmentManager.doTransaction(func: FragmentTransaction.() ->
FragmentTransaction) {
    beginTransaction().func().commit()
}

fun MainActivity.replaceFragment(frameId: Int, fragment: Fragment) {
    supportFragmentManager.doTransaction{replace(frameId, fragment).addToBackStack("HOME")}
}