package com.example.video_player

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.video_player.databinding.ActivityMainBinding
import com.example.video_player.fragments.FoldersFragment
import com.example.video_player.fragments.VideoFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.ak_pink_nav)
        setContentView(binding.root)

        //for Navigation drawer
        toggle =
            ActionBarDrawerToggle(this@MainActivity, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        setFragment(VideoFragment())
        requestRunTimeFilePermission()


        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.videoView -> setFragment(VideoFragment())
                R.id.foldersView -> setFragment(FoldersFragment())
            }
            return@setOnItemSelectedListener true
        }

        binding.navViewDrawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.feedBack -> Toast.makeText(
                    this@MainActivity,
                    "FeedBack Pressed",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.themes -> Toast.makeText(
                    this@MainActivity,
                    "Theme Pressed",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.sortOrder -> Toast.makeText(
                    this@MainActivity,
                    "SortOrder Pressed",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.about -> Toast.makeText(this@MainActivity, "About Pressed", Toast.LENGTH_SHORT)
                    .show()
                R.id.exit -> exitProcess(1)
            }

            return@setNavigationItemSelectedListener true
        }

    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrame, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    //for requesting permission
    private fun requestRunTimeFilePermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(WRITE_EXTERNAL_STORAGE),
                13
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 13) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Permission granted", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this@MainActivity, "Permission denied", Toast.LENGTH_SHORT).show()
            requestRunTimeFilePermission()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
